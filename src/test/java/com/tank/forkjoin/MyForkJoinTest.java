package com.tank.forkjoin;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class MyForkJoinTest {
	public static void main(String[] args) {
		Instant bgn = Instant.now();
		ForkJoinPool pool = new ForkJoinPool(8, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
		FolderProcessor system = new FolderProcessor("/Users/duanzhiwei/work", "java");

		pool.execute(system);
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Running Threads: %d\n", pool.getRunningThreadCount());
			System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.MILLISECONDS.sleep(200L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!system.isDone());
		pool.shutdown();
		
//		try {
//			while (!pool.awaitTermination(200L, TimeUnit.MILLISECONDS)){
//				System.out.printf("******************************************\n");
//				System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
//				System.out.printf("Main: Running Threads: %d\n", pool.getRunningThreadCount());
//				System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
//				System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
//				System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
//				System.out.printf("******************************************\n");
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		List<String> results = system.join();
//		并行度 1 19538 ms
//		并行度 2 6316 ms
//		并行度 4 6113 ms
//		并行度 6 6005 ms
//		并行度 8 6270 ms
		System.out.println("all task takes " + (Instant.now().toEpochMilli() - bgn.toEpochMilli()) + " ms");
		System.out.printf("java: %d files found.\n", results.size());
	}
}
