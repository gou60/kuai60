package com.kuaileren.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kuaileren.util.FileUtil;
import com.kuaileren.util.HtmlParseUtil;
import com.kuaileren.util.HttpClientUtil;

public class PPShopService {
	 private static Log log = LogFactory.getLog(PPShopService.class);
	 
	public static void main(String[] args){
		new PPShopService().execute();
	}
	
	public void execute(){
		
		List<String> itemurllist = readShopItemList();

		try {
		for(String url :itemurllist){
			long rand = Math.round(Math.random());
			if(rand==1) {
				accessItemDetail(url);
			}
			
			Thread.sleep(300);
		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void accessItemDetail(String url) {
			HttpClientUtil.sendHttpClient(url);
	}
	
	
	public static List<String> readShopItemList() {
		List<String> list = new ArrayList<String>();
		String itemStr = "";
		String itemUrl = "";
		try{
		
		int i=1;
			while(true){
				itemUrl = "http://shop.paipai.com/1304310326/0-0000000000-0-9-"+i+"-20-1-0-0-0//index.shtml";
				i++;
				itemStr = HttpClientUtil.sendHttpClient(itemUrl);
				List<String> tmplist = HtmlParseUtil.parseShopItemUrl(itemStr);
				if(tmplist==null || tmplist.isEmpty()) break;
				
				list.addAll(tmplist);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	
	public static  List<String> list = new ArrayList<String>();
	
	
	public List<String> addUrlToList(String url) {
		if(!list.contains(url)){
			list.add(url);
			FileUtil.write(FileUtil.url_file, url);
		}
		return list;
	}
	
	public void accessList() {
		if(list == null || list.isEmpty()){
			readList();
		}
		
		for(String url :list){
			if(StringUtils.isNotBlank(url)){
				accessItemDetail(url);
				log.warn("access ...>>>> "+url);
				
				sleep(300);
			}
		}
	}

	private void readList() {
		list = FileUtil.readIpList(FileUtil.url_file);
		
	}
	
	public void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			//System.out.println("sleep ..."+time);
		}
	}
	

}
