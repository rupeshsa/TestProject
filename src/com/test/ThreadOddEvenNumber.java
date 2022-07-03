package com.test;

public class ThreadOddEvenNumber {

	public static void main(String[] args) {
        Thread t1 = new Thread(new OddEvenRunnable(0), "1st Thread");
        Thread t2 = new Thread(new OddEvenRunnable(1), "2nd Thread");

        t1.start(); 
        t2.start();
    }

	static class OddEvenRunnable implements Runnable {
	    Integer evenflag;
	    static Integer number = 1;
	    static Object lock = new Object();

	    OddEvenRunnable(Integer evenFlag) {
	        this.evenflag = evenFlag;
	    }

	    @Override
	    public void run() {
	        while (number < 10) {
	            synchronized (lock) {
	                try {
	                    while (number % 2 != evenflag) {
	                        lock.wait();
	                    }
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }

	                System.out.println(Thread.currentThread().getName() + " " + number);
	                number++;
	                lock.notifyAll();
	            }
	        }
	    }
	}
}
