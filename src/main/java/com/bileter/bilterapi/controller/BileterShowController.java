package com.bileter.bilterapi.controller;

import com.baeldung.openapi.api.BileterApi;
import com.baeldung.openapi.model.*;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BileterShowController implements BileterApi {
    @Override
    public ResponseEntity<ChangesBuildingResponse> postChangesBuilding(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ChangesBuildingRequest postChangesBuilding) {
        return BileterApi.super.postChangesBuilding(postChangesBuilding);
    }

    @Override
    public ResponseEntity<ChangeShowResponse> postChangesShow(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ChangesShowRequest postChangesShow) {
        return BileterApi.super.postChangesShow(postChangesShow);
    }

    @Override
    public ResponseEntity<DataBuildingResponse> postDataBuilding(@ApiParam(value = "" ,required=true )  @Valid @RequestBody DataBuildingRequest postDataBuilding) {
        return BileterApi.super.postDataBuilding(postDataBuilding);
    }

    @Override
    public ResponseEntity<DataShowResponse> postDataShow(@ApiParam(value = "" ,required=true )  @Valid @RequestBody DataShowRequest postDataShow) {
        return BileterApi.super.postDataShow(postDataShow);
    }
}
