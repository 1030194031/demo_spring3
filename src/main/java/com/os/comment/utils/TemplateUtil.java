package com.os.comment.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class TemplateUtil {
	public static void main(String[] args) {
		test();
	}
	
	/**
	 * 测试方法
	 */
	public static void test(){
		try{
			String vmContent="${dataMap.value1}中文<br/>${dataMap.value2}";
			String vmFileUrl ="";//CommonConstants.templateFileDir;
			//生成模板文件
			String fileName = createTemplateFile(vmContent,vmFileUrl);
			//模板上下文
			Map<String,Object> dataMap = new HashMap<String, Object>();
			dataMap.put("value1", "ssssssssssssssssssssss");
			dataMap.put("value2", "中文");
			VelocityContext context = new VelocityContext();
	        context.put("dataMap", dataMap);
	        
			//获取模板生成器
			VelocityEngine engine = initEngine("");//CommonConstants.templateFileDir
			//html文件生成全路径 
			File file = new File("","UTF-8");//CommonConstants.htmlFileDir+"/index.html"
			boolean createSuccess = createHtmlFile(engine,fileName,file,context);
			if(createSuccess){
				System.out.println("文件生成完成");
			}else{
				System.out.println("文件生成失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成静态的HTML文件
	 * @param engine 模板生成器
	 * @param tempFileName 模板文件
	 * @param file HTML生成的全路径
	 * @param context 模板上下文
	 * @return true生成成功 false生成失败
	 * @throws Exception
	 */
	public static boolean createHtmlFile(VelocityEngine engine,String tempFileName,File file,VelocityContext context){
		try{
			if(file.getParentFile().exists()==false){
	        	file.getParentFile().mkdirs();
	        }
			Template temp = engine.getTemplate(tempFileName, "UTF-8");
			FileOutputStream tempFos = new FileOutputStream(file); 
	        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(tempFos,"UTF-8"));
	        temp.setEncoding("UTF-8");
	        temp.merge(context, writer);
	        writer.close();
	        tempFos.close();
	        return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 初始化模板生成器
	 * @return VelocityEngine
	 * @throws Exception
	 */
	public static VelocityEngine initEngine(String genDir) throws Exception{
		VelocityEngine engine = new VelocityEngine();
		Properties properties = new Properties();  
        properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,genDir);  
        properties.setProperty(VelocityEngine.INPUT_ENCODING,"UTF-8"); 
		properties.setProperty(VelocityEngine.OUTPUT_ENCODING,"UTF-8"); 
        engine.init(properties);   
        return engine;
	}
	
	/***
	 * 创建模板文件
	 * @param fileContext 模板内容
	 * @param createDirUrl 模板保存目录
	 */
	public static String createTemplateFile(String fileContext,String createDirUrl){
		BufferedWriter bw = null;
		try{
			String fileDirUrl=createDirUrl+".vm";
			File file = new File(fileDirUrl);
			if(file.getParentFile().exists()==false){
				file.getParentFile().mkdirs();
			}
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			bw.write(fileContext);
			return fileDirUrl;
		}catch (Exception e) {
			System.out.println("生成模板文件出错");
			e.printStackTrace();
			return null;
		}finally{
			if(bw!=null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
