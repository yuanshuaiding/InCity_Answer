package com.fykj.gpc.xmlparse.pojo;

import java.util.List;

public class Feed {
	private String feedTitle;//������Դ����
	private String feedDesc;//������Դ����
	private String feedImg;//������Դ�ṩ��ͼƬ
	private String feedLink;//������Դ������
	private List<NewsItem> newsList;//������Դ���������ż���
	public String getFeedTitle() {
		return feedTitle;
	}
	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}
	public String getFeedDesc() {
		return feedDesc;
	}
	public void setFeedDesc(String feedDesc) {
		this.feedDesc = feedDesc;
	}
	public String getFeedImg() {
		return feedImg;
	}
	public void setFeedImg(String feedImg) {
		this.feedImg = feedImg;
	}
	public String getFeedLink() {
		return feedLink;
	}
	public void setFeedLink(String feedLink) {
		this.feedLink = feedLink;
	}
	public List<NewsItem> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<NewsItem> newsList) {
		this.newsList = newsList;
	}
	public Feed(){
		
	}
	@Override
	public String toString() {
		return "Feed [feedTitle=" + feedTitle + ", feedDesc=" + feedDesc
				+ ", feedImg=" + feedImg + ", feedLink=" + feedLink
				+ ", newsList=" + newsList + "]";
	}
	
	
}
