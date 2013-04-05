package com.kuaileren.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kuaileren.service.PPShopService;

public class AccessItemAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -689738328041269821L;
	private static final Log log = LogFactory.getLog(AccessItemAction.class);

	
	private PPShopService ppShopSeviceJob;
	private List<String> list = new ArrayList<String>();
	
	
	private String url ;

	public String access() {
		try {
			request = ServletActionContext.getRequest();

			if(StringUtils.isNotBlank(url)){
				list = ppShopSeviceJob.addUrlToList(url);
			}
			
			
			return SUCCESS;
			

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ERROR;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPpShopSeviceJob(PPShopService ppShopSeviceJob) {
		this.ppShopSeviceJob = ppShopSeviceJob;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	
}
