package com.example.integrationbileter.scheduler;

import com.example.integrationbileter.controllers.Controller;
import com.example.integrationbileter.model.TimeParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@EnableScheduling
@Service
@Slf4j
public class Scheduler {

    @Autowired
    Controller controller;

//    @Scheduled(cron = "${com.example.batch.cron}")
//    public void run() throws Exception {
//        controller.doSendShow();
//    }
}
