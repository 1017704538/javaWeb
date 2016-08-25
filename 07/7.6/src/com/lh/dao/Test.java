package com.lh.dao;
import java.util.List;
public class Test {
	
	public static void main(String[] args) {
		int rows = StuDao.getInstance().saveStuBatch();
		System.out.println("批量添加学生信息的行数为："+rows);
	}

}
