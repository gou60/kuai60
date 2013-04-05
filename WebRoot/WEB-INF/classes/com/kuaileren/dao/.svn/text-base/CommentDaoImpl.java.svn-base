package com.kuaileren.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.kuaileren.domain.CommentDO;

public class CommentDaoImpl extends BaseDao implements CommentDao {

	  private static final Logger log = Logger.getLogger(CommentDaoImpl.class);
	
	public List<CommentDO> queryCommentList(CommentDO comment) {

		@SuppressWarnings("unchecked")
		List<CommentDO> commentList = (List<CommentDO>)queryForList("comment.queryCommentList",comment);
		return commentList;
	}

	@Override
	public CommentDO queryCommentById(CommentDO comment) {
		CommentDO commentDO = (CommentDO)queryForObject("comment.queryComment",comment);
		return commentDO;
	}
	

	@Override
	public CommentDO modifyCommentById(CommentDO comment) {
		 update("comment.updateComment", comment);
		return comment;
	}

	@Override
	public CommentDO addComment(CommentDO commentDO) {
		 int commentId = (Integer)insert("comment.insertComment", commentDO);
		 commentDO.setCommentId(commentId);
			return commentDO;
	}
	
	@Override
	public int getNextCommentFloor(int articleId) {
		return (Integer)queryForObject("comment.getNextCommentFloor", articleId);
	}

}
