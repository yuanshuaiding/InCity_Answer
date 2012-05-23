package com.fykj.gpc.fileupdown.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpDownLoader {
	/**
	 * ����URL�����ļ���ǰ��������ļ����е��������ı��������ķ���ֵ�����ļ����е�����
	 * 1.����һ��URL����
	 * 2.ͨ��URL���󣬴���һ��HttpURLConnection����
	 * 3.�õ�InputStram
	 * 4.��InputStream���ж�ȡ����
	 * @param urlStr
	 * @return
	 */
	public String downText(String urlStr){
		BufferedReader bufferedReader=null;
		StringBuffer result=new StringBuffer();
		String line=null;
		try {
			//����URL
			URL url=new URL(urlStr);
			//����URL���Ӷ���
			HttpURLConnection urlConn=(HttpURLConnection) url.openConnection();
			//����URL���Ӷ����ȡ��Ӧ��������
			InputStream in=urlConn.getInputStream();
			//ʹ��IO������������,ʹ��BufferReaderЧ�ʻ�������Ŷ,����һ�ο��Զ�ȡһ������
			bufferedReader=new BufferedReader(new InputStreamReader(in));
			while((line=bufferedReader.readLine())!=null){
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * @author eric
	 * @param urlҪ���ص��ļ�·��(����������ļ�����)
	 * @param path���غ�Ĵ洢·��
	 * @param fileName���غ���ļ���
	 * @param isDelete�Ƿ�ɾ���Ѵ��ڵ��ļ�
	 * @return ����-1��������ʧ�ܣ�0�������سɹ���1�����ļ��Ѿ�����
	 */
	public int downFile(String urlStr,String path,String fileName,boolean isDelete){
		FileUtil fileUtil=new FileUtil();
		InputStream in=null;
		try {
			if(fileUtil.isFileExists(path+fileName)){
				//���ָ��·���µ��ļ��Ѿ������򷵻�1
				if(isDelete)
					fileUtil.deleteFile(path,fileName);//ɾ���Ѵ����ļ�
				else
					return 1;
			}
			URL url=new URL(urlStr);
			HttpURLConnection urlConn=(HttpURLConnection) url.openConnection();
			in=urlConn.getInputStream();
			File file=fileUtil.write2SDFromInput(path, fileName, in);
			if(file==null){
				return -1;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
