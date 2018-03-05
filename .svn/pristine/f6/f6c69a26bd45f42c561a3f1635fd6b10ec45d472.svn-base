package com.topstar.volunteer.validate;

import java.util.List;

import org.junit.Test;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Role;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;

public class UserValidator extends BaseTest{
	
	@Test
	public void validate(){
		Role role = new Role();
		role.setRoleName("1");
        List<ValidateMessage> errors = ValidatorUtil.validate(role);
		if(errors != null && errors.size() > 0){
			for(ValidateMessage error : errors){
				System.out.println(error);
			}
		}
	}
	
	@Test
	public void validateByGroup(){
		Role role = new Role();
		role.setRoleName("1");
        List<ValidateMessage> errors = ValidatorUtil.validate(role,Groups.Add.class);
		if(errors != null && errors.size() > 0){
			for(ValidateMessage error : errors){
				System.out.println(error);
			}
		}
	}

	@Test
	public void validateProperty(){
		Role role = new Role();
		role.setRoleName("1");
        List<ValidateMessage> errors = ValidatorUtil.validateProperty(role,"roleName");
		if(errors != null && errors.size() > 0){
			for(ValidateMessage error : errors){
				System.out.println(error);
			}
		}
	}
	

	@Test
	public void validatePropertyByGroup(){
		User user=new User();
		user.setRealName("  ");
        List<ValidateMessage> errors = ValidatorUtil.validateProperty(user,"realName",Groups.Add.class);
		if(errors != null && errors.size() > 0){
			for(ValidateMessage error : errors){
				System.out.println(error);
			}
		}
	}
}
