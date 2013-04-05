package com.kuaileren.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.kuaileren.domain.ArticleDO;
import com.kuaileren.domain.IndexConstants;
import com.kuaileren.domain.IndexSearchFilter;
import com.kuaileren.domain.SearchResultPO;
import com.kuaileren.util.DateUtil;
import com.kuaileren.util.DigitalUtil;

public class SearchServerImpl implements SearchServer
{
    private static Log log = LogFactory.getLog(SearchServerImpl.class);
    
    private Directory directory = null;
    private IndexReader reader = null;
    private IndexSearcher searcher = null;
    
    private String indexServerName;
    private String indexFileStorePath;
    
    private WriterServer writerServer;
    
    public void doInitAction() throws Exception {
        log.info("启动列表服务搜索类");
    }
    

    /*
     * 根据指定条件进行搜索
     * 返回结果总数和指定起始位置的结果列表
     */
    public SearchResultPO doSearchAction(IndexSearchFilter filter) throws Exception {
        Date startTime = new Date();
        
        
        SearchResultPO resultPO = new SearchResultPO();
        
        //根据传入参数构建查询条件
        Query mainQuery = buildSearchQuery(filter);

        int totalCount = 0;
        
        initResources();
        
        try {
            //根据最后修改时间排序
            Sort sort =  new Sort(new SortField[]{new SortField(IndexConstants.Field_modifyTime, SortField.LONG, true)});
            
            //得到当前要查找的记录总数,由于lucene不支持分页，所以必须从0开始查找到当前页的位置
            long queryCount = filter.getOffset() + filter.getLimit();
            if (queryCount <= 0) {
                queryCount = 10;
                log.warn("分页查询参数不合格(queryCount <= 0)，程序自动修正");
            }
            //搜索
            TopDocs hits = searcher.search(mainQuery, null, (int) queryCount, sort);
            
            //返回符合条件的记录总数
            totalCount = hits.totalHits;
            resultPO.setCount(totalCount);
            
            //返回符合条件的记录清单
            List<ArticleDO> resultList = new ArrayList<ArticleDO>();
            
            for (int i = (int) filter.getOffset(); i < hits.scoreDocs.length; i++) {
                //根据查找结果对象构建SPUBO对象
                if (i < 0) {
                    log.warn("分页查询参数不合格(Offset ="+filter.getOffset()+")，忽略结果");
                    continue;
                }
                ScoreDoc scoreDoc = hits.scoreDocs[i];
                Document document = searcher.doc(scoreDoc.doc);
                
                resultList.add(buildArticleDOFromDocument(document));
            
            }
            resultPO.setResultList(resultList);

            return resultPO;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            Date endTime = new Date();
            
            log.info("执行查询( "+mainQuery+" )耗时: " + (endTime.getTime() - startTime.getTime())+ " ms, 符合条件的记录数:" + totalCount);
            
            mainQuery = null;
        }
    }
    private Query buildSearchQuery(IndexSearchFilter filter) {

        boolean hasAnyCondition = false;
        BooleanQuery mainQuery = new BooleanQuery();
        
        if (StringUtils.isNotEmpty(filter.getContent())) {
            //关键词支持多个条件一起搜索，多个关键词之间是【与】的关系
            try {
                QueryParser parser = new QueryParser(Version.LUCENE_30, IndexConstants.Field_content, writerServer.getAnalyzer());
                parser.setDefaultOperator(Operator.AND);
                Query query = parser.parse(filter.getContent());
                mainQuery.add(query, Occur.MUST);
                hasAnyCondition=true;
                parser = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        //根据articleId进行搜索
        if (filter.getArticleId() > 0) {
            Query query = new TermQuery(new Term(IndexConstants.Field_articleId, String.valueOf(filter.getArticleId())));
            mainQuery.add(query, Occur.MUST);
            hasAnyCondition=true;
        } 
       
      //根据articleId进行搜索
        if (StringUtils.isNotEmpty(filter.getTitle())) {
            Query query = new TermQuery(new Term(IndexConstants.Field_title, filter.getTitle()));
            mainQuery.add(query, Occur.MUST);
            hasAnyCondition=true;
        } 
        
        //根据创建人名称搜索
        if (StringUtils.isNotEmpty(filter.getUserName())) {
            Query query = new TermQuery(new Term(IndexConstants.Field_userName, filter.getUserName()));
            mainQuery.add(query, Occur.MUST);
            hasAnyCondition=true;
        }
        

        if (hasAnyCondition) {
            return mainQuery;
        } else {
            return new MatchAllDocsQuery();
        }
    }
    
    //将索引文件的记录转化为SPUBO
    private ArticleDO buildArticleDOFromDocument(Document document) {
        
        ArticleDO articleDO = new ArticleDO();
        
        articleDO.setArticleId(DigitalUtil.paseInt(document.get(IndexConstants.Field_articleId)));
        articleDO.setTitle(document.get(IndexConstants.Field_title));
        
        articleDO.setUserName(document.get(IndexConstants.Field_userName));
        articleDO.setCreateTime(DateUtil.strParseToDate(document.get(IndexConstants.Field_createTime)));
        articleDO.setContent(document.get(IndexConstants.Field_content));
        
        return articleDO;
    }
 public static void main(String[] args) {

}
     
     public void initResources() throws Exception {
   
    		 log.info("索引文件中有新数据加入，加载更新的索引数据。");
  
        	 try {
	             if (searcher != null) {
	                 searcher.close();
	                 
	                 log.info("旧的searcher对象已经关闭。");
                 }
             } catch (Exception e) {
                 log.error("关闭旧的searcher对象失败", e);
             } finally {
            	 searcher = null;
             }
             
             try {
                 if (reader != null) {
                	 reader.close();
                	 
                     log.info("旧的reader对象已经关闭。");
                 }
             } catch (Exception e) {
                 log.error("关闭旧的reader对象失败", e);
             } finally {
            	 reader = null;
             }
  
             try {
                 if (directory != null) {
                     directory.close();
                     log.info("旧的directory对象已经关闭。");
                 }
             } catch (Exception e) {
            	 log.error("关闭旧的directory对象失败", e);
             } finally {
            	 directory = null;
             }
 
         if (directory == null) {
             log.info("创建directory对象");
             try {
                 File  dir = new File(indexFileStorePath);
                 directory = FSDirectory.open(dir);
             } catch (Exception e) {
                 
             }
         }
         if (reader == null) {
             log.info("创建reader对象");
             try {
                 reader = IndexReader.open(directory, true);
                
             } catch (Exception e) {
                 
                 log.error("IndexReader初始化失败" , e);
             }
         }
         if (searcher == null) {
        	 log.info("创建searcher对象");
             try {
                 searcher = new IndexSearcher(reader) ;
             } catch (Exception e) {

                 log.error("IndexSearcher初始化失败" , e);
             }
         }
         
     }

    public String getIndexServerName()
    {
        return indexServerName;
    }

    public void setIndexServerName(String indexServerName)
    {
        this.indexServerName = indexServerName;
    }

    public String getIndexFileStorePath()
    {
        return indexFileStorePath;
    }

    public void setIndexFileStorePath(String indexFileStorePath)
    {
        this.indexFileStorePath = indexFileStorePath;
    }

    public WriterServer getWriterServer()
    {
        return writerServer;
    }

    public void setWriterServer(WriterServer writerServer)
    {
        this.writerServer = writerServer;
    }
     
}
