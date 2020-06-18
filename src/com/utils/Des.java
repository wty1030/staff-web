package com.utils;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*
�������Ʊ���. Access restriction: The type Resource is not accessible due to restriction on required library
����һ:
ȫ������Project>preferences>java>Compiler>Errors/Warnings>���Ҳ�� Deprecated and restricted API>Forbidden reference��Error ��Ϊ Warning

������(�ռ�����:)
 ��Ŀ����preferences>java build path>���Ҳ�libraries �е�JRE System Library ɾ�����µ���.
*/

public class Des{
	static Key key;
	static {
		setKey("sheep"); 
	}
	/**
	 * ���ݲ�������KEY
	 */
	public static void setKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(strKey.getBytes()));
			key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			throw new RuntimeException(
					"Des ���� :Error initializing SqlMap class. Cause: " + e);
		}
	}

	/**
	 * ����String��������,String�������
	 */
	public static String encStr(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes("UTF8");
			byteMi = getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			throw new RuntimeException(
					"Des ���� : Error initializing SqlMap class. Cause: " + e);
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * ���� ��String��������,String�������
	 * 
	 * @param strMi
	 * @return
	 */  
	public static String desStr(String strMi) {
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = getDesCode(byteMi);
			strMing = new String(byteMing, "UTF8");
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * ������byte[]��������,byte[]�������
	 * 
	 * @param byteS
	 * @return
	 */
	private static byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * ������byte[]��������,��byte[]�������
	 * 
	 * @param byteD
	 * @return
	 */
	private static byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	public static void main(String args[]) {
		String str="����һ������"; 
		String encStr= Des.encStr(str);
		System.out.println("���ܺ�:"+ encStr);   //OAq18JRp/0Y39IxHWxm9r52t3gWNzVhn    v/BtU65e3TMUjsiSifkyEfSvEdY7373I
		System.out.println("���ܺ�:"+Des.desStr(encStr));
	}
}
