package com.component.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {

	private static Logger logger = Logger.getLogger(MinaServer.class);
	private static int PORT = 3005;

	public static void main(String[] args) {
		IoAcceptor acceptor = null;

		try {
			// ����һ����������server�˵�Socket
			acceptor = new NioSocketAcceptor();
			// ���ù�������ʹ��Mina�ṩ���ı����з����������
//			acceptor.getFilterChain().addLast(
//					"codec",
//					new ProtocolCodecFilter(new TextLineCodecFactory(Charset
//							.forName("UTF-8"),
//					LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS
//							.getValue())));
			
			acceptor.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
			
//			acceptor.getFilterChain().addLast(
//					"codec",
//					new ProtocolCodecFilter(new MyMessageCodecFactory(
//					new MyMessageDecoder(Charset.forName("utf-8")),
//					new MyMessageEncoder(Charset.forName("utf-8")))));			
			
			
			// ���ö�ȡ���ݵĻ�������С
			acceptor.getSessionConfig().setReadBufferSize(2048);
			// ��дͨ��10�����޲����������״̬
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			// ���߼�������
			acceptor.setHandler(new MinaServerHandler());
			// �󶨶˿�
			acceptor.bind(new InetSocketAddress(PORT));
			logger.info("����������ɹ�... �˿ں�Ϊ��" + PORT);
		} catch (Exception e) {
			logger.error("����������쳣....", e);
			e.printStackTrace();
		}

	}
}
