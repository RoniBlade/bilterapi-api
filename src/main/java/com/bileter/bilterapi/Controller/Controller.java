package com.bileter.bilterapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Controller {

    @Autowired
    public void Main()
    {


        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "https://api.bileter.ru/ekp";
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
        // Fetch JSON response as String wrapped in ResponseEntity
        ResponseEntity<String> response
                = restTemplate.postForEntity(resourceUrl, requestJson, String.class);

        String productsJson = response.getBody();

        System.out.println(productsJson);

    }

}
