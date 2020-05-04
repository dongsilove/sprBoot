package com.mindone.okch.common.utils;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @Class Name : SessionUtil.java
 * @Description : 세션관리
 * @Modification Information
 * @author 박이정
 * @since 2019. 07. 18
 * @version 1.0
 * @see
 * 
 */
public class SessionUtil {
	
	/**
	 * session에 넣어둔 loginInfo정보를 가져온다.
	 * @param request
	 * @return LoginInfo
	 */
	public static Map<String,String> getLoginInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) return null;
		ServletContext servletContext = session.getServletContext();
		if (servletContext == null) return null;
		//LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		@SuppressWarnings("unchecked")
		Map<String,String> loginInfo = (Map<String,String>)servletContext.getContext("/").getAttribute("loginInfo");
		
		return loginInfo;
	}

	/**
	 * session에 loginInfo정보를 저장한다.
	 * @param request
	 * @return LoginInfo
	 */
	public static void setLoginInfo(HttpServletRequest request, Map<String,String> loginInfo) {
		HttpSession session = request.getSession();
		if (session == null) return;
		ServletContext servletContext = session.getServletContext();
		if (servletContext == null) return;
		//session.setAttribute("loginInfo", loginInfo);
		servletContext.setAttribute("loginInfo", loginInfo);
	}
	
	/**
	 * session에 넣어둔 loginId정보를 가져온다.
	 * @param request
	 * @return LoginInfo
	 */
	public static String getLoginId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) return null;
		ServletContext servletContext = session.getServletContext();
		if (servletContext == null) return null;
		//LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		@SuppressWarnings("unchecked")
		Map<String,String> loginInfo = (Map<String,String>)servletContext.getContext("/").getAttribute("loginInfo");
		
		if(loginInfo == null){
			return null;
		}else {
			return loginInfo.get("USERID");
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param context
	 * @return ServletContext
	 */
	public static ServletContext getServletContext(String context) {
		HttpSession session = getRequest().getSession();
		if (session == null)
			return null;
		ServletContext servletContext = session.getServletContext().getContext(context);
		return servletContext;
	}

	/**
	 * 
	 * @param request
	 * @return ServletContext
	 */
	public static ServletContext getServletContext() {
		return getServletContext("/");
	}

	public static void setAttribute(String context, String args0, Object args1) {
		ServletContext servletContext = getServletContext(context);
		servletContext.setAttribute(args0, args1);
	}

	public static void setAttribute(String args0, Object args1) {
		setAttribute("/", args0, args1);
	}

	public static void removeAttribute(String context, String args0) {
		ServletContext servletContext = getServletContext(context);
		servletContext.removeAttribute(args0);
	}

	public static void removeAttribute(String args0) {
		removeAttribute("/", args0);
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

}
