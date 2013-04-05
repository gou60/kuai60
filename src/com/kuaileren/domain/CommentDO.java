package com.kuaileren.domain;

import java.util.Date;

import com.kuaileren.common.PageInfo;
import com.kuaileren.util.ToolsUtil;

/**
 * 评论实体
 * @author lubang
 *
 */
public class CommentDO {

	   private int commentId;
	   private String content;//评论内容
	   private int userId;//评论者id
	   private String userName;//评论者名字
	   private Date createTime;
	   private Date modifyTime;
	   private int status;//状态：0：正常，1：删除
	   private int floor;//评论层级
	   private int anony;//是否匿名
	   
	   private int articleId;//文章id
	   
	   
	   
	public int getAnony() {
		return anony;
	}
	public void setAnony(int anony) {
		this.anony = anony;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	PageInfo pageInfo = new PageInfo();
	   
		public PageInfo getPageInfo() {
			return pageInfo;
		}
		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}
		
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean isAnony(){
		return anony==1?true:false;
	}
	 
	public String userIconUrl(){
		return ToolsUtil.getUserIconUrl(userId);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CommentDO[");
		sb.append("commentId:"+commentId);
		sb.append(",content:"+content);
		sb.append(",userId:"+userId);
		sb.append(",userName:"+userName);
		sb.append(",createTime:"+createTime);
		sb.append(",modifyTime:"+modifyTime);
		sb.append(",status:"+status);
		sb.append("]");
		
		return sb.toString();
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null)return false;
		
		if(obj instanceof CommentDO){
			CommentDO comment = (CommentDO)obj;
			if(comment.getCommentId()==this.commentId){
				return true;
			}
		}
		
		return false;
	}
	   
}
