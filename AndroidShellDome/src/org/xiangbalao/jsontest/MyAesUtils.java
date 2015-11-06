package org.xiangbalao.jsontest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Log;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public class MyAesUtils {
	/*
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */	
	// 加密
	public static String encrypt(String sSrc, String encodingFormat,
			String sKey, String ivParameter) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		byte[] raw = sKey.getBytes();
		
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		
		byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));

		return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
	}

	
	
	// 解密
	public static String decrypt(String sSrc, String encodingFormat,
			String sKey, String ivParameter) throws Exception {
	
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, encodingFormat);
			return originalString;

		
	}

	
}
