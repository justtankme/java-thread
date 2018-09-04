package com.tank.threadpool;

public interface MyThreadPool {
	public void create(int corePoolSize, int maximumPoolSize, int queueSize);
	
	public void execute(Runnable task);

	void destory();
}
