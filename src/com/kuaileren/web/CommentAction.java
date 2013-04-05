package com.kuaileren.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kuaileren.common.CommentResult;
import com.kuaileren.common.ResultCode;
import com.kuaileren.domain.CommentDO;
import com.kuaileren.service.CommentService;
import com.kuaileren.util.DigitalUtil;
import com.kuaileren.util.StringUtil;

public class CommentAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -689738328041269821L;
	private static final Log log = LogFactory.getLog(CommentAction.class);

	private String resultCode = StringUtils.EMPTY;
	CommentDO commentDO = new CommentDO();
	
	/**
	 * 用户信息查询
	 * @Description :TODO
	 * @return String
	 */
	public String queryCommentList() throws Exception {
		init();
		commentDO.getPageInfo().setCurrentPage(DigitalUtil.paseInt(request.getParameter("currentPage")));
		commentDO.setArticleId(DigitalUtil.paseInt(request.getParameter("articleId")));
		
		CommentResult result  = commentService.queryCommentList(commentDO);
		if(result.isSuccess()){
			commentDOList = result.getCommentList();
		}
		return SUCCESS;
	}
	
	
	public String addComment() throws Exception {
		init();
		commentDO.setContent(StringUtil.UnicodeToGB(request.getParameter("content")));
		commentDO.setArticleId(DigitalUtil.paseInt(request.getParameter("articleId")));
		commentDO.setAnony(DigitalUtil.paseInt(request.getParameter("anony")));
		commentDO.setUserId(getUserId());
		commentDO.setUserName(getUserName());
		
		commentDO.setCreateTime(new Date());
		commentDO.setModifyTime(new Date());
		
		CommentResult result = commentService.addComment(commentDO);
		if(result.isSuccess()){
			commentDO = result.getCommentDO();
		}
		return SUCCESS;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
		
	}
	public String getResultCode() {
		return ResultCode.getResultCodeMessage(resultCode);
		
	}
	
	private CommentService commentService;
	private List<CommentDO> commentDOList;
	
	public List<CommentDO> getCommentDOList() {
		return commentDOList;
	}
	public void setCommentDOList(List<CommentDO> commentDOList) {
		this.commentDOList = commentDOList;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}


	public CommentDO getCommentDO() {
		return commentDO;
	}


	public void setCommentDO(CommentDO commentDO) {
		this.commentDO = commentDO;
	}
	
}
