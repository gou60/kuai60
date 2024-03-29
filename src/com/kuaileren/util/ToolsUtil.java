package com.kuaileren.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kuaileren.common.UserResult;
import com.kuaileren.domain.ArticleDO;
import com.kuaileren.domain.CountInfoDO;
import com.kuaileren.domain.KuaiLeDO;
import com.kuaileren.domain.UserDO;
import com.kuaileren.service.ArticleService;
import com.kuaileren.service.StatisticsSeviceJob;
import com.kuaileren.service.UserService;

public class ToolsUtil {
	
	private static final Log log = LogFactory.getLog(ToolsUtil.class);
	
	public static String getUserIconUrl(int userId){
		if(userMap==null || userMap.isEmpty()) return "missing.png";
		
		UserDO user = userMap.get(userId);
		
		if(user == null) return "missing.png";
		
		return user.getIconUrl();
		
	}
	
	
	public static Map<Integer,UserDO> userMap = new HashMap<Integer,UserDO>();

	
	public static void initUser(UserService userService){
		
		try{
		if(userMap !=null && !userMap.isEmpty()) return ;
		
		UserResult result = userService.queryUserList(new UserDO());
		
		if(result == null || !result.isSuccess()) return;
		
		log.info(" 加载用户数据："+result.getUserList().size()+" 条");
		
		List<UserDO> userList = result.getUserList();
		for(UserDO user:userList){
			userMap.put(user.getUserId(), user);
			System.out.println(user.toString());
		}
		
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		
	}
	
	public boolean isLoginPage(String url){
		if(url == null || url.isEmpty()) return false;
		
		if(url.indexOf("/login.htm")>-1){
			return true;
		}
		
		if(url.indexOf("/login.vm")>-1){
			return true;
		} 
		
		return false;
	}
	
	public static int getMaxUserId(){
		int maxUserId = 0;
		for(int userId:ToolsUtil.userMap.keySet()){
			
			if(userId>maxUserId){
				maxUserId = userId;
			}
		}
		return maxUserId;
	}
	
	public static void addUserToMap(UserDO user){
		if(user == null) return ;
		
		userMap.put(user.getUserId(), user);
	}
	
	static CountInfoDO countInfo = null;
	
	public static void initCountInfo(ArticleService articleService){
		
		try{
		if(countInfo!=null) return ;
		
		countInfo = new CountInfoDO();
		ArticleDO article = new ArticleDO();
		article.setCreateTime(Calendar.getInstance().getTime());
		List<ArticleDO> lastArticle = articleService.getLastArticleList(article);
		
		countInfo.setLastArticle(lastArticle);
		
		List<KuaiLeDO> lastday = new ArrayList<KuaiLeDO>();
		
		countInfo.setLastday(lastday);
		
		KuaiLeDO yesterday = new KuaiLeDO();
		countInfo.setYesterday(yesterday);
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}
	}

	
	public static List<KuaiLeDO> getKuaiLeRen() {
		
		List<KuaiLeDO> countUserList = StatisticsSeviceJob.countUserList;
		
		for(KuaiLeDO kuai:countUserList){
			kuai.setUserName(ToolsUtil.getUserNameByUserId(kuai.getUserId()));
			kuai.setKuaiLeCount(kuai.getKuaiArticleCount());
			kuai.setLoginKuaiLeCount(100);
		}

		if(CollectionUtils.isEmpty(countUserList)) countUserList = new ArrayList<KuaiLeDO>();
		return countUserList;
	}

	public static KuaiLeDO getBestKuaileRen(){
		return  getKuaiLeRen().get(0);
	}
	
	
	public int getTitleLength(String title){
		if(title == null || title.isEmpty()) return 0;
		
		int count = ChineseUtil.chineseCount(title);
		int len = title.length()+count;
		
			len = len * 13;
		
		return len>500?500:len;
	}
	public static Map<Integer, UserDO> getUserMap() {
		return userMap;
	}

	public static void setUserMap(Map<Integer, UserDO> userMap) {
		ToolsUtil.userMap = userMap;
	}

	public static CountInfoDO getCountInfo() {
		return countInfo;
	}

	public static void setCountInfo(CountInfoDO countInfo) {
		ToolsUtil.countInfo = countInfo;
	}
	
	public static UserDO getUserInfoByUserId(int userId){
		return getUserMap().get(userId);
	}
	
	public static String getUserNameByUserId(int userId){
		UserDO user = getUserInfoByUserId(userId);
		if(user!=null)return user.getUserName();
		
		return null;
	}
	
	
	public List<String> tag_cloud(){
		TagCloudUtil.initTagCloud();
		return TagCloudUtil.tagCloud();
	}
  
}
