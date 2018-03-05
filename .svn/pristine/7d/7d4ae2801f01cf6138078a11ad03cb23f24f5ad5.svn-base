package com.topstar.volunteer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerUtils;
import org.quartz.spi.OperableTrigger;

public class CronTest {
	
	public static void main(String[] args) throws ParseException, InstantiationException, IllegalAccessException {
		String expression="1,3,22,24,50,53,54 0 0 0 0 ? 2018-2019";
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(expression);
		Trigger trigger=TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
		List<Date> list=TriggerUtils.computeFireTimes((OperableTrigger)trigger, null, 5);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<list.size();i++){
			System.out.println(format.format(list.get(i)));
		}
	}

}
