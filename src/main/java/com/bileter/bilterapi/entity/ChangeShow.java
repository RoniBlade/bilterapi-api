package com.bileter.bilterapi.entity;

import com.baeldung.openapi.model.DateBuldings;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString(includeFieldNames = true)
public class ChangeShow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long primary_id;

    private String auth;

    private String id;

    private String jsonrpc;

    private String method;

    private DateBuldings params;




    public ChangeShow(String auth, String id, String jsonrpc, String method, DateBuldings params) {
        this.auth = auth;
        this.id = id;
        this.jsonrpc = jsonrpc;
        this.method = method;
        this.params = params;
    }

    public ChangeShow() {
    }
}
