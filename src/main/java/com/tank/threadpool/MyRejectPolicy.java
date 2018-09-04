package com.tank.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectPolicy implements RejectedExecutionHandler {
	
	public MyRejectPolicy() {
	}

	public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
		System.out.println("rejected");
		if (!e.isShutdown()) {
			while (e.getMaximumPoolSize() <= e.getActiveCount()) {
				System.out.println("pool size is full");
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			System.out.println("rejected end");
			e.execute(r);
		}
	}
}
