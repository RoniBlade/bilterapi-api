package com.example.integrationbileter.service;


import com.example.integrationbileter.repository.ShowRepo;
import com.example.integrationbileter.configuration.ConfigurationAPI;
import com.example.integrationbileter.entity.DescriptionEntity;
import com.example.integrationbileter.entity.ShowEntity;
import com.example.integrationbileter.model.*;
import com.example.integrationbileter.utils.UtilApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Slf4j
@Service
public class ServiceAPI {

    @Autowired
    ShowRepo showRepo;

    @Autowired
    UtilApi utilApi;

    @Autowired
    ConfigurationAPI configurationAPI;

    @Autowired
    ObjectMapper objectMapper;

    @Transactional
    public ShowEntity getShow(Integer id) {

        Optional<ShowEntity> showEntityOptional = showRepo.findById(id);
        if(showEntityOptional.isPresent()){
            return showEntityOptional.get();
        }

        Response response = utilApi.getDataShow(new ArrayList<>() {{
            add(id);
        }});

        DataShowResponse showResponse = objectMapper
                .convertValue(response.getResult(), DataShowResponse[].class)[0];

        ShowEntity show = new ShowEntity();

        show.setId(id);
        show.setAgeRestriction(showResponse.getAgeRestriction());
        show.setName(showResponse.getName());
        show.setShowType(showResponse.getShowType().toString());
        show.setIdCity(showResponse.getIdCity());
        show.setCity(showResponse.getCity());
        show.setImageShow(showResponse.getImageShow());
        show.setShowUrl(showResponse.getShowUrl());
        show.setReviewUrl(showResponse.getReviewUrl());
        show.setBuyUrl(showResponse.getBuyUrl());
        show.setDescription(DescriptionEntity.builder()
                .showEntity(show)
                .textDescriptionShow(showResponse.getDescriptionShow().getTextDescriptionShow())
                .reviewShow(showResponse.getDescriptionShow().getReviewShow())
                .actorShow(showResponse.getDescriptionShow().getActorShow())
                .duration(showResponse.getDescriptionShow().getDuration())
                .producerShow(showResponse.getDescriptionShow().getProducerShow())
                .authorShow(showResponse.getDescriptionShow().getAuthorShow())
                .startDateTime(showResponse.getDescriptionShow().getStartDateTime())
                .endDateTime(showResponse.getDescriptionShow().getEndDateTime())
                .build());

        if (showResponse.getDescriptionShow().getEndDateTime() != null
                && showResponse.getDescriptionShow().getEndDateTime().length() != 0) {
            show.getDescription().setEndDateTime(showResponse.getDescriptionShow().getEndDateTime());
        }

        return showRepo.save(show);
    }

    @Transactional
    public List<ShowEntity> checkShows(Date beginTime, Date endTime) {
        Response response = utilApi.getChangesShow(beginTime, endTime);
        ChangesShowResponse[] changes = objectMapper.convertValue(response.getResult(), ChangesShowResponse[].class);
        List<ShowEntity> shows = new ArrayList<>();
        for (ChangesShowResponse change : changes) {
            ShowEntity show = getShow(change.getIdShow());
                show.setLatestUpdate(change.getChangesTime());

            showRepo.save(show);
            shows.add(show);
        }
        return shows;
    }
}


