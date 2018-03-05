package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Document;

public class DocumentMapperTest extends BaseTest {

	@Autowired
	private DocumentMapper documentMapper;
	
	@Test
	public void allList(){
//		Document doc = new Document();
//		List<Document> list = documentMapper.list(doc, null);
//		System.out.println(list.size());
	}
	
	@Test
	public void getMaxDocOrder(){
		Long channelId = 161L;
		Long maxDocOrder = documentMapper.getMaxDocOrder(channelId);
		System.out.println(maxDocOrder);
	}
	
	@Test
	public void findDocById(){
		Long id = 1L;
		Document doc = documentMapper.findDocById(id);
		System.out.println(doc);
	}
	
	@Test
	public void getDocNotice(){
		Channel channel = new Channel();
		channel.setId(6L);
		List<Document> list = documentMapper.getDocNotice(channel, 5);
		for (Document document : list) {
			System.out.println(document.toString());
		}
	}
}
