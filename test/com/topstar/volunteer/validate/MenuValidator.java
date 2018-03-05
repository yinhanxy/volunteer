package com.topstar.volunteer.validate;

import java.util.List;

import org.junit.Test;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Menu;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;

public class MenuValidator extends BaseTest{
	
	@Test
	public void validate(){
		Menu menu = new Menu();
		menu.setId(0L);
		menu.setMenuName("122");
		menu.setMenuDesc("123143");
		menu.setMenuType(3);
        List<ValidateMessage> errors = ValidatorUtil.validate(menu,Groups.Update.class);
		if(errors != null && errors.size() > 0){
			for(ValidateMessage error : errors){
				System.out.println(error);
			}
		}
	}
	
	
}
