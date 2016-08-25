package com.lh.filter;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comluh.util.ResponseWrapper;

/**
 * 将JSP文件生成静态的HTML文件
 */
public class HtmlFileFilter implements Filter {
    /**
     * 默认构造方法 
     */
    public HtmlFileFilter() {  
    	ServletOutputStream stream=null;
    }
	/**
	 * 该方法用于处理客户端请求
	 */
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		boolean debug=true;
		HttpServletRequest httpRequest = (HttpServletRequest)request;	
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String jspUrl = httpRequest.getRealPath("index.jsp");//获得jsp文件的绝对路径		
		File jspFile = new File(jspUrl);//根据jsp文件路径创建File对象
		/**在与JSP文件相同路径下，创建一个名为index.html的文件*/
		File htmlFile = new File(jspFile.getParentFile(),"index.html");
		/**创建自定义的响应对象*/
		ResponseWrapper responseWrapper = new ResponseWrapper(httpResponse);
		chain.doFilter(request, responseWrapper);//完成过滤链的业务
		responseWrapper.getWriter().flush();//从内存中输出此响应对象中的数据流			
		
		Date htmlDate = null;
		/**判断HTML文件是否存在，如果存在，获得文件的最后修改时间*/
		if(htmlFile.exists()){
			htmlDate = new Date(htmlFile.lastModified());
		}else{
			htmlFile.createNewFile();
		}
		/**判断HTML文件最后修改时间是否是当前时间，如果不是则重新生成*/
		if(debug||htmlDate==null||htmlDate.getDate()!=new Date().getDate()){
			FileOutputStream fileStream = new FileOutputStream(htmlFile); //创建HTML文件输出流
			DataOutputStream dataStream = new DataOutputStream(fileStream);//创建数据输出流
			//创建日期格式器
			DateFormat format = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
			dataStream.writeChars("");//输出空字符
			dataStream.writeUTF("生成时间："+format.format(new Date()));		
			dataStream.writeUTF(responseWrapper.getContent());//使用UTF格式输出HTML内容，保存到HTML文件中
			dataStream.close();
		}
		request.getRequestDispatcher("index.html").forward(request, response);
	}
	/**
	 * Filter对象的初始化方法
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	/**
	 * 销毁Filter对象方法
	 */
	public void destroy() {}
}
