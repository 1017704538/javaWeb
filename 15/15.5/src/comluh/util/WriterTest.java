package comluh.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterTest {

	public static void writeStrToFile(){
		try {
			
			FileWriter fw = new FileWriter("webContent/test.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();//换行
			bw.write("你好！");//将字符写入文件
			bw.newLine();//换行
			bw.write("明日科技有限公司！");
			bw.flush();//刷新缓冲区
			bw.close();//关闭缓冲区输出流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		WriterTest.writeStrToFile();
	}

}
