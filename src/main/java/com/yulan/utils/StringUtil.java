package com.yulan.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 乱码转换
 * @author Administrator
 *
 */
public class StringUtil {
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}
	public static String getUtf8(String string) throws UnsupportedEncodingException {

		if(string == null || string.length() <= 0){
				return null;
		}else{
			String utf8=new String(string.getBytes(getEncoding(string)),"gbk");
			return utf8;
		}

	}

    public static String setUtf8(String string) throws UnsupportedEncodingException {

        if(string == null || string.length() <= 0){
            return null;
        }else {
            String utf8 = new String(string.getBytes("GBK"), "ISO-8859-1");
            return utf8;
        }
    }

	/**
	 * 替换文本
	 * @param text 模板
	 * @param beReplaced 被取代的点
	 * @param replaces 取代的数据
	 * @return 取代后的文本
	 */
	public static String replace(String text,String beReplaced,List<Object> replaces) {
		Pattern pattern = Pattern.compile(beReplaced);
		Matcher matcher = pattern.matcher(text);
		String result = new StringBuffer(text).toString();
		int i=0;
		while (matcher.find()) {
			if(replaces.size()<i+1) {
				break;
			}
			if(replaces.get(i) == null || replaces.get(i).equals("")) {
				replaces.add(i,"");
			}
			String replace = replaces.get(i).toString();
			result = result.replaceFirst(beReplaced,replace);
			i++;
		}
		return result;
	}

	public static String createStringID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 替换特定文本
	 * @param text
	 * @param beReplaced
	 * @param replaces
	 * @return
	 */

	public static List<String> replaceState(List<String> text, String beReplaced, String replaces) {
		Pattern pattern = Pattern.compile(beReplaced);
		List<String> result = new ArrayList<>();
		for(String t : text){
			Matcher matcher = pattern.matcher(t);
			while(matcher.find()) {
				t=t.replaceFirst(beReplaced, replaces);
			}
			result.add(t);
		}
		return result;
	}

    /**
     *
     * @param resource 源文本
     * @param norm 匹配的正则
     * @param
     * @return
     */
    public static String getName(String resource,String norm,String term){
	    if(resource==null||resource.equals("")){
	        return "";
        }
        Pattern pattern = Pattern.compile(norm);
        Matcher matcher = pattern.matcher(resource);
        String result ="";
		int i=0;
        while(matcher.find()) {

            result=matcher.group(i);

			i++;
        }
		result = result.replaceAll(term, "");
        return result;

    }
}
