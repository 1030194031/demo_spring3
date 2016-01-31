/**
 * Administrator
 * 2015-5-5
 */
package com.os.controller.subject;

import com.google.gson.Gson;
import com.os.comment.base.BaseController;
import com.os.entity.subject.Subject;
import com.os.service.subject.SubjectService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *         2015-5-5
 */
@Controller
@RequestMapping("/front")
public class SubjectController extends BaseController {
    private static final long serialVersionUID = 1L;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String subject_list = "/WEB-INF/view/subject/subject_list";//专业列表

    @Autowired
    private SubjectService subjectService;

    // 绑定变量名参数(作用:将页面提交的属性与实体想关联)
    @InitBinder("subject")
    public void initBinderSubject(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("subject.");
    }

    /**
     * 跳转专业页面
     *
     * @return
     */
    @RequestMapping("/subject/subjectList")
    public ModelAndView subjectList() {
        ModelAndView model = new ModelAndView(subject_list);
        try {
            List<Subject> subjectList = subjectService.getSubjectList(null);
            if (subjectList != null) {
                Gson gson = new Gson();
                String subjectListString = gson.toJson(subjectList);
                model.addObject("subjectListString", subjectListString);
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("SubjectController.subjectList--error", e);
        }
        return model;
    }

    /**
     * 修改专业名称
     *
     * @param subject
     * @return
     */
    @RequestMapping("/subject/updateSubject")
    @ResponseBody
    public Map<String, Object> updateSubject(@ModelAttribute("subject") Subject subject) {
        Map<String, Object> json = null;
        try {
            if (subject != null) {
                subjectService.updateSubject(subject);
            }
            json = this.getJsonMap(true, "操作成功", null);
        } catch (Exception e) {
            json = this.getJsonMap(false, null, null);
            logger.error("SubjectController.updateSubject--error", e);
        }
        return json;
    }

    /**
     * 删除专业
     *
     * @return
     */
    @RequestMapping("/subject/delSubject")
    @ResponseBody
    public Map<String, Object> delSubject(@RequestParam("ids") String ids) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(ids)) {
                subjectService.deleteSubjectById(ids);
            }
            json = this.getJsonMap(true, null, null);
        } catch (Exception e) {
            json = this.getJsonMap(false, null, null);
            logger.error("SubjectController.delSubject--error", e);
        }
        return json;
    }

    /**
     * 修改子节点的父节点
     *
     * @return
     */
    @RequestMapping("/subject/updateSubjectParent")
    @ResponseBody
    public Map<String, Object> updateSubjectParent(@ModelAttribute("subject") Subject subject) {
        Map<String, Object> json = null;
        try {
            if (subject != null) {
                subjectService.updateSubjectParent(subject);
            }
            json = this.getJsonMap(true, "操作成功！", null);
        } catch (Exception e) {
            json = this.getJsonMap(false, null, null);
            logger.error("SubjectController.updateSubjectParent--error", e);
        }
        return json;
    }

    /**
     * 创建专业节点
     *
     * @return
     */
    @RequestMapping("/subject/createSubject")
    @ResponseBody
    public Map<String, Object> createSubject(@ModelAttribute("subject") Subject subject) {
        Map<String, Object> json = null;
        try {
            if (subject != null) {
                subjectService.createSubject(subject);
            }
            json = this.getJsonMap(true, "操作成功！", null);
        } catch (Exception e) {
            json = this.getJsonMap(false, null, null);
            logger.error("SubjectController.createSubject--error", e);
        }
        return json;
    }
}
