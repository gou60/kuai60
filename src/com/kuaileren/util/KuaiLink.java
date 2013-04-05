package com.kuaileren.util;

import org.apache.velocity.tools.struts.SecureLinkTool;

public class KuaiLink extends SecureLinkTool {
	
	@Override
	public org.apache.velocity.tools.view.LinkTool setURI(String uri) {
		
		String contextUrl = getContextURL();
		
		if(contextUrl.endsWith("/")){
			uri = contextUrl +uri;
		}else{
			uri = contextUrl + "/" +uri;
		}
		
		
		return super.setURI(uri);
	}

	
	


}
