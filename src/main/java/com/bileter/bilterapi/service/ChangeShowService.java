package com.bileter.bilterapi.service;


import com.baeldung.openapi.model.DateBuldings;
import com.bileter.bilterapi.entity.ChangeShow;
import com.bileter.bilterapi.repository.BiliterShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ChangeShowService {

    private final BiliterShowRepository biliterShowRepository;

    private final ChangeShowService changesShowService;



    @Autowired
    public ChangeShowService(BiliterShowRepository biliterShowRepository, ChangeShowService changerShowService, ChangeShowService changesShowService, ChangeShowService changesShowService1) {
        this.biliterShowRepository = biliterShowRepository;
        this.changesShowService = changesShowService1;
    }

    public Long get(String auth, String id, String jsonrpc, String method, String endTime, String beginTime) {
        DateBuldings params = new DateBuldings();
        params.beginTime(beginTime);
        params.endTime(endTime);
        ChangeShow changeShow = new ChangeShow(auth, id, jsonrpc, method, params);

        return biliterShowRepository.save(changeShow).getPrimary_id();
    }



}
