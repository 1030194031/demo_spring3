package com.os.dao.impl.subject;

import com.os.comment.base.BaseDaoImpl;
import com.os.dao.subject.SubjectDao;
import com.os.entity.subject.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subjectDao")
public class SubjectDaoImpl extends BaseDaoImpl implements SubjectDao {

	@Override
	public void createSubject(Subject subject) {
		// TODO Auto-generated method stub
		insert("SubjectMapper.createSubject", subject);
	}

	@Override
	public void deleteSubjectById(String ids) {
		// TODO Auto-generated method stub
		delete("SubjectMapper.deleteSubjectById", ids);
	}

	@Override
	public void updateSubject(Subject subject) {
		// TODO Auto-generated method stub
		update("SubjectMapper.updateSubject", subject);
	}

	@Override
	public void updateSubjectParent(Subject subject) {
		// TODO Auto-generated method stub
		update("SubjectMapper.updateSubjectParent", subject);
	}

	@Override
	public List<Subject> getSubjectList(Subject subject) {
		// TODO Auto-generated method stub
		return selectList("SubjectMapper.getSubjectList", subject);
	}

	
}
