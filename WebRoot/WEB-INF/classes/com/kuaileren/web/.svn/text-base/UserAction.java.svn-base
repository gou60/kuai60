package com.kuaileren.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kuaileren.common.ResultCode;
import com.kuaileren.common.UserResult;
import com.kuaileren.domain.UserDO;
import com.kuaileren.service.UserService;

public class UserAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -689738328041269821L;
	private static final Log log = LogFactory.getLog(UserAction.class);
	UserDO user = new UserDO();
	private String resultCode = StringUtils.EMPTY;
	UserResult reult = new UserResult(true);
	private String LOGIN = "login";
	private String redirectUrl;
	/**
	 * 用户信息查询
	 * @Description :TODO
	 * @return String
	 */
	public String queryUserList() throws Exception {
		request = ServletActionContext.getRequest();
		reult =  userService.queryUserList(user);
		
		userList = reult.getUserList();
		setResultCode(reult.getResultCode());
		return SUCCESS;
	}
	
	/**
	 * 用户修改用户信息
	 * @Title:modify
	 * @Description :TODO
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public String modify() throws Exception {
		reult = userService.modify(user);
		setSession(reult.getUserDO());
		
		setResultCode(reult.getResultCode());
		return SUCCESS;
	}
	
	/**
	 * 登陆
	 * @Title:login
	 * @Description :TODO
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String doLogin(){
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setUserId(getUserId());
		user.setCreateTime(new Date());
		user.setModifyTime(new Date());
		user.setRemark(request.getRemoteAddr());
		reult  = userService.login(user);

		if(reult.isSuccess()){
			setSession(reult.getUserDO());
			return SUCCESS;
		}else{
			return LOGIN;
		}
		
	}
	
	
	public String login(){
		init();
		return SUCCESS;
	}

	/**
	 * 退出
	 * @Title:logout
	 * @Description :TODO
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String logout(){
		init();
		request.getSession().removeAttribute("userDO");
		request.getSession().invalidate();
		 
		return SUCCESS;
	}

	public String kuaileCenter(){
		init();
		 
		return SUCCESS;
	}
	
	
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
		
	}
	public String getResultCode() {
		return ResultCode.getResultCodeMessage(resultCode);
		
	}
	

	private void setSession(UserDO userDO) {
		session.setAttribute("userDO", userDO);
	}
	
	private List<UserDO> userList;
	
	public List<UserDO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserDO> userList) {
		this.userList = userList;
	}

	public UserDO getUser() {
		return user;
	}

	public void setUser(UserDO user) {
		this.user = user;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public UserResult getReult() {
		return reult;
	}

	public void setReult(UserResult reult) {
		this.reult = reult;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	
}
