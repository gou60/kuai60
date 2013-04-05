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
		tags.put("������", 20);
		tags.put("����", 2);
		tags.put("WC", 4);
		tags.put("����", 11);
		tags.put("����", 2);
		tags.put("�ɷ�", 8);
		tags.put("����", 16);
		tags.put("�������", 20);
		tags.put("ϴ���·���", 20);
		tags.put("��", 10);
		tags.put("����2", 11);
		tags.put("����2", 2);
		tags.put("�ɷ�2", 8);
		tags.put("����2", 16);
		tags.put("�������2", 20);
		tags.put("ϴ���·���2", 20);
		tags.put("��2", 10);
		
		return tags;
	}
	
	public static List<String> tagCloud() {

		List<String> tagList = new ArrayList<String>();
		int maxsize = 48;// ��������С
		int minsize = 12;
		int maxval = getMaxVal(tags);// ��ȡ��ǩ���������������ֵ
		int minval = getMinVal(tags);// ��ȡ��Сֵ

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

			int index = RandomUtils.nextInt(color.length - 1);// ����0�����鳤�ȵ��������,��ʵ�������ɫ
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
