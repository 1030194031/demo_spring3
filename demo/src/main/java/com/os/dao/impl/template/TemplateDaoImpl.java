package com.os.dao.impl.template;

import com.os.comment.base.BaseDaoImpl;
import com.os.dao.template.TemplateDao;
import com.os.entity.template.Template;
import org.springframework.stereotype.Repository;

@Repository("templateDao")
public class TemplateDaoImpl extends BaseDaoImpl implements TemplateDao {

	@Override
	public void createTemplate(Template template) {
		// TODO Auto-generated method stub
		this.insert("TemplateMapper.createTemplate", template);
	}

	@Override
	public void updateTemplate(Template template) {
		// TODO Auto-generated method stub
		this.update("TemplateMapper.updateTemplate", template);
	}

	@Override
	public Template getTemplateById(long id) {
		// TODO Auto-generated method stub
		return this.selectOne("TemplateMapper.getTemplateById", id);
	}
	
}
