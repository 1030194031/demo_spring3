package com.os.comment.base;

import com.os.comment.handle.page.PageEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Administrator
 * 2015-5-12
 */
@SuppressWarnings("serial")
public class BaseController extends PageEntity {
	private PageEntity page;
	public PageEntity getPage() {
		return page;
	}
	public void setPage(PageEntity page) {
		this.page = page;
	}
	@InitBinder("page")
	public void initBinderPage(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("page.");
	}
	
	/**
	 * 获取请求URI
	 * @param request
	 * @return
	 */
	public String getServletRequestUriParms(HttpServletRequest request) {
		StringBuffer sbUrlParms = new StringBuffer(request.getRequestURI());
		sbUrlParms.append("?");
		@SuppressWarnings("unchecked")
		Enumeration<String> parNames = request.getParameterNames();
		while (parNames.hasMoreElements()) {
			String parName = parNames.nextElement().toString();
			try {
				sbUrlParms.append(parName).append("=")
				.append(URLEncoder.encode(request.getParameter(parName), "UTF-8"))
				.append("&");
			} catch (UnsupportedEncodingException e) {
				return "";
			}
		}
		return sbUrlParms.toString();
	}
	
	/**
	 * 获取列表url
	 * @param request
	 * @param url
	 * @return
	 */
	public String getServletRequestUrls(HttpServletRequest request,String url){
		String visitUrl=request.getSession().getAttribute("visitUrl")+"";
		if(StringUtils.isEmpty(visitUrl)){
			return "redirect:"+ url;
		}else{
			return "redirect:"+visitUrl;
		}
	}
	
	/**
	 * json返回 
	 * @param success
	 * @param message
	 * @param entity
	 * @return
	 */
	public Map<String, Object> getJsonMap(boolean success, String message, Object entity) {
        HashMap<String, Object> json = new HashMap<String, Object>(4);
        json.put("success", Boolean.valueOf(success));
        json.put("message", message);
        json.put("entity", entity);
        return json;
    }
		
}
