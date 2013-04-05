package com.kuaileren.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 *
 * 系统字符集转码过滤器
 */
public class CharacterEncodingFilter implements Filter {

    private final Log log = LogFactory.getLog(this.getClass());
    /**
     * 设置默认的字符集
     */
    private String encoding = "GBK";

    /**
     * 设置默认的转码状况
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
                log.error("------------过滤器转码失败--------------");
            }
        }
        arg2.doFilter(arg0, arg1);
    }

    public void init(FilterConfig arg0) throws ServletException {
        log.info("-----字符集过滤器初始化中-----");
        String _encoding = arg0.getInitParameter("encoding");
        String _setCharacter = arg0.getInitParameter("setCharacter");
        if(_encoding!=null){
            encoding = _encoding;
        }
        if(!encoding.equals("GBK")){
            log.warn("-----字符集没有设置成为GBK-----");
        }else{
            log.info("-----字符集被设置成为GBK-----");
        }
        if(_setCharacter!=null)
            try{
                setCharacter = Boolean.parseBoolean(_setCharacter);
                if(setCharacter){
                    log.info("-----字符集过滤器设置为--允许-----");
                }else{
                    log.info("-----字符集过滤器设置为--禁止-----");
                }

            }catch(Exception e){
                e.printStackTrace();
                log.warn("-----过滤器设置出现"+e.getLocalizedMessage()+"-----");
                log.warn("-----使用默认设置--允许-----");

            }
        log.info("-----过滤器初始化完毕-----");
    }
}
