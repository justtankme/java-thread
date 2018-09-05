package com.tank.fiber;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.FiberForkJoinScheduler;

public class MyFiberTest {
	public static void main(String[] args) throws InterruptedException {
		TimeUnit.SECONDS.sleep(15);
		Instant bgn = Instant.now();
		//8 62990ms
		//80 6729ms
		//400 1746ms
		FiberForkJoinScheduler scheduler = new FiberForkJoinScheduler("MyfiberScheduler", 400);
		MyFiberScheduler<Integer> myFiberScheduler = new MyFiberScheduler<>();
		List<Fiber<Integer>> fiberList = new ArrayList<>();
		for(int i = 0; i < 400; i ++) {
			fiberList.add(myFiberScheduler.submit(scheduler, new MyFiber(1)));
		}
		int count = 0;
		fiberList.parallelStream().map(t -> {
			try {
				return t.get();
			} catch (ExecutionException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).recollect(collector);
		
//		forEach(fiber -> {
//			try {
//				count += fiber.get();
//			} catch (ExecutionException | InterruptedException e) {
//				e.printStackTrace();
//			}
//		});
		
		System.out.println("all task takes " + (Instant.now().toEpochMilli() - bgn.toEpochMilli()) + " ms");
	}
}
