package com.fykj.gpc.xmlparse.pojo;

import java.util.List;

public class Feed {
	private String feedTitle;//新闻来源标题
	private String feedDesc;//新闻来源描述
	private String feedImg;//新闻来源提供的图片
	private String feedLink;//新闻来源的链接
	private List<NewsItem> newsList;//新闻来源包含的新闻集合
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
