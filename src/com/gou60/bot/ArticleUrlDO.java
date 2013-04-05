package com.gou60.bot;

public class ArticleUrlDO {


	 int Fid;
	 int parent_id;
	 String parent_title;
	 String article_title;
	 String article_url;
	public int getFid() {
		return Fid;
	}
	public void setFid(int fid) {
		Fid = fid;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getParent_title() {
		return parent_title;
	}
	public void setParent_title(String parent_title) {
		this.parent_title = parent_title;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_url() {
		return article_url;
	}
	public void setArticle_url(String article_url) {
		this.article_url = article_url;
	}
	 
	 
	 
	
}
