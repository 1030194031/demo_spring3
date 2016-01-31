/**
 * Administrator
 * 2015-5-14
 */
package com.os.comment.utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class test {
	private int maxLength;
	private HashMap<String,String> filterStrs=new HashMap<String,String>();
	
	private String initStr(int n){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<n;i++){
			sb.append('*');
		}
		return sb.toString();
	}
	private String getNextStr(String str,int start,int slength){
		if(start==0){
			str=str.substring(slength);
		}else if(start+slength<str.length()){
			str=str.substring(start+slength);
		}
		return str;
	}
	private StringBuffer getFilterStr(StringBuffer sb,String str,int start,String s){
		if(start!=0){
			sb.append(str.substring(0,start));
		}
		sb.append(filterStrs.get(s));
		return sb;
	}
	public String filter(String str) {
		StringBuffer resultStr=new StringBuffer();
		for(int start=0;start<str.length();start++){
			for(int end=start+1;end<=str.length()&&end<=start+maxLength;end++){
				String s=str.substring(start, end);
				int slength=s.length();
				if(filterStrs.containsKey(s)){
				resultStr=getFilterStr(resultStr,str,start,s);
				str=getNextStr(str,start,slength);
				start=0;
				end=start;
			}
		}
		}
		resultStr.append(str);
		return resultStr.toString();
	}
	
	public void put(String key) {
		int keyLength=key.length();
		filterStrs.put(key, initStr(keyLength));
		if(keyLength>this.maxLength)
		maxLength=keyLength;
	}
	public static void main(String[] agrs){
//		test t=new test();
//		t.put("TMD");
//		t.put("TNND");
//		t.put("操");
//		t.put("NND");
//		String str="啦啦啦啦阿拉啦啦啦啦啦123nnd";
//		System.out.println(t.filter(str.toUpperCase()));
		
		 String   str   =   "*adCVs*34_a _09_b5*[/435^*&城池()^$$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？";        
         System.out.println(str);        
         System.out.println(StringFilter(str)); 
	}
	
	 public static String StringFilter(String str) throws PatternSyntaxException{        
       // 清除掉所有特殊字符     
	   String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";     
	   Pattern   p   =   Pattern.compile(regEx);        
	   Matcher   m   =   p.matcher(str);        
	   return   m.replaceAll("").trim();        
	 }   
}
