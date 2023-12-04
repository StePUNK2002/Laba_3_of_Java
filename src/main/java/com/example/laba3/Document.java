package com.example.laba3;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public interface Document extends Iterable<Integer> {
    String getTitle();
    int getPagesCount();
    int getLength();
    boolean isBenefit() throws Book.InvalidBookException, Article.InvalidArticleException;
    void setArrayElement(int index, int value);
    int getArrayElement(int index);
    int[] getPages();
    void output(OutputStream out) throws Exception;

    void write(Writer out) throws IOException;
}
