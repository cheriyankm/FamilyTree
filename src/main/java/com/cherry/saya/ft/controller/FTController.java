package com.cherry.saya.ft.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cherry.saya.ft.model.CreateTableRequest;
import com.cherry.saya.ft.services.FTInterface;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api")
public class FTController {

    @Autowired
    FTInterface service;

    @RequestMapping(value = "/create/table", method = RequestMethod.POST)
    public String createTable(@RequestBody CreateTableRequest createTableRequest) throws IOException{
        return service.createNewSheet(createTableRequest);
    }
    
    @RequestMapping(value = "/getValue/{value}", method = RequestMethod.GET)
    public String getValue(@PathVariable String value){
        return service.getValue(value);
    }

    @RequestMapping(value = "/polling", method = RequestMethod.GET)
    public String poll(){
        return service.pollService();
    }
}