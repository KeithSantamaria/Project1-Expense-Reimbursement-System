package com.ex;

public class DivideByZeroException extends RuntimeException {
    public DivideByZeroException() {}
    public DivideByZeroException(String msg) {
        super(msg);
    }
    public DivideByZeroException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
