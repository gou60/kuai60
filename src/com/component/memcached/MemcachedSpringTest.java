package com.component.memcached;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.danga.MemCached.MemCachedClient;

public class MemcachedSpringTest {

	private MemCachedClient cachedClient;

	@Before
	public void init() {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-memcached.xml");
		cachedClient = (MemCachedClient) context.getBean("memcachedClient");
	}

	@Test
	public void testMemcachedSpring() {

		UserBean user = new UserBean("lou", "jason");

		cachedClient.set("user", user);

		UserBean cachedBean = (UserBean) user;

		System.out.println(cachedBean);
		Assert.assertEquals(user, cachedBean);

	}
}
