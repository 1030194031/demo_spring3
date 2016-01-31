package com.os.comment.utils;

import com.os.comment.base.CommonConstants;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 网站工具类
 * @author Administrator
 */
public class WebUtils {

	/**
	 * 去html
	 * @param src
	 * @return
     */
    public static String replaceTagHTML(String src) {
        String regex = "\\<(.+?)\\>";
        return org.apache.commons.lang.StringUtils.isNotEmpty(src)?src.replaceAll(regex, ""):"";
    }

	/**
	 * 正则表达试验证邮箱号
	 * @param value
	 * @param length
     * @return
     */
	public static boolean checkEmail(String value, int length) {
		if (length == 0) {
			length = 40;
		}
		return (value.matches(CommonConstants.emailRegex)) && (value.length() <= length);
	}

	/**
	 * 正则表达试验证密码
	 * @param password
	 * @return
     */
	public static boolean isPasswordAvailable(String password) {
		return (password.matches(CommonConstants.pwdRegex)) && (password.length() >= 6) && (password.length() <= 16);
	}

	/**
	 * 获取请求IP地址
	 * @param request
	 * @return
     */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		ipAddress = request.getHeader("x-forwarded-for");
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}
		if ((ipAddress != null) && (ipAddress.length() > 15)) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 判断如果不是ajax请求
	 * @param request
	 * @return
     */
	public static boolean isNotAjaxRequest(HttpServletRequest request) {
		return !isAjaxRequest(request);
	}
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String her = request.getHeader("x-requested-with");
		return org.apache.commons.lang.StringUtils.isNotEmpty(her);
	}
}
