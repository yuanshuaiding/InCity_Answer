package com.fykj.gpc.xmlparse.pojo;

import com.yinxc.StringUtil;

public class NewsItem {
	private String title;
	private String desc;
	private String link;
	

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public NewsItem(){}


	@Override
	public String toString() {
		return StringUtil.replaceNT(this.title.trim())+"\n";
	}
	
	
}
