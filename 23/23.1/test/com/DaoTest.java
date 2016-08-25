package com;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hrl.dao.impl.UserDaoImpl;
import com.hrl.model.User;

public class DaoTest {
	static ApplicationContext context = null;
	static {
		context = new ClassPathXmlApplicationContext(
				"applicationContext-common.xml");
	}

	@Test
	public void save() {
		UserDaoImpl dao = (UserDaoImpl) context.getBean("userDao");
		User user = new User();
		user.setPassword("sdf");
		dao.addUser(user);
	}
}
