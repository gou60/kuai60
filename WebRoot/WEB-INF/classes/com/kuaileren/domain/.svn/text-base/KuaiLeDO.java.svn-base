package com.kuaileren.domain;

import com.kuaileren.util.ToolsUtil;

public class KuaiLeDO {
	
	private int userId;
	private String userName;
	private String userIconUrl;
	
	private int kuaiLeCount;
	private int kuaiArticleCount;
	private int loginKuaiLeCount ;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIconUrl() {
		
		userIconUrl = ToolsUtil.getUserIconUrl(userId);
		return userIconUrl;
	}
	public void setUserIconUrl(String userIconUrl) {
		this.userIconUrl = userIconUrl;
	}
	public int getKuaiLeCount() {
		return kuaiLeCount;
	}
	public void setKuaiLeCount(int kuaiLeCount) {
		this.kuaiLeCount = kuaiLeCount;
	}
	public int getKuaiArticleCount() {
		return kuaiArticleCount;
	}
	public void setKuaiArticleCount(int kuaiArticleCount) {
		this.kuaiArticleCount = kuaiArticleCount;
	}
	public int getLoginKuaiLeCount() {
		return loginKuaiLeCount;
	}
	public void setLoginKuaiLeCount(int loginKuaiLeCount) {
		this.loginKuaiLeCount = loginKuaiLeCount;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("KuaiLeDO[");
		sb.append("userId:"+userId);
		sb.append(",userName:"+userName);
		sb.append(",userIconUrl:"+getUserIconUrl());
		sb.append(",kuaiLeCount:"+kuaiLeCount);
		sb.append(",kuaiArticleCount:"+kuaiArticleCount);
		sb.append(",loginKuaiLeCount:"+loginKuaiLeCount);
		sb.append("]");
		
		return sb.toString();
	}
	
	
}
