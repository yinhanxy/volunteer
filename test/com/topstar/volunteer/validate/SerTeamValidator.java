package com.topstar.volunteer.validate;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;

public class SerTeamValidator  extends BaseTest{
	
	@Test
	public void validator(){
		SerTeam serTeam=new SerTeam();
		serTeam.setName("湖北省美术馆总队");
		serTeam.setOrgId((long)2);
		serTeam.setSummary("呵呵呵呵呵呵呵呵呵呵呵呵");
		serTeam.setContact("王五");
		serTeam.setContactTel("138888888882222222222222");
		serTeam.setFax("12456487");
		serTeam.setEmail("2254@136.com");
		serTeam.setPrincipal("小李");
		serTeam.setPrincipalTel("13888888888");
		serTeam.setAddress("湖北武汉");
		serTeam.setAreaId((long)1);
		serTeam.setCrUser("yinh");
		serTeam.setCrTime(new Date());
		serTeam.setStatus(1);
		 List<ValidateMessage> errors = ValidatorUtil.validate(serTeam,Groups.Add.class);
			if(errors != null && errors.size() > 0){
				for(ValidateMessage error : errors){
					System.out.println(error);
				}
			}
	}
}
