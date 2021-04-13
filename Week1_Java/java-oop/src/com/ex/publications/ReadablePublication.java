package com.ex.publications;

public abstract class ReadablePublication extends Publication implements Readable{
    protected int currentPage;

    @Override
    public void nextPage() {
        if(currentPage < this.numOfPages) {
            ++currentPage;
        }
    }

    @Override
    public void prevPage() {
        if(currentPage > 0) {
            --currentPage;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
