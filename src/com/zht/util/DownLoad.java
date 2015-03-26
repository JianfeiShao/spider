package com.zht.util;

import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class DownLoad {
	private static Set<String> visitedUrl = new HashSet<String>();
	
	public void getContent() throws Exception{
		
		//���IP
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//http://gsxt.saic.gov.cn/zjgs/security/verify_ip ������ܻ����IP
		HttpPost httpPost = new HttpPost("http://gsxt.saic.gov.cn/zjgs/security/verify_ip");
		HttpResponse response =  httpClient.execute(httpPost);
		System.out.println("IP��֤"+EntityUtils.toString(response.getEntity()));
		
		//http://gsxt.saic.gov.cn/zjgs/security/verify_keyword POST
		//keyword = ˳��
		HttpPost httpPostSearch = new HttpPost("http://gsxt.saic.gov.cn/zjgs/security/verify_keyword");
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addTextBody("keyword", "˳��");
		httpPostSearch.setEntity(builder.build());
		HttpResponse responseSearch =  httpClient.execute(httpPostSearch);
		System.out.println("����"+EntityUtils.toString(responseSearch.getEntity()));
		
		//http://gsxt.saic.gov.cn/zjgs/search/popup_captcha ������֤��,����������
		HttpGet httpGetCaptcha = new HttpGet("http://gsxt.saic.gov.cn/zjgs/search/popup_captcha");
		HttpResponse responseCaptcha = httpClient.execute(httpGetCaptcha);
		System.out.println("��֤��"+EntityUtils.toString(responseCaptcha.getEntity()));
		
		//http://gsxt.saic.gov.cn/zjgs/captcha?preset=&ra=0.4095862335565241 get
//		HttpGet captchaImg = new HttpGet("http://gsxt.saic.gov.cn/zjgs/captcha?preset=&ra=0.4095862335565241");
//		HttpResponse responseImg = httpClient.execute(captchaImg);
		
		//http://gsxt.saic.gov.cn/zjgs/security/verify_captcha ��֤��϶�
		HttpPost httpPostVerifyCaptcha = new HttpPost("http://gsxt.saic.gov.cn/zjgs/security/verify_captcha");
		MultipartEntityBuilder builderVerifyCaptcha = MultipartEntityBuilder.create();
		builderVerifyCaptcha.addTextBody("captcha", "14");
		builderVerifyCaptcha.addTextBody("session.token", "14");
		httpPostSearch.setEntity(builderVerifyCaptcha.build());
		HttpResponse responseVerify =  httpClient.execute(httpPostVerifyCaptcha);
		System.out.println("��֤���ж�"+EntityUtils.toString(responseVerify.getEntity()));
		
		//http://gsxt.saic.gov.cn/zjgs/search/ent_info_list ��ʾ�б�
		HttpPost entInfoList = new HttpPost("http://gsxt.saic.gov.cn/zjgs/search/ent_info_list");
		MultipartEntityBuilder builderEntInfoList = MultipartEntityBuilder.create();
		builderEntInfoList.addTextBody("searchType", "14");
		builderEntInfoList.addTextBody("captcha", "14");
		builderEntInfoList.addTextBody("session.token", "14");
		builderEntInfoList.addTextBody("condition.keyword", "14");
		httpPostSearch.setEntity(builderEntInfoList.build());
		HttpResponse responseEntInfoList =  httpClient.execute(entInfoList);
		System.out.println("�����"+EntityUtils.toString(responseEntInfoList.getEntity()));
		
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(URLEncoder.encode("˳��","utf-8"));
	}
}
