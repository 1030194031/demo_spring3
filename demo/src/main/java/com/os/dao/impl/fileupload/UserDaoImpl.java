package com.os.dao.impl.fileupload;

import com.os.comment.base.BaseDaoImpl;
import com.os.dao.fileupload.FileUpLoadDao;
import com.os.entity.fileupload.FileUpLoad;
import com.os.entity.fileupload.QueryFileUpLoad;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("fileuploadDao")
public class UserDaoImpl extends BaseDaoImpl implements FileUpLoadDao {

	@Override
	public void createFileUpLoad(FileUpLoad file) {
		// TODO Auto-generated method stub
		this.insert("FileUpLoadMapper.createFileUpLoad", file);
	}

	@Override
	public void deleteFileUpLoadById(String ids) {
		// TODO Auto-generated method stub
		this.delete("FileUpLoadMapper.deleteFileUpLoadById", ids);
	}

	@Override
	public FileUpLoad getFileUpLoadById(long id) {
		// TODO Auto-generated method stub
		return this.selectOne("FileUpLoadMapper.getFileUpLoadById", id);
	}

	@Override
	public List<FileUpLoad> getFileUpLoadList(QueryFileUpLoad query) {
		// TODO Auto-generated method stub
		return selectList("FileUpLoadMapper.getFileUpLoadList", query);
	}
}
