package com.yinxc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static String replaceNT(String str){
		String newStr=new String();
		newStr=str.replaceAll("\t", "");
		newStr=str.replaceAll("\n","");
		return newStr;
	}
	/**
	 * 将指定字符串中的符合正则表达式的所有字符替换为指定字符
	 * @param orgStr
	 * @param regExpress
	 * @param repStr
	 * @return
	 */
	public static String replace(String orgStr,String regExpress,String repStr){
		if(orgStr==null||orgStr.equals(""))
			return orgStr;
		Pattern pattern=Pattern.compile(regExpress);//根据正在表达式字符串初始化正则表达式
		Matcher matcher=pattern.matcher(orgStr);//传入要匹配的字符串
		StringBuffer buffer=new StringBuffer();
		while(matcher.find()){
			String matchStr=matcher.group();//获取匹配正则表达式的那部分字符串
			matcher.appendReplacement(buffer, repStr);
		}
		
		return "";
	}
}
