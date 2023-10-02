package com.example.laba3;

public interface Document {
    String getTitle();
    int getPagesCount();
    boolean isBenefit() throws Book.InvalidBookException, Article.InvalidArticleException;
    void setArrayElement(int index, int value);
    int getArrayElement(int index);
}
