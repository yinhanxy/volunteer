package com.topstar.volunteer.validate;

import java.util.List;

import org.junit.Test;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;

public class ConfigValidator extends BaseTest{
	
	@Test
	public void validate(){
		Config config= new Config();
		config.setName("   ");
		List<ValidateMessage> errors = ValidatorUtil.validate(config,Groups.Add.class);
		if(errors != null && errors.size() > 0){
			for(ValidateMessage error : errors){
				System.out.println(error);
			}
		}
	}
	
	
}
