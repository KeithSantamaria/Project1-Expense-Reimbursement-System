package com.ex;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("I'm a thread");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Thread is ending");
    }
}
