package com.component.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedUtil {

	/**
	 * 
	 * memcached�ͻ��˵���
	 */

	private static MemCachedClient cachedClient = new MemCachedClient();

	/**
	 * 
	 * ��ʼ�����ӳ�
	 */

	static {

		// ��ȡ���ӳص�ʵ��

		SockIOPool pool = SockIOPool.getInstance();

		// �������б���Ȩ��

		String[] servers = { "127.0.0.1:11211" };

		Integer[] weights = { 3 };

		// ���÷�������Ϣ

		pool.setServers(servers);

		pool.setWeights(weights);

		// ���ó�ʼ����������С������������������������ʱ��

		pool.setInitConn(10);

		pool.setMinConn(10);

		pool.setMaxConn(1000);

		pool.setMaxIdle(1000 * 60 * 60);

		// �������ӳ��ػ��̵߳�˯��ʱ��

		pool.setMaintSleep(60);

		// ����TCP���������ӳ�ʱ

		pool.setNagle(false);

		pool.setSocketTO(60);

		pool.setSocketConnectTO(0);

		// ��ʼ�����������ӳ�

		pool.initialize();

		// ѹ�����ã�����ָ����С�Ķ�ѹ��

		// cachedClient.setCompressEnable(true);

		// cachedClient.setCompressThreshold(1024*1024);

	}

	private MemcachedUtil() {

	}

	public static boolean add(String key, Object value) {

		return cachedClient.add(key, value);

	}

	public static boolean add(String key, Object value, Integer expire) {

		return cachedClient.add(key, value, expire);

	}

	public static boolean put(String key, Object value) {

		return cachedClient.set(key, value);

	}

	public static boolean put(String key, Object value, Integer expire) {

		return cachedClient.set(key, value, expire);

	}

	public static boolean replace(String key, Object value) {

		return cachedClient.replace(key, value);

	}

	public static boolean replace(String key, Object value, Integer expire) {

		return cachedClient.replace(key, value, expire);

	}

	public static Object get(String key) {

		return cachedClient.get(key);

	}

}
