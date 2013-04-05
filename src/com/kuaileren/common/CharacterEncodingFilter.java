package com.kuaileren.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 *
 * ϵͳ�ַ���ת�������
 */
public class CharacterEncodingFilter implements Filter {

    private final Log log = LogFactory.getLog(this.getClass());
    /**
     * ����Ĭ�ϵ��ַ���
     */
    private String encoding = "GBK";

    /**
     * ����Ĭ�ϵ�ת��״��
     */
    private boolean setCharacter = true;

    public void destroy() {

    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain arg2) throws IOException, ServletException {
        if (setCharacter) {
            try{
                arg0.setCharacterEncoding(encoding);
            }catch(Exception e){
                e.printStackTrace();
                log.error("------------������ת��ʧ��--------------");
            }
        }
        arg2.doFilter(arg0, arg1);
    }

    public void init(FilterConfig arg0) throws ServletException {
        log.info("-----�ַ�����������ʼ����-----");
        String _encoding = arg0.getInitParameter("encoding");
        String _setCharacter = arg0.getInitParameter("setCharacter");
        if(_encoding!=null){
            encoding = _encoding;
        }
        if(!encoding.equals("GBK")){
            log.warn("-----�ַ���û�����ó�ΪGBK-----");
        }else{
            log.info("-----�ַ��������ó�ΪGBK-----");
        }
        if(_setCharacter!=null)
            try{
                setCharacter = Boolean.parseBoolean(_setCharacter);
                if(setCharacter){
                    log.info("-----�ַ�������������Ϊ--����-----");
                }else{
                    log.info("-----�ַ�������������Ϊ--��ֹ-----");
                }

            }catch(Exception e){
                e.printStackTrace();
                log.warn("-----���������ó���"+e.getLocalizedMessage()+"-----");
                log.warn("-----ʹ��Ĭ������--����-----");

            }
        log.info("-----��������ʼ�����-----");
    }
}
