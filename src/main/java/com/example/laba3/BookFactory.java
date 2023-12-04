package com.example.laba3;

public class BookFactory implements DocumentFactory {
    @Override
    public Document createInstance() {
        return new Book();
    }

    @Override
    public Document createInstance(String name, int col, int[] pages) throws Exception {
        return new Book(name, col, pages);
    }
}
