package com.ex.publications;

public class Book extends ReadablePublication{
    private boolean isFiction;

    // ALWAYS supply a no-args constructor
    public Book(){}

    public boolean isFiction() {
        return isFiction;
    }

    public void setFiction(boolean fiction) {
        isFiction = fiction;
    }
}
