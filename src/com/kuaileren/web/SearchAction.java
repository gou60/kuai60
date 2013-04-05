package com.kuaileren.web;

import java.util.List;

import com.kuaileren.domain.ArticleDO;
import com.kuaileren.domain.IndexSearchFilter;
import com.kuaileren.domain.SearchResultPO;
import com.kuaileren.service.SearchServer;
import com.kuaileren.util.StringUtil;

public class SearchAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4613025207724831983L;
	private int resultCode;
    private SearchServer searchServer;
    private IndexSearchFilter filter = new IndexSearchFilter();
    SearchResultPO result;
    private String searchText;
    private String searchItem;

    public String search() throws Exception {
    	
        try {
        	init();
        	filter.setTitle(getSearch("title"));
        	filter.setUserName(getSearch("userName"));
        	filter.setContent(getSearch("content"));

        	result = searchServer.doSearchAction(filter);

        	articleList = result.getResultList();
   
            
        } catch (Exception e) {
        	e.printStackTrace();
        	resultCode=1;
        }

        return SUCCESS;

    }
    
	private String getSearch(String item) {
		if(StringUtil.isNotBlank(searchItem)){
			if(searchItem.equals(item)){
				return searchText;
			}
		}
		return null;
	}

	public IndexSearchFilter getFilter() {
		return filter;
	}
	public void setFilter(IndexSearchFilter filter) {
		this.filter = filter;
	}
	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public SearchServer getSearchServer() {
		return searchServer;
	}

	public void setSearchServer(SearchServer searchServer) {
		this.searchServer = searchServer;
	}

	public SearchResultPO getResult() {
		return result;
	}

	public void setResult(SearchResultPO result) {
		this.result = result;
	}
     private List<ArticleDO> articleList;
	
	public List<ArticleDO> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<ArticleDO> articleList) {
		this.articleList = articleList;
	}

	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
	}
}
