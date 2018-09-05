package com.tank.fiber;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableCallable;

public class MyFiber implements SuspendableCallable<Integer> {
	private static final long serialVersionUID = 1L;
	
	private int i;

	public MyFiber() {
	}
	
	public MyFiber(int i) {
		this.i = i;
	}

	@Override
	public Integer run() throws SuspendExecution, InterruptedException {
		System.out.println("executing " + i);
		TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
		return i;
	}

}
