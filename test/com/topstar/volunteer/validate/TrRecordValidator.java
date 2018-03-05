package com.topstar.volunteer.validate;

import java.util.List;

import org.junit.Test;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;

public class TrRecordValidator  extends BaseTest{
	
	@Test
	public void validator(){
		TrRecord trRecord=new TrRecord();
//		trRecord.se
		 List<ValidateMessage> errors = ValidatorUtil.validate(trRecord,Groups.Add.class);
			if(errors != null && errors.size() > 0){
				for(ValidateMessage error : errors){
					System.out.println(error);
				}
			}
	}
}
