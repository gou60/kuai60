package com.component.mina;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.component.mina.domain.EventDto;

public class MinaClientHandler extends IoHandlerAdapter {
	private static Logger logger = Logger.getLogger(MinaClientHandler.class);

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {

		if (message instanceof EventDto) {
			EventDto res = (EventDto) message;
			 
			 logger.info("�ͻ��˽��յ�����ϢΪ��" + res); 
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("�ͻ��˷����쳣...", cause);
	}
}