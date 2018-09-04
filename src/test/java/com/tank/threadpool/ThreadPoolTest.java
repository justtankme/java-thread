package com.tank.threadpool;

import java.time.Instant;

import com.tank.threadpool.MyThreadPool;
import com.tank.threadpool.MyThreadPoolImpl;

public class ThreadPoolTest {
	public static void main(String[] args) {
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MyThreadPool myThreadPool = new MyThreadPoolImpl();
		myThreadPool.create(1, 2, 1);
		System.out.println("=====提交任务1,当线程数小于核心线程数时，创建线程=====");
		myThreadPool.execute(getTask(1));
		System.out.println("=====提交任务2,当线程数大于等于核心线程数，且任务队列未满时，将任务放入任务队列,队列中的任务由核心线程来执行,因此任务2在任务1结束时开始执行=====");
		myThreadPool.execute(getTask(2));
		System.out.println("=====提交任务3,当线程数大于等于核心线程数，且任务队列已满,若线程数小于最大线程数，创建线程,因此任务3与任务1同时开始执行=====");
		myThreadPool.execute(getTask(3));
		System.out.println("=====提交任务4,当线程数大于等于核心线程数，且任务队列已满,若线程数等于最大线程数，调用MyRejectPolicy=====");
		System.out.println("=====自定义拒绝策略中间隔1s判断线程池线程是否空余,因此任务4开始执行时间是随机的=====");
		myThreadPool.execute(getTask(4));
		// myThreadPool.execute(getTask(5));
		// myThreadPool.execute(getTask(6));
		// myThreadPool.execute(getTask(7));
		// myThreadPool.execute(getTask(8));
		myThreadPool.destory();
	}

	private static Runnable getTask(int i) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(new StringBuilder().append(Instant.now()).append(" the number ").append(i)
							.append(" thread started ").append(Thread.currentThread().getName()).toString());
					Thread.sleep(2000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(new StringBuilder().append(Instant.now()).append(" the number ").append(i)
						.append(" thread end").toString());
			}
		};
	}
}
