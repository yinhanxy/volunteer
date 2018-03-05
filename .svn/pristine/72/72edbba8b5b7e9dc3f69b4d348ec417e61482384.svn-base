package com.topstar.volunteer.util.secrecy;

import java.security.MessageDigest;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

/**  
 * 类说明：MD5加密类
 */
public class MD5Util {
    
    /**
     * 检查密码
     * @param inputPasswd 未加密的密码
     * @param storePasswd 加密的密码
     * @return true:通过检查,false:未通过
     */
    public static boolean checkPassword(String inputPasswd, String storePasswd){
        boolean success = false;
        try{
            byte[] saltBys = storePasswd.substring(0,2).getBytes("UTF-8");
            String inPwd = encode(inputPasswd, saltBys);
            success = inPwd.equals(storePasswd);
        }catch(Exception ex){
            ex.printStackTrace();
            success = false;
        }
        return success;
    }
    
    /**
     * 加密
     * @param inputPasswd 未加密的密码
     * @return
     */
    public static String encode(String inputPasswd){
        byte[] salt = getSaltOfASCII(2);
        return encode(inputPasswd, salt);
    }
    
    /**
     * 加密
     * @param inputPasswd 未加密的字符串
     * @param salt 盐
     * @return 加密后的字符串
     */
    private static String encode(String inputPasswd, byte[] salt){
        String pwd = "";
        try{
        	MessageDigest md = MessageDigest.getInstance("MD5", "SUN");
            md.reset();
            md.update(salt);
            md.update(inputPasswd.getBytes("UTF-8"));
            byte[] bys = md.digest();
            pwd += (char)salt[0];
            pwd += (char)salt[1];
            pwd += Base64.encodeBase64String(bys);
        }catch(Exception e){
            e.printStackTrace();
            pwd = "";
        }
        return pwd;
    }
    
    /**
     * 返回指定长度的盐(ASCII码)
     * @param len 长度
     * @return
     */
    private static byte[] getSaltOfASCII(int len){
        byte[] salt = new byte[len];
        Random rand = new Random();
        for(int i=0; i<len; i++){
            salt[i] = (byte) ((rand.nextInt('~'-'!')+'!') & 0x007f);
        }
        return salt;
    }
    
    public static void main(String[] args) {
        String a = "admin";
        String p = MD5Util.encode(a);
        System.out.println(p);
        String d = "7)RCobTwnjXDnR0vKT7lSDFQ==";
        boolean b = MD5Util.checkPassword(a, d);
        System.out.println(b);
    }
}