package com.kuaileren.common;

import java.util.ArrayList;
import java.util.List;

import com.kuaileren.domain.ArticleDO;

public class ArticleResult extends BaseResult {

	private ArticleDO articleDO =null;
	private List<ArticleDO> articleList  = new ArrayList<ArticleDO>();
	
	public ArticleResult(boolean b) {
		super(b);
	}

	public ArticleDO getArticleDO() {
		return articleDO;
	}

	public void setArticleDO(ArticleDO articleDO) {
		this.articleDO = articleDO;
	}

	public List<ArticleDO> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleDO> articleList) {
		this.articleList = articleList;
	}

	
}
