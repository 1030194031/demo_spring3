/**
 * Administrator
 * 2015-5-5
 */
package com.os.controller.user;

import com.os.comment.base.BaseController;
import com.os.comment.base.CommonConstants;
import com.os.comment.handle.page.PageEntity;
import com.os.comment.utils.file.FileExcelImportUtil;
import com.os.comment.utils.file.FileExportImportUtil;
import com.os.entity.user.QueryUser;
import com.os.entity.user.User;
import com.os.service.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *         2015-5-5
 */
@Controller
@RequestMapping("/front")
public class UserController extends BaseController {
    private static final long serialVersionUID = 1L;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String userList = "/WEB-INF/view/user/user_manager";//用户列表
    private static final String user_add = "/WEB-INF/view/user/user_add";//用户添加
    private static final String user_update = "/WEB-INF/view/user/user_update";//用户修改
    private static final String user_batch_add = "/WEB-INF/view/user/user_batch_add";//用户批量添加
    private static final String select_user_list = "/WEB-INF/view/user/select_user_list";//查询用户列表
    private static final String user_ajax_list = "/WEB-INF/view/user/user_ajax_list";//查询ajax用户列表

    @Autowired
    private UserService userService;

    // 绑定变量名参数(作用:将页面提交的属性与实体想关联)
    @InitBinder("queryUser")
    public void initBinderQueryUser(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("queryUser.");
    }

    @InitBinder("user")
    public void initBinderUser(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    /**
     * 根据条件查询数据
     *
     * @param request
     * @param queryUser
     * @param page
     * @return
     */
    @RequestMapping("/user/getList")
    public ModelAndView getList(HttpServletRequest request, @ModelAttribute("queryUser") QueryUser queryUser, @ModelAttribute("page") PageEntity page) {
        ModelAndView model = new ModelAndView();
        model.setViewName(userList);
        try {
            page.setPageSize(5);
            //查询用户列表
            List<User> userList = userService.queryUserPage(queryUser, page);
            model.addObject("userList", userList);
            String visitUrl = this.getServletRequestUriParms(request);
            request.getSession().setAttribute("visitUrl", visitUrl);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("UserController.getList--error", e);
        }
        return model;
    }

    /**
     * ajax用户分页
     *
     * @param request
     * @param queryUser
     * @param page
     * @return
     */
    @RequestMapping("/user/ajaxList")
    public String ajaxList(HttpServletRequest request, @ModelAttribute("queryUser") QueryUser queryUser, @ModelAttribute("page") PageEntity page) {
        try {
            page.setPageSize(5);
            //查询用户列表
            List<User> userList = userService.queryUserPage(queryUser, page);
            request.setAttribute("userList", userList);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("UserController.getList--error", e);
        }
        return user_ajax_list;
    }

    /**
     * 跳转添加页面
     *
     * @return
     */
    @RequestMapping("/user/to_add")
    public ModelAndView to_add() {
        ModelAndView model = new ModelAndView();
        model.setViewName(user_add);
        return model;
    }

    /**
     * 添加用户
     *
     * @return
     */
    @RequestMapping("/user/add")
    @ResponseBody
    public Map<String, Object> add(HttpServletRequest request, @ModelAttribute("user") User user) {
        Map<String, Object> json = null;
        try {
            if (user != null) {
                userService.addUser(user);
                json = this.getJsonMap(true, "添加成功", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "", null);
            logger.error("UserController.add--error", e);
        }
        return json;
    }

    /**
     * 跳转修改信息页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/user/to_update/{id}")
    public ModelAndView toUpdate(@PathVariable("id") long id) {
        ModelAndView model = new ModelAndView();
        model.setViewName(user_update);
        try {
            if (id > 0) {
                User user = userService.getUserById(id);
                model.addObject("user", user);
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("UserController.toUpdate--error", e);
        }
        return model;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/update")
    public ModelAndView update(HttpServletRequest request, @ModelAttribute("user") User user) {
        ModelAndView model = new ModelAndView();
        model.setViewName(getServletRequestUrls(request, "redirect:/front/user"));
        try {
            if (user != null) {
                userService.updateUser(user);
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("UserController.update--error", e);
        }
        return model;
    }

    /**
     * 跳转批量导入学生页面
     *
     * @return
     */
    @RequestMapping("/user/to_batch_add")
    public ModelAndView to_batch_add() {
        ModelAndView model = new ModelAndView();
        model.setViewName(user_batch_add);
        return model;
    }

    /**
     * excel导入学生信息
     *
     * @param myFile
     * @return
     */
    @RequestMapping("/user/importExcel")
    public ModelAndView importExcel(@RequestParam("myFile") MultipartFile myFile) {
        ModelAndView model = new ModelAndView(user_batch_add);
        try {
            String msg = FileExcelImportUtil.importExcel(myFile);
            model.addObject("msg", msg);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("UserController.importExcel--error", e);
        }
        return model;
    }

    /**
     * 批量导出execl
     *
     * @param request
     * @param response
     */
    @RequestMapping("/user/export")
    public void userExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            //指定文件生成路径
            String dir = CommonConstants.excelfile;
            //文件名
            String expName = "学员信息_" + new Date().getTime();
            //表头信息
            String[] headName = {"ID", "昵称"};
            List<File> srcfile = new ArrayList<File>();//生成的excel的文件的list
            List<User> userList = userService.getUserList(null);
            List<List<String>> list = userJoint(userList);
            File file = FileExportImportUtil.createExcel(headName, list, expName, dir);
            srcfile.add(file);
            FileExportImportUtil.createRar(response, dir, srcfile, expName);//生成的多excel的压缩包
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("UserController.userExport--error", e);
        }
    }

    /**
     * 学员信息excel格式拼接
     *
     * @return
     */
    public List<List<String>> userJoint(List<User> userList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < userList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(userList.get(i).getId() + "");
            small.add(userList.get(i).getNickname());
            list.add(small);
        }
        return list;
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @RequestMapping("/user/del")
    @ResponseBody
    public Map<String, Object> delObj(@RequestParam("ids") String ids) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(ids)) {
                userService.deleteUserById(ids);
                json = this.getJsonMap(true, "操作成功", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "", null);
            logger.error("UserController.delObj", e);
        }
        return json;
    }

    /**
     * 弹窗显示用户列表
     *
     * @return
     */
    @RequestMapping("/user/selectUserList")
    public ModelAndView selectUserList(@ModelAttribute("queryUser") QueryUser queryUser) {
        ModelAndView model = new ModelAndView(select_user_list);
        try {
            //查询用户列表
            List<User> userList = userService.getUserList(queryUser);
            model.addObject("userList", userList);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("UserController.selectUserList--error", e);
        }
        return model;
    }

    /**
     * 查询用户列表
     *
     * @param ids
     * @return
     */
    @RequestMapping("/user/getUserIds")
    @ResponseBody
    public Map<String, Object> getUserIds(@RequestParam("ids") String ids) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(ids)) {
                List<User> userList = userService.getUserListByIds(ids);
                json = this.getJsonMap(true, null, userList);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, null, null);
            logger.error("UserController.getUserIds--error", e);
        }
        return json;
    }
}
