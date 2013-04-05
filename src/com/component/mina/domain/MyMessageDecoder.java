package com.component.mina.domain;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;


public class MyMessageDecoder implements MessageDecoder {
	private Logger logger = Logger.getLogger(MyMessageDecoder.class);
	private Charset charset;

	public MyMessageDecoder(Charset charset) {
		this.charset = charset;
	}

	// ��������IoBuffer�Ƿ��ʺϽ���
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		// ��ͷ����==6
		if (in.remaining() < 6) {
			return MessageDecoderResult.NEED_DATA;
		}
		// tag����
		short tag = in.getShort();
		// ע���Ȱ�16���Ʊ�ʶֵת��Ϊshort���͵�ʮ�������ݣ�Ȼ����tag�Ƚ�
		if (tag == (short) 0x0001 || tag == (short) 0x8001) {
			logger.info("�����ʶ����" + tag);
		} else {
			logger.error("δ֪�Ľ�������....");
			return MessageDecoderResult.NOT_OK;
		}
		// ��ʵ���ݳ���
		int len = in.getInt();
		if (in.remaining() < len) {
			return MessageDecoderResult.NEED_DATA;
		}

		return MessageDecoderResult.OK;
	}

	public MessageDecoderResult decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		logger.info("���룺" + in.toString());
		CharsetDecoder decoder = charset.newDecoder();
		AbstrMessage message = null;
		short tag = in.getShort(); // tag
		int len = in.getInt(); // len
		byte[] temp = new byte[len];
		in.get(temp); // ������
		// ===============����������׼��======================
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		buf.put(temp);
		buf.flip(); // Ϊ��ȡ����������������׼��
		IoBuffer databuf = IoBuffer.allocate(100).setAutoExpand(true);
		databuf.putShort(tag);
		databuf.putInt(len);
		databuf.put(temp);
		databuf.flip(); // Ϊ��ȡ��ʵ������������׼��
		// ================��ʼ����=========================
		// ע���Ȱ�16���Ʊ�ʶֵת��Ϊshort���͵�ʮ�������ݣ�Ȼ����tag�Ƚ�
		if (tag == (short) 0x0001) { // ����˽���
			ChannelInfoRequest req = new ChannelInfoRequest();
			short channel_id = buf.getShort();
			byte channel_desc_len = buf.get();
			String channel_desc = null;
			if (channel_desc_len > 0) {
				channel_desc = buf.getString(channel_desc_len, decoder);
			}
			req.setChannel_id(channel_id);
			req.setChannel_desc(channel_desc);
			message = req;
		} else if (tag == (short) 0x8001) { // �ͻ��˽���
			ChannelInfoResponse res = new ChannelInfoResponse();

			int channel_addr = buf.getInt();
			byte channel_len = buf.get();
			if (databuf.position() == 0) {
				databuf.position(channel_addr);
			}
			String channelName = null;
			if (channel_len > 0) {
				channelName = databuf.getString(channel_len, decoder);
			}
			res.setChannelName(channelName);
			short event_num = buf.getShort();
			EventDto[] events = new EventDto[event_num];
			for (int i = 0; i < event_num; i++) {
				EventDto edt = new EventDto();
				byte dayIndex = buf.get();
				buf.getInt();
				byte eventName_len = buf.get();
				String eventName = null;
				if (eventName_len > 0) {
					eventName = databuf.getString(eventName_len, decoder);
				}
				int beginTime = buf.getInt();
				short totalTime = buf.getShort();
				byte status = buf.get();
				buf.getInt();
				byte url_len = buf.get();
				String url = null;
				if (url_len > 0) {
					url = databuf.getString(url_len, decoder);
				}
				edt.setDayIndex(dayIndex);
				edt.setEventName(eventName);
				edt.setBeginTime(beginTime);
				edt.setTotalTime(totalTime);
				edt.setStatus(status);
				edt.setUrl(url);
				events[i] = edt;
			}

			res.setEvents(events);
			message = res;
		} else {
			logger.error("δ�ҵ�������....");
		}
		out.write(message);
		// ================����ɹ�=========================
		return MessageDecoderResult.OK;
	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
	}
}