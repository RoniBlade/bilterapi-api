package com.example.integrationbileter.utils;

import com.example.integrationbileter.configuration.ConfigurationAPI;
import com.example.integrationbileter.model.IdShowParams;
import com.example.integrationbileter.model.Request;
import com.example.integrationbileter.model.Response;
import com.example.integrationbileter.model.TimeParams;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Slf4j
@Service
public class UtilApi {

    @Value("${com.example.bileter.id}")
    private String ID;
    @Value("${com.example.bileter.authKey}")
    private String authKey;
    @Value("${com.example.bileter.jsonrpc}")
    private String jsonrpc;
    @Value("${com.example.bileter.basePath}")
    private String basePath;

    private final SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    ConfigurationAPI configurationAPI;

    @Autowired
    private RestTemplate restTemplate;

    private Response getShowResponse(String method, Object params) {
        Request request = new Request().auth(authKey).id(ID).jsonrpc(jsonrpc).method(method).params(params);
        Response response = restTemplate.postForObject(basePath, request, Response.class);
        return response;
    }

    public Response getChangesShow(Date beginTime, Date endTime) {
        TimeParams timeParams = new TimeParams();
        return getShowResponse("changes-show", timeParams
                .beginTime(form.format(beginTime))
                .endTime(form.format(endTime)));
    }

    public Response getDataShow(List<Integer> IdShows) {
        IdShowParams idShowParams = new IdShowParams();
        return getShowResponse("data-show", idShowParams.idShow(IdShows));
    }
}
