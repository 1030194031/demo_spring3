/**
 * Administrator
 * 2015-5-5
 */
package com.os.controller.template;

import com.os.comment.base.BaseController;
import com.os.comment.base.CommonConstants;
import com.os.comment.utils.TemplateUtil;
import com.os.entity.template.Template;
import com.os.service.template.TemplateService;
import com.os.service.user.UserService;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

/**
 * @author Administrator
 *         2015-5-5
 */
@Controller
@RequestMapping("/front")
public class TemplateController extends BaseController {
    private static final long serialVersionUID = 1L;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String template = "/WEB-INF/view/template/template";

    @Autowired
    private TemplateService templateService;
    @Autowired
    private UserService userService;

    // 绑定变量名参数(作用:将页面提交的属性与实体想关联)
    @InitBinder("template")
    public void initBinderTemplate(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("template.");
    }

    /**
     * 跳转模板页面
     *
     * @return
     */
    @RequestMapping("/template")
    public ModelAndView to_template() {
        ModelAndView model = new ModelAndView(template);
        try {
            Template template = templateService.getTemplateById(1L);
            model.addObject("template", template);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("TemplateController.to_template", e);
        }
        return model;
    }

    /**
     * 修改模板文件
     *
     * @param template
     * @return
     */
    @RequestMapping("/updateTemplate")
    public ModelAndView updateTemplate(@ModelAttribute("template") Template template) {
        ModelAndView model = new ModelAndView("redirect:/front/template");
        try {
            if (template != null) {
                templateService.updateTemplate(template);
                String tempDir = CommonConstants.templatepath + File.separator + "template";
                TemplateUtil.createTemplateFile(template.getContent(), tempDir);
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("TemplateController.updateTemplate", e);
        }
        return model;
    }

    /**
     * 发布模板
     *
     * @return
     */
    @RequestMapping("/pubVelocity")
    @ResponseBody
    public Map<String, Object> pubVelocity() {
        Map<String, Object> json = null;
        try {
            VelocityContext context = new VelocityContext();
            context.put("ctx", CommonConstants.contextPath);
            context.put("userService", userService);

            publishIndex(context);
            json = this.getJsonMap(true, "发布成功", null);
        } catch (Exception e) {
            json = this.getJsonMap(false, "", null);
            logger.error("", e);
        }
        return json;
    }

    /**
     * 发布模板提取方法
     *
     * @param context
     * @return
     * @throws Exception
     */
    private String publishIndex(VelocityContext context) throws Exception {
        //模板路径
        String tempFile = "/template.vm";
        String file = CommonConstants.htmlfilepath + File.separator + "index.html";
        File filePath = new File(file);
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        VelocityEngine engine = TemplateUtil.initEngine(CommonConstants.templatepath);
        boolean isOk = TemplateUtil.createHtmlFile(engine, tempFile, filePath, context);
        if (isOk == false) {
            throw new ArithmeticException("dvided with number 0.");
        }
        return tempFile;
    }
}
