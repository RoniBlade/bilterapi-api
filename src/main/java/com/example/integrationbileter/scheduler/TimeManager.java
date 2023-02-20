package com.example.integrationbileter.scheduler;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class TimeManager {

    private final SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd_HH:mm");


    private String bodyOfName = getPathFile();

    private String beginDate = "2022-03-02 00:00:00";
    private String endDate = "2022-03-02 23:59:59";

    public Date getParsedBeginDate() throws ParseException {
        return form.parse(beginDate);
    }

    public Date getParsedEndDate() throws ParseException {
        return form.parse(endDate);
    }

    public String getPathFile() {

        return "SHOW_" + LocalDateTime.now().format(format) + ".xml";
    }
}
