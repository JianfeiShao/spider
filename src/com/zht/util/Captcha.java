package com.zht.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Captcha {
	public String getCaptcha() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet captchaImg = new HttpGet("http://gsxt.saic.gov.cn/zjgs/captcha?preset=&ra=0.4095862335565241");
		HttpResponse responseImg = httpClient.execute(captchaImg);
		return null;
	}
}
