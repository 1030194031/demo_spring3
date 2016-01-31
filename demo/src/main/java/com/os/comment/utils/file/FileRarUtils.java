package com.os.comment.utils.file;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


/**
 * @author Administrator 文件管理操作实用类
 */
public class FileRarUtils {
	
	 /**
     * 创建多文件压缩包
     * @param response
     * @param dir 文件路径
     * @param srcfile 文件file集合
     * @param expName 文件名
     */
    public static void createRar(HttpServletResponse response,String dir,List<Map<String,Object>> srcfile,String expName){
    	 
    	 if(!new File(dir).exists()){//检测生成路径
             new File(dir).mkdirs();
         }
         File zipfile = new File(dir+"/"+expName+".rar");
         FileUtils.deleteFile(zipfile);//删除之前的压缩文件
         for(int i=0;i<srcfile.size();i++){//删除之前的xls
        	 FileUtils.deleteFile(new File(dir+"/"+expName+i+".xls"));
         }
         FileRarUtils.zipFiles(srcfile, zipfile);//生成压缩文件
         try {
        	// 设置response的Header 
             response.addHeader("Content-Disposition", "attachment;filename="+new String(zipfile.getName().getBytes("gbk"),"iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
             response.addHeader("Content-Length", "" + zipfile.length());
             
	         InputStream fis = new BufferedInputStream(new FileInputStream(zipfile)); 
	         byte[] buffer = new byte[fis.available()]; 
	         fis.read(buffer); 
	         fis.close(); 
	          
	         OutputStream toClient = new BufferedOutputStream(response.getOutputStream()); 
	         toClient.write(buffer); 
	         toClient.flush(); 
	         toClient.close(); 
         } catch (IOException e) {
   			e.printStackTrace(); 
   		 }

    }

	/**
	* 压缩文件
	* @param srcfile File[] 需要压缩的文件列表
	* @param zipfile File 压缩后的文件
	* @author
	*/
 	public static void zipFiles(List<Map<String,Object>> srcfile, File zipfile) {
 		byte[] buf = new byte[1024];
 		String ZIP_ENCODEING = "GBK"; 
 		try {
 			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
 			out.setEncoding(ZIP_ENCODEING);
 			for (int i = 0; i < srcfile.size(); i++) {
 				Map<String,Object> fMap = srcfile.get(i);
 				File file=(File) fMap.get("file");
 				FileInputStream in = new FileInputStream(file);
 				out.putNextEntry(new ZipEntry(fMap.get("name")+""));
 				int len;
 				while ((len = in.read(buf)) > 0) {
 					out.write(buf, 0, len);
 				}
 				out.closeEntry();
 				in.close();
 			}
 			out.close();
 		} catch (IOException e) {
 			e.printStackTrace(); 
 		}
 	}
 	
 	/**
 	 * 解压单个文件
 	 * @param zipPath 压缩文件路径 "d:/hello.zip"
 	 * @param filePath 解压缩文件路径"d:/hello.txt"
 	 * @param zipFileName 解压缩文件名 "hello.txt"
 	 * @throws IOException
 	 */
 	public static void unZip(String zipPath,String filePath,String zipFileName) throws IOException{
 		File file = new File(zipPath);
		File outFile = new File(filePath);
		ZipFile zipFile = new ZipFile(file);
		ZipEntry entry = zipFile.getEntry(zipFileName);//zipFileName 为压缩包中文件的名称
		InputStream input = zipFile.getInputStream(entry);
		OutputStream output = new FileOutputStream(outFile);
		int temp = 0;
		while((temp = input.read()) != -1){
			output.write(temp);
		}
		input.close();
		output.close();
 	}
 	
 	/**
 	 * 解压缩文件
 	 * @param zipFile 压缩文件路径 "d:/hello.zip"
 	 * @param descDir 解压缩文件目录 "d:/hello"
 	 * @throws IOException
 	 */
 	public static void unZipFiles(File zipFile,String descDir)throws IOException{  
        File pathFile = new File(descDir);  
        if(!pathFile.exists()){  
            pathFile.mkdirs();  
        }  
        ZipFile zip = new ZipFile(zipFile);  
        for(Enumeration<?> entries = zip.getEntries();entries.hasMoreElements();){  
            ZipEntry entry = (ZipEntry)entries.nextElement();  
            String zipEntryName = entry.getName();  
            InputStream in = zip.getInputStream(entry);  
            String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;  
            //判断路径是否存在,不存在则创建文件路径  
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));  
            if(!file.exists()){  
                file.mkdirs();  
            }  
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
            if(new File(outPath).isDirectory()){  
                continue;  
            }  
            //输出文件路径信息  
            System.out.println(outPath);  
              
            OutputStream out = new FileOutputStream(outPath);  
            byte[] buf1 = new byte[1024];  
            int len;  
            while((len=in.read(buf1))>0){  
                out.write(buf1,0,len);  
            }  
            in.close();  
            out.close();  
        }  
        System.out.println("******************解压完毕********************");  
    }  
}
