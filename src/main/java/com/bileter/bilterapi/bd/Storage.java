package com.bileter.bilterapi.bd;

import io.codejournal.maven.swagger2java.model.DataOfBuildingsAnsw;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    List<DataOfBuildingsAnsw> list = new ArrayList<>();

    public void add(DataOfBuildingsAnsw str){

        list.add(str);

    }

}
