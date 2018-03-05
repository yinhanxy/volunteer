package com.topstar.volunteer.util.secrecy;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.util.PropertiesUtil;

/**  
 * DES加密算法工具类
 */
public class DESUtil {
    
    /**
     * 加密密钥
     */
    private static String key = PropertiesUtil.getInstance().getProperty("DESKEY");
    
    /**
     * 加密
     * @param input 未加密的文本
     * @return 已加密文本
     * @throws SoughtException 
     */
    public static String encode(String input) throws TPSException {
    	if(StringUtils.isBlank(key)){
    		throw new TPSException("加密的密钥不存在");
    	}
        String text;
        try {
        	byte[] desKey = key.getBytes("UTF-8");
            text = encrypt(input,desKey);
        } catch (Exception e) {
            throw new TPSException("DES加密字符串出错",e);
        }
        return text;
    }
    
    /**
     * 解密
     * @param password 已加密的文本
     * @return
     * @throws SoughtException
     */
    public static String decode(String password) throws TPSException {
    	if(StringUtils.isBlank(key)){
    		throw new TPSException("加密的密钥不存在");
    	}
        String text;
        try {
        	byte[] desKey = key.getBytes("UTF-8");
            text = decrypt(password,desKey);
        } catch (Exception e) {
            throw new TPSException("DES解密字符串出错",e);
        }
        return text;
    }
    
    private static String encrypt(String input,byte[] desKey) throws Exception {
    	String result = base64Encode(desEncrypt(input.getBytes(),desKey));
        return HexUtil.encodeHexStr(result.getBytes());
    }
  
    private static String decrypt(String input,byte[] desKey) throws Exception {
    	String hex = new String(HexUtil.decodeHex(input.toCharArray()));
    	byte[] result = base64Decode(hex);
        String decrypt = new String(desDecrypt(result,desKey));
        return decrypt;
    }
    
    private static byte[] desEncrypt(byte[] plainText,byte[] desKey) throws Exception {
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = desKey;
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        byte data[] = plainText;
        byte encryptedData[] = cipher.doFinal(data);
        return encryptedData;
    }
  
    private static byte[] desDecrypt(byte[] encryptText,byte[] desKey) throws Exception {
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = desKey;
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        byte encryptedData[] = encryptText;
        byte decryptedData[] = cipher.doFinal(encryptedData);
        return decryptedData;
    }  
  
    private static String base64Encode(byte[] s) {
        if (s == null){
            return null;
        }
        return Base64.encodeBase64String(s);
    }

    private static byte[] base64Decode(String s) throws IOException{
        if (s == null){
            return null;
        }
        return Base64.decodeBase64(s);
    }
    
    public static void main(String[] args) {
		String a = "123456";
		try {
			String b = DESUtil.encode(a);
			System.out.println(b);
			String c = DESUtil.decode(b);
			System.out.println(c);
		} catch (TPSException e) {
			e.printStackTrace();
		}
	}
}