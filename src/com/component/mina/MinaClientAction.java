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

	private String msg ="�Ұ������ˣ�";
	
	public String minatest() {
		try {
			// ����һ���������Ŀͻ��˳���
			IoConnector connector = new NioSocketConnector();
			// �������ӳ�ʱʱ��
			connector.setConnectTimeoutMillis(30000);
			// ��ӹ�����
//			connector.getFilterChain().addLast(
//					"codec",
//					new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
//							LineDelimiter.WINDOWS.getValue())));
			
			
			connector.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
			
			// ���ҵ���߼���������
			connector.setHandler(new MinaClientHandler()) ;
			IoSession session = null;
			try {
				ConnectFuture future = connector.connect(new InetSocketAddress(
						HOST, PORT));// ��������
				future.awaitUninterruptibly();// �ȴ����Ӵ������
				session = future.getSession();// ���session
				
				
				EventDto edt = new EventDto();
				edt.setBeginTime(10);
				edt.setDayIndex(1);
				edt.setEventName("setEventName");
				edt.setStatus(1);
				edt.setTotalTime(100);
				edt.setUrl("www.baidu.com");
				
				session.write(edt);// ������Ϣ
 
			} catch (Exception e) {
				log.error("�ͻ��������쳣...", e);
			}
			session.getCloseFuture().awaitUninterruptibly();// �ȴ����ӶϿ�
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
