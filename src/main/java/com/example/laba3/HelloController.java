package com.example.laba3;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
    protected TextArea field;
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
        try
        {
            field.setText("Лабораторная работа №5 Задание №1");
            var shared = db.get(0);
            var writer = new WriterDocument(shared, field);
            var reader = new ReaderDocument(shared, field);

            writer.setPriority(Thread.MAX_PRIORITY);
            writer.start();

            reader.setPriority(Thread.MIN_PRIORITY);
            reader.start();

        }
        catch (Exception e)
        {
            field.appendText(e.getMessage());
        }

        /*
        String answer = "";
        for(int i = 0; i < db.size(); i++){
            answer = answer + db.get(i) + "\n";
        }
        board.setText(answer);

         */

    }
    @FXML
    protected void Task2() {
        try {
            field.setText("Лабораторная работа №5 Задание №2");
            var shared = db.get(0);
            var sync = new DocumentSynchronizer(shared, field);

            var writer = new SyncWriterDocument(sync);
            var reader = new SyncReaderDocument(sync);

            var writerThread = new Thread(writer);
            var readerThread = new Thread(reader);

            writerThread.start();
            readerThread.start();
        }
        catch (Exception e) {
            field.appendText(e.getMessage());
        }
        /*
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

         */

    }
    @FXML
    protected void Task3() {
        try {
            field.setText("Лабораторная работа №5 Задание №3");
            var shared = db.get(0);
            var sync = WorkFlow.synchronizedDocument(shared);

            var setOneThread = new Thread(() -> {
                var arr = sync.getPages();
                arr[0] = 1;
                Platform.runLater(() -> field.appendText("\nsetOneThread"));
            });

            var setTwoThread = new Thread(() -> {
                var arr = sync.getPages();
                arr[0] = 2077;
                Platform.runLater(() -> field.appendText("\nsetTwoThread"));
            });

            var readThread = new Thread(() -> {
                var arr = sync.getPages();
                Platform.runLater(() -> field.appendText("\nRead: " + arr[0]));
            });

            setOneThread.start();
            readThread.start();
            setTwoThread.start();
        }
        catch (Exception e) {
            field.appendText(e.getMessage());
        }
        /*
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

         */
    }
    @FXML
    protected void Laba4() {
        try
        {
            String name_file_bytes = "/Users/edavkinstepan/Desktop/Java/Laba 3/Laba3/outputBytes.txt";
            String name_file_string = "/Users/edavkinstepan/Desktop/Java/Laba 3/Laba3/outputString.txt";
            String name_file_ser = "/Users/edavkinstepan/Desktop/Java/Laba 3/Laba3/output_ser.txt";
            Scanner in = new Scanner(System.in);//writeFormat
            PrintWriter out = new PrintWriter(System.out);
            int menu = 0;
            while (menu==0) {
                System.out.println("Список документов:");
                for (int i=0; i<db.size();i++)
                {
                    WorkFlow.writeFormat(db.get(i), out);
                }
                out.flush();
                System.out.println("Меню программы:");
                System.out.println("1 - Добавить документ");
                System.out.println("2 - Байтовый");
                System.out.println("3 - Текстовый");
                System.out.println("4 - Байтовый. Сериализация");
                System.out.println("0 - Выход");
                int action = Integer.parseInt(in.nextLine());
                switch (action)
                {
                    case 1:
                        try{
                            System.out.println("Введите следующие значения в троку через пробел:");
                            System.out.println("0 - Article");
                            System.out.println("1 - Book");
                            System.out.println("Ввести надо\n название,\nобщее кол-во страниц\n кол-во страниц в разделах с мусором");
                            db.add(WorkFlow.readFormat(in));}
                        catch (Exception err)
                        {
                            System.out.println("Ошибка!"+ err.getMessage());

                        }
                        break;
                    case 2:
                        System.out.println("Байтовый. Запись");
                        FileOutputStream outputStream = new FileOutputStream(name_file_bytes);
                        for (int i=0;i<db.size();i++)
                        {
                            WorkFlow.output(db.get(i), outputStream);
                        }
                        outputStream.close();
                        FileInputStream inputStream = new FileInputStream(name_file_bytes);
                        List<Document> db_in = new ArrayList<>();
                        while (inputStream.available() > 0) {
                            db_in.add(WorkFlow.input(inputStream));
                        }
                        System.out.println("Массив считанный из байтового файла");
                        for(int i = 0; i < db_in.size(); ++i) {
                            System.out.println(db_in.get(i));
                        }
                        inputStream.close();
                        break;
                    case 3:
                        System.out.println("Текстовый");
                        FileWriter writer = new FileWriter(name_file_string, false);
                        for (int i=0;i< db.size();i++) {
                            WorkFlow.write(db.get(i), writer);
                        }
                        writer.flush();
                        FileReader reader = new FileReader(name_file_string);

                        List<Document> db_in2 = new ArrayList<>();

                        try {
                            while (true) {
                                db_in2.add(WorkFlow.read(reader));
                            }
                        }
                        catch(Exception ex) {
                            System.out.print(ex.getMessage());
                        }
                        System.out.println("Массив считанный из текстового файла");
                        for(int i = 0; i < db_in2.size(); ++i) {
                            System.out.println(db_in2.get(i));
                        }
                        reader.close();
                        break;
                    case 4:
                        System.out.println("Байтовый Сериализация");
                        FileOutputStream outputStream_ser = new FileOutputStream(name_file_ser);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream_ser);

                        for (int i=0;i<db.size();i++)
                        {
                            WorkFlow.serialize(db.get(i), objectOutputStream);
                        }
                        objectOutputStream.close();
                        FileInputStream fileInputStream = new FileInputStream(name_file_ser);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                        List<Document> list = new ArrayList<Document>();
                        int chek=0;
                        try
                        {
                            while (true)
                            {list.add(WorkFlow.deserialize(objectInputStream));}

                        }
                        catch(Exception ex){
                            chek = 1;
                        }
                        if(chek==1)
                        {
                            System.out.println("Массив считанный из файла");
                            for(int i = 0; i < list.size(); ++i) {
                                System.out.println(list.get(i));
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Конец работы программы");
                        menu = 1;
                        break;
                    default:
                        break;
                }
            }
            in.close();
            out.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}