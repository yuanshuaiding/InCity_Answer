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
	 * 根据URL下载文件，前提是这个文件当中的内容是文本，函数的返回值就是文件当中的内容
	 * 1.创建一个URL对象
	 * 2.通过URL对象，创建一个HttpURLConnection对象
	 * 3.得到InputStram
	 * 4.从InputStream当中读取数据
	 * @param urlStr
	 * @return
	 */
	public String downText(String urlStr){
		BufferedReader bufferedReader=null;
		StringBuffer result=new StringBuffer();
		String line=null;
		try {
			//创建URL
			URL url=new URL(urlStr);
			//创建URL连接对象
			HttpURLConnection urlConn=(HttpURLConnection) url.openConnection();
			//根据URL连接对象获取对应的输入流
			InputStream in=urlConn.getInputStream();
			//使用IO操作该输入流,使用BufferReader效率会有提升哦,而且一次可以读取一行文字
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
	 * @param url要下载的文件路径(可以是如何文件类型)
	 * @param path下载后的存储路径
	 * @param fileName下载后的文件名
	 * @param isDelete是否删除已存在的文件
	 * @return 返回-1代表下载失败，0代表下载成功，1代表文件已经存在
	 */
	public int downFile(String urlStr,String path,String fileName,boolean isDelete){
		FileUtil fileUtil=new FileUtil();
		InputStream in=null;
		try {
			if(fileUtil.isFileExists(path+fileName)){
				//如果指定路径下的文件已经存在则返回1
				if(isDelete)
					fileUtil.deleteFile(path,fileName);//删除已存在文件
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
