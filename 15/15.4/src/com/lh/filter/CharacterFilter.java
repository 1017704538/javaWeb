package com.lh.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 处理页面编码格式的过滤器
 * @author lh
 */

public class CharacterFilter implements Filter {
	String encoding = null;// 声明字符编码
	@Override
	public void destroy() {
		encoding = null;//销毁过滤器对象时，同时将字符编码值变为空
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {		
		if(encoding != null){
			request.setCharacterEncoding(encoding);// 设置request的编码格式		
     		response.setContentType("text/html; charset="+encoding);// 设置response字符编码
		}
		chain.doFilter(request, response);// 传递给下一过滤器
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
		encoding = filterConfig.getInitParameter("encoding");// 获取初始化参数
	}
}
