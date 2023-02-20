package com.example.integrationbileter.configuration;


import com.jcraft.jsch.ChannelSftp.LsEntry;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ConfigurationFTPS {

    @Value("${sftp.host.ip}")
    private String sftpHostIp;

    @Value("${sftp.host.port}")
    private int sftphostPort;

    @Value("${sftp.host.user}")
    private String sftpHostUser;

    @Value("${sftp.host.password}")
    private String sftpHostPassword;

    private final BeanFactory beanFactory;

    @Autowired
    public ConfigurationFTPS(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Bean
    public SessionFactory<LsEntry> sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
        factory.setHost(sftpHostIp);
        factory.setPort(sftphostPort);
        factory.setUser(sftpHostUser);
        factory.setPassword(sftpHostPassword);
        factory.setAllowUnknownKeys(true);
        return new CachingSessionFactory<LsEntry>(factory);
    }

    public void sendSftpFile(String filePath, String targetPath, FileExistsMode mode) {
        SftpRemoteFileTemplate fileTemplate = new SftpRemoteFileTemplate(sftpSessionFactory());
        fileTemplate.setRemoteDirectoryExpression(new LiteralExpression(targetPath));
        fileTemplate.setAutoCreateDirectory(true);
        fileTemplate.setCharset("UTF-8");
        fileTemplate.setBeanFactory(beanFactory);
        fileTemplate.afterPropertiesSet();
        Path file = Paths.get(filePath);
        if (file.toFile().exists()) {
            Message<File> message = MessageBuilder.withPayload(file.toFile()).build();
            if (null == mode) {
                fileTemplate.send(message);
            } else {
                if (fileTemplate.isUseTemporaryFileName()) {
                    fileTemplate.setUseTemporaryFileName(false);
                }
                fileTemplate.send(message, mode);
            }
        }
    }
}

