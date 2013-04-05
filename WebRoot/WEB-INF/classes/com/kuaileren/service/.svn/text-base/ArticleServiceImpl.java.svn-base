package com.kuaileren.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kuaileren.common.ArticleResult;
import com.kuaileren.common.ServiceException;
import com.kuaileren.dao.ArticleDao;
import com.kuaileren.domain.ArticleDO;
import com.kuaileren.domain.FavoriteDO;
import com.kuaileren.domain.KuaiLeDO;
import com.kuaileren.domain.VoteDO;

public class ArticleServiceImpl implements ArticleService {

	private static final Log log = LogFactory.getLog(ArticleServiceImpl.class);
	
	@Override
	public List<ArticleDO>  queryArticleList(ArticleDO articleDO)throws ServiceException {
		
		long totleRows = articleDao.countArticle(articleDO);
		articleDO.getPageInfo().setTotalRows(totleRows);
		
		List<ArticleDO> articleList = articleDao.queryArticleList(articleDO);
		
		return articleList;
	}

	private ArticleDao articleDao;
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	
	@Override
	public ArticleResult queryArticleById(ArticleDO article) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArticleResult modifyArticleById(ArticleDO article) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArticleResult addArticle(ArticleDO article) throws ServiceException{
		ArticleResult result = new ArticleResult(false);
		article = articleDao.addArticle(article);
		
		result.setArticleDO(article);
		result.setSuccess(true);
		return result;
	}


	@Override
	public ArticleDO queryArticleByArticleId(int articleId) throws ServiceException{
		ArticleDO articleDO = articleDao.queryArticleByArticleId(articleId);
		return articleDO;
	}
	
	@Override
	public ArticleResult updateArticle(ArticleDO article) throws ServiceException{
		ArticleResult result = new ArticleResult(false);
		//更新文章投票数
		ArticleDO articleDO = articleDao.updateArticle(article);
		
		result.setArticleDO(articleDO);
		result.setSuccess(true);
		return result;
	}


	@Override
	public ArticleResult favorite(ArticleDO article) throws ServiceException{
		ArticleResult result = new ArticleResult(false);
		
		FavoriteDO favorite = new FavoriteDO();
		favorite.setArticleId(article.getArticleId());
		favorite.setUserId(article.getUserId());
		favorite.setStatus(0);
		favorite.setFavoriteTime(new Date());
		
		//查是否已经投过票（通过userId,articleId）
		boolean isFavorited = articleDao.isFavorite(favorite);
		if(isFavorited){
			result.setErrMsg("已经收藏过了");
			return result;
		}
		//添加投票记录
		favorite = articleDao.favorite(favorite);
		
		//更新文章投票数
		ArticleDO articleDO = articleDao.updateArticle(article);
		
		result.setArticleDO(articleDO);
		result.setSuccess(true);
		return result;
	}


	@Override
	public ArticleResult articleVote(ArticleDO article) throws ServiceException {
		ArticleResult result = new ArticleResult(false);
		VoteDO vote = new VoteDO();
		vote.setArticleId(article.getArticleId());
		vote.setUserId(article.getUserId());
		vote.setVoteType(article.isVoteAgree()?1:-1); //投顶票 投票类型为+1分
		vote.setVoteTime(new Date());
		
		//查是否已经投过票（通过userId,articleId）
		boolean isVoted = articleDao.isVoted(vote);
		if(isVoted){
			result.setErrMsg("已经投过票了");
			return result;
		}
		//添加投票记录
		vote = articleDao.addVote(vote);

		updateArticle(article);
		
		result.setArticleDO(article);
		result.setSuccess(true);
		return result;
	}


	@Override
	public List<ArticleDO> getLastArticleList(ArticleDO article) throws ServiceException{
		return articleDao.getLastArticleList(article);
	}


	@Override
	public long getArticleCount(ArticleDO article) throws ServiceException{
		long totleRows = articleDao.countArticle(article);
		return totleRows;
	}

	@Override
	public List<KuaiLeDO> countArticle() throws ServiceException{
		List<KuaiLeDO> countList = articleDao.countArticle();
		return countList;
	}
}
