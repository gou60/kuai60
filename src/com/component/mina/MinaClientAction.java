package com.component.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.component.mina.domain.EventDto;
import com.kuaileren.web.BaseAction;

public class MinaClientAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4392075472717143537L;
	private static Logger log = Logger.getLogger(MinaClientAction.class);
	private static String HOST = "127.0.0.1";
	private static int PORT = 3005;

	private String msg ="我爱死你了！";
	
	public String minatest() {
		try {
			// 创建一个非阻塞的客户端程序
			IoConnector connector = new NioSocketConnector();
			// 设置链接超时时间
			connector.setConnectTimeoutMillis(30000);
			// 添加过滤器
//			connector.getFilterChain().addLast(
//					"codec",
//					new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
//							LineDelimiter.WINDOWS.getValue())));
			
			
			connector.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
			
			// 添加业务逻辑处理器类
			connector.setHandler(new MinaClientHandler()) ;
			IoSession session = null;
			try {
				ConnectFuture future = connector.connect(new InetSocketAddress(
						HOST, PORT));// 创建连接
				future.awaitUninterruptibly();// 等待连接创建完成
				session = future.getSession();// 获得session
				
				
				EventDto edt = new EventDto();
				edt.setBeginTime(10);
				edt.setDayIndex(1);
				edt.setEventName("setEventName");
				edt.setStatus(1);
				edt.setTotalTime(100);
				edt.setUrl("www.baidu.com");
				
				session.write(edt);// 发送消息
 
			} catch (Exception e) {
				log.error("客户端链接异常...", e);
			}
			session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
			connector.dispose();
			
			
			return SUCCESS;
			

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ERROR;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
