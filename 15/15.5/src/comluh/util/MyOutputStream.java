package comluh.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class MyOutputStream extends ServletOutputStream {
	ByteArrayOutputStream stream;//创建字节数组输出流
	@Override
	public void write(int b) throws IOException {
		stream.write(b);//重写父类的输出方法
	}
	public MyOutputStream(ByteArrayOutputStream stream){
		this.stream = stream;//初始化输出流
	}
}
