package com.cherry.saya.ft.implementations;

import com.cherry.saya.ft.services.FTInterface;
import com.cherry.saya.ft.util.SchedulePolling;
import com.cherry.saya.ft.util.TomyUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class FTImplementation implements FTInterface {

    private int count = 0;

    @Override
    public String getValue(String value){
        String newValue = getCollectedValues(value);
        return "Hello "+newValue;
    }

    @Override
    public String pollService() {
        count++;
        System.out.println("Count="+count);
        if(count==2) {
            return "COMPLETED";
        }else{
            return "PROGRESS";
        }
    }

    private String getCollectedValues(String value) {
        //TomyUtil.getData();
        Timer timer = new Timer();
        TimerTask timerTask = new SchedulePolling();
        timer.schedule(timerTask,0,3000);
        synchronized (timerTask) {
            try {
                timerTask.wait();
                timer.cancel();
                System.out.println("purge : "+timer.purge());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return "Completed";
    }
}
