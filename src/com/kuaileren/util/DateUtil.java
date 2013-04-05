package com.kuaileren.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: Songzou
 * Date: 2007-4-8
 * Time: 11:27:55
 * 
 *
 * ���ڲ�����
 */
public class DateUtil {

  /**
   * �ж��ַ����Ƿ�����Ч�����ڣ�
   * ��ʽ"yyyy-MM-dd,yyyy-MM-d,yyyy-M-dd,yyyy-M-d
   * "yyyy/MM/dd,yyyy/MM/d,yyyy/M/dd,yyyy/M/d"
   * "yyyyMMdd"
   *
   * @param date �����ַ���
   * @return ���򷵻�true�����򷵻�false
   */
  public static boolean isValidDate(String date) {
    if ((date == null) || (date.length() < 8)) {
      return false;
    }
    try {
      boolean result = false;
      SimpleDateFormat formatter;
      char dateSpace = date.charAt(4);
      String format[];
      if ((dateSpace == '-') || (dateSpace == '/')) {
        format = new String[4];
        String strDateSpace = Character.toString(dateSpace);
        format[0] = "yyyy" + strDateSpace + "MM" + strDateSpace + "dd";
        format[1] = "yyyy" + strDateSpace + "MM" + strDateSpace + "d";
        format[2] = "yyyy" + strDateSpace + "M" + strDateSpace + "dd";
        format[3] = "yyyy" + strDateSpace + "M" + strDateSpace + "d";
      }
      else {
        format = new String[1];
        format[0] = "yyyyMMdd";
      }

      for (int i = 0; i < format.length; i++) {
        String aFormat = format[i];
        formatter = new SimpleDateFormat(aFormat);
        formatter.setLenient(false);
        String tmp = formatter.format(formatter.parse(date));
        if (date.equals(tmp)) {
          result = true;
          break;
        }
      }
      return result;
    }
    catch (ParseException e) {
      return false;
    }
  }

  /**
   * �ж��ַ����Ƿ�����Ч�����ڣ���ʽ"yyyy-MM-dd HH:mm:ss"
   *
   * @param date �����ַ���
   * @return ���򷵻�true�����򷵻�false
   */
  public static boolean isValidTime(String date) {
    try {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      formatter.setLenient(false);
      formatter.parse(date);
      return true;
    }
    catch (ParseException e) {
      return false;
    }
  }

  /**
   * ת���ַ���Ϊ���ڣ���ʽ"yyyy-MM-dd"
   *
   * @param date �����ַ���
   * @return ���ظ�ʽ��������
   */
  public static Date strToDate(String date) {
	  try{
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  formatter.setLenient(false);
		  return formatter.parse(date);
	  }catch(Exception e){}
	  return null;
  }

  /**
   * ת���ַ���Ϊ���ڣ���ʽ"yyyy-MM-dd HH:mm:ss"
   *
   * @param date �����ַ���
   * @return ���ظ�ʽ��������
   */
  public static Date strToTime(String date) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    formatter.setLenient(false);
    return formatter.parse(date);
  }

  /**
   * ת������Ϊ�ַ�������ʽ"yyyy-MM-dd"
   *
   * @param date ����
   * @return ���ظ�ʽ���������ַ���
   */
  public static String dateToStr(Date date) {
    if (date == null) return null;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(date);
  }

  /**
   * ת������Ϊ�ַ�������ʽ"yyyy-MM-dd HH:mm:ss"
   *
   * @param date ����
   * @return ���ظ�ʽ���������ַ���
   */
  public static String timeToStr(Date date) {
    if (date == null) return null;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return formatter.format(date);
  }

  /**
   * ȡ�����ڵ����ڣ���ʽ"yyyy-MM-dd HH:mm:ss"
   *
   * @return ���ظ�ʽ���������ַ���
   */
  public static String getNow() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date Now = new Date();
    return formatter.format(Now);
  }

  /**
   * ȡ�����ڵ����ڣ���ʽ"yyyy-MM-dd"
   *
   * @return ���ظ�ʽ���������ַ���
   */
  public static String getDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date Now = new Date();
    return formatter.format(Now);
  }

  /**
   * ȡ�����ڵ�ʱ�䣬��ʽ"HH:mm:ss"
   *
   * @return ���ظ�ʽ����ʱ���ַ���
   */
  public static String getTime() {
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    Date Now = new Date();
    return formatter.format(Now);
  }

  /**
   * ȡ����ݣ���ʽ"yyyy"
   *
   * @return ���ص�ǰ���
   */
  public static int getYear() {
    Date Now = new Date();
    return getYear(Now);
  }

  /**
   * ȡ�����ڵ���ݣ���ʽ"yyyy"
   *
   * @param date ����
   * @return ���ڵ����
   */
  public static int getYear(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
    return Integer.parseInt(formatter.format(date));
  }

  /**
   * ȡ���·�
   *
   * @return ���ص�ǰ�·�
   */
  public static int getMonth() {
    Date Now = new Date();
    return getMonth(Now);
  }

  /**
   * ȡ�����ڵ��·�
   *
   * @param date ����
   * @return ���ڵ��·�
   */
  public static int getMonth(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("M");
    return Integer.parseInt(formatter.format(date));
  }

  /**
   * ȡ�ý����������
   *
   * @return ���ؽ����������
   */
  public static int getDay() {
    Date Now = new Date();
    return getDay(Now);
  }

  /**
   * ȡ�����ڵ�����
   *
   * @param date ����
   * @return ���ڵ�����
   */
  public static int getDay(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("d");
    return Integer.parseInt(formatter.format(date));
  }

  /**
   * �����ĳ����������������
   *
   * @param date     ָ��������
   * @param addCount ���������
   * @return ���ص�����
   */
  public static Date addDay(Date date, int addCount) throws ParseException {
    //Calendar cal = new GregorianCalendar();
    //�����Calendar.getInstance();
    //��Ϊ�еĵط�������ʹ��GregorianCalendar�ġ�
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(strToDate(dateToStr(date)));
    calendar.add(Calendar.DATE, addCount);
    return calendar.getTime();
  }

  /**
   * �����ĳ����������µ�����
   *
   * @param date     ָ��������
   * @param addCount ���������
   * @return ���ص�����
   */
  public static Date addMonth(Date date, int addCount) throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(strToDate(dateToStr(date)));
    calendar.add(Calendar.MONTH, addCount);
    return calendar.getTime();
  }

  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

  /**
   * ��������ת�����ַ�����ʽ��
   * 
   * @param date ����
   * @param dateFormat ���ڸ�ʽ������"yyyy/MM/dd"��"yyyy��MM��dd"
   * @return �����ַ���
   */
  public static String toLocaleString(Date date, String dateFormat) {
      if (date == null) {
          return "";
      }

      if (StringUtil.isBlank(dateFormat)) {
          return simpleDateFormat.format(date);
      }

      return new SimpleDateFormat(dateFormat).format(date);
  }

  /**
   * ��������ת����"yyyy/MM/dd/"�ַ�����ʽ��
   * 
   * @param date
   * @return �����ַ���
   */
  public String toLocaleString(Date date) {
      if (date == null) {
          return "";
      }

      return simpleDateFormat.format(date);
  }

  /**
   * ���sysdate+hours���ʱ��
   * 
   * @param hours ��ǰ�����ƺ��ʱ��
   * @return sysdate+hours���ʱ��
   */
  public static Date getSysDate(int hours) {
      Calendar time = Calendar.getInstance();

      time.add(Calendar.HOUR, hours);

      return time.getTime();
  }

  /**
   * ����˵��:������
   * 
   * @param firstDate
   * @param lastDate
   * @return
   */
  public static int getTimeIntervalDays(Date firstDate, Date lastDate) {
      long intervals = lastDate.getTime() - firstDate.getTime() + (60 * 1000);

      if (intervals > 0) {
          long daysd = intervals / (24 * 60 * 60 * 1000);

          return new Long(daysd).intValue();
      }

      return 0;
  }

  /**
   * ����˵��:Сʱ��
   * 
   * @param firstDate
   * @param lastDate
   * @return
   */
  public static int getTimeIntervalHours(Date firstDate, Date lastDate) {
      long intervals = lastDate.getTime() - firstDate.getTime() + (60 * 1000);

      if (intervals > 0) {
          long longHours = (intervals / (60 * 60 * 1000)) % 24;

          return new Long(longHours).intValue();
      }

      return 0;
  }

  /**
   * ����˵��:���Ӳ�
   * 
   * @param firstDate
   * @param lastDate
   * @return
   */
  public static int getTimeIntervalMins(Date firstDate, Date lastDate) {
      long intervals = lastDate.getTime() - firstDate.getTime() + (60 * 1000);

      if (intervals > 0) {
          long longMins = (intervals / (60 * 1000)) % 60;

          return new Long(longMins).intValue();
      }

      return 0;
  }

  /**
   * ����˵��:parse date
   * 
   * @param firstDate
   * @param lastDate
   * @return
   */
  public static Date parseDate(String date, String dateformat) {
      SimpleDateFormat sdf = new SimpleDateFormat(dateformat);

      try {
          return sdf.parse(date);
      } catch (ParseException e) {
          return null;
      }
  }

  /**
   * �Ƚ������Ƿ���ڵ�ǰ����
   */
  public boolean afterNow(Date date) {
      if (date == null) {
          return false;
      }

      Calendar nowCar = Calendar.getInstance();
      Calendar car = Calendar.getInstance();

      car.setTime(date);

      return car.after(nowCar);
  }

  /*
   * �鿴�Ƿ��缸��
   */
  public static boolean afterDays(Date date, int day) {
      if (date == null) {
          return false;
      }

      Calendar levelDay = Calendar.getInstance();
      Calendar createDay = Calendar.getInstance();

      createDay.setTime(date);
      createDay.add(Calendar.DATE, day);

      if (createDay.after(levelDay)) {
          return true;
      } else {
          return false;
      }
  }

  /*
   * �鿴�Ƿ��缸Сʱ
   */
  public boolean afterHours(Date date, int hours) {
      if (date == null) {
          return false;
      }

      Calendar levelDay = Calendar.getInstance();
      Calendar createDay = Calendar.getInstance();

      createDay.setTime(date);
      createDay.add(Calendar.HOUR, hours);

      if (createDay.after(levelDay)) {
          return true;
      } else {
          return false;
      }
  }

  /**
   * ȡ��ϵͳ��ǰ����
   * 
   * @return
   */
  public static Date getCurrentTime() {
      return new Date();
  }

  /**
   * ���ض���ʱ���ǰ��ʱ��, seconds �����Ǹ���
   * 
   * @param when
   * @param seconds
   * @return
   */
  public Date addTime(Date when, int seconds) {
      Calendar c = Calendar.getInstance();

      c.setTime(when);
      c.add(Calendar.SECOND, seconds);

      return c.getTime();
  }
  
  /**
   * ������ʱ��ת��������
   * @Description :TODO
   * @return Date
   */
	public static Date strParseToDate(String date) {
		try {
			long l = Long.parseLong(date);
			return new Date(l);
		} catch (Exception e) {
		}
		return null;

	}
}
