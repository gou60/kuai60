package com.component.mina.domain;

import java.io.Serializable;
import java.nio.charset.Charset;
import org.apache.log4j.Logger;

public class EventDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6020390998981012994L;
	//private Logger logger = Logger.getLogger(EventDto.class);
	private String eventName;
	private int beginTime;
	private int totalTime;
	private int dayIndex;
	private int status;
	private String url;

	// ��Ŀ���ַ����ݵ��ֽڳ���
	public int getLen(Charset charset) {
		int len = 0;
		try {
			if (eventName != null && !"".equals(eventName)) {
				len += eventName.getBytes(charset).length;
			}
			if (url != null && !"".equals(url)) {
				len += url.getBytes(charset).length;
			}
		} catch (Exception e) {
			//logger.error("��Ŀ��Ϣת��Ϊ�ֽ������...", e);
		}
		return len;
	}
	// ������get/set�������ԡ�����

//	public Logger getLogger() {
//		return logger;
//	}
//
//	public void setLogger(Logger logger) {
//		this.logger = logger;
//	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public int getDayIndex() {
		return dayIndex;
	}

	public void setDayIndex(int dayIndex) {
		this.dayIndex = dayIndex;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "EventDto [eventName=" + eventName
				+ ", beginTime=" + beginTime + ", totalTime=" + totalTime
				+ ", dayIndex=" + dayIndex + ", status=" + status + ", url="
				+ url + "]";
	}
	
	
	
	
}