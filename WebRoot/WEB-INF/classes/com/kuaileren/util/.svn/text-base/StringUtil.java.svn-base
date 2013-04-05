package com.kuaileren.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

public class StringUtil {
	
	public static boolean isBlank(String str){
		if(str == null || str.isEmpty()){
			return true;
		}
		if(str.trim().isEmpty()){
			return true;
		}
		return false;
	}
	
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}

	  /**
	   * 字符串替换函数,String的replace函数不能处理'|'符号
	   *
	   * @param strSource 被替换的源字符串
	   * @param strFrom   要查找并替换的子字符串
	   * @param strTo     要替换为的子字符串
	   * @return 替换完成的字符串
	   */
	  public static String replace(String strSource, String strFrom, String strTo) {

	    String strDest = "";
	    int intFromLen = strFrom.length();
	    int intPos;

	    if (strFrom.equals("")) {
	      return strSource;
	    }
	    while ((intPos = strSource.indexOf(strFrom)) != -1) {
	      strDest = strDest + strSource.substring(0, intPos);
	      strDest = strDest + strTo;
	      strSource = strSource.substring(intPos + intFromLen);
	    }
	    strDest = strDest + strSource;
	    return strDest;
	  }

	  /**
	   * 将普通字符串格式化成数据库认可的字符串格式
	   *
	   * @param input 要格式化的字符串
	   * @return 合法的数据库字符串
	   */
	  public static String toSql(String input) {
	    if (isEmpty(input)) {
	      return "";
	    }
	    else {
	      return input.replaceAll("'", "''");
	    }
	  }

	  /**
	   * 截取字符串左侧指定长度的字符串
	   *
	   * @param input 输入字符串
	   * @param count 截取长度
	   * @return 截取字符串
	   */
	  public static String left(String input, int count) {
	    if (isEmpty(input)) {
	      return "";
	    }
	    count = (count > input.length()) ? input.length() : count;
	    return input.substring(0, count);
	  }

	  /**
	   * 截取字符串右侧指定长度的字符串
	   *
	   * @param input 输入字符串
	   * @param count 截取长度
	   * @return 截取字符串
	   */
	  public static String right(String input, int count) {
	    if (isEmpty(input)) {
	      return "";
	    }
	    count = (count > input.length()) ? input.length() : count;
	    return input.substring(input.length() - count, input.length());
	  }

	  /**
	   * 从指定位置开始截取指定长度的字符串
	   *
	   * @param input 输入字符串
	   * @param index 截取位置，左侧第一个字符索引值是1
	   * @param count 截取长度
	   * @return 截取字符串
	   */
	  public static String middle(String input, int index, int count) {
	    if (isEmpty(input)) {
	      return "";
	    }
	    count = (count > input.length() - index + 1) ? input.length() - index + 1 :
	        count;
	    return input.substring(index - 1, index + count - 1);
	  }

	  /**
	   * Unicode转换成GBK字符集
	   *
	   * @param input 待转换字符串
	   * @return 转换完成字符串
	   */
	  public static String UnicodeToGB(String input) {
	   try{
		  if (isEmpty(input)) {
	      return "";
	    }
	    else {
	      String s1;
	      s1 = new String(input.getBytes("ISO8859_1"), "GBK");
	      return s1;
	    }
		  
	   }catch(Exception e){
			e.printStackTrace();  
		  }
	   
	   return "";
		  
	  }

	  
	  public static String Utf8ToGB(String input) {
		   try{
			  if (isEmpty(input)) {
		      return "";
		    }
		    else {
		      String s1;
		      s1 = new String(input.getBytes("gb2312"),"GBK");
		      return s1;
		    }
			  
		   }catch(Exception e){
				e.printStackTrace();  
			  }
		   
		   return "";
			  
		  }
	  /**
	   * GBK转换成Unicode字符集
	   *
	   * @param input 待转换字符串
	   * @return 转换完成字符串
	   */
	  public static String GBToUnicode(String input) throws UnsupportedEncodingException {
	    if (isEmpty(input)) {
	      return "";
	    }
	    else {
	      String s1;
	      s1 = new String(input.getBytes("GBK"), "ISO8859_1");
	      return s1;
	    }
	  }

	  /**
	   * 分隔字符串成数组.
	   * <p/>
	   * 使用StringTokenizer，String的split函数不能处理'|'符号
	   *
	   * @param input 输入字符串
	   * @param delim 分隔符
	   * @return 分隔后数组
	   */
	  public static String[] splitString(String input, String delim) {
	    if (isEmpty(input)) {
	      return new String[0];
	    }
	    ArrayList al = new ArrayList();
	    for (StringTokenizer stringtokenizer = new StringTokenizer(input, delim);
	         stringtokenizer.hasMoreTokens();
	         al.add(stringtokenizer.nextToken())) {
	    }
	    String result[] = new String[al.size()];
	    for (int i = 0; i < result.length; i++) {
	      result[i] = (String) al.get(i);
	    }
	    return result;
	  }

	  /**
	   * 判断字符串数组中是否包含某字符串元素
	   *
	   * @param substring 某字符串
	   * @param source    源字符串数组
	   * @return 包含则返回true，否则返回false
	   */
	  public static boolean isIn(String substring, String[] source) {
	    if (source == null || source.length == 0) {
	      return false;
	    }
	    for (int i = 0; i < source.length; i++) {
	      String aSource = source[i];
	      if (aSource.equals(substring)) {
	        return true;
	      }
	    }
	    return false;
	  }

	  /**
	   * 判断字符是否为空
	   *
	   * @param input 某字符串
	   * @return 包含则返回true，否则返回false
	   */
	  public static boolean isEmpty(String input) {
	    return input == null || input.length() == 0;
	  }

	  /**
	   * 判断字符是否为空
	   *
	   * @param input 某字符串
	   * @return 包含则返回false，否则返回true
	   */
	  public static boolean isNotEmpty(String input) {
		    return !isEmpty(input);
	  }
	  /**
	   * 获得0-9的随机数
	   *
	   * @param length
	   * @return String
	   */
	  public static String getRandomNumber(int length) {
	    Random random = new Random();
	    StringBuffer buffer = new StringBuffer();

	    for (int i = 0; i < length; i++) {
	      buffer.append(random.nextInt(10));
	    }
	    return buffer.toString();
	  }

	  /**
	   * 获得0-9的随机数 长度默认为10
	   *
	   * @return String
	   */
	  public static String getRandomNumber() {
	    return getRandomNumber(10);
	  }

	  /**
	   * 获得0-9,a-z,A-Z范围的随机数
	   * @param length 随机数长度
	   * @return String
	   */

	  public static String getRandomChar(int length) {
	    char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	    Random random = new Random();
	    StringBuffer buffer = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	      buffer.append(chr[random.nextInt(62)]);
	    }
	    return buffer.toString();
	  }

	  public static String getRandomChar() {
	    return getRandomChar(10);
	  }

	  /**
	   *
	   * 获得13位当前年月日时秒分随机码13位做位主键
	   */
	  public static String getPrimaryKey() {
	    Date now = new Date();
	    SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    return dateformat.format(now) + getRandomChar(13);
	  }
	 /**
	   *
	   *做位主键选者主键长度
	   */
	  public static String getPrimaryKey(int index){
	    Date now = new Date();
	    SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    String indexAppendZero = DigitalUtil.appendZero(index,4);
	    return dateformat.format(now) + indexAppendZero + getRandomChar(9-indexAppendZero.length());
	  }


	  public static String filterHTML(String input) {
	    StringBuffer filtered = new StringBuffer();
	    char c;
	    for (int i = 0; i <= input.length() - 1; i++) {
	      c = input.charAt(i);
	      switch (c) {
	        case '&':
	          filtered.append("&amp;");
	          break;
	        case '<':
	          filtered.append("&lt;");
	          break;
	        case '>':
	          filtered.append("&gt;");
	          break;
	        case '"':
	          filtered.append("&#034;");
	          break;
	        case '\'':
	          filtered.append("&#039;");
	          break;
	        default:
	          filtered.append(c);
	      }
	    }
	    return (filtered.toString());
	  }


	  public static void main(String[] args) {
	    System.out.println(getPrimaryKey(5));

	  }


	  static public String prefixZoreFill(String sourceStr,int len){
		    int prefix = len - sourceStr.length();
			if (prefix<=0) return sourceStr;
			for (int i=0;i<prefix;i++ ){
				sourceStr = "0" + sourceStr;
			}
			return sourceStr;
		}

		static public String replaceAll(String str, String regex, String replacement) {
			if (str==null || str.compareTo("")==0 || str.compareTo("null")==0){
				return str;
			}
			if (regex==null || regex.compareTo("null")==0){
				return str;
			}
			if (replacement==null || replacement.compareTo("null")==0){
				return str;
			}

			try{
				int iIndex,iFromIndex;
				String stmp=new String();;
				int iLen = regex.length();

				iFromIndex=0;
				iIndex=str.indexOf(regex,iFromIndex);
				stmp="";
				while (iIndex>=0) {
					stmp=stmp+str.substring(iFromIndex,iIndex)+replacement;
					str=str.substring(iIndex+iLen);
					iIndex=str.indexOf(regex,iFromIndex);
				}
				stmp=stmp+str;

				return stmp;
			}catch(Exception e){
				return str;
			}
		}

		static public int length(String str) {
			if (str==null || str.compareTo("")==0 || str.compareTo("null")==0) {
				return 0;
			}

			int enLen=0;
			int chLen=0;
			char ch=' ';
			Character CH=new Character(' ');
			int iValue=0;

			for(int i=0;i<str.length();i++) {
				ch=str.charAt(i);
				CH=new Character(ch);
				iValue=CH.charValue();
				if(iValue<128) {
					enLen++;
				}else{
					chLen++;
				}
			}

			return (enLen + chLen/2);
		}

		static public String substring(String str, int beginIndex, int endIndex)  {
			if (str==null || str.compareTo("")==0 || str.compareTo("null")==0) {
				return "";
			}

			String rtsValue=null;
			int enLen=0;
			int chLen=0;
			char ch=' ';
			Character CH=new Character(' ');
			int iValue=0;
			int iLength=0;
			int realBegin=0;
			int realEnd=0;
			int i=0;

			while(iLength<beginIndex) {
				ch=str.charAt(i);
				CH=new Character(ch);
				iValue=CH.charValue();
				if(iValue<128) {
					enLen++;
				}else{
					chLen++;
				}
				iLength=enLen + chLen/2;
				i++;
			}

			realBegin=enLen + chLen;

			i=realBegin;
			while(iLength<endIndex) {
				ch=str.charAt(i);
				CH=new Character(ch);
				iValue=CH.charValue();
				if(iValue<128) {
					enLen++;
				}else{
					chLen++;
				}
				iLength=enLen + chLen/2;
				i++;
			}

			realEnd=enLen + chLen;

			rtsValue=str.substring(realBegin,realEnd);

			return rtsValue;
		}
}
