package com.ex;

public class SimpleCalculator implements Calculator{
    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public int sub(int x, int y) {
        return x - y;
    }

    @Override
    public int mul(int x, int y) {
        return 0;
    }

    @Override
    public int div(int x, int y) {
        return x / y;
    }
}
