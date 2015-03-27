package com.zht.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class EntInfoList {
	
	public static String getResult(String captcha,String token) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost content = new HttpPost("http://gsxt.saic.gov.cn/zjgs/search/ent_info_list");
		MultipartEntityBuilder entity = MultipartEntityBuilder.create();
		entity.addTextBody("captcha", captcha);
		entity.addTextBody("condition.pageNo", "1");
		entity.addTextBody("condition.insType", "");
		entity.addTextBody("session.token", token);
		entity.addTextBody("condition.keyword", "Ë³·á");
		content.setEntity(entity.build());
		HttpResponse responseContent;
		try {
			responseContent = httpClient.execute(content);
			return EntityUtils.toString(responseContent.getEntity());
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
		} return null;
	}
}
