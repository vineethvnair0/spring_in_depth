package com.vineeth.learning.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Service
public class TimeService {

    SimpleDateFormat fullHrFormater = new SimpleDateFormat("HH:mm", Locale.UK);

    SimpleDateFormat simpleDateFormater = new SimpleDateFormat("hh:mm aa", Locale.UK);


    @Value("#{new Boolean(environment['spring.profiles.active']!='dev')}")
    private boolean is24;

    public String getTimeGenerated(){
        Date date = new Date();
        if (is24){
         return fullHrFormater.format(date);
        } else {
            return simpleDateFormater.format(date);
        }

    }
}
