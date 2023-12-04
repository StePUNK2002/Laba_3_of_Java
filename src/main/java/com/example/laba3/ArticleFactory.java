package com.example.laba3;

public class ArticleFactory implements DocumentFactory {
    @Override
    public Document createInstance() {
        return new Article();
    }

    @Override
    public Document createInstance(String name, int col, int[] pages) throws Exception {
        return new Article(name, col, pages);
    }
}
