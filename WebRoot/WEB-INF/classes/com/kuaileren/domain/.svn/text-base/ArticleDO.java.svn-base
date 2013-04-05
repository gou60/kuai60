package com.kuaileren.domain;

import java.util.Date;

import com.kuaileren.common.PageInfo;
import com.kuaileren.util.ToolsUtil;
/**
 * 发表文章实体
 * @author lubang
 *
 */
public class ArticleDO {

	   public static String MV="mv";//有视频
	   public static String PIC="pic";//有图片
	   
	   private int articleId;
	   private String title;//发表标题
	   private String content ;//发表内容
	   private String type;//文章类型
	   private String keyWord;//设置的关键字，用作标签
	   private int userId;//发表者id
	   private String userName; //发表者名称
	   private Date createTime;
	   private Date modifyTime;
	   private int status;//状态 0:正常,1:删除
	   private int commentCount;//评论数
	   private int agree;//好贴
	   private int disAgree;//水贴
	   private int favoriteCount;//收藏次数
	   
	   public int getAgree() {
		return agree;
	}
	public void setAgree(int agree) {
		this.agree = agree;
	}
	public int getDisAgree() {
		return disAgree;
	}
	public void setDisAgree(int disAgree) {
		this.disAgree = disAgree;
	}
	
	public boolean isVoteAgree(){
		return agree==1?true:false;
	}
	
	public boolean isVoteDisAgree(){
		return !isVoteAgree();
	}
	
	PageInfo pageInfo = new PageInfo();
	   
	   
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	 
	
	public String userIconUrl(){
		return ToolsUtil.getUserIconUrl(userId);
	}
	 public boolean hasPic(){
		 if(PIC.equalsIgnoreCase(type)){
			 return true;
		 }
		 return false;
	 }
	 
	 public boolean hasMovie(){
		 if(MV.equalsIgnoreCase(type)){
			 return true;
		 }
		 return false; 
	 }
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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
	   
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	
	
	public int getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ArticleDO[");
		sb.append("articleId:"+articleId);
		sb.append(",title:"+title);
		sb.append(",content:"+content);
		sb.append(",type:"+type);
		sb.append(",keyWord:"+keyWord);
		sb.append(",userId:"+userId);
		sb.append(",userName:"+userName);
		sb.append(",createTime:"+createTime);
		sb.append(",modifyTime:"+modifyTime);
		sb.append(",status:"+status);
		sb.append(",commentCount:"+commentCount);
		sb.append(",agree:"+agree);
		sb.append(",disAgree:"+disAgree);
		sb.append(",favoriteCount:"+favoriteCount);
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
		
		if(obj instanceof ArticleDO){
			ArticleDO article = (ArticleDO)obj;
			if(article.getArticleId()==this.articleId){
				return true;
			}
		}
		return false;
	}   
	   
}
