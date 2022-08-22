package com.bileter.bilterapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SchangesShowController {


    public String ChangesShow()
    {

        String requestJson = "{\n" +
                "    \"auth\": \"uuSbAIbTdfe3RtAgwbgUliBlChZNhr5eblaFdv9G\",\n" +
                "    \"id\": \"123456\",\n" +
                "    \"jsonrpc\": \"2.0\",\n" +
                "    \"method\": \"changes-show\",\n" +
                "    \"params\": {\n" +
                "        \"BeginTime\":\"2020-01-24 00:00:00\",\n" +
                "        \"EndTime\":\"2020-01-24 23:59:59\"\n" +
                "    }\n" +
                "}";


        return requestJson;


    }

}

