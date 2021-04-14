package com.ex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    static String s;
    public static void main(String[] args) {
//        try {
//            int q = 2 / 0;
//            System.out.println(q);
////             s.charAt(0);
//        } catch (ArithmeticException ex) {
//            ex.printStackTrace();
//            System.out.println("You tried to divide by zero");
//            if(ex.getMessage().equals("/ by zero")) {
//                throw new DivideByZeroException("Please check the equation. The Divisor is zero", ex);
//            }
//        } catch (RuntimeException ax) {
//            ax.printStackTrace();
//            System.out.println("Your tried to access a null object");
//        }
//        System.out.println("Finish");
        int div = 2;
        int divisor = 1;
        try(InputStream in = new FileInputStream("no/file")) {
            div(div, divisor);
//            in = new FileInputStream("no/file");
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // System.out.println("Finish");

    }

    private static int div(int div, int divisor) throws DivideByZeroException{
        if(divisor == 0) {
            throw new DivideByZeroException("Divisor can't be zero");
        }
        return div / divisor;
    }
}
