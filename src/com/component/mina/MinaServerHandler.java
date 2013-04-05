package com.component.mina;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.component.mina.domain.EventDto;

public class MinaServerHandler extends IoHandlerAdapter {

	public static Logger logger = Logger.getLogger(MinaServerHandler.class);

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("�������ͻ��˴�������...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("�������ͻ������Ӵ�...");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if (message instanceof EventDto) {
			EventDto edt = (EventDto) message; 
			logger.info(edt); 
			session.write(edt);
		} else {
			logger.info("δ֪����");
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		session.close();
		logger.info("����˷�����Ϣ�ɹ�...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.info("����˽������״̬...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("����˷����쳣...", cause);
	}

}
