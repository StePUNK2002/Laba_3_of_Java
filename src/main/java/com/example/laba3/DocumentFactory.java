package com.example.laba3;

public interface DocumentFactory {
    Document createInstance();
    Document createInstance(String name, int col, int[] pages) throws Exception;
}
