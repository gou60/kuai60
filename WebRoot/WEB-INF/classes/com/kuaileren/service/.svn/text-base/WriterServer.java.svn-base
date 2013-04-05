package com.kuaileren.service;

import org.apache.lucene.analysis.Analyzer;

public interface WriterServer 
{
  
    //从数据库中读取SPU数据来初始化索引文件
    public void updateFromDatabase();

    //返回分析器
    public Analyzer getAnalyzer();
    
    public void doInitAction()throws Exception;

}
