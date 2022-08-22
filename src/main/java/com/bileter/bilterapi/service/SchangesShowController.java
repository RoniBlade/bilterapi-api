package com.bileter.bilterapi.service;

import io.codejournal.maven.swagger2java.model.DataOfBuildings;
import io.codejournal.maven.swagger2java.model.Params;


public class SchangesShowController {


    public DataOfBuildings ChangesShow()
    {

        String requestJson = "{\n" +
                "    \"auth\": \"uuSbAIbTdfe3RtAgwbgUliBlChZNhr5eblaFdv9G\",\n" +
                "    \"id\": \"123456\",\n" +
                "    \"jsonrpc\": \"2.0\",\n" +
                "    \"method\": \"changes-show\",\n" +
                "    \"params\": {\n" +
                "        \"BeginTime\":\"2020-01-24 00:00:00\",\n" +
                "        \"EndTime\":\"2020-01-24 23:59:59\"\n" +
                "    }\n" +
                "}";


        String auth = "uuSbAIbTdfe3RtAgwbgUliBlChZNhr5eblaFdv9G";
        int id = 23;
        String jsonrpc = "2.0";
        String method = "changes-show";

        DataOfBuildings dataOfBuildings = new DataOfBuildings();

        dataOfBuildings.setAuth(auth);
        dataOfBuildings.setId(id);
        dataOfBuildings.setJsonrpc(jsonrpc);
        dataOfBuildings.setMethod(method);

        Params params = new Params();

        params.setBeginTime("2020-01-24 00:00:00");
        params.setEndTime("2020-01-24 23:59:59");

        dataOfBuildings.setContent(params);

//        DataOfBuildingsAnsw dataOfBuildingsAnsw = new DataOfBuildingsAnsw();
        return dataOfBuildings;


    }

}

