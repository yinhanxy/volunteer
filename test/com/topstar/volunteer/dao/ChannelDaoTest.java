package com.topstar.volunteer.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Channel;

public class ChannelDaoTest extends BaseTest {

	@Autowired
	private ChannelDao channelDao;
	
	@Test
	public void getTopChannelBySiteId(){
		Long siteId=101l;
		List<Channel> channels=channelDao.findTopChannelBySiteId(siteId);
		for (Channel channel : channels) {
			System.out.println(channel);
		}
	}
	
	@Test
	public void getMaxOrderNoByParent(){
		Long maxOrderNo=channelDao.getMaxOrderNoByParentId(1l);
		System.out.println(maxOrderNo);
	}
}
