package com.os.service.fileupload;

import com.os.entity.fileupload.FileUpLoad;
import com.os.entity.fileupload.QueryFileUpLoad;

import java.util.List;

/**
 * 上传文件service
 * @author Administrator
 * 2015-5-15
 */
public interface FileUpLoadService {
	/**
	 * 创建上传信息
	 * @param file
	 */
	public void createFileUpLoad(FileUpLoad file);
	/**
	 * 删除上传信息
	 * @param ids
	 */
	public void deleteFileUpLoadById(String ids);
	/**
	 * 根据id查询上传信息
	 * @param id
	 * @return
	 */
	public FileUpLoad getFileUpLoadById(long id);
	/**
	 * 上传信息列表
	 * @param query
	 * @return
	 */
	public List<FileUpLoad> getFileUpLoadList(QueryFileUpLoad query);
}