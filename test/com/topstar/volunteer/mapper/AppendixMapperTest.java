package com.topstar.volunteer.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Appendix;

public class AppendixMapperTest extends BaseTest{

	@Autowired
	private AppendixMapper mapper;
	
	@Test
	public void selectByDocId(){
		Long docId = 1L;
		List<Appendix> list = mapper.selectByDocId(docId);
		if(list != null && !list.isEmpty()){
			for(Appendix p : list){
				System.out.println(p);
			}
		}
	}
	
	@Test
	public void getFileName(){
		Long id = 28L;
		String fileName = mapper.getFileName(id);
		System.out.println(fileName);
	}
	
	public static void main(String[] args) {
		System.out.println(Appendix.Type.file.getValue());
	}
}
