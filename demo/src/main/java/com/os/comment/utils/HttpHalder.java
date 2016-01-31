package com.os.comment.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * 2015-7-31
 */
public class HttpHalder {
	
	public static void main(String[] args) {
		try {
			Map<String,String> map=new HashMap<String, String>();
			map.put("showapi_appid", "4787");
			String timestamp=DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
			map.put("showapi_timestamp",timestamp);
			map.put("showapi_sign","fb3fac5858c5433c96f777782eff6d1a");
			map.put("area","石家庄");
			map.put("needIndex", "0");
			map.put("needMoreDay", "0");
			String result=postMethod("http://route.showapi.com/9-2",map);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get请求
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String getMethod(String url,Map<String,String> map) throws Exception{
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		if(ObjectUtils.isNotNull(map)){
			url+="?";
			for(Map.Entry<String, String> entry: map.entrySet()) {
				url+=entry.getKey()+"="+URLEncoder.encode(entry.getValue(),"utf-8")+"&";
			}
			url=url.substring(0,url.length()-1);
		}
		GetMethod method = new GetMethod(url);
		client.executeMethod(method);
		String result = method.getResponseBodyAsString();
		method.releaseConnection();
		return result;
	}

	/**
	 * post请求(一般用post请求)
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String postMethod(String url,Map<String,String> map) throws Exception{
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		PostMethod post = new PostMethod(url);
		int index=0;
		if(ObjectUtils.isNotNull(map)){
			NameValuePair[] nameValue = new NameValuePair[map.size()];
			for(Map.Entry<String, String> entry: map.entrySet()) {
				nameValue[index]=new NameValuePair(entry.getKey(),entry.getValue());
				index++;
			}
			post.setRequestBody(nameValue);
		}
		client.executeMethod(post);
		String result = post.getResponseBodyAsString();
		post.releaseConnection();
		return result;
	}
}
