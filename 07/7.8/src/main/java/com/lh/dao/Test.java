package com.lh.dao;

import java.sql.Connection;

public class Test {

	/**
	 * @param args
	 */
	
public static void main(String[] args) {
	Connection con = DBCon.getConn();
	if(con!=null){
		System.out.println("SqlServer2005数据库连接成功！");
	}else{
		System.out.println("SqlServer2005数据库连接失败！");
	}
}

}
