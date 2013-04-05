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

        //ɨ��SPUCacheList�����Ƿ���MQ���ݴ���������
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
    //���յ���Ϣ���ȷŵ������У�Ȼ��������������
    public  void updateFromMQ(ArticleDO articleDO) {
        SPUCacheList.add(articleDO);
    }
    private synchronized  void refreshIndex(ArticleDO articleDO) {
        //������ڳ�ʼ�����������
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
            log.info("�����ؽ������Դ����");
        }
    }

  //ɨ��SPUCacheList�����Ƿ���MQ���ݴ���������
    private class IndexFromMQThread extends Thread {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    
                }
                //��û�г�ʼ��������£��ӻ����ж�ȡMQ���ݼ�������
                if (!indexActionIsRunning && SPUCacheList.size() > 0) {
                    
                    log.debug("�ӻ����ж�ȡMQ���ݼ�������,size=" + SPUCacheList.size());
                    
                    try {
                        
                        indexActionIsRunning = true;
                        
                        List<ArticleDO> articleList = new ArrayList<ArticleDO>();
                        while (SPUCacheList.size() > 0) {
                        	ArticleDO articleDO = SPUCacheList.get(0);
                        	articleList.add(articleDO);
                            SPUCacheList.remove(0);
                        }
                        //������������
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
  
            //����һ��������ٸ�SPU
            articleDO.getPageInfo().setPerPageRows(pageSize);
 
                
                //ȡ�÷ֱ��·���������SPU����
                long dataCount = articleService.getArticleCount(articleDO);
                int startIndex = 0;
               
                //��ҳ����
                if (true) {
                	articleDO.getPageInfo().setStartIndex(startIndex);
                    
                    Date startTime = new Date();
                    
                    List<ArticleDO> articleList = articleService.queryArticleList(articleDO);
                    if (articleList.size() == 0) {
                       // break;
                    }
                    Date endTime = new Date();
                    
                    //�õ���ǰҳ���SPU�б�����ӵ���������
                    updateIndexBatch(0, articleList);
                    
                    Date indexTime = new Date();
                    log.info("�����ݿ��в�ѯ( "+articleList.size()+" ��("+startIndex + "/" + dataCount + ")��¼��ʱ: " + (endTime.getTime() - startTime.getTime())  + " ms, ���������ʱ: " + (indexTime.getTime() - endTime.getTime())  + " ms");
                    
                    //��һҳ
                    startIndex += pageSize;
                    
                    
                    //����
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        
                    }
                    
                }
           
        
        
        log.info("��ʼ�����!");
    }
    //�������뵽�����ļ���
    private synchronized void updateIndexBatch(long creatorUIN, List<ArticleDO> articleList) {
        
        
        //��ɾ�������ļ��п��ܴ�����ͬ��SPU
        try {

            for (ArticleDO articleDO:articleList) {
                writer.deleteDocuments(new Term(IndexConstants.Field_articleId, String.valueOf(articleDO.getArticleId())));
                log.info("article:" + articleDO.getArticleId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //���SPU���ݵ������ļ���
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

    
    //����Ʒ���ơ��͡��ؼ����ԡ���Ϊ���ؼ��ʡ��ֶμ��뵽�����ļ���
    private String createSearchKeyword(String productName) {
        String searchKeywords = new StringBuffer()
            .append(productName)
            .append(" ").toString();

        return searchKeywords;
    }

    
    //��SPU���ݹ���Ϊluceneʶ���Document����
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
            log.info("����directory����");
            try {
                File  dir = new File(indexFileStorePath);
                directory = FSDirectory.open(dir);                 
            } catch (Exception e) {
                
            }
        }
 
        if (writer == null) {
            
//            try {
//                if (IndexWriter.isLocked(directory)) {
//                    log.info("����IndexWriter");
//                    IndexWriter.unlock(directory);
//                }
//            } catch (Exception e) {
//                
//                log.error("����IndexWriterʧ��" ,e);
//            }
            try {

                writer = new IndexWriter(directory,analyzer,  IndexWriter.MaxFieldLength.LIMITED);
                updateFromDatabase();
            } catch (Exception e) {
                
                log.error("IndexSearcher��ʼ��ʧ��" , e);
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
