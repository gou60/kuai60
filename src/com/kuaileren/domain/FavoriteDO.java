package com.kuaileren.domain;

import java.util.Date;

/**
 * �ղ�
 * @description TODO
 * @author lubang
 * @date 2011-9-8
 */
public class FavoriteDO {

	private int favoriteId;
	private int userId;//�û�id
	private int articleId;//�ղص�id
	private Date favoriteTime;//�ղ�ʱ��
	private int status;//״̬
	public int getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public Date getFavoriteTime() {
		return favoriteTime;
	}
	public void setFavoriteTime(Date favoriteTime) {
		this.favoriteTime = favoriteTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("favoriteId="+favoriteId);
		sb.append("articleId="+articleId);
		sb.append("userId="+userId);
		sb.append("status="+status);
		sb.append("favoriteTime="+favoriteTime);
		
		return sb.toString();
		
	}
	
}
