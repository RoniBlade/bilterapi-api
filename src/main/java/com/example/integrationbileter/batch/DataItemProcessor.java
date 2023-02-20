package com.example.integrationbileter.batch;

import com.example.integrationbileter.entity.ShowEntity;
import com.example.integrationbileter.xjc.Show;
import org.springframework.batch.item.ItemProcessor;

public class DataItemProcessor implements ItemProcessor<ShowEntity, Show> {

    @Override
    public Show process(ShowEntity item) throws Exception {
        Show show = new Show();
        show.setCity(item.getCity());
        show.setName(item.getName());
        show.setAgeRestriction(item.getAgeRestriction());
        return show;
    }
}