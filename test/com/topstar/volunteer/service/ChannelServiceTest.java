package com.topstar.volunteer.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.model.ChannelView;

public class ChannelServiceTest extends BaseTest{

	@Autowired
	private ChannelService channelService;
	
	@Test
	public void getSiteTopChannel(){
		List<ChannelView> list=channelService.getSitesBySiteIdIncTopChannels();
		if(list!=null){
			for (ChannelView channelView : list) {
				System.out.println("channelView:"+channelView);
			}
		}
	}
	
	@Test
	public void getChannelsByChannelName(){
		PageInfo<Channel> page=channelService.findByChannelName("服务", null, 1, 1);
		List<Channel> channels=page.getList();
		if(channels!=null){
			for (Channel channel : channels) {
				System.out.println("channel:"+channel);
			}
		}
	}
	
	@Test
	public void getChannelViewsByParentId(){
		PageInfo<Channel> page=channelService.findSubChannelsByParentId(101l, null,null, 1, 1);
		List<Channel> channels=page.getList();
		if(channels!=null){
			for (Channel channel : channels) {
				System.out.println("channel:"+channel);
			}
		}
	}
	
	@Test
	public void getAllChannelsListByParentId(){
		List<Channel> result=new ArrayList<Channel>();
		channelService.getAllChannelsListByParentId(2l,result,10l);
		for (Channel channel : result) {
			System.out.println(channel);
		}
	}
	
	@Test
	public void getTopChannel(){
		Channel currChannel=new Channel();
		currChannel.setId(1l);
		currChannel.setSiteId(101l);
		currChannel.setParentId(101l);
		List<Channel> topChannels=new ArrayList<Channel>(); 
		channelService.getParentChannelsWithCurrChannel(currChannel,topChannels);
		System.out.println(topChannels);
	}
	
	/*@Test
	public void getAllChannel(){
		List<ChannelView> allChannelView=channelService.getAllChannels();
		for (ChannelView channelView : allChannelView) {
			System.out.println(channelView );
		}
	}*/
	
	@Test
	public void update(){
		Channel channel=new Channel();
		channel.setId(6l);
		channel.setStatus(2);
		int a=channelService.updateNotNull(channel);
		if(a>0){
			System.out.println("成功");
		}
	}
	
	@Test
	public void getRecycleChannels(){
		PageInfo<Channel> list=channelService.getRecycleChannels(1l,null, null, 1, 10);
		List<Channel> channels=list.getList();
		for (Channel channel : channels) {
			System.out.println(channel);
		}
	}
	
	@Test
	public void getChannelsByParentId(){
		Channel currChannel=channelService.selectByKey(162l);
		Channel preChannel=channelService.selectByKey(163l);
		List<Channel> channels=channelService.getBroChannelsByParentId(currChannel);
		for (Channel channel : channels) {
			System.out.println(channel);
		}
		System.out.println(channels.contains(preChannel));
	}
	
	@Test
	public void saveChannel(){
		Channel channel=new Channel();
		channel.setId(162l);
		channel.setParentId(1l);
		
		channelService.saveChannel(channel, 164l);
		
	}
}
