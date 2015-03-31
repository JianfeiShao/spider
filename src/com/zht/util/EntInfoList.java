package com.zht.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class EntInfoList {
	
	public static String getResult(String captcha,String token,String setCookies) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost content = new HttpPost("http://gsxt.saic.gov.cn/zjgs/search/ent_info_list");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("searchType", "1"));
		params.add(new BasicNameValuePair("captcha", captcha));
		params.add(new BasicNameValuePair("session.token", token));
		params.add(new BasicNameValuePair("condition.insType",""));
		params.add(new BasicNameValuePair("condition.pageNo","1"));
		params.add(new BasicNameValuePair("condition.keyword","顺丰"));
		
//		UrlEncodedFormEntity 普通 from 表单
//		MultipartEntityBuilder enctype=”multipart/form-data”  区别
		
//		MultipartEntityBuilder entity = MultipartEntityBuilder.create();
//		entity.addTextBody("captcha", captcha);
//		entity.addTextBody("condition.pageNo", "1");
//		entity.addTextBody("condition.insType", "");
//		entity.addTextBody("session.token", token);
//		entity.addTextBody("condition.keyword", URLEncoder.encode("顺丰", "utf-8"));
//		content.setEntity(entity.build());
		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf-8");
			content.setEntity(formEntity);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpResponse responseContent;
		content.setHeader("Accept" , "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		content.setHeader("Accept-Language" , "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		content.setHeader("Accept-Encoding" , "gzip, deflate");
		content.setHeader("Cookie", setCookies);
		content.setHeader("Referer", "http://gsxt.saic.gov.cn/zjgs/");
		content.setHeader("Host", "gsxt.saic.gov.cn");
		content.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:36.0) Gecko/20100101 Firefox/36.0");
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
	
	public static void main(String[] args) {
		try {
			System.out.println(URLEncoder.encode("顺丰", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
