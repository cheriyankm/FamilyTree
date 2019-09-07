package com.cherry.saya.ft.controller;

import com.cherry.saya.ft.services.FTInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@RestController
@RequestMapping("/api")
public class FTController {

    @Autowired
    FTInterface service;

    @RequestMapping(value = "/getValue/{value}", method = RequestMethod.GET)
    public String getValue(@PathVariable String value){
        return service.getValue(value);
    }

    @RequestMapping(value = "/polling", method = RequestMethod.GET)
    public String poll(){
        return service.pollService();
    }
}