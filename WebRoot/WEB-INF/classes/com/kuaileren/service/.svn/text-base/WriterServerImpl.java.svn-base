package com.kuaileren.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.kuaileren.common.ServiceException;
import com.kuaileren.domain.ArticleDO;
import com.kuaileren.domain.IndexConstants;

public class WriterServerImpl  implements WriterServer {
    private static Log log = LogFactory.getLog(WriterServerImpl.class);
    
    private Directory directory = null;
    private IndexWriter writer = null;
    
    private String indexFileStorePath;
    
    private int pageSize = 500;
    
    private ArticleService articleService;
    
    private static boolean indexActionIsRunning = false;
    
    private Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
 
    private static List<ArticleDO> SPUCacheList = new ArrayList<ArticleDO>();
    
    public WriterServerImpl() {

        //扫描SPUCacheList对象是否有MQ数据待加入索引
        IndexFromMQThread thread = new IndexFromMQThread();
        thread.start();

        
    }
    public Analyzer getAnalyzer() {
        return analyzer;
    }
    
    public void updateFromDatabase()
    {
        ArticleDO articleDO = new ArticleDO();
        refreshIndex(articleDO);
        
    }
    //接收到消息是先放到缓存中，然后批量加入索引
    public  void updateFromMQ(ArticleDO articleDO) {
        SPUCacheList.add(articleDO);
    }
    private synchronized  void refreshIndex(ArticleDO articleDO) {
        //如果正在初始化，则加锁。
        if (!indexActionIsRunning) {
            try {
                
                indexActionIsRunning = true;
                doIndexAction(articleDO);
            }catch(Exception e){
            	log.error(e.getMessage(), e);
            
        }finally {
                indexActionIsRunning = false;
            }
        } else {
            log.info("正在重建，忽略此命令。");
        }
    }

  //扫描SPUCacheList对象是否有MQ数据待加入索引
    private class IndexFromMQThread extends Thread {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    
                }
                //在没有初始化的情况下，从缓存中读取MQ数据加入索引
                if (!indexActionIsRunning && SPUCacheList.size() > 0) {
                    
                    log.debug("从缓存中读取MQ数据加入索引,size=" + SPUCacheList.size());
                    
                    try {
                        
                        indexActionIsRunning = true;
                        
                        List<ArticleDO> articleList = new ArrayList<ArticleDO>();
                        while (SPUCacheList.size() > 0) {
                        	ArticleDO articleDO = SPUCacheList.get(0);
                        	articleList.add(articleDO);
                            SPUCacheList.remove(0);
                        }
                        //批量加入索引
                        updateIndexBatch(0, articleList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        indexActionIsRunning = false;
                    }
                }
                
            }
        }
    }
    private synchronized void doIndexAction(ArticleDO articleDO)throws ServiceException{
  
            //设置一批处理多少个SPU
            articleDO.getPageInfo().setPerPageRows(pageSize);
 
                
                //取该分表下符合条件的SPU总数
                long dataCount = articleService.getArticleCount(articleDO);
                int startIndex = 0;
               
                //分页处理
                if (true) {
                	articleDO.getPageInfo().setStartIndex(startIndex);
                    
                    Date startTime = new Date();
                    
                    List<ArticleDO> articleList = articleService.queryArticleList(articleDO);
                    if (articleList.size() == 0) {
                       // break;
                    }
                    Date endTime = new Date();
                    
                    //得到当前页面的SPU列表，并添加到索引库中
                    updateIndexBatch(0, articleList);
                    
                    Date indexTime = new Date();
                    log.info("从数据库中查询( "+articleList.size()+" 条("+startIndex + "/" + dataCount + ")记录耗时: " + (endTime.getTime() - startTime.getTime())  + " ms, 添加索引耗时: " + (indexTime.getTime() - endTime.getTime())  + " ms");
                    
                    //下一页
                    startIndex += pageSize;
                    
                    
                    //休眠
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        
                    }
                    
                }
           
        
        
        log.info("初始化完毕!");
    }
    //批量加入到索引文件中
    private synchronized void updateIndexBatch(long creatorUIN, List<ArticleDO> articleList) {
        
        
        //先删除索引文件中可能存在相同的SPU
        try {

            for (ArticleDO articleDO:articleList) {
                writer.deleteDocuments(new Term(IndexConstants.Field_articleId, String.valueOf(articleDO.getArticleId())));
                log.info("article:" + articleDO.getArticleId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //添加SPU数据到索引文件中
        try {
            
            for (ArticleDO article:articleList) {
                Document document = createIndexDocument(article);
                writer.addDocument(document);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	try {
				writer.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
        }
    }

    
    //【产品名称】和【关键属性】作为【关键词】字段加入到索引文件中
    private String createSearchKeyword(String productName) {
        String searchKeywords = new StringBuffer()
            .append(productName)
            .append(" ").toString();

        return searchKeywords;
    }

    
    //将SPU数据构建为lucene识别的Document对象
    int index=1;
    private Document createIndexDocument(ArticleDO article) {
        Document document = new Document();
        
        String searchKeywords = createSearchKeyword(article.getTitle());
       // document.add(new Field(IndexConstants.Field_Search_Keyword,searchKeywords, Field.Store.NO, Field.Index.ANALYZED));
        document.add(new Field(IndexConstants.Field_articleId,String.valueOf(article.getArticleId()), Field.Store.YES, Field.Index.NOT_ANALYZED));
        document.add(new Field(IndexConstants.Field_title,article.getTitle(), Field.Store.YES, Field.Index.ANALYZED));

        document.add(new Field(IndexConstants.Field_createTime,String.valueOf(article.getCreateTime().getTime()), Field.Store.YES, Field.Index.NOT_ANALYZED));
        document.add(new Field(IndexConstants.Field_content,article.getContent(), Field.Store.YES, Field.Index.ANALYZED));
        
        log.info(document.toString());
        return document;
    }
    public void doInitAction() throws Exception {
        
        if (directory == null) {
            log.info("创建directory对象");
            try {
                File  dir = new File(indexFileStorePath);
                directory = FSDirectory.open(dir);                 
            } catch (Exception e) {
                
            }
        }
 
        if (writer == null) {
            
//            try {
//                if (IndexWriter.isLocked(directory)) {
//                    log.info("解锁IndexWriter");
//                    IndexWriter.unlock(directory);
//                }
//            } catch (Exception e) {
//                
//                log.error("解锁IndexWriter失败" ,e);
//            }
            try {

                writer = new IndexWriter(directory,analyzer,  IndexWriter.MaxFieldLength.LIMITED);
                updateFromDatabase();
            } catch (Exception e) {
                
                log.error("IndexSearcher初始化失败" , e);
            }
        }
        
        
    }
    
    public String getIndexFileStorePath()
    {
        return indexFileStorePath;
    }
    public void setIndexFileStorePath(String indexFileStorePath)
    {
        this.indexFileStorePath = indexFileStorePath;
    }
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

    
    
}
