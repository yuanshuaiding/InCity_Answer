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
 * ʹ��XmlPullParser�Ĳ������£� 1.����XmlPullParserFactoryʵ���� 2.ʹ�ù���ʵ������XmlPullParser����
 * 3.ʹ��XmlPullParser�������xml�ļ���������
 * 
 * @author dingys
 */
public class XmlPullParseUtil {
	public static List<Question> parseXmlFile(InputStream in) {
		Question item = null;
		List<Question> list = new ArrayList<Question>();
		int eventType = -1;
		String preTagName = "";// ���ڼ�¼��ǰ�������ı�ǩ�ĸ���ǩ
		try {
			XmlPullParserFactory xmlPPF = XmlPullParserFactory.newInstance();
			// ����Ϊtrue����factory������XmlPullParser�ṩ��xml �����ռ��֧��
			xmlPPF.setNamespaceAware(true);
			XmlPullParser xpp = xmlPPF.newPullParser();
			xpp.setInput(new BufferedReader(new InputStreamReader(in)));
			while ((eventType = xpp.next()) != XmlPullParser.END_DOCUMENT) {
				// ֻ�н�������ʼ��ǩ��ʱ�����Ҫ��صĴ���
				if (eventType == XmlPullParser.START_TAG) {
					// ��¼����
					if (xpp.getName().equals("question")) {
						preTagName = "question";
						item = new Question();
					}

					// �����������ǰ��ǩ�ĸ���ǩΪquestion�����item������Ը�ֵ
					if (preTagName.equals("question")) {
						if (xpp.getName().equals("content"))
							item.setContent(xpp.nextText().trim());
						if (xpp.getName().equals("choice"))
							item.getChoices().add(xpp.nextText().trim());
						if (xpp.getName().equals("answer")) {
							// ����������ͽ���Map<String,Object>���뼯����
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