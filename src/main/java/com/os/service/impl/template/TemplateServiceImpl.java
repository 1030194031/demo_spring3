package com.os.service.impl.template;

import com.os.dao.template.TemplateDao;
import com.os.entity.template.Template;
import com.os.service.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateDao templateDao;

	@Override
	public void createTemplate(Template template) {
		// TODO Auto-generated method stub
		templateDao.createTemplate(template);
	}

	@Override
	public void updateTemplate(Template template) {
		// TODO Auto-generated method stub
		templateDao.updateTemplate(template);
	}

	@Override
	public Template getTemplateById(long id) {
		// TODO Auto-generated method stub
		return templateDao.getTemplateById(id);
	}
}
