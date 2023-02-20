package com.example.integrationbileter.batch;

import com.example.integrationbileter.repository.ShowRepo;
import com.example.integrationbileter.entity.ShowEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DataItemReader implements ItemReader<ShowEntity> {

    @Autowired
    ShowRepo showRepo;

    private IteratorItemReader<ShowEntity> delegateReader;

    @BeforeStep
    private void beforeStep(StepExecution stepExecution) {
        List<ShowEntity> contacts = new ArrayList<>();
        Iterable<ShowEntity> customers = showRepo.findAll();
        for (ShowEntity show : customers) {
            contacts.add(createContact(show.getCity(), show.getName(), show.getAgeRestriction()));
        }

        this.delegateReader = new IteratorItemReader<>(contacts);
    }

    @Override
    public ShowEntity read() {
        return this.delegateReader.read();
    }

    private ShowEntity createContact(String city, String name, Integer ageRestriction) {
        ShowEntity contact = new ShowEntity();
        contact.setCity(city);
        contact.setName(name);
        contact.setAgeRestriction(ageRestriction);
        return contact;
    }
}
