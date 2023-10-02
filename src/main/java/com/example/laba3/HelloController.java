package com.example.laba3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloController {
    List<Document> db = new ArrayList<>();
    @FXML
    protected TextField name;
    @FXML
    protected TextField count;
    @FXML
    protected TextField massive;
    @FXML
    protected Label board;
    @FXML
    protected void CreateBook() {
        String[] pages = massive.getText().split(" ");
        Book book = new Book(name.getText(), Integer.parseInt(count.getText()), pages.length);
        for (int i = 0; i < pages.length; i++)
        {
            book.setArrayElement(i, Integer.parseInt(pages[i]));
        }
        db.add(book);
    }
    @FXML
    protected void CreateArticle() {
        String[] pages = massive.getText().split(" ");
        Article article = new Article(name.getText(), Integer.parseInt(count.getText()), pages.length);
        for (int i = 0; i < pages.length; i++)
        {
            article.setArrayElement(i, Integer.parseInt(pages[i]));
        }
        db.add(article);
    }
    @FXML
    protected void Task1() {
        String answer = "";
        for(int i = 0; i < db.size(); i++){
            answer = answer + db.get(i) + "\n";
        }
        board.setText(answer);
    }
    @FXML
    protected void Task2() {
        List<Document> goodDocuments = new ArrayList<>();
        List<Document> badDocuments = new ArrayList<>();
        for(int i = 0; i < db.size(); i++){
            try
            {
                if (db.get(i).isBenefit() == true)
                {
                    goodDocuments.add(db.get(i));
                }
                else
                {
                    badDocuments.add(db.get(i));
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }

        String answer = "true\n\n";
        for (int i = 0; i< goodDocuments.size(); i++)
        {
            answer = answer + goodDocuments.get(i) + "\n";
        }
        answer = answer + "\nfalse\n\n";
        for (int i = 0; i< badDocuments.size(); i++)
        {
            answer = answer + badDocuments.get(i) + "\n";
        }
        board.setText(answer);

    }
    @FXML
    protected void Task3() {
        List<Article> articles = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < db.size(); i++)
        {
            if (db.get(i) instanceof Article)
            {
                Article article = (Article)db.get(i);
                articles.add(article);
            }
            else
            {
                Book book = (Book)db.get(i);
                books.add(book);
            }
        }
        String answer = "Articles\n\n";
        for (int i = 0; i< articles.size(); i++)
        {
            answer = answer + articles.get(i) + "\n";
        }
        answer = answer + "\nBooks\n\n";
        for (int i = 0; i< books.size(); i++)
        {
            answer = answer + books.get(i) + "\n";
        }
        board.setText(answer);
    }

}