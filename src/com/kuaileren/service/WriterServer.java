package com.kuaileren.service;

import org.apache.lucene.analysis.Analyzer;

public interface WriterServer 
{
  
    //�����ݿ��ж�ȡSPU��������ʼ�������ļ�
    public void updateFromDatabase();

    //���ط�����
    public Analyzer getAnalyzer();
    
    public void doInitAction()throws Exception;

}
