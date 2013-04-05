package com.kuaileren.common;

import java.util.HashMap;
import java.util.Map;

public class ResultCode {

	public static final String GeneralError = "GeneralError";
	public static final String ParamError = "ParamError";
	public static final String UserNameIsNull = "UserNameIsNull";
	
	
	public static Map<String,String> map = new HashMap<String,String>();
	
	
	static {
		
		map.put(GeneralError, "系统错误");
		map.put(ParamError, "参数错误");
		map.put(UserNameIsNull, "用户名不能为空");
	}
	
	public static String getResultCodeMessage(String resultCode){
		return map.get(resultCode);
	}
}
