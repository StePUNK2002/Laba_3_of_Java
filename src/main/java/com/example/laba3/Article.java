package com.example.laba3;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.Objects;
import java.nio.ByteBuffer;

public class Article implements Document {
    private int[] sections;
    private String title;
    private int marketingCount;
    public Article()
    {
        this.title = "Название по умолчанию";
        this.marketingCount = 20;
        this.sections = new int[2];
        setArrayElement(0, 200);
        setArrayElement(1, 200);
    }

    public Article(String title, int marketingCount, int count_articles) {
        this.title = title;
        this.marketingCount = marketingCount;
        this.sections = new int[count_articles];
    }
    public Article(String title, int marketingCount, int[] massive) {
        this.title = title;
        this.marketingCount = marketingCount;
        this.sections = massive;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getPagesCount() {
        return marketingCount;
    }

    @Override
    public int getLength() {
        return sections.length;
    }

    public void setArrayElement(int index, int value) {
        if (index < 0 || index >= sections.length)
        {
            throw new Article.InvalidPagesArrayException("Индекс вне диапозона");
        }
        else if (value <= 0)
        {
            throw new Article.InvalidPagesArrayException("Количество страниц должно быть положительно");
        }
        else
        {
            sections[index] = value;
        }
    }
    public int getArrayElement(int index) {
        if (index < 0 || index >= sections.length)
        {
            throw new Article.InvalidPagesArrayException("Индекс вне диапозона");
        }
        else
        {
            return sections[index];
        }
    }

    @Override
    public int[] getPages() {
        return sections;
    }

    public void output(OutputStream out) throws Exception {
        out.write(0);
        // Преобразование и запись названия статьи
        byte[] byte_title = this.title.getBytes();
        byte[] byte_title_length = ByteBuffer.allocate(4).putInt(byte_title.length).array();
        out.write(byte_title_length);
        out.write(byte_title);

        // Преобразование и запись количества страниц
        byte[] byte_marketingCount = ByteBuffer.allocate(4).putInt(this.marketingCount).array();
        out.write(byte_marketingCount);

        // Преобразование и запись массива sections
        byte[] byte_sections_length = ByteBuffer.allocate(4).putInt(this.sections.length).array();
        out.write(byte_sections_length);
        for(int i = 0; i < this.sections.length; ++i) {
            out.write(ByteBuffer.allocate(4).putInt(this.sections[i]).array());
        }
    }

    public void write(Writer out) throws IOException {
        // Формирование строки с данными статьи
        String str = "0 " + this.title + " " + this.marketingCount + " " + this.sections.length;

        // Добавление каждого элемента массива sections в строку
        for(int i = 0; i < this.sections.length; i++) {
            str = str + " " + this.sections[i];
        }

        // Запись строки в символьный поток
        out.write(str + "\n");
    }

    @Override
    public boolean isBenefit() throws InvalidArticleException {
        int valuePages = 0;
        for (int i = 0; i < sections.length; i++)
        {
            if (sections[i] - getPagesCount() <= 0)
            {
                throw new Article.InvalidArticleException("В журнале минимум одна страница");
            }
            valuePages = valuePages + (sections[i] - getPagesCount());
        }
        if (valuePages >= 10)
        {
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "Article{" +
                "title='" + this.getTitle() + '\'' +
                ", marketingCount=" + this.getPagesCount() +
                ", sections=" + Arrays.toString(sections) +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return marketingCount == article.getPagesCount() &&
                this.title == article.title &&
                Arrays.equals(sections, article.sections);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, marketingCount, Arrays.hashCode(sections));
    }
    public class InvalidArticleException extends Exception {
        public InvalidArticleException(String message) {
            super(message);
        }
    }
    public class InvalidPagesArrayException extends RuntimeException {
        public InvalidPagesArrayException(String message) {
            super(message);
        }
    }
}
