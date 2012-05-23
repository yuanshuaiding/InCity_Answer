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
	 * ��ָ���ַ����еķ���������ʽ�������ַ��滻Ϊָ���ַ�
	 * @param orgStr
	 * @param regExpress
	 * @param repStr
	 * @return
	 */
	public static String replace(String orgStr,String regExpress,String repStr){
		if(orgStr==null||orgStr.equals(""))
			return orgStr;
		Pattern pattern=Pattern.compile(regExpress);//�������ڱ��ʽ�ַ�����ʼ��������ʽ
		Matcher matcher=pattern.matcher(orgStr);//����Ҫƥ����ַ���
		StringBuffer buffer=new StringBuffer();
		while(matcher.find()){
			String matchStr=matcher.group();//��ȡƥ��������ʽ���ǲ����ַ���
			matcher.appendReplacement(buffer, repStr);
		}
		
		return "";
	}
}
