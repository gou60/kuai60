package com.kuaileren.service;

import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

import com.kuaileren.common.ResultCode;
import com.kuaileren.common.UserResult;
import com.kuaileren.dao.UserDao;
import com.kuaileren.domain.UserDO;
import com.kuaileren.util.StringUtil;
import com.kuaileren.util.ToolsUtil;

public class UserServiceImpl implements UserService {

	public UserResult  queryUserList(UserDO user) {
		UserResult result = new UserResult(false);
		List<UserDO> userList = userDao.queryUserList(user);
		
		result.setUserList(userList);
		result.setSuccess(true);
		return result;
	}

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public UserResult login(UserDO user) {
		UserResult result = new UserResult(false);
		if(StringUtil.isBlank(user.getUserName()) || StringUtil.isBlank(user.getPassword())){
			result.setErrMsg("你怎么能没有身份或身份密码？");
			return result;
		}
		UserDO userQ = userDao.queryUser(user);
		
		//如果为空,则说明身份没有被占用，系统自动注册一个
		if(userQ == null) {
			user.setIconUrl(getRandomUrl());
			if(user.getUserId()<=0){
				//userQ = userDao.register(user);
			}else{
				//userQ = userDao.modify(user);
			}
			result.setUserDO(user);
			result.setSuccess(true);
			
			ToolsUtil.addUserToMap(userQ);
			return result;
		}
		
		//身份和身份密码都匹配
		if(user.getUserName().equals(userQ.getUserName()) 
				&& user.getPassword().equals(userQ.getPassword())){
			
			result.setUserDO(userQ);
			result.setSuccess(true);
			return result;
		}
		
		if(!result.isSuccess()){
			result.setErrMsg("也许这个身份已经被别人占用了，也许你忘记了身份密码？");
		}
		return result;
	}
	
	
	private String getRandomUrl() {
		String url ="";
		int rand = RandomUtils.nextInt(15);
		 rand++;
		 
		 if(rand%3==0){
			 url = "A ";
		 }else if(rand%3==1){
			 url = "B ";
		 }else if(rand%3==2){
			 url = "Q ";
		 }
		 
		 if(rand<10){
			 url += "0"+rand+".gif";
		 }else{
			 url += rand+".gif";
		 }
		return url;
	}
	@Override
	public UserResult register(UserDO user) {
		UserResult result = new UserResult(false);
		UserDO userDO =userDao.register(user);
		
		result.setUserDO(userDO);
		result.setSuccess(true);
		return result;
	}
	@Override
	public UserResult modify(UserDO user) { 
		UserResult result = new UserResult(false);
		if(user.getUserId() <= 0){
			result.setResultCode(ResultCode.ParamError);
		}else if(StringUtil.isBlank(user.getUserName())){
			result.setResultCode(ResultCode.UserNameIsNull);
		}
		
		UserDO userDO =userDao.modify(user);
		result.setUserDO(userDO);
		result.setSuccess(true);
		
		return result;
	}

}
