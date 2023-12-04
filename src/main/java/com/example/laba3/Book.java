package com.example.laba3;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.*;
import java.nio.ByteBuffer;
import java.io.Serializable;

public class Book implements Document, Serializable {
    private int[] chapters;
    private String title;
    private int pagesCount;

    private List<Integer> pagesList;

    public Book()
    {
        this.title = "Название по умолчанию";
        this.pagesCount = 20;
        this.chapters = new int[2];
        setArrayElement(0, 300);
        setArrayElement(1, 300);
    }

    public Book(String title, int pagesCount, int[] massive) {
        this.title = title;
        this.pagesCount = pagesCount;
        this.chapters = massive;
        this.pagesList = pagesToList();
    }

    private List<Integer> pagesToList() {
        List<Integer> pagesList = new ArrayList<Integer>();
        for (int i = 0; i < chapters.length; i++)
        {
            pagesList.add(chapters[i]);
        }
        return pagesList;
    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getPagesCount() {
        return pagesCount;
    }

    @Override
    public int getLength() {
        return chapters.length;
    }

    public void setArrayElement(int index, int value) {
        if (index < 0 || index >= chapters.length)
        {
            throw new InvalidPagesArrayException("Индекс вне диапозона");
        }
        else if (value <= 0)
        {
            throw new InvalidPagesArrayException("Количество страниц должно быть положительно");
        }
        else
        {
            chapters[index] = value;
        }
    }
    public int getArrayElement(int index) {
        if (index < 0 || index >= chapters.length)
        {
            throw new InvalidPagesArrayException("Индекс вне диапозона");
        }
        else
        {
            return chapters[index];
        }
    }

    @Override
    public int[] getPages() {
        return chapters ;
    }


    public void output(OutputStream out) throws Exception {
        out.write(1);
        byte[] byte_title = this.title.getBytes();
        byte[] byte_title_length = ByteBuffer.allocate(4).putInt(byte_title.length).array();
        out.write(byte_title_length);
        out.write(byte_title);

        byte[] byte_pagesCount = ByteBuffer.allocate(4).putInt(this.pagesCount).array();
        out.write(byte_pagesCount);

        byte[] byte_chapters_length = ByteBuffer.allocate(4).putInt(this.chapters.length).array();
        out.write(byte_chapters_length);

        for(int i = 0; i < this.chapters.length; ++i) {
            out.write(ByteBuffer.allocate(4).putInt(this.chapters[i]).array());
        }
    }


    public void write(Writer out) throws IOException {
        String str = "1 " + this.title + " " + this.pagesCount + " " + this.chapters.length;
        for(int i = 0; i < this.chapters.length; i++) {
            str = str + " " + this.chapters[i];
        }
        out.write(str + "\n");
    }

    @Override
    public boolean isBenefit() throws InvalidBookException {
        int valuePages = 0;
        for (int i = 0; i < chapters.length; i++)
        {
            if (chapters[i] - getPagesCount() <= 0)
            {
                throw new InvalidBookException("В книге минимум одна страница");
            }
            valuePages = valuePages + (chapters[i] - getPagesCount());
        }
        if (valuePages >= 10)
        {
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "Book{" +
                "title='" + this.getTitle() + '\'' +
                ", pagesCount=" + this.getPagesCount() +
                ", pages=" + Arrays.toString(chapters) +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pagesCount == book.pagesCount &&
                this.title == book.title &&
                Arrays.equals(chapters, book.chapters);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, pagesCount, Arrays.hashCode(chapters));
    }
    public class InvalidBookException extends Exception {
        public InvalidBookException(String message) {
            super(message);
        }
    }
    public class InvalidPagesArrayException extends RuntimeException {
        public InvalidPagesArrayException(String message) {
            super(message);
        }
    }
    @Override
    public Iterator<Integer> iterator() {
        return new Itr();
    }
    private class Itr implements Iterator<Integer> {
        int cursor = 0;
        int lastRet = -1;
        Itr()
        {

        }
        @Override
        public boolean hasNext()
        {
            return cursor != Book.this.pagesList.size();
        }
        @Override
        public  Integer next()
        {
            int i = cursor;
            if (i >= Book.this.pagesList.size())
            {
                throw  new NoSuchElementException();
            }
            cursor = i + 1;
            return Book.this.pagesList.get(lastRet = i);
        }
        @Override
        public void remove() {
            if (lastRet < 0)
            {
                throw new IllegalStateException();
            }
            Book.this.pagesList.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }

}
