package com.artsoft.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	
	public static java.sql.Date getDefaultDate(){
		String dateString = "0000-00-00 00:00:00";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    java.util.Date date;
	    try {
	    	date = df.parse(dateString);
	        return new java.sql.Date(date.getTime());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	
	public static java.sql.Timestamp getCurrentTimestamp(){
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		return stamp;
	}
	
	
	
    public static String getDateToString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }
}
