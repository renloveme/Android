package com.example.filesocket.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DataUtil {

    public static String getDataNow(){

        Date date=new Date();   //这里的时util包下的
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataTime=temp.format(date);
        return dataTime;
    }
}
