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

public class ReadTaobaoItemsList {
	
	
	static String path = "E:\\Dropbox\\workspace\\"+new Date().getTime()+"_itemlist.txt";
	static Map<String,String> allmap = new HashMap<String,String>();
	
	public static void main(String[] args) throws Exception {
		ClassService classService = new ClassService();
		List<String> list =null;

		List<ClassDO> classList = classService.getClassList();
		
		for(ClassDO classDO:classList){
			String key = URLEncoder.encode(classDO.getFclass_leaf_name(), "GBK");
			System.out.println("========================================================");
			System.out.println(classDO.getFclass_leaf_name());
			System.out.println("========================================================");
			list =readShopItem(key);
			
			classService.insert(classDO,list);
		
		}	
	}
	
	public static List<String> readShopItem(String key) throws HttpException,IOException, InterruptedException {
		List<String> list = new ArrayList<String>();
	
			for(int i=0;i<2;i++){
				String url = "http://list.tmall.com/search_product.htm?spm=a220m.1000858.1000724.7.MJrh2N&area_code=440300&style=g&sort=d&q="+key+"&n=60&s="+(i*60)+"&cat=2#J_Filter";
				String itemStr = sendHttpClient(url);
				List<String> itemurllist = parse(itemStr);
				if(itemurllist==null || itemurllist.isEmpty()) break;
				list.addAll(itemurllist);
			}
		
		return list;
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

	
	static int c=1;
	private static List<String> parse(String html) {

		List<String> urllist = new ArrayList<String>();
		try {
			if (StringUtils.isBlank(html))
				return urllist;

			urllist = getUrlList(html);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return urllist;

	}

		
	private static List<String> getUrlList(String html) {
		String urlRegex = "<a .*? title=.*?>";
		Pattern urlPattern = Pattern.compile(urlRegex);
		Matcher urlMatcher;

		String urlRegex1 = "http.*?&";
		Pattern urlPattern1 = Pattern.compile(urlRegex1);
		Matcher urlMatcher1;
		
	 String message = "";
		List<String> list = new ArrayList<String>();
		
		urlMatcher = urlPattern.matcher(html);
		
		while (urlMatcher.find()) {
			message = urlMatcher.group();
			
			urlMatcher1 = urlPattern1.matcher(message);
			if (urlMatcher1.find()) {
				String url = urlMatcher1.group();

			url = url.replaceAll("&", "");
			url = url.replaceAll("\"", "");
			
			if(url.indexOf("http://detail.tmall.com")>-1){
				if(!url.equals(allmap.get(url))){
					list.add(url);
					allmap.put(url, url);
					System.out.println("getItem>>>>>>>> "+url);
				}
			}
			
			}

		}
		return list;
	}
	
	
}
