package com.example.integrationbileter.controllers;

import com.example.integrationbileter.configuration.ShowBatchConfiguration;
import com.example.integrationbileter.entity.ShowEntity;
import com.example.integrationbileter.scheduler.TimeManager;
import com.example.integrationbileter.service.ServiceAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
public class Controller {

    @Autowired
    ShowBatchConfiguration configuration;
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    private ServiceAPI integrator;

    @PostMapping("/getShowAll")
    public List<ShowEntity> getShowAll(TimeManager time) throws ParseException {
        return integrator.checkShows(time.getParsedBeginDate(), time.getParsedEndDate());
    }

    @PostMapping("/showSend")
    public void doSendShow() throws Exception {
        jobLauncher.run(
                configuration.contactExportJob(),
                new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters());
    }
}
