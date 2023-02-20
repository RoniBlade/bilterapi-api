package com.example.integrationbileter.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "show_data")
public class ShowEntity {
    @Id
    private Integer id;

    private Integer ageRestriction;
    private String name;
    private String showType;
    private Integer idCity;
    private String city;

    private String imageShow;

    @OneToOne(mappedBy = "showEntity", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private DescriptionEntity description;

    private String showUrl;
    private String reviewUrl;
    private String buyUrl;

    private String latestUpdate;
}