package com.kuaileren.service;

import java.util.List;

import com.kuaileren.common.ArticleResult;
import com.kuaileren.common.ServiceException;
import com.kuaileren.domain.ArticleDO;
import com.kuaileren.domain.CountDO;
import com.kuaileren.domain.KuaiLeDO;

public interface ArticleService {

	public List<ArticleDO> queryArticleList(ArticleDO article) throws ServiceException;

	public ArticleResult queryArticleById(ArticleDO article)throws ServiceException;

	public ArticleResult modifyArticleById(ArticleDO article)throws ServiceException;
	
	public ArticleResult addArticle(ArticleDO article)throws ServiceException;

	public ArticleDO queryArticleByArticleId(int articleId)throws ServiceException;

	ArticleResult updateArticle(ArticleDO article)throws ServiceException;

	public ArticleResult favorite(ArticleDO article)throws ServiceException;

	public ArticleResult articleVote(ArticleDO article)throws ServiceException;

	public List<ArticleDO> getLastArticleList(ArticleDO article)throws ServiceException;
	
	public long getArticleCount(ArticleDO article)throws ServiceException;

	List<KuaiLeDO> countArticle() throws ServiceException;

}
