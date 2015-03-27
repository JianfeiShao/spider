package com.zht.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PopupCaptcha {
	
	public static String getToken() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getToken = new HttpGet("http://gsxt.saic.gov.cn/zjgs/search/popup_captcha");
		HttpResponse responseToken = httpClient.execute(getToken);
		String content = EntityUtils.toString(responseToken.getEntity());
		Pattern zp = Pattern.compile("session.token\": \"(.*?)\"");
		Matcher zm = zp.matcher(content);
		if(zm.find()) {
			return zm.group(1);
		}
		httpClient.close();
		return null;
	}
	
	public void mian(String[] args) {
		String content = "\"session.token\": \"402c5484-c829-4db4-81d4-aa5e65897100\"";
		Pattern zp = Pattern.compile("session.token\": \"(.*?)\"");
		Matcher zm = zp.matcher(content);
		if(zm.find()) {
			System.out.println(zm.group(1));
		}
		
		String s = "<a href=\"/book/1485278.html\" title=\"Visual C++实践与提高  ActiveX篇     含盘\">";
		Pattern p = Pattern.compile("title=\"(.*?)\"");
		Matcher m = p.matcher(s);
		if(m.find()) {
		    System.out.println(m.group(1));
		}
	}
}
