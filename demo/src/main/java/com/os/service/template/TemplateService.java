package com.os.service.template;

import com.os.entity.template.Template;

/**
 * 模板文件service: 
 */
public interface TemplateService {
	/**
	 * 创建模板文件
	 * @param template
	 */
	public void createTemplate(Template template);
	/**
	 * 修改模板文件
	 * @param template
	 */
	public void updateTemplate(Template template);
	/**
	 * 获得模板文件
	 * @param id
	 * @return
	 */
	public Template getTemplateById(long id);
}