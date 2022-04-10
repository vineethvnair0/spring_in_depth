package com.vineeth.learning.learningspring.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date createDateFromDateString(String dateString){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if (null != dateString && !dateString.equals("")) {
            try {
                date = dateFormat.parse(dateString);
            } catch (ParseException e) {
                date = new Date();
            }
        }else {
            date = new Date();
        }
        return date;
    }
}
