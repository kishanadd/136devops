package com.srk.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WMSUtil {
	public static Timestamp getTimestampFromString(String startDate){
    	java.util.Date date = null;
		java.sql.Timestamp timeStamp = null;
    	try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = simpleDateFormat.parse(startDate);
			timeStamp = new java.sql.Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return timeStamp;
    }
	
	public static String getDiffMins(String deviceTime) {
		String diffTime = "";
		try {
			String curentTime = getTodayTimestamp().toString();

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Date d1 = format.parse(deviceTime);
			Date d2 = format.parse(curentTime);

			//in milliseconds
			long diff  = d2.getTime() - d1.getTime();
			
			long diffSeconds = diff / 1000 % 60;
			long diffMinutess = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			
			diffTime = diffDays+" "+diffHours+":"+diffMinutess+":"+diffSeconds;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return diffTime;
	}
    
    /**
     * @return
     */
    public static Timestamp getTodayTimestamp() {
		java.util.Date date = null;
		java.sql.Timestamp timeStamp = null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			java.sql.Date dt = new java.sql.Date(calendar.getTimeInMillis());
			java.sql.Time sqlTime = new java.sql.Time(calendar.getTime()
					.getTime());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = simpleDateFormat.parse(dt.toString() + " "+ sqlTime.toString());
			timeStamp = new java.sql.Timestamp(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeStamp;
	}
}
