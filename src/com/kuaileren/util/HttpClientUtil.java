package com.kuaileren.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientUtil {

	public static String sendHttpClient(String itemUrl) {
		try{
		System.out.println("access   "+itemUrl+"........");	
			
		HttpClient  client = new  HttpClient ();  
		
		GetMethod get = new  GetMethod(itemUrl);  
		get.setRequestHeader("User-Agent","Mozilla/5.0 (X11; Linux i686; rv:5.0) Gecko/20100101 Firefox/5.0");

		//get.setRequestHeader("Cookie" , "visitkey=10916219027349561; pvid=6487059550; flv=-; pt2gguin=o1304310326; ptcz=af941f8c5c85262afb6911c1d9925f1f3d5bfbd8e615204fff6280e260631fb7; pgv_pvid=8055676628; ssuserid=1331436808447; searchScreen=1366x768; sw=1; buy_uin=1304310326; PPRD_P=IA.20084.1.3; PPRD_S=PVS.PAIPAI-PVSE.0; pgv_info=pgvReferrer=&ssid=s5600430038; sssrc=3|www.paipai.com; sspvnum=1; ssclosed=1; verifysession=h00cee22a44b9a8e346229b365672118f487799af8258f23afa2d3676bff95b2b05a80fe5ff7bc228cbafced35ccc146505; ptui_loginuin=1304310326; uin=1304310326; skey=@6fWBKIny7; ptisp=ctc; show_id=; hs=1%2F0%2F0%2FE%u4E3D%u5A1C; mp=2%3A1%3A0%3A0%3A0%3A268435460%3A0%3A0%3A0%3A0%3A0%3A20%3A0%3A0; selleruin=0");  
        client.executeMethod(get);  
        String responseString = get.getResponseBodyAsString();  
        return responseString ;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "";

	}
	
}
