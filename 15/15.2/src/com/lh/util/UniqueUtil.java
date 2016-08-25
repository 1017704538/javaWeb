package com.lh.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class UniqueUtil {
	/**
	 * 将当前系统时间毫秒数保存到session域中
	 * @param request 请求
	 */
	
	public static void saveUnique(HttpServletRequest request){
		String id = String.valueOf(new Date().getTime());
		request.getSession().setAttribute("UniqueId", id);
	}
	/**
	 * 判断session中的值与表单中的值是否相同
	 * @param request
	 * @return 如果相同返回true,否则返回false
	 */
	
	public static boolean checkUnique(HttpServletRequest request){
		boolean bool = false;
		String uniqueId1 = request.getSession().getAttribute("UniqueId").toString();
		String uniqueId2 = request.getParameter("uniqueid");
		if(uniqueId1.equals(uniqueId2)){
			bool = true;
		}
		return bool;
	}
	/**
	 * 将session中的标识符清空
	 * @param request
	 */
	
	public static void clearUnique(HttpServletRequest request){
		request.getSession().setAttribute("UniqueId", "");
	}
}
