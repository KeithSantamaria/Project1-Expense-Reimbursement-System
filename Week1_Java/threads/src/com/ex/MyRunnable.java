package com.ex;

public class MyRunnable implements Runnable {
    @Override
    public void run() {

        try {
            int counter = 0;
            while(counter <= 10) {
                System.out.println("I'm a runnable");
                Thread.sleep(5500);
                counter += 1;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Runnable is ending");
    }
}
