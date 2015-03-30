package com.zht.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PopupCaptcha {
	
	public static String getToken() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet getUrl= new HttpGet("http://gsxt.saic.gov.cn/zjgs/search/popup_captcha");
		String setCookies = CaptchaImg.getCaptchaImg();
		
		getUrl.addHeader("Cookie", setCookies);//这里是图片Cookies
		
//		getUrl.setHeader("Cookie", setCookies);
		
		HttpResponse responseToken;
		try {
			responseToken = httpClient.execute(getUrl);
			String content = EntityUtils.toString(responseToken.getEntity());
			Pattern zp = Pattern.compile("session.token\": \"(.*?)\"");
			Matcher zm = zp.matcher(content);
			if(zm.find()) {
				return zm.group(1)+","+setCookies;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
