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
 * 日期操作类
 */
public class DateUtil {

  /**
   * 判断字符串是否是有效的日期，
   * 格式"yyyy-MM-dd,yyyy-MM-d,yyyy-M-dd,yyyy-M-d
   * "yyyy/MM/dd,yyyy/MM/d,yyyy/M/dd,yyyy/M/d"
   * "yyyyMMdd"
   *
   * @param date 日期字符串
   * @return 是则返回true，否则返回false
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
   * 判断字符串是否是有效的日期，格式"yyyy-MM-dd HH:mm:ss"
   *
   * @param date 日期字符串
   * @return 是则返回true，否则返回false
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
   * 转换字符串为日期，格式"yyyy-MM-dd"
   *
   * @param date 日期字符串
   * @return 返回格式化的日期
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
   * 转换字符串为日期，格式"yyyy-MM-dd HH:mm:ss"
   *
   * @param date 日期字符串
   * @return 返回格式化的日期
   */
  public static Date strToTime(String date) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    formatter.setLenient(false);
    return formatter.parse(date);
  }

  /**
   * 转换日期为字符串，格式"yyyy-MM-dd"
   *
   * @param date 日期
   * @return 返回格式化的日期字符串
   */
  public static String dateToStr(Date date) {
    if (date == null) return null;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(date);
  }

  /**
   * 转换日期为字符串，格式"yyyy-MM-dd HH:mm:ss"
   *
   * @param date 日期
   * @return 返回格式化的日期字符串
   */
  public static String timeToStr(Date date) {
    if (date == null) return null;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return formatter.format(date);
  }

  /**
   * 取得现在的日期，格式"yyyy-MM-dd HH:mm:ss"
   *
   * @return 返回格式化的日期字符串
   */
  public static String getNow() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date Now = new Date();
    return formatter.format(Now);
  }

  /**
   * 取得现在的日期，格式"yyyy-MM-dd"
   *
   * @return 返回格式化的日期字符串
   */
  public static String getDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date Now = new Date();
    return formatter.format(Now);
  }

  /**
   * 取得现在的时间，格式"HH:mm:ss"
   *
   * @return 返回格式化的时间字符串
   */
  public static String getTime() {
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    Date Now = new Date();
    return formatter.format(Now);
  }

  /**
   * 取得年份，格式"yyyy"
   *
   * @return 返回当前年份
   */
  public static int getYear() {
    Date Now = new Date();
    return getYear(Now);
  }

  /**
   * 取得日期的年份，格式"yyyy"
   *
   * @param date 日期
   * @return 日期的年份
   */
  public static int getYear(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
    return Integer.parseInt(formatter.format(date));
  }

  /**
   * 取得月份
   *
   * @return 返回当前月份
   */
  public static int getMonth() {
    Date Now = new Date();
    return getMonth(Now);
  }

  /**
   * 取得日期的月份
   *
   * @param date 日期
   * @return 日期的月份
   */
  public static int getMonth(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("M");
    return Integer.parseInt(formatter.format(date));
  }

  /**
   * 取得今天的日期数
   *
   * @return 返回今天的日期数
   */
  public static int getDay() {
    Date Now = new Date();
    return getDay(Now);
  }

  /**
   * 取得日期的天数
   *
   * @param date 日期
   * @return 日期的天数
   */
  public static int getDay(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("d");
    return Integer.parseInt(formatter.format(date));
  }

  /**
   * 获得与某日期相隔几天的日期
   *
   * @param date     指定的日期
   * @param addCount 相隔的天数
   * @return 返回的日期
   */
  public static Date addDay(Date date, int addCount) throws ParseException {
    //Calendar cal = new GregorianCalendar();
    //最好用Calendar.getInstance();
    //因为有的地方，不是使用GregorianCalendar的。
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(strToDate(dateToStr(date)));
    calendar.add(Calendar.DATE, addCount);
    return calendar.getTime();
  }

  /**
   * 获得与某日期相隔几月的日期
   *
   * @param date     指定的日期
   * @param addCount 相隔的月数
   * @return 返回的日期
   */
  public static Date addMonth(Date date, int addCount) throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(strToDate(dateToStr(date)));
    calendar.add(Calendar.MONTH, addCount);
    return calendar.getTime();
  }

  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

  /**
   * 把日期型转换成字符串形式。
   * 
   * @param date 日期
   * @param dateFormat 日期格式，例如"yyyy/MM/dd"、"yyyy年MM月dd"
   * @return 日期字符串
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
   * 把日期型转换成"yyyy/MM/dd/"字符串形式。
   * 
   * @param date
   * @return 日期字符串
   */
  public String toLocaleString(Date date) {
      if (date == null) {
          return "";
      }

      return simpleDateFormat.format(date);
  }

  /**
   * 获得sysdate+hours后的时间
   * 
   * @param hours 提前或者推后的时间
   * @return sysdate+hours后的时间
   */
  public static Date getSysDate(int hours) {
      Calendar time = Calendar.getInstance();

      time.add(Calendar.HOUR, hours);

      return time.getTime();
  }

  /**
   * 方法说明:天数差
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
   * 方法说明:小时差
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
   * 方法说明:分钟差
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
   * 方法说明:parse date
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
   * 比较日期是否大于当前日期
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
   * 查看是否早几天
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
   * 查看是否早几小时
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
   * 取得系统当前日期
   * 
   * @return
   */
  public static Date getCurrentTime() {
      return new Date();
  }

  /**
   * 返回多少时间的前的时间, seconds 可以是负的
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
   * 长整型时间转换成日期
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
