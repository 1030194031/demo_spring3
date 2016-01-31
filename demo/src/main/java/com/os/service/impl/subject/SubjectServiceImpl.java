package com.os.service.impl.subject;

import com.os.dao.subject.SubjectDao;
import com.os.entity.subject.Subject;
import com.os.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectDao subjectDao;

	@Override
	public void createSubject(Subject subject) {
		// TODO Auto-generated method stub
		subjectDao.createSubject(subject);
	}

	@Override
	public void deleteSubjectById(String ids) {
		// TODO Auto-generated method stub
		subjectDao.deleteSubjectById(ids);
	}

	@Override
	public void updateSubject(Subject subject) {
		// TODO Auto-generated method stub
		subjectDao.updateSubject(subject);
	}

	@Override
	public void updateSubjectParent(Subject subject) {
		// TODO Auto-generated method stub
		subjectDao.updateSubjectParent(subject);
	}

	@Override
	public List<Subject> getSubjectList(Subject subject) {
		// TODO Auto-generated method stub
		return subjectDao.getSubjectList(subject);
	}

}
