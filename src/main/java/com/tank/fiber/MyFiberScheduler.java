package com.tank.fiber;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.FiberScheduler;
import co.paralleluniverse.strands.SuspendableCallable;

public class MyFiberScheduler<V> {
	public Fiber<V> submit(FiberScheduler scheduler, SuspendableCallable<V> callable) {
		return scheduler.newFiber(callable).start();
	}

}
