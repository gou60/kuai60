package com.kuaileren.common;

import org.apache.commons.lang.StringUtils;

public class ServiceException extends Exception {

	
	private String resultCode = StringUtils.EMPTY;
	
	public ServiceException(){
		
	}
	
	public ServiceException(String msg){
		super(msg);
	}
	
	public ServiceException(String msg,String resultCode){
		super(msg);
		this.resultCode = resultCode;
	}
	
	public ServiceException(Throwable cause , String resultCode){
		super(cause);
		this.resultCode = resultCode ;
	}
	
	public ServiceException(String msg ,Throwable cause){
		super(msg ,cause);
	}
	
	public ServiceException(Throwable cause){
		super(cause);
	}
	
	public String getResultCode(){
		return resultCode;
	}
	
	public void setResultCode(String resultCode){
		this.resultCode = resultCode ;
	}
}
