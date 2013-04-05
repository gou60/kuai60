package com.kuaileren.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class HtmlParseUtil {

	public static List<String> parseShopItemUrl(String html) {
		String nameRegex = "<h4 class=\"title\">.*?</h4>";
		Pattern namePattern = Pattern.compile(nameRegex);
		Matcher nameMatcher;

		String tempRegex1 = "<a href=.*?\">";
		Pattern tempPattern1 = Pattern.compile(tempRegex1);
		Matcher tempMatcher1;
		
		String tempRegex2 = "target.*?\">";
		
		String message = null;
		List<String> list = new ArrayList<String>();
		try {
			if (StringUtils.isBlank(html))
				return list;

			nameMatcher = namePattern.matcher(html);
			
			
			while (nameMatcher.find()) {
				message = nameMatcher.group();
				//System.out.println(name);

				tempMatcher1 = tempPattern1.matcher(message);
				if (tempMatcher1.find()) {
					message = tempMatcher1.group();

				
				message = message.replaceAll(tempRegex2, "\t");
				message = message.replaceAll("<a href=", "\t");
				message = message.replaceAll("</a>", "");
				message = message.replaceAll("\"", "");
				list.add(message);
					//System.out.println(message);
				}
				
			}


		} catch (Exception e) {
			e.printStackTrace();

		}
		return list;

	}
	
}
