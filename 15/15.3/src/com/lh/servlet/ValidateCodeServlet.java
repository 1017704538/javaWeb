package com.lh.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lh.model.RandomColor;

/**
 * Servlet implementation class ValidateCodeTest
 */
public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateCodeServlet() {
        super();
    }
	/**
	 * 客户端请求时首先调用此方法
	 */
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应是图片并且格式为Jpeg
		response.setContentType("image/jpeg");	
		//创建一个指定长宽的图象   
		int width=60, height=20;   
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   	
		Graphics g = image.getGraphics();    // 获取用于处理图形上下文对象
		Random random = new Random();        //创建生成随机数的类      	
		g.setColor(RandomColor.getRandomColor(200,250));//设置图象背景色
		g.fillRect(0, 0, width, height);     //填充指定的矩形	   
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));//设定字体格式	
		g.setColor(RandomColor.getRandomColor(160,200));
		for(int i=0;i<130;i++){//产生130条随机干扰线
			int x = random.nextInt(width);   
			int y = random.nextInt(height);   
			int xl = random.nextInt(12);   
			int yl = random.nextInt(12);   
			g.drawLine(x,y,x+xl,y+yl); //在图象的随机指定坐标之间画干扰线  
		}    
		String strCode="";  
		for (int i=0;i<4;i++){   
			String strNumber=String.valueOf(random.nextInt(10)); //把随机数转换成String字符串
			strCode=strCode+strNumber;   
			g.setColor(new Color(15+random.nextInt(120),15+random.nextInt(120),15+random.nextInt(120)));
			g.drawString(strNumber,13*i+6,16);//将认证码依次画到图上
		}   	
		request.getSession().setAttribute("Code",strCode);//把验证码保存到Session对象中   
		g.dispose();//释放此图形的上下文以及它使用的所有系统资源
		ImageIO.write(image, "JPEG", response.getOutputStream());//输出JPEG格式图片到页面    
		response.getOutputStream().flush();//刷新输出流 
		response.getOutputStream().close();//关闭输出流 
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
