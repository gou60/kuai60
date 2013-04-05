package com.kuaileren.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.kuaileren.domain.ArticleDO;
import com.kuaileren.domain.CountDO;
import com.kuaileren.domain.FavoriteDO;
import com.kuaileren.domain.KuaiLeDO;
import com.kuaileren.domain.VoteDO;

public class ArticleDaoImpl extends BaseDao implements ArticleDao {

	  private static final Logger log = Logger.getLogger(ArticleDaoImpl.class);
	
	public List<ArticleDO> queryArticleList(ArticleDO article) {

		@SuppressWarnings("unchecked")
		List<ArticleDO> articleList = (List<ArticleDO>)queryForList("article.queryArticleList",article,
				article.getPageInfo().getStartIndex(),article.getPageInfo().getPerPageRows());
		return articleList;
	}

	@Override
	public ArticleDO queryArticleById(ArticleDO article) {
		ArticleDO articleDO = (ArticleDO)queryForObject("article.queryArticle",article);
		return articleDO;
	}
	

	@Override
	public ArticleDO modifyArticleById(ArticleDO articleDO) {
		int count = update("article.updateArticle", articleDO);
		return articleDO;
	}

	@Override
	public ArticleDO addArticle(ArticleDO articleDO) {
		int articleId = (Integer)insert("article.insertArticle", articleDO);
		articleDO.setArticleId(articleId);
		return articleDO;
	}

	@Override
	public long countArticle(ArticleDO article) {
		long count = (Long)queryForObject("article.countArticle",article);
		return count;
	}

	@Override
	public ArticleDO queryArticleByArticleId(int articleId) {
		return  (ArticleDO)queryForObject("article.queryArticleByArticleId",articleId);
	}

	@Override
	public ArticleDO updateArticle(ArticleDO article) {
		long count = update("article.updateArticle", article);
		 return article;
	}
	
	@Override
	public VoteDO addVote(VoteDO voteDO) {
		int voteId = (Integer)insert("vote.insertVote", voteDO);
		voteDO.setVoteId(voteId);
		 return voteDO;
	}

	@Override
	public boolean isVoted(VoteDO vote) {
		int voted = (Integer)queryForObject("vote.isVoted",vote);
		
		if(voted > 0){
			return true;//已经投过票了
		}
		return false;
	}

	@Override
	public boolean isFavorite(FavoriteDO favorite) {
		int voted = (Integer)queryForObject("favorite.isFavorite",favorite);
		
		if(voted > 0){
			return true;//已经收藏过了
		}
		return false;
	}

	@Override
	public FavoriteDO favorite(FavoriteDO favorite) {
		int favoriteId =(Integer)insert("favorite.insertFavorite", favorite);
		favorite.setFavoriteId(favoriteId);
		 return favorite;
	}

	@Override
	public List<ArticleDO> getLastArticleList(ArticleDO article) {
		@SuppressWarnings("unchecked")
		List<ArticleDO> articleList = (List<ArticleDO>)queryForList("article.getLastArticleList",article,
				1,8);
		return articleList;
	}
	@Override
	public List<KuaiLeDO> countArticle(){
		@SuppressWarnings("unchecked")
		List<KuaiLeDO> countList = (List<KuaiLeDO>)queryForList("article.statisticsArticle",null,1,8);
		return countList;
		
	}

}
