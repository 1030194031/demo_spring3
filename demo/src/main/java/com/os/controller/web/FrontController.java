package com.os.controller.web;

import com.os.comment.base.BaseController;
import com.os.comment.service.EmailService;
import com.os.comment.utils.DateUtils;
import com.os.comment.utils.HttpHalder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *         2015-5-5
 */
@Controller
public class FrontController extends BaseController {
    private static final long serialVersionUID = 1L;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String indexJsp = "/WEB-INF/view/index";//首页
    private static final String choose_user = "/WEB-INF/view/user/choose_user";//选择用户
    private static final String to_validate = "/WEB-INF/view/validate/validate";//验证控件
    private static final String to_validform = "/WEB-INF/view/validate/valid_form";//验证控件
    private static final String to_epiClock = "/WEB-INF/view/chajian/epiClock";//倒计时
    private static final String My97DatePicker = "/WEB-INF/view/chajian/My97DatePicker";//My97DatePicker
    private static final String interfaces = "/WEB-INF/view/interfaces/interfaces";//接口调取
    private static final String baiduMap = "/WEB-INF/view/baiduMap/baiduMap";//百度地图
    private static final String user_ajax_page = "/WEB-INF/view/user/user_ajax_page";//ajax分页
    private static final String MD5 = "/WEB-INF/view/MD5/MD5";//MD5
    private static final String userPic = "/WEB-INF/view/user/user_pic";//头像裁剪
    private static final String uploadifyTest = "/WEB-INF/view/chajian/uploadify_test";//上传插件测试

    @Autowired
    private EmailService emailService;

    @RequestMapping("/")
//spirng mvc 配置根节点访问“/”方式 即使web.xml没有添加，tomcat也会自动默认去寻找在webroot目录下面的index文件，如果要使用这种方法，则要保证webroot下面没有index相关的文件。
    public ModelAndView test() {
        ModelAndView model = new ModelAndView(indexJsp);
        return model;
    }

    /**
     * 弹窗选择用户
     *
     * @return
     */
    @RequestMapping("/front/chooseUser")
    public ModelAndView to_choose() {
        ModelAndView model = new ModelAndView(choose_user);
        return model;
    }

    /**
     * 页面表单验证控件
     *
     * @return
     */
    @RequestMapping("/front/toValidate")
    public ModelAndView toValidate() {
        ModelAndView model = new ModelAndView(to_validate);
        return model;
    }

    /**
     * 页面表单验证
     *
     * @return
     */
    @RequestMapping("/front/toValidform")
    public ModelAndView toValidform() {
        ModelAndView model = new ModelAndView(to_validform);
        return model;
    }

    /**
     * 跳转倒计时页面
     *
     * @return
     */
    @RequestMapping("/front/epiClock")
    public ModelAndView epiClock() {
        ModelAndView model = new ModelAndView(to_epiClock);
        return model;
    }

    /**
     * My97DatePicker
     *
     * @return
     */
    @RequestMapping("/front/My97DatePicker")
    public ModelAndView My97DatePicker() {
        ModelAndView model = new ModelAndView(My97DatePicker);
        return model;
    }

    /**
     * 发送邮件
     *
     * @param email
     * @return
     */
    @RequestMapping("/front/ajax/sendEmail")
    @ResponseBody
    public Map<String, Object> sendEmail(@RequestParam("email") String email) {
        Map<String, Object> json = null;
        try {
            emailService.sendMail("标题", "邮件测试", email);
            json = this.getJsonMap(true, "发送成功", null);
        } catch (Exception e) {
            logger.error("FrontController.sendEmail--error", e);
            json = this.getJsonMap(false, "系统繁忙,请稍后再试", null);
        }
        return json;
    }

    /**
     * 接口调取
     *
     * @param request
     * @return
     */
    @RequestMapping("/front/interfaces")
    public ModelAndView weather(HttpServletRequest request) {
        ModelAndView model = new ModelAndView(interfaces);
        return model;
    }

    /**
     * 获取天气信息https://www.showapi.com/api/apiList
     *
     * @param request
     * @return
     */
    @RequestMapping("/front/getWeather")
    @ResponseBody
    public Map<String, Object> getWeather(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String cityname = request.getParameter("cityname");
            Map<String, String> map = new HashMap<String, String>();
            map.put("showapi_appid", "4787");
            String timestamp = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
            map.put("showapi_timestamp", timestamp);
            map.put("showapi_sign", "fb3fac5858c5433c96f777782eff6d1a");
            map.put("area", cityname);
            map.put("needIndex", "0");
            map.put("needMoreDay", "0");
            String result = HttpHalder.postMethod("http://route.showapi.com/9-2", map);
            json = this.getJsonMap(true, null, result);
        } catch (Exception e) {
            logger.error("FrontController.getWeather---error", e);
            json = this.getJsonMap(false, null, null);
        }
        return json;
    }

    /**
     * 笑话接口调取https://www.showapi.com/api/apiList
     *
     * @param request
     * @return
     */
    @RequestMapping("/front/getJoke")
    @ResponseBody
    public Map<String, Object> getJoke(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("showapi_appid", "4787");
            String timestamp = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
            map.put("showapi_timestamp", timestamp);
            map.put("showapi_sign", "fb3fac5858c5433c96f777782eff6d1a");
            map.put("time", "2015-07-10");
            map.put("page", "");
            String result = HttpHalder.postMethod("http://route.showapi.com/341-2", map);
            System.out.println(result);
            json = this.getJsonMap(true, null, result);
        } catch (Exception e) {
            logger.error("FrontController.getJoke---error", e);
            json = this.getJsonMap(false, null, null);
        }
        return json;
    }

    /**
     * 百度地图
     *
     * @return
     */
    @RequestMapping("/front/baiduMap")
    public ModelAndView baiduMap() {
        ModelAndView model = new ModelAndView(baiduMap);
        return model;
    }

    /**
     * 用户列表  ajax分页
     *
     * @param request
     * @return
     */
    @RequestMapping("/front/userAjaxPage")
    public String userAjaxPage(HttpServletRequest request) {
        return user_ajax_page;
    }

    /**
     * md5加解密
     *
     * @return
     */
    @RequestMapping("/front/toMd5")
    public String toMd5() {
        return MD5;
    }

    /**
     * 头像裁剪
     *
     * @return
     */
    @RequestMapping("/front/userPic")
    public String userPic() {
        return userPic;
    }

    /**
     * 上传插件测试
     *
     * @return
     */
    @RequestMapping("/front/uploadifyTest")
    public String uploadifyTest() {
        return uploadifyTest;
    }
}
