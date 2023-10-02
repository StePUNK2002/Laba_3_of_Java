package com.example.laba3;

import java.util.Arrays;
import java.util.Objects;

public class Book implements Document {
    private int[] chapters;
    private String title;
    private int pagesCount;

    public Book()
    {
        this.title = "Название по умолчанию";
        this.pagesCount = 20;
        this.chapters = new int[2];
        setArrayElement(0, 300);
        setArrayElement(1, 300);
    }

    public Book(String title, int pagesCount, int count_books) {
        this.title = title;
        this.pagesCount = pagesCount;
        this.chapters = new int[count_books];
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getPagesCount() {
        return pagesCount;
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

}
