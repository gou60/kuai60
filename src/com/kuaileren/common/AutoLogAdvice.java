package com.kuaileren.common;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

/**
 * 
 * �Զ���־
 */
public class AutoLogAdvice implements AfterReturningAdvice {
	private static final Log log = LogFactory.getLog(AutoLogAdvice.class);

	public static boolean autoLog = true;

	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		if (autoLog) {
			log.info("����ֵ:" + returnValue.getClass());
			log.info("����:" + method);
			log.info("target:" + target.getClass());
			
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					log.info("args"+i+":"+ args[i].getClass());
				}
			}
		}
	}
}
