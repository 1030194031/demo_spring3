package com.os.controller.MD5;

import com.os.comment.base.BaseController;
import com.os.comment.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Administrator
 *         2015-5-5
 */
@Controller
public class Md5Controller extends BaseController {
    private static final long serialVersionUID = 1L;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * md5加密
     *
     * @param txt
     * @return
     */
    @RequestMapping("/front/ajax/stringMd5")
    @ResponseBody
    public Map<String, Object> md5(@RequestParam("txt") String txt) {
        Map<String, Object> json = null;
        try {
            json = this.getJsonMap(true, null, MD5.convertMD5(txt));
        } catch (Exception e) {
            logger.error("Md5Controller.stringMd5---error", e);
            json = this.getJsonMap(false, "系统繁忙", null);
        }
        return json;
    }

    /**
     * md5解密
     *
     * @param txt
     * @return
     */
    @RequestMapping("/front/ajax/convertMD5")
    @ResponseBody
    public Map<String, Object> convertMD5(@RequestParam("txt") String txt) {
        Map<String, Object> json = null;
        try {
            json = this.getJsonMap(true, null, MD5.convertMD5(txt));
        } catch (Exception e) {
            logger.error("Md5Controller.convertMD5---error", e);
            json = this.getJsonMap(false, "系统繁忙", null);
        }
        return json;
    }
}
