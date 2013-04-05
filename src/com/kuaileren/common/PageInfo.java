package com.kuaileren.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页mapping
 */
public class PageInfo{

	private static final long serialVersionUID = 7873852623469564487L;

	public long totalRows = 0;//总的记录数 如:共100条记录

	public List pageList = new ArrayList(0);

    public int startIndex = 0; //当前页面第一条记录数

    public int currentPage = 1; //当前页面 如:当前第1页

    public int totalPages = 1; //总的页面数 如:共10页

    public int perPageRows = 20;//每一页显示的记录数 (可以通过set方法来进行修改)
    
    public String pageUrl ="";

    public String html ; //分页查询按钮
    //是不是实现分页
    private boolean showPages = true;

    public int getCurrentPage() {
    	if(currentPage<=0) return 1;
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPerPageRows() {
    	if(perPageRows<=0) return 1;
        return perPageRows;
    }

    public void setPerPageRows(int perPageRows) {
        if(perPageRows>0)
            this.perPageRows = perPageRows;
    }

    public boolean isShowPages() {
        return showPages;
    }

    public void setShowPages(boolean showPages) {
        this.showPages = showPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
        //计算出现在的总的页数
        this.totalPages = (int) Math.ceil((this.totalRows - 1) / this.perPageRows) + 1;
        if (this.currentPage > this.totalPages) {
            this.currentPage = 1;
        }
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public int getStartIndex() {
        this.setStartIndex((currentPage-1)*perPageRows);
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	/**
     *
     * @return 分页查询按钮
     */
    public  String getHtml2(){
        if(!showPages)return "&nbsp;";
        StringBuffer string=new StringBuffer();
        string.append("<script>\n");
        string.append("function go(num){\n");
        string.append(" var pageform = window.document.getElementById('pageform');\n");
        string.append(" pageform.action=pageform.action+'&currentPage='+num;\n");
        string.append(" pageform.submit();\n");
        string.append("}\n");
        string.append("function goto(thisPage,totalPage){\n");
        string.append(" var gotoPageObject = window.document.getElementById('gotoPage');\n");
        string.append(" var gotoPage = gotoPageObject.value ;\n");
        string.append(" if(isNaN(gotoPage)){\n");
        string.append("     gotoPageObject.focus();\n") ;
        string.append("     alert('你输入的页码有误请重新输入!');\n");
        string.append("		gotoPageObject.value=1;\n");
        string.append("		return ; \n");
        string.append(" }\n");
        string.append(" go(gotoPage);\n");
        string.append("}\n");
        string.append("</script>\n");
        //当前为第一页
        string.append("每页记录");
        string.append("<input type='text' class='int' name='perPageRows' id='perPageRows' value='");
        string.append(perPageRows);
        string.append("' style='width:30px;'/>");
        string.append("条");
        if(currentPage==1){
            string.append("<input type='button' class='Normal' onMouseOver=\"this.className='Normalover'\"  onmouseout=\"this.className='Normal'\"  style='width:50px;' value='首  页' disabled/>");
            string.append("<input type='button' class='Normal' onMouseOver=\"this.className='Normalover'\"  onmouseout=\"this.className='Normal'\"  style='width:50px;' value='上一页' disabled/>");
            string.append("&nbsp;");
        }else{
            string.append("<input type='button' class='Normal' onMouseOver=\"this.className='Normalover'\"  onmouseout=\"this.className='Normal'\"  style='width:50px;' value='首  页' onClick='go(1);' />");
            string.append("<input type='button' class='Normal' onMouseOver=\"this.className='Normalover'\"  onmouseout=\"this.className='Normal'\"  style='width:50px;' value='上一页' onClick='go(");
            string.append(currentPage-1);
            string.append(")'/>&nbsp;");
        }
        //当前为最后一页
        
        
        if(currentPage==totalPages){
            string.append("<input type='button' class='Normal' onMouseOver=\"this.className='Normalover'\"  onmouseout=\"this.className='Normal'\"  style='width:50px;' value='下一页' disabled/>");
            string.append("<input type='button' class='Normal' onMouseOver=\"this.className='Normalover'\"  onmouseout=\"this.className='Normal'\"  style='width:50px;' value='尾  页' disabled/>");
        }else{
            string.append("<input type='button' class='Normal' onMouseOver=\"this.className='Normalover'\"  onmouseout=\"this.className='Normal'\"  style='width:50px;' value='下一页' onClick='go(");
            string.append(currentPage+1);
            string.append(")'/>");
            string.append("<input type='button' class='Normal' onMouseOver=\"this.className='Normalover'\"  onmouseout=\"this.className='Normal'\"  style='width:50px;' value='尾  页' onClick='go(");
            string.append(totalPages);
            string.append(")'/>&nbsp;");
        }
        string.append("&nbsp;");
        string.append("<input type='text' id='gotoPage' class='int' value='");
        string.append(currentPage);
        string.append("' style='width:30px;'/>&nbsp;&nbsp;<input type='button' class='Normal' onMouseOver=\"this.className='Normalover'\"  onmouseout=\"this.className='Normal'\"  style='width:30px;' value='GO' onClick='goto(");
        string.append(currentPage);
        string.append(",");
        string.append(totalPages);
        string.append(")'/>") ;
        string.append("&nbsp;当前第");
        string.append(currentPage);
        string.append("页/共");
        string.append(totalPages);
        string.append("页&nbsp;总共记录数");
        string.append(totalRows);
        string.append("条&nbsp;");
        setHtml(string.toString());
        return html;
    }
    
    
    
    public String getHtml(){
    	StringBuffer sb = new StringBuffer();
    	
    	int pageNo = currentPage;
    	int prePage = pageNo - 1;
    	
    	int nextPage = pageNo + 1;
    	int tPage = totalPages;
    		

    	if(pageNo > 1){
    		sb.append("<a href='javascript:void(0)' onclick='gopage("+prePage+")'>上一页</a>");
    	}
    		
    	int leftStart = 1;
    	int leftEnd = 1;
    	int mStart = pageNo - 2;
    	int mEnd = pageNo + 4;
    	int rStart = tPage;
    	int rEnd = tPage;
    	   
    	if (mStart <= leftEnd){
    	    	leftStart = 0;
    	    	leftEnd = 0;
    	    	mStart = 1;
    	}
    	    
    	if(mEnd >= rStart){
    	    	rStart = 0;
    	    	rEnd = 0;
    	    	mEnd = tPage;
       }
    	    
    	if (leftEnd >= leftStart)
    		for(int p=leftStart;p<=leftEnd;p++){
    			if(p != 0){
    				sb.append("<a href='javascript:void(0)' onclick='gopage("+p+")'>"+p+"</a>");
    			}
    		}
    	    	
    	if(mStart >(leftEnd +1)){
    		 sb.append("...");
    	 }
    	   
    	for(int p=mStart;p<=mEnd;p++){
    				if(p == pageNo){
    					sb.append("<b><a href='javascript:void(0)' onclick='gopage("+p+")' class='select'>"+p+"</a></b>");
    				}else if(p != 0){
    					sb.append("<a href='javascript:void(0)' onclick='gopage("+p+")'>"+p+"</a>");
    				}			 
    	}
    			
       if(rEnd >= rStart && rEnd > 0){
    			if(rStart > (mEnd+1)){
    				sb.append("...");
    			}
       		
       			for(int p=rStart;p<=rEnd;p++){
       				sb.append("<a href='javascript:void(0)' onclick='gopage("+p+")'>"+p+"</a>");
       			}
    	}       

       if (pageNo < tPage){
    	   	sb.append("<a href='javascript:void(0)' onclick='gopage("+nextPage+")'>下一页</a>");
       }
       
       sb.append("到第<input type='text' id='J_PageNum' maxlengh='4' size='2'>页&nbsp;");
       sb.append("<input type='button' onclick='goto()' id='J_JumpTo' value='确定'>");
       
       
       sb.append("\n<script>");
       sb.append("function gopage(num){");
       sb.append(" var pageform = window.document.getElementById('pageform');");
       sb.append(" pageform.action=pageform.action+'&pageNum='+num;");
       sb.append(" pageform.submit();");
       sb.append("}");
       sb.append("function goto(obj){");
       sb.append(" var gotoPageObject = window.document.getElementById('J_PageNum');");
       sb.append(" var gotoPage = gotoPageObject.value ;");
       sb.append(" if(isNaN(gotoPage)){");
       sb.append("     gotoPageObject.focus();") ;
       sb.append("		gotoPageObject.value=1;");
       sb.append("		return ;");
       sb.append(" }");
       sb.append(" gopage(gotoPage);");
       sb.append("}");
       sb.append("</script>\n");
    	
       setHtml(sb.toString());
       return html;
    }
    
    
    public void setHtml(String html) {
        this.html = html;
    }
}

