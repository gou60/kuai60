package com.kuaileren.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kuaileren.common.SessionKeeper;
import com.kuaileren.common.UserResult;
import com.kuaileren.domain.UserDO;
import com.kuaileren.service.UserService;
import com.kuaileren.util.FileUtil;
import com.kuaileren.util.StringUtil;
import com.kuaileren.util.UniqID;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2440681634659781027L;
	ServletContext context = null;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	HttpSession session = null;
	
	protected UserService userService;
    
	@Override
	public String doDefault() throws Exception {
		return super.doDefault();
	}


	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	public void init(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		context = ServletActionContext.getServletContext();
		session = request.getSession();
	}
	
	public int getUserId() {
		UserDO user =(UserDO)session.getAttribute("userDO");
		if(user==null)return 0;
		return user.getUserId();
	}
	
	public String getUserName() {
		UserDO user =(UserDO)session.getAttribute("userDO");
		if(user==null)return "";
		
		return user.getUserName();
	}

	
	
	@Override
	public void validate() {
		init();
		String trackId =null;
		
		trackId = getCookieValueByName(SessionKeeper.ATTRIBUTE_TRACK_ID);
		
		if (StringUtil.isBlank(trackId)) {
			trackId = UniqID.getInstance().getUniqIDHash();
			Cookie cookie = new Cookie(SessionKeeper.ATTRIBUTE_TRACK_ID, trackId);
			cookie.setMaxAge(1000000000);
			response.addCookie(cookie);
		}
		
		getUserInfo(trackId);
		super.validate();
		
	}
	
	
	private void getUserInfo(String trackId) {
		if(!check()){
			return ;
		}
		
		UserDO sessionUser =(UserDO)session.getAttribute("userDO");
		if(sessionUser!=null) return ;
		
		UserDO user = new UserDO();
		user.setTrackId(trackId);
		user.setUserName(trackId);
		user.setPassword(trackId);  
		user.setCreateTime(new Date());
		user.setModifyTime(new Date());
		user.setRemark(request.getRemoteAddr());
		UserResult result = userService.login(user);
		session.setAttribute("userDO",result.getUserDO());
	}


	private boolean check() {
		try{
		String ip = request.getRemoteAddr();
		
		List<String> list = FileUtil.readIpList(context.getRealPath("/WEB-INF")+"/ip_list.txt");
		for(String line:list){
			if(ip.equals(line)){
				System.out.println(line);
				return false;//在黑名单中
			}
			
			if(ip.startsWith(line)){
				return false;//在网段中
			}
		}
		
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
		return true;
	}


	public String getCookieValueByName(String name){
		Cookie cookie = getCookieByName(name);
		if(cookie !=null && StringUtils.isNotBlank(cookie.getValue())){
			return cookie.getValue();
		}else{
			return null;
		}
	}
	
	public Cookie getCookieByName(String name){
		    Map<String,Cookie> cookieMap = ReadCookieMap();
		    if(cookieMap.containsKey(name)){
		        Cookie cookie = (Cookie)cookieMap.get(name);
		        return cookie;
		    }else{
		        return null;
		    }  
		}

	private  Map<String,Cookie> ReadCookieMap(){ 
		    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		    Cookie[] cookies = request.getCookies();
		    if(null!=cookies){
		        for(Cookie cookie : cookies){
		            cookieMap.put(cookie.getName(), cookie);
		        }
		    }
		    return cookieMap;
		}

	
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
  
}
