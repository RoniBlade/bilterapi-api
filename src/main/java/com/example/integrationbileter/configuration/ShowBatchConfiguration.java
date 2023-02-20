package com.example.integrationbileter.configuration;

import com.example.integrationbileter.batch.DataItemProcessor;
import com.example.integrationbileter.batch.DataItemReader;
import com.example.integrationbileter.entity.ShowEntity;
import com.example.integrationbileter.repository.ShowRepo;
import com.example.integrationbileter.scheduler.TimeManager;
import com.example.integrationbileter.xjc.Show;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;


@Configuration
@EnableBatchProcessing
public class ShowBatchConfiguration {

    @Autowired
    ShowRepo showRepo;
    @Autowired
    ConfigurationFTPS configurationFTPS;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Value("${sftp.host.remote.directory}")
    private String sftpRemoteDirectory;

    @Value("${sftp.host.local.directory}")
    private String sftpLocalDirectory;

    private final TimeManager timeManager = new TimeManager();

    @Bean
    public Job contactExportJob() throws Exception {
        return jobBuilderFactory.get("showExtractJob")
                .start(exportContactListStep())
                .next(stepSendToFtp())
                .build();
    }

    @Bean
    public DataItemReader reader() {
        return new DataItemReader();
    }

    @Bean
    public DataItemProcessor processor() {
        return new DataItemProcessor();
    }

    @Bean
    ItemWriter<Show> writer() throws Exception {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Show.class);
        return new StaxEventItemWriterBuilder<Show>()
                .name("showItemWriter")
                .version("1.0")
                .rootTagName("showList")
                .resource(new FileSystemResource(sftpLocalDirectory + timeManager.getBodyOfName()))
                .marshaller(marshaller)
                .build();
    }

    @Bean
    public Step exportContactListStep() throws Exception {
        return stepBuilderFactory.get("exportShowListStep")
                .<ShowEntity, Show>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Step stepSendToFtp() {
        return stepBuilderFactory.get("stepSendToFtpAndDeleteLocal")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        String fullPathTheFile = sftpLocalDirectory + timeManager.getBodyOfName();
                        configurationFTPS.sendSftpFile(fullPathTheFile, sftpRemoteDirectory, null);

                        FileUtils.deleteQuietly(new File(sftpLocalDirectory + timeManager.getBodyOfName()));
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

}
