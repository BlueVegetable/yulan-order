package com.yulan.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String toMD5(String value) {
		byte[] secretBytes=null;
		
		try {
			secretBytes=MessageDigest.getInstance("md5").digest(value.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("不存在MD5编码格式");
		}
		
		return new BigInteger(1,secretBytes).toString(16);
	}
}