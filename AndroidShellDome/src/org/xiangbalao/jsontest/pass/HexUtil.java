package org.xiangbalao.jsontest.pass;

/**
 * 16进制相关的算法工具类
 * @author chunjiang.shieh
 *
 */
public class HexUtil {


	/**
	 * 将字符内容转化为16进制字符串
	 * @param txt
	 * @return
	 */
	public static String toHex(String txt) {   
		return toHex(txt.getBytes());   
	}   
	
	
	/**
	 * 转成16进制字符串 打印
	 * @param buf
	 * @return
	 */
	public static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}
	
	
	/**
	 * 将16进制字符串转化成字符串
	 * @param hex
	 * @return
	 */
	public static String fromHex(String hex) {   
		return new String(hextoBytes(hex));   
	}  
	
	
	
	/**
	 * 将16进制的字符（如：AFD0123）串转成字节数组
	 * 一个16进制的字符占4bit
	 * @param hexString
	 * @return
	 */
	public static byte[] hextoBytes(String hexString) {   
		int len = hexString.length()/2;   
		byte[] result = new byte[len];   
		for (int i = 0; i < len; i++)   
			result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();   
		return result;   
	}  

	private final static String HEX = "0123456789abcdef";

	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}
	
	
}
