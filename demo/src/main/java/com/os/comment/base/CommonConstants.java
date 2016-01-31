package com.os.comment.base;

import java.io.InputStream;
import java.util.Properties;


/**
 * @author Administrator
 * 2015-5-13
 */
public class CommonConstants {

    public static String propertyFile = "project.properties";// 配置文件名字
  
    /**
     * 获取配置文件属性值
     * @param value 配置文件中的键
     * @return 配置文件键所对应的值
     */
    public static String porper(String value){
    	InputStream inputStream=CommonConstants.class.getClassLoader().getResourceAsStream(propertyFile);    
    	Properties p=new Properties();    
    	try {
    		 p.load(inputStream);  
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return p.getProperty(value);
    }
    
    /**项目访问路径*/
    public static final String contextPath=porper("contextPath");
    /**生成文件路径*/
    public static final String excelfile=porper("excelfile");
    /**上传文件保存的路径*/
    public static final String uploadfile=porper("uploadfile");
    /**找到上传文件路径**/
    public static final String findfilepath=porper("findfilepath");
    /**上传图片保存的路径*/
    public static final String uploadimgfile=porper("uploadimgfile");
    /**下载保存文件路径*/
    public static final String downfilepath=porper("downfilepath");
    /**模板文件路径*/
    public static final String templatepath=porper("templatepath");
    /**生成html保存路径*/
    public static final String htmlfilepath=porper("htmlfilepath");
    /**邮箱正则表达式*/
    public static String emailRegex="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    /**密码正则表达式*/
    public static String pwdRegex="^[_0-9a-zA-Z]{6,}$";
    /**验证码范围值*/
    public static final String verifyCodeValue=porper("verifyCodeValue");
}
