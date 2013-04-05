package com.kuaileren.domain;


/**
 * 用户实体
 * @author lubang
 *
 */
public class UserQueryDO {

	private long userId;
	private String userName;
	private String password;
	
	private int status;//0:正常，1:删除
	   
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("UserQueryDO[");
		sb.append("userId:"+userId);
		sb.append(",userName:"+userName);
		sb.append(",password:"+password);
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
		
		if(obj instanceof UserQueryDO){
			UserQueryDO user = (UserQueryDO)obj;
			if(user.getUserId()==this.userId){
				return true;
			}
		}
		
		return false;
	}
	
	
}
