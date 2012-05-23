package com.fykj.gpc.fileupdown.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtil {
	private String SDPATH;

	public FileUtil() {
		SDPATH = Environment.getExternalStorageDirectory() + "/";
		System.out.println(SDPATH);
	}

	/**
	 * 在sdCard上根据给定文件名创建文件
	 * 
	 * @param fileName
	 * @return
	 */
	public File createFile(String fileName) {
		File file = new File(SDPATH + fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件创建失败！");
		}
		return file;
	}

	public File createDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		if (!dir.exists())
			dir.mkdir();
		return dir;
	}

	public boolean isFileExists(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.exists();
	}

	public File write2SDFromInput(String path, String fileName, InputStream in) {
		File file = null;
		OutputStream os = null;
		int i = -1;
		createDir(path);
		file = createFile(path + fileName);
		try {
			os = new FileOutputStream(file);
			byte[] bytes = new byte[1024 * 1024];
			while ((i = in.read(bytes)) != -1) {
				os.write(bytes, 0, i);
			}
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public void deleteFile(String path, String fileName) {
		File file = new File(SDPATH + path + fileName);
		file.delete();
	}

}
