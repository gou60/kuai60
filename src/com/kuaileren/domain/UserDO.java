package com.kuaileren.domain;

import java.util.Date;

import com.kuaileren.common.PageInfo;

/**
 * 用户实体
 * @author lubang
 *
 */
public class UserDO {

	private int userId;
	private String userName;
	private String password;
	private int sex;
	private int type;//用户级别
	private int point;//积分
	private Date birthday;
	private String qq;
	private String email;
	private String remark;//描述
	private Date createTime;
	private Date modifyTime;
	private int status;//0:正常，1:删除
	
	private String trackId;
	
	private String iconUrl;//头像图片路径
	   
	PageInfo pageInfo = new PageInfo();
	   
	   
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("UserDO[");
		sb.append("userId:"+userId);
		sb.append(",userName:"+userName);
		sb.append(",password:"+password);
		sb.append(",sex:"+sex);
		sb.append(",type:"+type);
		sb.append(",point:"+point);
		sb.append(",birthday:"+birthday);
		sb.append(",qq:"+qq);
		sb.append(",email:"+email);
		sb.append(",remark:"+remark);
		sb.append(",createTime:"+createTime);
		sb.append(",modifyTime:"+modifyTime);
		sb.append(",status:"+status);
		sb.append(",iconUrl:"+iconUrl);
		
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
		
		if(obj instanceof UserDO){
			UserDO user = (UserDO)obj;
			if(user.getUserId()==this.userId){
				return true;
			}
		}
		
		return false;
	}
	
	
}
