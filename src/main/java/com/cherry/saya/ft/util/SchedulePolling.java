package com.cherry.saya.ft.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.TimerTask;

public class SchedulePolling extends TimerTask {
    public void run(){
        synchronized (this) {
            RestTemplate template = new RestTemplate();
            ResponseEntity<String> result = new ResponseEntity<>("PROGRESS", HttpStatus.OK);
            result = template.getForEntity("http://localhost:9000/api/polling", String.class);
            if (result.hasBody()) {
                if ("COMPLETED".equalsIgnoreCase(result.getBody())) {
                    System.out.println("Completed");
                    this.cancel();
                    notify();
                }else if ("ERROR".equalsIgnoreCase(result.getBody())){
                    System.out.println("Error");
                }else{
                    System.out.println("Progress");
                }
            }
        }
    }
}
