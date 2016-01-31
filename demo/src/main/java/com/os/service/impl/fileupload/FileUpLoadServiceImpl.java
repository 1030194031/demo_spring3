package com.os.service.impl.fileupload;

import com.os.dao.fileupload.FileUpLoadDao;
import com.os.entity.fileupload.FileUpLoad;
import com.os.entity.fileupload.QueryFileUpLoad;
import com.os.service.fileupload.FileUpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fileuploadService")
public class FileUpLoadServiceImpl implements FileUpLoadService {
	@Autowired
	private FileUpLoadDao fileUpLoadDao;

	@Override
	public void createFileUpLoad(FileUpLoad file) {
		// TODO Auto-generated method stub
		fileUpLoadDao.createFileUpLoad(file);
	}

	@Override
	public void deleteFileUpLoadById(String ids) {
		// TODO Auto-generated method stub
		fileUpLoadDao.deleteFileUpLoadById(ids);
	}

	@Override
	public FileUpLoad getFileUpLoadById(long id) {
		// TODO Auto-generated method stub
		return fileUpLoadDao.getFileUpLoadById(id);
	}

	@Override
	public List<FileUpLoad> getFileUpLoadList(QueryFileUpLoad query) {
		// TODO Auto-generated method stub
		return fileUpLoadDao.getFileUpLoadList(query);
	}
}
