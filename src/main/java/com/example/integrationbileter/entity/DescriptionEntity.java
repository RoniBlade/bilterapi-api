package com.example.integrationbileter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "showEntity")
@Table(name = "description_data")
public class DescriptionEntity {
    @Id
    @Column(name = "show_id")
    @JsonIgnore
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "show_id")
    @JsonIgnore
    private ShowEntity showEntity;
    @Lob
    private String textDescriptionShow;
    @Lob
    private String reviewShow;
    @Lob
    private String actorShow;
    private Integer duration;
    private String producerShow;
    private String authorShow;
    @ElementCollection()
    private List<String> startDateTime;
    private String endDateTime;
}
