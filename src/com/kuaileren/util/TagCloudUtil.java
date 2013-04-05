package com.kuaileren.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;

public class TagCloudUtil {

	static Map<String, Integer> tags = new HashMap<String, Integer>();
	
	 public static void main(String[] args) {  
		 initTagCloud();
		 List<String> tagList = tagCloud();
	    	
		 for(String str: tagList){
			 System.out.println(str);
		 }
	    }  
	 
	public static Map<String, Integer> initTagCloud(){
		tags = new HashMap<String, Integer>();
		tags.put("快乐人", 20);
		tags.put("快乐", 2);
		tags.put("WC", 4);
		tags.put("哈哈", 11);
		tags.put("飞人", 2);
		tags.put("丈夫", 8);
		tags.put("苦恼", 16);
		tags.put("甜过初恋", 20);
		tags.put("洗错衣服了", 20);
		tags.put("嗨", 10);
		tags.put("哈哈2", 11);
		tags.put("飞人2", 2);
		tags.put("丈夫2", 8);
		tags.put("苦恼2", 16);
		tags.put("甜过初恋2", 20);
		tags.put("洗错衣服了2", 20);
		tags.put("嗨2", 10);
		
		return tags;
	}
	
	public static List<String> tagCloud() {

		List<String> tagList = new ArrayList<String>();
		int maxsize = 48;// 最大字体大小
		int minsize = 12;
		int maxval = getMaxVal(tags);// 获取标签下文章数量的最大值
		int minval = getMinVal(tags);// 获取最小值

		int spread = maxval - minval;
		int step = 1;
		if (spread != 0) {
			step = (maxsize - minsize) / spread;
		}

		String[] color = new String[] { "#FF0000", "#FFCC00", "#FF9900","#0099FF", "#999999" };
		Iterator<String> it = tags.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			int val = tags.get(key);

			int index = RandomUtils.nextInt(color.length - 1);// 产生0到数组长度的随机数字,来实现随机颜色
			int size = Math.round(minsize + ((val - minval) * step));
			tagList.add("<a href=\"#\" style=\"font-size:" + size + "px;color:" + color[index] + "\">" + key + "</a>");
		}

		return tagList;
	}

	
	
	private static int getMinVal(Map<String, Integer> tags) {
		List<Integer> list = mapToList(tags);
		Collections.sort(list, new TagCloudComparable());
		
		return list.get(list.size()-1);
	}

	private static int getMaxVal(Map<String, Integer> tags) {
		List<Integer> list = mapToList(tags);
		Collections.sort(list, new TagCloudComparable());
		
		return list.get(0);
	}
	
	private static List<Integer> mapToList(Map<String, Integer> tags){
		List<Integer> tagList = new ArrayList<Integer>(); 
		for(String key:tags.keySet()){
			tagList.add(tags.get(key));
		}
		return tagList;
	}
	
}
