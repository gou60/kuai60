package com.component.memcached;

import org.junit.Test;

import junit.framework.Assert;

public class MemcachedUtilTest {

	
	@Test
	public void testMemcached() {

		MemcachedUtil.put("hello", "world", 60);

		String hello = (String) MemcachedUtil.get("hello");

		Assert.assertEquals("world", hello);

		for (int i = 0; i < 10; ++i) {

			//UserBean userBean = new UserBean("Jason" + i, "123456-" + i);

			//MemcachedUtil.put("user" + i, userBean, 60);

			Object obj = MemcachedUtil.get("user" + i);

			System.out.println(obj);

		}

	}

}
