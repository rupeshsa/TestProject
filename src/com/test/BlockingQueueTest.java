package com.test;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueueTest {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue queue = new BlockingQueue(); 
		queue.give("test");
		System.out.println(queue.take());
	}
	
	static class BlockingQueue {
	    Queue<String> buffer = new LinkedList<String>();

	    public void give(String data) {
	    	synchronized (this) {
		        buffer.add(data);
		        notify();                   // Since someone may be waiting in take!
	    	}
	    }

	    public String take() throws InterruptedException {
	    	synchronized (this) {
		        while (buffer.isEmpty())    // don't use "if" due to spurious wakeups.
		            wait();
		        return buffer.remove();
	    	}
	    }
	}

}
