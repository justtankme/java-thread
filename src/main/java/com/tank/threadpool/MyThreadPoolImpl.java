package com.tank.threadpool;

import java.time.Instant;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolImpl implements MyThreadPool {
	private ThreadPoolExecutor executor = null;

	public ThreadPoolExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(ThreadPoolExecutor executor) {
		this.executor = executor;
	}

	@Override
	public void create(int corePoolSize, int maximumPoolSize, int queueSize) {
		setExecutor(new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 2, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(queueSize), new MyThreadFactory(), new MyRejectPolicy()));
	}

	@Override
	public void execute(Runnable task) {
		System.out.println("before execute active count thread num is : " + executor.getActiveCount());
		System.out.println("before execute queue size is : " + executor.getQueue().size());
		executor.execute(task);
		System.out.println("after execute active count thread num is : " + executor.getActiveCount());
		System.out.println("after execute queue size is : " + executor.getQueue().size());
	}

	@Override
	public void destory() {
		System.out.println("=====超时时间是指非核心线程空闲后被回收的时间=====");
		System.out.println(Instant.now() + " 当前线程数为:" + executor.getPoolSize());
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Instant.now() + " 等待10s后,当前线程数为:" + executor.getPoolSize());
		System.out.println("=====线程池的shutdown是一个异步动作,不再接收新任务,但会等待线程池中所有任务(包括队列中等待的)都执行完成后关闭线程池=====");
		System.out.println("=====而awaitTermination与shutdown不同之处在于,它可以接收新任务=====");
		executor.shutdown();
		System.out.println("shuting down pool pool size " + executor.getPoolSize());
		System.out.println("shuting down pool max size " + executor.getMaximumPoolSize());
	}
}
