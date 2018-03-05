package com.topstar.volunteer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {
	
	public static void main(String[] args) {
		Calendar startTime=Calendar.getInstance();
		int day_of_week = startTime.get(Calendar.DAY_OF_WEEK) - 1;
		  if (day_of_week == 0)
		   day_of_week = 7;
		  startTime.add(Calendar.DATE, -day_of_week + 1);
		String sStartTime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime.getTime());
		System.out.println(sStartTime);
	}

}
