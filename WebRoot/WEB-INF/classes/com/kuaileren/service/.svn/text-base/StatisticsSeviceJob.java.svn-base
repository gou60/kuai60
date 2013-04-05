package com.kuaileren.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kuaileren.common.ServiceException;
import com.kuaileren.domain.KuaiLeDO;
import com.kuaileren.util.ToolsUtil;

public class StatisticsSeviceJob {

	private ArticleService articleService;
	private static Log log = LogFactory.getLog(SearchServerImpl.class);
	
	public static List<KuaiLeDO> countUserList = new ArrayList<KuaiLeDO>();

	public void execute(){
		try {

			countUserList = articleService.countArticle();
			for(KuaiLeDO kuai:countUserList){
				log.info(kuai.toString());
			}
			
			log.info("Í³¼Æ½áÊø£º");
		} catch (ServiceException e) {
			log.error(e.getMessage(), e);
		}
	}


	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	
	
}
