package com.lh.dao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class LoadDataBaseDao {
	private static LoadDataBaseDao instance = null ;
	public static LoadDataBaseDao getInstance(){
		if (instance == null) 
			instance = new LoadDataBaseDao();
		return instance;
	}
	/**
	 * 查询schemata系统数据库表中的所有数据库名
	 * @return 返回包含所有数据库名的集合
	 */

public List<String> getDatabaseName(){
	List<String> list = new ArrayList<String>();
	Connection conn = null ;
	try{
		conn =DBCon.getConn() ; 							//创建数据库连接
		String sql = "select schema_name from schemata";	//查询语句
		PreparedStatement stmt = conn.prepareStatement(sql);//创建PreparedStatement对象
		ResultSet rs = stmt.executeQuery(); 				//执行查询并返回结果集
		while(rs.next()){
			list.add(rs.getString(1));						//所有的数据库名添加到集合中
		}
		rs.close();
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return list ;
}
	/**
	 * 执行数据库还原的方法
	 * @param database 要还原的数据库
	 * @param path 备份文件的完整路径名
	 * @return 还原成功返回true ,否则返回false
	 */

public boolean loadDB(String database ,String path){
	String pa = path ;
	try{
		String command = "cmd.exe /c mysql -uroot -p111 "+database;//此命令用于连接到指定数据库
		Process p =Runtime.getRuntime().exec(command); 		//执行连接数据库命令	
		OutputStream out =p.getOutputStream();				//控制台输出流
		FileInputStream fs =new FileInputStream(path);		//创建文件输入流，将指定备份文件输入到内存
		InputStreamReader ir =new InputStreamReader(fs,"utf8");//将字节流信息转换为utf8格式的字符流	
		BufferedReader br = new BufferedReader(ir);			//创建BufferedReader对象读取备份文件信息
		StringBuffer sb = new StringBuffer(""); 			//创建StringBuffer对象，用于动态添加每行信息
		String outLine;
		while((outLine=br.readLine())!=null){				//循环读取备份文件中的每一行数据
			sb.append(outLine+"\r\n");						//将每行数据连接成StringBuffer字符串
		}	
		Writer writer= new BufferedWriter(new OutputStreamWriter(out,"utf8"));
		writer.write(sb.toString());						//将备份数据写入控制台中执行						
		writer.flush();										
		ir.close();
		br.close();
		writer.close();
		fs.close();
		return true;
	}catch(Exception ex){
		ex.printStackTrace();
		return false;
	}	
}
/**
 * 另一种简单的数据库恢复方法
 * @param database： 要恢复的数据库
 * @param path：备份文件的路径
 * @return
 */

public boolean loadData(String database ,String path){
	String pa = path ;
	try{
		String command = "cmd.exe /c mysql -uroot -p111 "+database+" < "+path+"";//恢复数据库的命令
		Runtime.getRuntime().exec(command); 		//执行数据库恢复命令	
		return true;
	}catch(Exception ex){
		ex.printStackTrace();
		return false;
	}	
}
}
