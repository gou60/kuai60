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
		logger.info("服务端与客户端创建连接...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("服务端与客户端连接打开...");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if (message instanceof EventDto) {
			EventDto edt = (EventDto) message; 
			logger.info(edt); 
			session.write(edt);
		} else {
			logger.info("未知请求！");
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		session.close();
		logger.info("服务端发送信息成功...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.info("服务端进入空闲状态...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("服务端发送异常...", cause);
	}

}
