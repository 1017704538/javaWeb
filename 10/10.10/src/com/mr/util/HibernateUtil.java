package com.mr.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	//SessionFactory对象
	private static SessionFactory factory = null;
	//静态块
	static{
		try {
			String filename = "Hibernate.cfg.xml";
			//加载Hibernate配置文件
			Configuration cfg = new Configuration().configure(filename);
			//实例化SessionFactory对象
			factory = cfg.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println("session创建失败");
		}
	}
	
	/**
	 * 获取Session对象
	 * @return Session对象
	 */
	public static Session getSession() {
		//如果SessionFacroty不为空，则开启Session
		Session	session = (factory != null) ? factory.openSession() : null;
		return session;
	}
	
	/**
	 * 获取SessionFactory对象
	 * @return SessionFactory对象
	 */
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	
	/**
	 * 关闭Session
	 * @param session对象
	 */
	public static void closeSession(Session session) {
		if (session != null) {
			if (session.isOpen()) {
				session.close();//关闭Session
			}
		}
	}
}
