package com.ex;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        PrintStream newOut = System.out;
        System.out.println("Hello, World!");
        oldOut.println(oldOut);
        oldOut.println(newOut);

        System.setOut(oldOut);
        System.out.println("Hello, World!");
    }
}
