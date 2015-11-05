package org.xiaangbalao.shell;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;

public class AndShell {
	/**
	 * 需要加壳的程序
	 */
	private static String apkPath = "g:/AndroidShellDome.apk";
	/**
	 * 解壳dex
	 */
	private static String shellDexPath = "g:/unshell.dex";
	/**
	 * 加壳后的dex
	 */
	private static String newDexFile = "g:/classes.dex";

	public static void main(String[] args) {

		// 检查输入的参数是否正确
		checkArgs(args);

		try {
			File payloadSrcFile = new File(apkPath);
			File unShellDexFile = new File(shellDexPath);
			if (!payloadSrcFile.exists()) {
				System.out.println("APK不存在");
				return;
			}

			if (!unShellDexFile.exists()) {
				System.out.println("加壳程序的dex不存在");
				return;
			}

			byte[] payloadArray = encrpt(readFileBytes(payloadSrcFile));// 以二进制形式读出apk，并进行加密处理

			System.out.println("APK的长度：" + payloadArray.length);

			byte[] unShellDexArray = readFileBytes(unShellDexFile);// 以二进制形式读出dex

			System.out.println("Dex的长度：" + unShellDexArray.length);
			int payloadLen = payloadArray.length;
			int unShellDexLen = unShellDexArray.length;
			int totalLen = payloadLen + unShellDexLen + 4;// 多出4字节是存放长度的。
			byte[] newdex = new byte[totalLen]; // 申请了新的长度
			System.out.println("加壳后Dex的长度" + newdex.length);

			// 添加解壳代码
			System.arraycopy(unShellDexArray, 0, newdex, 0, unShellDexLen);// 先拷贝dex内容
			// 添加加密后的解壳数据
			System.arraycopy(payloadArray, 0, newdex, unShellDexLen, payloadLen);// 再在dex内容后面拷贝apk的内容
			// 添加解壳数据长度
			System.arraycopy(intToByte(payloadLen), 0, newdex, totalLen - 4, 4);// 最后4为长度
			// 修改DEX file size文件头
			fixFileSizeHeader(newdex);
			// 修改DEX SHA1 文件头
			fixSHA1Header(newdex);
			// 修改DEX CheckSum文件头
			fixCheckSumHeader(newdex);
			// 把内容写到 newDexFile
			File file = new File(newDexFile);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream localFileOutputStream = new FileOutputStream(
					newDexFile);
			localFileOutputStream.write(newdex);
			localFileOutputStream.flush();
			localFileOutputStream.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 检测输入的参数
	 * 
	 * @param args
	 */
	private static void checkArgs(String[] args) {
		if (args.length != 3) {
			System.out.println("请输入要APK的路径：比如  D:/AndroidShellDome.apk");

			BufferedReader strin = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				apkPath = strin.readLine();
				System.out.println("APK目录：" + apkPath);
				System.out.println("请输入壳的dex录径：比如 D:/unshell.dex");
				strin = new BufferedReader(new InputStreamReader(System.in));
				shellDexPath = strin.readLine();
				System.out.println("壳的dex的录径：" + shellDexPath);

				System.out.println("请输入加壳后的dex路径及文件名:比如 D:/classes.dex");

				strin = new BufferedReader(new InputStreamReader(System.in));
				newDexFile = strin.readLine();
				System.out.println("加壳后的dex路径及文件名:" + newDexFile);

			} catch (IOException e) {

				e.printStackTrace();
			}

		} else {

			System.out.println("APK录径：" + args[0]);
			System.out.println("dex录径：" + args[1]);
			System.out.println("生成的dex：" + args[2]);
		}
	}

	/**
	 * 直接返回数据，读者可以添加自己加密方法
	 * 
	 * @param srcdata
	 * @return
	 */

	private static byte[] encrpt(byte[] srcdata) {
		return srcdata;
	}

	/**
	 * 修改dex头，CheckSum 校验码
	 * 
	 * @param dexBytes
	 */
	private static void fixCheckSumHeader(byte[] dexBytes) {
		Adler32 adler = new Adler32();
		adler.update(dexBytes, 12, dexBytes.length - 12);// 从12到文件末尾计算校验码
		long value = adler.getValue();
		int va = (int) value;
		byte[] newcs = intToByte(va);
		// 高位在前，低位在前掉个个
		byte[] recs = new byte[4];
		for (int i = 0; i < 4; i++) {
			recs[i] = newcs[newcs.length - 1 - i];

		}
		System.arraycopy(recs, 0, dexBytes, 8, 4);// 效验码赋值（8-11）
		System.out.println("较验码字节码数组长度：" + newcs.length);
		System.out.println();
	}

	/**
	 * int 转byte[]
	 * 
	 * @param number
	 * @return
	 */
	public static byte[] intToByte(int number) {
		byte[] b = new byte[4];
		for (int i = 3; i >= 0; i--) {
			b[i] = (byte) (number % 256);
			number >>= 8;
		}
		return b;
	}

	/**
	 * 修改dex头 sha1值
	 * 
	 * @param dexBytes
	 * @throws NoSuchAlgorithmException
	 */
	private static void fixSHA1Header(byte[] dexBytes)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(dexBytes, 32, dexBytes.length - 32);// 从32为到结束计算sha--1
		byte[] newdt = md.digest();
		System.arraycopy(newdt, 0, dexBytes, 12, 20);// 修改sha-1值（12-31）
		// 输出sha-1值，可有可无
		String hexstr = "";
		for (int i = 0; i < newdt.length; i++) {
			hexstr += Integer.toString((newdt[i] & 0xff) + 0x100, 16)
					.substring(1);
		}
		System.out.println("sha-1 值：" + hexstr);

	}

	/**
	 * 修改dex头 file_size值
	 * 
	 * @param dexBytes
	 */
	private static void fixFileSizeHeader(byte[] dexBytes) {
		// 新文件长度
		byte[] newfs = intToByte(dexBytes.length);

		byte[] refs = new byte[4];
		// 高位在前，低位在前掉个个
		for (int i = 0; i < 4; i++) {
			refs[i] = newfs[newfs.length - 1 - i];

		}
		System.arraycopy(refs, 0, dexBytes, 32, 4);// 修改（32-35）
		System.out.println();
	}

	/**
	 * 以二进制读出文件内容
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	private static byte[] readFileBytes(File file) throws IOException {
		byte[] arrayOfByte = new byte[1024];
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(file);
		while (true) {
			int i = fis.read(arrayOfByte);
			if (i != -1) {
				localByteArrayOutputStream.write(arrayOfByte, 0, i);
			} else {
				return localByteArrayOutputStream.toByteArray();
			}
		}
	}
}
