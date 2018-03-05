package com.topstar.volunteer.schedule.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.schedule.QuartzJob;

public class ScheduleTaskTest extends QuartzJob{

	Logger logger=LoggerFactory.getLogger(ScheduleTaskTest.class);
	SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
	public void getParamaters(String name,String tel) throws InterruptedException{
		System.out.println("===============================");
		Thread.sleep(1000*5);
		getParamaters2(name, tel);
		Thread.sleep(1000*10);
		System.out.println("任务完成");
	};
	
	public void getParamaters2(String name,String tel){
		System.out.println("getParamaters2("+name+","+tel+")"+format.format(new Date()));
	}

	@Override
	protected void runJob() throws Exception {
		String name=getArgAsString("name");
		String tel=getArgAsString("tel");
		try{
			getParamaters(name, tel);
		}catch (Exception e) {
			e.printStackTrace();
		}
	};

	
}
