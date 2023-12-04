package com.example.laba3;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

public class UnmodifiableDocument implements Document {
    private Document document;
    public  UnmodifiableDocument(Document document)
    {
        this.document = document;
    }
    @Override
    public String getTitle() {
        return document.getTitle();
    }

    @Override
    public int getPagesCount() {
        return document.getPagesCount();
    }

    @Override
    public int getLength() {
        return document.getLength();
    }

    @Override
    public boolean isBenefit() throws Book.InvalidBookException, Article.InvalidArticleException {
        return document.isBenefit();
    }

    @Override
    public void setArrayElement(int index, int value) {
        throw new UnsupportedOperationException("Невозможно изменить объект");
    }

    @Override
    public int getArrayElement(int index) {
        return document.getArrayElement(index);
    }

    @Override
    public int[] getPages() {
        return document.getPages();
    }

    @Override
    public void output(OutputStream out) throws Exception {
        document.output(out);
    }

    @Override
    public void write(Writer out) throws IOException {
        document.write(out);
    }

    @Override
    public Iterator<Integer> iterator() {
        return document.iterator() ;
    }
}
