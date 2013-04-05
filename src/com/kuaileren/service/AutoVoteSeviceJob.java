package com.kuaileren.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kuaileren.dao.ArticleDao;
import com.kuaileren.domain.ArticleDO;
import com.kuaileren.domain.UserDO;
import com.kuaileren.domain.VoteDO;
import com.kuaileren.util.ToolsUtil;

public class AutoVoteSeviceJob {

	private ArticleDao articleDao;
	private static Log log = LogFactory.getLog(SearchServerImpl.class);
	
	public static List<ArticleDO> acticleList = new ArrayList<ArticleDO>();
	int count =0;
	
	public void execute(){
		log.info("�Զ�ͶƱ��ʼ");
		try {
			ArticleDO article = new ArticleDO();
			article.getPageInfo().setCurrentPage(1);
			article.getPageInfo().setPerPageRows(500);
			acticleList = articleDao.queryArticleList(article);
			
			count =0;
			
			//int maxUserId= ToolsUtil.getMaxUserId();
			//System.out.println("==============="+maxUserId);
			
			for(ArticleDO acticle:acticleList){
				int userId = RandomUtils.nextInt(2000);
				
				log.info("userId:"+userId);
					acticle.setUserId(userId);
					UserDO user = ToolsUtil.userMap.get(userId);
					if(user!=null){
						acticle.setUserName(user.getUserName());
						
						if(count%10==0){
							acticle.setDisAgree(1);
						}else{
							acticle.setAgree(1);
						}
						
						log.info("�Զ�ͶƱ,acticleId:"+acticle.getArticleId()+",userId:"+userId);
						vote(acticle);
					}
				
			}
			
			log.info("�Զ�ͶƱ������");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}


	private void vote(ArticleDO article) {

		article.setCreateTime(new Date());
		article.setModifyTime(new Date());

		VoteDO vote = new VoteDO();
		vote.setArticleId(article.getArticleId());
		vote.setUserId(article.getUserId());
		vote.setVoteType(article.isVoteAgree()?1:-1); //Ͷ��Ʊ ͶƱ����Ϊ+1��
		vote.setVoteTime(new Date());
		
		//���Ƿ��Ѿ�Ͷ��Ʊ��ͨ��userId,articleId��
		//boolean isVoted = articleDao.isVoted(vote);
		//if(isVoted){
		//	log.info("�Ѿ�Ͷ��Ʊ��");
		//	return;
		//}
		//���ͶƱ��¼
		vote = articleDao.addVote(vote);

		articleDao.updateArticle(article);
		count++;
	}


	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	
}
