package com.kuaileren.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kuaileren.domain.ArticleDO;

public class GetContentPicture {

	/**
	 * 分析网页代码，找到匹配的网页图片地址
	 * 
	 * @Description :TODO
	 * @return boolean
	 */
	public static String getPicPath(String content) {
		try{
		String searchImgReg = "http://([\\w-]+\\.)+[\\w-]+(:[0-9]+)*(/[\\w-\\.]+)*(/[\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF))";
		try{
		Pattern pattern = Pattern.compile(searchImgReg);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String path = matcher.group();
			if(path!=null && path.indexOf("a.tbcdn.cn")==-1){
				return matcher.group();
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		}catch(Exception e){}
		
		return null;
	}
	
	
	public static String getMVPath(String content) throws IOException {
		
		String searchImgReg = "http://([\\w-]+\\.)+[\\w-]+(:[0-9]+)*(/[\\w-\\.-\\=]+)*(/[\\w-]+\\.(swf))";
		
		
		try{
		Pattern pattern = Pattern.compile(searchImgReg);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static String getArticleType(String content){
		try{
			if(getMVPath(content)!=null){
				return ArticleDO.MV;
			}
			if(getPicPath(content)!=null){
				return ArticleDO.PIC;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "";
	}
	
	// 主函数url网页的地址
	public static void main(String[] args) throws IOException {

		String content = "http://img03.taobaocdn.com/tps/i3/T1bjOmXc8jXXXXXXXX-69-46.p2ng"
				+ "http://a.tbcdn.cn/tbsp.p/img/header/logo.png"
				+ "http://img03.taobaocdn.com/tps/i3/T1ogqgXfXeXXXXXXXX-168-42.p2ng"
				+ "http://img02.taobaocdn.com/tps/i2/T1hM9mXj4xXXXXXXXX.gi2f"+
				"http://player.youku.com/player.php/sid/XMzU4OTQyMzY=-/v.swf";

		String hasPic = getArticleType(content);

		System.out.println(hasPic);

	}

}
