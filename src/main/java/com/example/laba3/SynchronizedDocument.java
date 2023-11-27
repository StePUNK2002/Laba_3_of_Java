package com.example.laba3;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
public class SynchronizedDocument implements Document {
    private Document document;

    public SynchronizedDocument(Document document) {
        this.document = document;
    }

    @Override
    public synchronized String getTitle() {
        return document.getTitle();
    }

    @Override
    public synchronized int getPagesCount() {
        return document.getPagesCount();
    }

    @Override
    public  synchronized int getLength() {
        return document.getLength();
    }

    @Override
    public synchronized boolean isBenefit() throws Book.InvalidBookException, Article.InvalidArticleException {
        return document.isBenefit();
    }

    @Override
    public synchronized void setArrayElement(int index, int value) {
        document.setArrayElement(index, value);
    }

    @Override
    public  synchronized int getArrayElement(int index) {
        return document.getArrayElement(index);
    }

    @Override
    public  synchronized int[] getPages() {
        return document.getPages();
    }

    @Override
    public synchronized void output(OutputStream out) throws Exception {
        document.output(out);
    }

    @Override
    public synchronized void write(Writer out) throws IOException {
        document.write(out);
    }
}
