package com.kuaileren.dao;

import java.util.List;

import com.kuaileren.domain.CommentDO;


public interface CommentDao {
	
	public List<CommentDO> queryCommentList(CommentDO commentDO) ;

	public CommentDO queryCommentById(CommentDO commentDO);

	public CommentDO modifyCommentById(CommentDO commentDO);

	public CommentDO addComment(CommentDO commentDO);

	int getNextCommentFloor(int articleId);
}
