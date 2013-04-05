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
        log.info("�����б����������");
    }
    

    /*
     * ����ָ��������������
     * ���ؽ��������ָ����ʼλ�õĽ���б�
     */
    public SearchResultPO doSearchAction(IndexSearchFilter filter) throws Exception {
        Date startTime = new Date();
        
        
        SearchResultPO resultPO = new SearchResultPO();
        
        //���ݴ������������ѯ����
        Query mainQuery = buildSearchQuery(filter);

        int totalCount = 0;
        
        initResources();
        
        try {
            //��������޸�ʱ������
            Sort sort =  new Sort(new SortField[]{new SortField(IndexConstants.Field_modifyTime, SortField.LONG, true)});
            
            //�õ���ǰҪ���ҵļ�¼����,����lucene��֧�ַ�ҳ�����Ա����0��ʼ���ҵ���ǰҳ��λ��
            long queryCount = filter.getOffset() + filter.getLimit();
            if (queryCount <= 0) {
                queryCount = 10;
                log.warn("��ҳ��ѯ�������ϸ�(queryCount <= 0)�������Զ�����");
            }
            //����
            TopDocs hits = searcher.search(mainQuery, null, (int) queryCount, sort);
            
            //���ط��������ļ�¼����
            totalCount = hits.totalHits;
            resultPO.setCount(totalCount);
            
            //���ط��������ļ�¼�嵥
            List<ArticleDO> resultList = new ArrayList<ArticleDO>();
            
            for (int i = (int) filter.getOffset(); i < hits.scoreDocs.length; i++) {
                //���ݲ��ҽ�����󹹽�SPUBO����
                if (i < 0) {
                    log.warn("��ҳ��ѯ�������ϸ�(Offset ="+filter.getOffset()+")�����Խ��");
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
            
            log.info("ִ�в�ѯ( "+mainQuery+" )��ʱ: " + (endTime.getTime() - startTime.getTime())+ " ms, ���������ļ�¼��:" + totalCount);
            
            mainQuery = null;
        }
    }
    private Query buildSearchQuery(IndexSearchFilter filter) {

        boolean hasAnyCondition = false;
        BooleanQuery mainQuery = new BooleanQuery();
        
        if (StringUtils.isNotEmpty(filter.getContent())) {
            //�ؼ���֧�ֶ������һ������������ؼ���֮���ǡ��롿�Ĺ�ϵ
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
        
        //����articleId��������
        if (filter.getArticleId() > 0) {
            Query query = new TermQuery(new Term(IndexConstants.Field_articleId, String.valueOf(filter.getArticleId())));
            mainQuery.add(query, Occur.MUST);
            hasAnyCondition=true;
        } 
       
      //����articleId��������
        if (StringUtils.isNotEmpty(filter.getTitle())) {
            Query query = new TermQuery(new Term(IndexConstants.Field_title, filter.getTitle()));
            mainQuery.add(query, Occur.MUST);
            hasAnyCondition=true;
        } 
        
        //���ݴ�������������
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
    
    //�������ļ��ļ�¼ת��ΪSPUBO
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
   
    		 log.info("�����ļ����������ݼ��룬���ظ��µ��������ݡ�");
  
        	 try {
	             if (searcher != null) {
	                 searcher.close();
	                 
	                 log.info("�ɵ�searcher�����Ѿ��رա�");
                 }
             } catch (Exception e) {
                 log.error("�رվɵ�searcher����ʧ��", e);
             } finally {
            	 searcher = null;
             }
             
             try {
                 if (reader != null) {
                	 reader.close();
                	 
                     log.info("�ɵ�reader�����Ѿ��رա�");
                 }
             } catch (Exception e) {
                 log.error("�رվɵ�reader����ʧ��", e);
             } finally {
            	 reader = null;
             }
  
             try {
                 if (directory != null) {
                     directory.close();
                     log.info("�ɵ�directory�����Ѿ��رա�");
                 }
             } catch (Exception e) {
            	 log.error("�رվɵ�directory����ʧ��", e);
             } finally {
            	 directory = null;
             }
 
         if (directory == null) {
             log.info("����directory����");
             try {
                 File  dir = new File(indexFileStorePath);
                 directory = FSDirectory.open(dir);
             } catch (Exception e) {
                 
             }
         }
         if (reader == null) {
             log.info("����reader����");
             try {
                 reader = IndexReader.open(directory, true);
                
             } catch (Exception e) {
                 
                 log.error("IndexReader��ʼ��ʧ��" , e);
             }
         }
         if (searcher == null) {
        	 log.info("����searcher����");
             try {
                 searcher = new IndexSearcher(reader) ;
             } catch (Exception e) {

                 log.error("IndexSearcher��ʼ��ʧ��" , e);
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
