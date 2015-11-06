package org.xiangbalao.jsontest.pass;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 128bits密钥 加解密
 * @author chunjiang.shieh
 *
 */
public class AES {

	/**
	 * 
	 * @param seed		种子
	 * @param cleartext 需要加密的明文
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String seed, String cleartext) throws Exception {   
		byte[] rawKey = getRawKey(seed.getBytes());   
		byte[] result = encrypt(rawKey, cleartext.getBytes());   
		return HexUtil.toHex(result);   
	}   

	/**
	 * 
	 * @param seed			种子
	 * @param encrypted 需要解密的密文（16进制数据）
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String seed, String encrypted) throws Exception {   
		byte[] rawKey = getRawKey(seed.getBytes());   
		byte[] enc = HexUtil.hextoBytes(encrypted);   
		byte[] result = decrypt(rawKey, enc);   
		return new String(result);   
	}   


	/**
	 * 根据种子生产128bit的密钥
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	private static byte[] getRawKey(byte[] seed) throws Exception {   
		/**
		 * 创建一个新的KeyGenerator实例提供指定的加密算法、
		 * 此类提供（对称）密钥生成器的功能。
		 */
		KeyGenerator kgen = KeyGenerator.getInstance("AES");  
		/**
		 * SecureRandom 实现作为随机源
		 * 返回实现指定随机数生成器 (RNG) 算法的 SecureRandom 对象
		 */
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");   
		//设置实际随机种子 然后生成伪随机序列
		sr.setSeed(seed);  
		//使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小
		kgen.init(128, sr); // 192 and 256 bits may not be available 
		// 生成一个密钥
		SecretKey skey = kgen.generateKey();  
		//返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null
		byte[] raw = skey.getEncoded();   
		return raw;   
	}   


	/**
	 * AES  加密
	 * @param raw		AES 加密的KEY
	 * @param clear		AES 加密的内容 （128位的明文）
	 * @return  返回 128位的密文
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {   
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");   
		Cipher cipher = Cipher.getInstance("AES");   
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);   
		byte[] encrypted = cipher.doFinal(clear);   
		return encrypted;   
	}   

	/**
	 * AES  解密
	 * @param raw	AES 解密的KEY
	 * @param encrypted	AES 解密的内容 （128位的密文）
	 * @return 返回 （128位的明文）
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {   
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");   
		Cipher cipher = Cipher.getInstance("AES");   
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);   
		byte[] decrypted = cipher.doFinal(encrypted);   
		return decrypted;   
	}   

}
