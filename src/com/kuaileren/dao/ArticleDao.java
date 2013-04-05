package com.kuaileren.dao;

import java.util.List;

import com.kuaileren.domain.ArticleDO;
import com.kuaileren.domain.CountDO;
import com.kuaileren.domain.FavoriteDO;
import com.kuaileren.domain.KuaiLeDO;
import com.kuaileren.domain.VoteDO;


public interface ArticleDao {
	
	public List<ArticleDO> queryArticleList(ArticleDO articleDO) ;
	
	public long countArticle(ArticleDO articleDO) ;

	public ArticleDO queryArticleById(ArticleDO articleDO);

	public ArticleDO modifyArticleById(ArticleDO articleDO);
	
	public ArticleDO addArticle(ArticleDO articleDO);

	public ArticleDO queryArticleByArticleId(int articleId);

	public ArticleDO updateArticle(ArticleDO article);

	boolean isVoted(VoteDO vote);

	VoteDO addVote(VoteDO voteDO);

	public boolean isFavorite(FavoriteDO favorite);

	public FavoriteDO favorite(FavoriteDO favorite);

	public List<ArticleDO> getLastArticleList(ArticleDO article);

	List<KuaiLeDO> countArticle();
}
