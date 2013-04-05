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
		log.info("自动投票开始");
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
						
						log.info("自动投票,acticleId:"+acticle.getArticleId()+",userId:"+userId);
						vote(acticle);
					}
				
			}
			
			log.info("自动投票结束：");
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
		vote.setVoteType(article.isVoteAgree()?1:-1); //投顶票 投票类型为+1分
		vote.setVoteTime(new Date());
		
		//查是否已经投过票（通过userId,articleId）
		//boolean isVoted = articleDao.isVoted(vote);
		//if(isVoted){
		//	log.info("已经投过票了");
		//	return;
		//}
		//添加投票记录
		vote = articleDao.addVote(vote);

		articleDao.updateArticle(article);
		count++;
	}


	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	
}
