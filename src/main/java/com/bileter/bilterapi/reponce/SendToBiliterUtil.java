package com.bileter.bilterapi.reponce;

import com.bileter.bilterapi.bd.Storage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SendToBiliterUtil {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void RestForTask1(String str) {

         RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "https://api.bileter.ru/ekp";

        ResponseEntity<String> response
                = restTemplate.postForEntity(resourceUrl, str, String.class);

         String answ = response.getBody();

         Storage storage = new Storage();

         storage.add(answ);



    }


}
