package com.bileter.bilterapi.reponce;

import com.bileter.bilterapi.bd.Storage;
import com.bileter.bilterapi.service.SchangesShowController;
import io.codejournal.maven.swagger2java.model.DataOfBuildings;
import io.codejournal.maven.swagger2java.model.DataOfBuildingsAnsw;
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

        SchangesShowController schangesShowController = new SchangesShowController();

        ResponseEntity<DataOfBuildingsAnsw> response
                = restTemplate.postForEntity(resourceUrl,
                schangesShowController.ChangesShow(),
                DataOfBuildingsAnsw.class);

         //String answ = response.getBody();

        DataOfBuildingsAnsw dataOfBuildingsAnsw = new DataOfBuildingsAnsw();

        dataOfBuildingsAnsw = response.getBody();

         Storage storage = new Storage();

         storage.add(dataOfBuildingsAnsw);



    }


}
