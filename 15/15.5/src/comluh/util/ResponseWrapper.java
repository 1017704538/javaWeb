package comluh.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {
	private MyOutputStream stream;
	private ByteArrayOutputStream byteStream;
	private PrintWriter out;
	public ResponseWrapper(HttpServletResponse response) {
		super(response);
		this.byteStream = new ByteArrayOutputStream();//初始化数组数据流
		this.stream = new MyOutputStream(byteStream);
		out = new PrintWriter(byteStream);
		
	}

	@Override
	public PrintWriter getWriter() throws IOException{
		return out;//重写父类方法，返回打印输出流
	}
	public String getContent() throws UnsupportedEncodingException{
		return byteStream.toString();//返回响应数据
	}
}
