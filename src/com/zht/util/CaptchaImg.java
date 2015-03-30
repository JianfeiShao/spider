package com.zht.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.slf4j.Logger;

public class CaptchaImg {
	
	public static String getCaptchaImg(){
		
		Logger log = org.slf4j.LoggerFactory.getLogger(CaptchaImg.class.toString());
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://gsxt.saic.gov.cn/zjgs/captcha");
		InputStream inputStream = null;
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			inputStream = response.getEntity().getContent();
			OutputStream out = new FileOutputStream(new File("c:\\"+UUID.randomUUID()+".jpg"));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {  
			    out.write(bytes, 0, read);  
			}
			Header[] header = response.getHeaders("Set-Cookie");
			StringBuffer sb = new StringBuffer();
			for (Header hd: header) {
				log.debug(hd.getValue());
				sb.append(hd.getValue()+";");
			}
			log.debug(sb.substring(0,sb.length()-1));
			return sb.substring(0,sb.length()-1);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
		return null;
	}
	
	
	@Test
	public void bored(){
		System.out.println(game(new int[]{8,7,5,3}));
	}
	
	static int game(int[] arr){
		int len =arr.length;
		if(len<2)
			throw new RuntimeException("length exception.");
		int[] arrA = new int[len%2==0?len/2:len/2+1];
		int[] arrB = new int[len/2];
		int first = 0;
		int end = len - 1;
		int aIndex = 0;
		int bIndex = 0;
		int offset = 0;
		while(len > 0){
			if(++offset % 2 != 0){
				if(++offset%2!=0){
					arrA[aIndex++] = arr[first++];
				}else {
					arrA[aIndex++] = arr[end--];
				}
			}else{
				if(arr[first] >= arr[end]){
					arrB[bIndex++] = arr[first++];
				}else{
					arrB[bIndex++] = arr[end--];
				}
			}
			len--;
		}
		int sumA = sum(arrA);
		int sumB = sum(arrB);
		String resultStr = sumA>sumB? "a win.":"b win.";
		System.out.println(resultStr);
		return sumA>sumB?sumA:sumB;
	}
	static int sum(int[] arr){
		int result = 0;
		for(int i=0;i<arr.length;i++){
			result+=arr[i];
		}
		return result;
	}
}
