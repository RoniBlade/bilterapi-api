package com.bileter.bilterapi.utils;

import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class RequestToBileterUtils {

    private static String BILETER_URL;

    private static RestTemplate restTemplate = new RestTemplate();

//    public RequestToBileterUtils(Environment environment) {
//        BILETER_URL = environment.getProperty("bileter.adress.url");
//    }

    //смотри что возвращать будешь
    public String requestToBileter(String requestJson){
        try {
            URI uri = new URI(BILETER_URL + "ekp");
            HttpHeaders requestHeaders = new HttpHeaders();
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            RequestEntity requestEntity = new RequestEntity(requestJson, requestHeaders, HttpMethod.POST, uri);
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
            return (responseEntity.getBody());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
