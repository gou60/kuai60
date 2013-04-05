package com.kuaileren.domain;

import java.io.Serializable;

public class IndexSearchFilter implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1570776259823124410L;
	
	private long offset=0;
    private long limit=10;
    
    
     private int articleId;
     private String userName = new String();
     private String title = new String();
     private String type = new String();
     private String content = new String();
     
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
