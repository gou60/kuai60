package com.kuaileren.service;

import java.util.Date;
import java.util.List;

import com.kuaileren.common.CommentResult;
import com.kuaileren.common.ServiceException;
import com.kuaileren.dao.CommentDao;
import com.kuaileren.domain.ArticleDO;
import com.kuaileren.domain.CommentDO;

public class CommentServiceImpl implements CommentService {

	public CommentResult queryCommentList(CommentDO comment) {
		CommentResult result = new CommentResult(false);
		List<CommentDO> commentList = commentDao.queryCommentList(comment);

		result.setCommentList(commentList);
		result.setSuccess(true);
		return result;
	}


	@Override
	public CommentResult modifyCommentById(CommentDO comment) {
		CommentResult result = new CommentResult(false);

		CommentDO commentDO = commentDao.modifyCommentById(comment);
		result.setCommentDO(commentDO);
		result.setSuccess(true);

		return result;
	}

	@Override
	public CommentResult queryCommentById(CommentDO comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentResult addComment(CommentDO commentDO) throws ServiceException {
		CommentResult result = new CommentResult(false);

		int floor = commentDao.getNextCommentFloor(commentDO.getArticleId());
		commentDO.setFloor(floor);
		commentDO = commentDao.addComment(commentDO);
		ArticleDO article = new ArticleDO();
		
		article.setUserId(10001);
		article.setArticleId(commentDO.getArticleId());
		article.setModifyTime(new Date());
		article.setCommentCount(1);//ÆÀÂÛ¼Ó1
		articleService.updateArticle(article);
		
		result.setCommentDO(commentDO);
		result.setSuccess(true);

		return result;
	}
	
	
	private CommentDao commentDao;
	private ArticleService articleService;

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

}
