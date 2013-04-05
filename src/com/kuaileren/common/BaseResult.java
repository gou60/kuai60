package com.kuaileren.common;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description TODO
 * @author lubang
 * @date 2011-7-14
 */
public class BaseResult {

	
	private boolean success = false;
	
	private String resultCode = StringUtils.EMPTY;
	
	private String errMsg = null;
	
	
	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public BaseResult(boolean b){
		success = b;
	}
	
	public boolean isSuccess(){
		return success;
	}
	
	public void setSuccess(boolean success){
		this.success = success;
	}
	
	public void setResultCode(String resultCode){
		this.resultCode = resultCode;
	}
	
	public String getResultCode(){
		return resultCode;
	}
	
	public String toString(){
		return "[resultCode:"+resultCode+",success:"+success+",errMsg:"+errMsg+"]";
	}
}
