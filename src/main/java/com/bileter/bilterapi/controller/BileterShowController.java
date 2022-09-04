package com.bileter.bilterapi.controller;

import com.baeldung.openapi.api.BileterApi;
import com.baeldung.openapi.model.ChangesShowRequest;
import com.bileter.bilterapi.service.ChangeShowService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class BileterShowController implements BileterApi {

    private final ChangeShowService changerShowService;

    public BileterShowController(ChangeShowService changerShowService) {
        this.changerShowService = changerShowService;
    }


    @PostMapping("/changes-show")
    public Long GetChangesShowData(@RequestBody ChangesShowRequest changesShowRequest) {
       return changerShowService.get(changesShowRequest.getAuth(), changesShowRequest.getId(),
               changesShowRequest.getJsonrpc(), changesShowRequest.getMethod(), changesShowRequest.getParams().getBeginTime(),changesShowRequest.getParams().getEndTime() );

   }




}
