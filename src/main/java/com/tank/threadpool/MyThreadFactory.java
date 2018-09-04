package com.tank.threadpool;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		System.out.println("new Thread");
		//这里可以实现自定义线程名称
		return new Thread(r, "my-newThread");
	}

}
