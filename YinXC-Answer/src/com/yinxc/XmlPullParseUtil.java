package com.yinxc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * 使用XmlPullParser的步骤如下： 1.创建XmlPullParserFactory实例； 2.使用工厂实例创建XmlPullParser对象；
 * 3.使用XmlPullParser对象解析xml文件输入流。
 * 
 * @author dingys
 */
public class XmlPullParseUtil {
	public static List<Question> parseXmlFile(InputStream in) {
		Question item = null;
		List<Question> list = new ArrayList<Question>();
		int eventType = -1;
		String preTagName = "";// 用于记录当前解析到的标签的父标签
		try {
			XmlPullParserFactory xmlPPF = XmlPullParserFactory.newInstance();
			// 设置为true，则factory创建的XmlPullParser提供对xml 命名空间的支持
			xmlPPF.setNamespaceAware(true);
			XmlPullParser xpp = xmlPPF.newPullParser();
			xpp.setInput(new BufferedReader(new InputStreamReader(in)));
			while ((eventType = xpp.next()) != XmlPullParser.END_DOCUMENT) {
				// 只有解析到开始标签的时候才需要相关的处理
				if (eventType == XmlPullParser.START_TAG) {
					// 记录父标
					if (xpp.getName().equals("question")) {
						preTagName = "question";
						item = new Question();
					}

					// 如果解析到当前标签的父标签为question，则给item相关属性赋值
					if (preTagName.equals("question")) {
						if (xpp.getName().equals("content"))
							item.setContent(xpp.nextText().trim());
						if (xpp.getName().equals("choice"))
							item.getChoices().add(xpp.nextText().trim());
						if (xpp.getName().equals("answer")) {
							// 解析到这里就将该Map<String,Object>放入集合中
							item.setAnswer(xpp.nextText().trim());
							list.add(item);
						}
					}
				}
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}