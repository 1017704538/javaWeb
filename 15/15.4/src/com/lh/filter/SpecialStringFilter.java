package com.lh.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lh.util.ResponseWrapper;

/**
 * 敏感字符过滤器
 */
public class SpecialStringFilter implements Filter {
    public SpecialStringFilter() {      
    }
	public void destroy() {	
	}
	/**
	 * 过滤敏感字符
	 */

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	HttpServletRequest req =(HttpServletRequest)request;
	HttpServletResponse res =(HttpServletResponse)response;
	ServletOutputStream outStream = res.getOutputStream();//获得输出字节流的对象
	ResponseWrapper wrapper = new ResponseWrapper(res);//创建自定义的响应类
	chain.doFilter(request, wrapper);//执行过滤		
	/**获取响应内容,此内容是网页的所有内容，包括网页源代码*/
	String resStr =wrapper.toString();
	String newString = "";
	if(resStr.indexOf("bad")!=-1){//判断响应内容是否包含敏感字符
		newString = resStr.replace("bad", "**");//替换敏感字符
		outStream.write(newString.getBytes("UTF-8"));//将过滤后的响应内容输出到页面
	}
	else{
		outStream.write(resStr.getBytes("UTF-8"));//没有敏感字符，输出原有的响应内容
	}	
	outStream.flush();
	outStream.close();
}
	public void init(FilterConfig fConfig) throws ServletException {		
	}
}
