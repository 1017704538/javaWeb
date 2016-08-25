package com.lh.util;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {
	private CharArrayWriter out;//声明输出字符流
	public ResponseWrapper(HttpServletResponse response) {//获取响应
		super(response);		
		out = new CharArrayWriter();//创建输出字符流
	}
	public PrintWriter getWriter(){
		return new PrintWriter(out);
	}
	public String toString(){//重载父类方法输出字符串
		return out.toString();
	}
}
