package com.topstar.volunteer.util;

import org.junit.Test;


public class StringTest {

	public static void main(String[] args) {
		String s ="b";
		String b = "asdfsdfsdfsdf";
		b = b.replaceAll("a", s);
		System.out.println(b);
	}
	
	@Test
	public void test1(){
		String  visitUrl = "/webpic\\sss\\a\\b";
		 visitUrl = visitUrl.replaceAll("\\\\", "/");
		 System.out.println(visitUrl);
	}
	
	
}
