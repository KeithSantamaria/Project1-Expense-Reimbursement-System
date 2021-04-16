package com.ex;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	    Thread myThread = new MyThread();
	    Thread myRunnable = new Thread(new MyRunnable());
	    Thread myLambda = new Thread( () -> {
			System.out.println("I'm a lambda");

			try {
				Thread.sleep(6000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println("Lambda is ending");
		});
	    myThread.start();
	    myRunnable.start();
	    myLambda.start();
        System.out.println("I'm the main thread");
//        myThread.join(); // main thread "wait for the 'myThread' to terminate"
//        myThread.start();
    }
}
