package com.gou60.bot;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;

public class ReadArticleList {
	
	static Map<String,String> allmap = new HashMap<String,String>();
	
	public static void main(String[] args) throws Exception {
		 
		List<String> list = new ArrayList<String>();

		for(int i=34;i<35;i++){
			List<String> list1 = read("http://www.liwuyou.com/article_cat.php?id="+i);
			list.addAll(list1);
		}
		
		for(String url:list){
			System.out.println(url);
			String[] urls = url.split(",");
			if(urls!=null && urls.length>1){
				readArticle(urls[0].trim());
			}
		}

		//classService.insert(classDO,list);
		
		
		
	}
	
	private static void readArticle(String url) throws HttpException, IOException {
		String itemStr = sendHttpClient("http://www.liwuyou.com/"+url);
		
		//System.out.println(url);
		parseArticle(itemStr,url);
	}

	private static void parseArticle(String html,String url) {
		//<a href="article_cat.php?id=13">ÀÕ ≤√¥¿ÒŒÔ£ø</a>
		String urlRegex = "<title>.*?</title>";
		
		Pattern urlPattern = Pattern.compile(urlRegex);
		Matcher urlMatcher;
 
		String message = "";
		List<String> list = new ArrayList<String>();
		
		urlMatcher = urlPattern.matcher(html);
		
		while (urlMatcher.find()) {
			message = urlMatcher.group();
			message = message.replaceAll("<title>", "");
			message = message.replaceAll("</title>", "");
			
			String[] mess = message.split("_");
			if(mess!=null){
				for(int i=0;i<mess.length;i++){
					System.out.print(mess[i]+"_");
				}
				System.out.println();
			}
		}
	 
	}

	public static List<String> read(String url) throws HttpException,IOException, InterruptedException {
				String itemStr = sendHttpClient(url);
			
				return getUrlList(itemStr);
	}

	public static String sendHttpClient(String itemUrl) throws HttpException,IOException {
		HttpClient  client = new  HttpClient ();  
		
		GetMethod get = new  GetMethod(itemUrl);  
		get.setRequestHeader("User-Agent","Mozilla/5.0 (X11; Linux i686; rv:5.0) Gecko/20100101 Firefox/5.0");

		//get.setRequestHeader("Cookie" , "visitkey=10916219027349561; pvid=6487059550; flv=-; pt2gguin=o1304310326; ptcz=af941f8c5c85262afb6911c1d9925f1f3d5bfbd8e615204fff6280e260631fb7; pgv_pvid=8055676628; ssuserid=1331436808447; searchScreen=1366x768; sw=1; buy_uin=1304310326; PPRD_P=IA.20084.1.3; PPRD_S=PVS.PAIPAI-PVSE.0; pgv_info=pgvReferrer=&ssid=s5600430038; sssrc=3|www.paipai.com; sspvnum=1; ssclosed=1; verifysession=h00cee22a44b9a8e346229b365672118f487799af8258f23afa2d3676bff95b2b05a80fe5ff7bc228cbafced35ccc146505; ptui_loginuin=1304310326; uin=1304310326; skey=@6fWBKIny7; ptisp=ctc; show_id=; hs=1%2F0%2F0%2FE%u4E3D%u5A1C; mp=2%3A1%3A0%3A0%3A0%3A268435460%3A0%3A0%3A0%3A0%3A0%3A20%3A0%3A0; selleruin=0");  
        client.executeMethod(get);  
        String responseString = get.getResponseBodyAsString();  
        return responseString ;

	}

	
	
		
	private static List<String> getUrlList(String html) {
		String urlRegex = "<a href=\"article.php.*?title=.*?</a>";
		Pattern urlPattern = Pattern.compile(urlRegex);
		Matcher urlMatcher;

		String urlRegex1 = "article.php.*?class=";
		Pattern urlPattern1 = Pattern.compile(urlRegex1);
		Matcher urlMatcher1;
		
	 String message = "";
		List<String> list = new ArrayList<String>();
		
		urlMatcher = urlPattern.matcher(html);
		
		while (urlMatcher.find()) {
			message = urlMatcher.group();
			
			//System.out.println(message);
			
			urlMatcher1 = urlPattern1.matcher(message);
			if (urlMatcher1.find()) {
				String url = urlMatcher1.group();

				url = url.replaceAll("title=", ",");
				url = url.replaceAll("class=" ,"");
				url = url.replaceAll("\"" ,"");
				list.add(url);
				//System.out.println(url);

			}

		}
		return list;
	}
	
	
}
