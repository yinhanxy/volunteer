package com.topstar.volunteer.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.topstar.volunteer.exception.TPSException;

public class FileTest {
	
	@Test
	public void copyFile(){
		String oldPath= "D:\\PICTEMPORARYPATH\\2017\\201707\\20170711\\U02017071115011101716886.jpg";
		String newPath ="D:\\fileupload\\2017\\201707\\20170711\\U02017071115011101716886.jpg";
		FileUtils.copyFile(oldPath, newPath);
	}
	
	@Test
	public void CpFile(){
		try{
			FileInputStream input=new FileInputStream("D:\\PICTEMPORARYPATH\\2017\\201707\\20170711\\U02017071115011101716886.jpg");//可替换为任何路径何和文件名
			FileOutputStream output=new FileOutputStream("D:\\fileupload\\2017\\201707\\20170711\\U02017071115011101716886.jpg");//可替换为任何路径何和文件名
			int in=input.read();
			while(in!=-1){
			output.write(in);
			in=input.read();
			}
			}catch (IOException e){
			System.out.println(e.toString());
			}
		
	}
	
	public static void main(String[] args) {
		String path = "D:\\PICTEMPORARYPATH\2017\201707\20170711";
		try {
			FileUtils.createNewFile(path);
		} catch (TPSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void createNewFile(){
		String path = "D:\\PICTEMPORARYPATH\2017\201707\20170712";
		try {
			FileUtils.createNewFile(path);
		} catch (TPSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void copFile(){
		String src ="D:\\PICTEMPORARYPATH\2017\201707\20170711\\U02017071115011101716886.jpg";
		String dest ="D:\\fileupload\2017\201707\20170711\\";
		try {
			FileUtils.copyFiles(src, dest);
		} catch (TPSException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
