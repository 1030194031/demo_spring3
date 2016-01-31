package com.os.service.subject;

import com.os.entity.subject.Subject;

import java.util.List;

/**
 * 专业管理接口 User: 
 */
public interface SubjectService {
	/**
	 * 创建专业
	 * @param subject
	 */
	public void createSubject(Subject subject);
	/**
	 * 删除专业
	 * @param ids
	 */
	public void deleteSubjectById(String ids);
	/**
	 * 修改专业名称
	 * @param subject
	 */
	public void updateSubject(Subject subject);
	/**
	 * 修改父专业
	 * @param subject
	 */
	public void updateSubjectParent(Subject subject);
	/**
	 * 获取专业列表
	 * @param subject
	 * @return
	 */
	public List<Subject> getSubjectList(Subject subject);
}