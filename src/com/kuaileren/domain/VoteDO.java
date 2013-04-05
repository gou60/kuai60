package com.kuaileren.domain;

import java.util.Date;

/**
 * 投票DO
 * @description TODO
 * @author lubang
 * @date 2011-9-7
 */
public class VoteDO {

	private int voteId;// id
	private int articleId;// 投票文章id
	private int userId;//  投票人id
	private int voteType;// 投票类型，加分+1,减分 -1
	private Date voteTime;// 投票时间
	public int getVoteId() {
		return voteId;
	}
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getVoteType() {
		return voteType;
	}
	public void setVoteType(int voteType) {
		this.voteType = voteType;
	}
	public Date getVoteTime() {
		return voteTime;
	}
	public void setVoteTime(Date voteTime) {
		this.voteTime = voteTime;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("voteId="+voteId);
		sb.append("articleId="+articleId);
		sb.append("userId="+userId);
		sb.append("voteType="+voteType);
		sb.append("voteTime="+voteTime);
		
		return sb.toString();
		
	}
}
