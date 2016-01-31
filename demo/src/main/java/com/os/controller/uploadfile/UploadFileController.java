package com.os.controller.uploadfile;

import com.os.comment.base.BaseController;
import com.os.comment.base.CommonConstants;
import com.os.comment.utils.DateUtils;
import com.os.comment.utils.ImageUtils;
import com.os.comment.utils.file.FileRarUtils;
import com.os.comment.utils.file.FileUtils;
import com.os.entity.fileupload.FileUpLoad;
import com.os.entity.fileupload.QueryFileUpLoad;
import com.os.service.fileupload.FileUpLoadService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @author Administrator
 *         2015-5-5
 */
@Controller
@RequestMapping("/front")
public class UploadFileController extends BaseController {
    private static final long serialVersionUID = 1L;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String fileUploadList = "/WEB-INF/view/fileupload/file_upload_list";
    @Autowired
    private FileUpLoadService fileUpLoadService;

    // 绑定变量名参数(作用:将页面提交的属性与实体相关联)
    @InitBinder("queryFileUpLoad")
    public void initBinderQueryFileUpLoad(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("queryFileUpLoad.");
    }

    /**
     * uploadify上传图片
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping("/uploadFile/img")
    @ResponseBody
    public String uploadFileImg(HttpServletRequest request) throws IOException {
        String uploadDir = CommonConstants.uploadimgfile;
        File dirPath = new File(uploadDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        //获取上传文件
        MultipartFile file = getFile(request);
        //上传文件名
        String oldFileName = file.getOriginalFilename();
        //经过处理要保存的名称
        String newFileName = "";
        newFileName = randName() + "." + getSuffix(oldFileName);
        String filePath = uploadDir + File.separator + newFileName;//sep+ newFileName;
        String realPath = filePath;
        File uploadedFile = new File(filePath);
        file.transferTo(uploadedFile);
        //截取路径
        filePath = filePath.substring(filePath.indexOf("/static"), filePath.length());

        //固定上传图片宽高
        ImageUtils.fixed(realPath, realPath, 500, 500, "jpg");
        //给图片添加水印
        ImageUtils.pressText("我是水印文字", realPath, realPath, "宋体", Font.BOLD, Color.white, 50, 50, 100, 0.5f, "jpg");
        return filePath;
    }

    /**
     * uploadify上传文件
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping("/uploadFile/file")
    @ResponseBody
    public String uploadFile(HttpServletRequest request) throws IOException {
        String uploadDir = CommonConstants.uploadfile;
        File dirPath = new File(uploadDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        //获取上传文件
        MultipartFile file = getFile(request);
        //上传文件名
        String oldFileName = file.getOriginalFilename();
        //经过处理要保存的名称
        String newFileName = "";
        newFileName = randName() + "." + getSuffix(oldFileName);
        String filePath = uploadDir + File.separator + newFileName;//sep+ newFileName;
        File uploadedFile = new File(filePath);
        file.transferTo(uploadedFile);
        filePath = filePath.substring(filePath.indexOf("/static"), filePath.length());

        //保存数据
        FileUpLoad fileUpLoad = new FileUpLoad();
        fileUpLoad.setName(oldFileName.substring(0, oldFileName.lastIndexOf(".")));
        fileUpLoad.setCreateTime(new Date());
        fileUpLoad.setUrl(filePath);
        fileUpLoadService.createFileUpLoad(fileUpLoad);

        return filePath;
    }

    /**
     * 跳转上传列表页面
     *
     * @return
     */
    @RequestMapping("/fileupload/list")
    public ModelAndView toUploadList(@ModelAttribute("queryFileUpLoad") QueryFileUpLoad queryFileUpLoad) {
        ModelAndView model = new ModelAndView(fileUploadList);
        try {
            List<FileUpLoad> fileList = fileUpLoadService.getFileUpLoadList(queryFileUpLoad);
            model.addObject("fileList", fileList);
        } catch (Exception e) {
            logger.error("UploadFileController.toUploadList--error", e);
        }
        return model;
    }

    /**
     * 下载文件
     *
     * @param ids
     * @param response
     */
    @RequestMapping("/downFile")
    public void downFile(@RequestParam("ids") String ids, HttpServletResponse response) {
        try {
            List<Map<String, Object>> srcfile = new ArrayList<>();
            if (ids.indexOf(",") > 0) {
                String[] idArray = ids.split(",");
                for (int i = 0; i < idArray.length; i++) {
                    if (StringUtils.isNotEmpty(idArray[i])) {
                        FileUpLoad fileshuju = fileUpLoadService.getFileUpLoadById(Long.parseLong(idArray[i]));
                        srcfile = filedeal(srcfile, fileshuju);
                    }
                }
            } else {
                FileUpLoad fileshuju = fileUpLoadService.getFileUpLoadById(Long.parseLong(ids));
                srcfile = filedeal(srcfile, fileshuju);
            }
            FileRarUtils.createRar(response, "", srcfile, "下载文件_" + DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
        } catch (Exception e) {
            logger.error("UploadFileController.downFile--error", e);
        }
    }

    /**
     * 处理文件集合
     *
     * @param mapList
     * @param fileshuju
     * @return
     */
    private List<Map<String, Object>> filedeal(List<Map<String, Object>> mapList, FileUpLoad fileshuju) {
        if (fileshuju != null && StringUtils.isNotEmpty(fileshuju.getUrl())) {
            Map<String, Object> map = new HashMap<>();
            File file = new File(CommonConstants.downfilepath + fileshuju.getUrl());
            map.put("file", file);
            map.put("name", fileshuju.getName() + "." + getSuffix(fileshuju.getUrl()));
            System.out.println(getSuffix(fileshuju.getUrl()));
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * KindEditor 上传图片
     *
     * @param request
     * @return
     */
    @RequestMapping("/editorUploadImg")
    @ResponseBody
    public Map<String, Object> editorImgObject(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            String uploadDir = CommonConstants.uploadimgfile;
            File dirPath = new File(uploadDir);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            //获取上传文件
            MultipartFile file = getFile(request);
            //上传文件名
            String oldFileName = file.getOriginalFilename();
            //经过处理要保存的名称
            String newFileName = "";
            newFileName = randName() + "." + getSuffix(oldFileName);
            String filePath = uploadDir + File.separator + newFileName;//sep+ newFileName;
            String realPath = filePath;
            File uploadedFile = new File(filePath);
            file.transferTo(uploadedFile);
            filePath = filePath.substring(filePath.indexOf("/static"), filePath.length());

            //给图片添加水印
            ImageUtils.pressText("我是水印文字", realPath, realPath, "宋体", Font.BOLD, Color.white, 50, 50, 100, 0.5f, "jpg");

            map.put("error", 0);
            map.put("url", filePath);
        } catch (Exception e) {
            logger.error("UploadFileController.editorImgObject--error", e);
        }
        return map;
    }

    /**
     * 获取上传文件
     *
     * @param request
     * @return
     */
    public MultipartFile getFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<?> item = multipartRequest.getFileNames();
        MultipartFile file = null;
        while (item.hasNext()) {
            String fileName = item.next().toString();
            file = multipartRequest.getFile(fileName);
        }
        return file;
    }

    /**
     * 获取后缀
     *
     * @param value
     * @return
     */
    private static String getSuffix(String value) {
        return value.substring(value.lastIndexOf(".") + 1);
    }

    /**
     * 生成随机名
     *
     * @return
     */
    private String randName() {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < 10; i++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }

    /**
     * 上传头像裁剪处理
     *
     * @param request
     * @return
     */
    @RequestMapping("/uploadFile/picImg")
    @ResponseBody
    public Map<String, Object> uploadPicImg(HttpServletRequest request, @RequestParam("path") String path, @RequestParam("x") int x,
                                            @RequestParam("y") int y, @RequestParam("w") int w, @RequestParam("h") int h) {
        Map<String, Object> json = null;
        try {
            String uploadDir = CommonConstants.findfilepath;
            String filePath = uploadDir + path;
            //裁剪过后图片的路径
            String newImg = CommonConstants.uploadimgfile + File.separator + randName() + ".jpg";
            ImageUtils.cut(filePath, newImg, x, y, w, h, "jpg");
            newImg = newImg.substring(newImg.indexOf("/static"), newImg.length());
            FileUtils.deleteFile(filePath);
            json = this.getJsonMap(true, null, newImg);
        } catch (Exception e) {
            logger.error("UploadFileController.uploadPicImg--error", e);
            json = this.getJsonMap(false, "系统繁忙,请稍后再试！", null);
        }
        return json;
    }
}
