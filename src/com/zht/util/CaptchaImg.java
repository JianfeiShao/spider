package com.zht.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import sun.misc.BASE64Encoder;

public class CaptchaImg {
	
	@Test
	public void getCaptchaImg(){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://gsxt.saic.gov.cn/zjgs/captcha");
		InputStream inputStream = null;
		BASE64Encoder encoder = null ;
		byte[] data = null;
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			inputStream = response.getEntity().getContent();
			int count = 0;
			while (count == 0) {
				count = (int) response.getEntity().getContentLength();
				System.out.println(response.getEntity().getContentLength());
			}
			data = new byte[count];
			int readCount = 0;
			while (readCount < count) {
				readCount += inputStream.read(data, readCount, count - readCount);
			}
			inputStream.read(data);
			encoder = new BASE64Encoder();
			System.out.println(encoder.encode(data));
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
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
