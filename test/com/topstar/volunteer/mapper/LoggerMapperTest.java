package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Logger;

public class LoggerMapperTest extends BaseTest{

	@Autowired
	private LoggerMapper mapper;
	
	@Autowired
	private LoggerBakMapper loggerBakMapper;
	
	@Test
	public void getLogger(){
		 List<Logger> list=	mapper.getLoggersThree();
		 for (Logger logger : list) {
				System.out.println(logger.toString());
			}
		 int res=loggerBakMapper.addLoggerBak(list);
		 System.out.println("res:"+res);
	}
	
	@Test
	public void delLogger(){
		int res =mapper.delLoggersThree();
		System.out.println("res:"+res);
	}
}
