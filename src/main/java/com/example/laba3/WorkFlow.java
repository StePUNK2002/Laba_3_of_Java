package com.example.laba3;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class WorkFlow {
    private static DocumentFactory factory;

    public  static  void setFactory(DocumentFactory fac)
    {
        factory = fac;
    }
    public static Document createInstance()
    {
        return factory.createInstance();
    }
    public static Document createInstance(String name, int col, int[] pages) throws Exception {
        return factory.createInstance(name, col, pages);
    }
    //запись в байтовый поток
    static void output(Document o, OutputStream out) {
        try {
            o.output(out);
        } catch (Exception e) {
            System.out.println("Ошибка при записи в байтовый поток");
        }
    }


    //запись в символьный поток
    static void write(Document o, Writer out) {
        try {
            o.write(out);

        } catch (Exception e) {
            System.out.println("Ошибка при записи в символьный поток");
        }
    }


    //чтение из байтового потока
    static Document input(InputStream in) {
        try {
            byte[] what = new byte[1];
            in.read(what);

            byte[] byte_title_l = new byte[4];
            in.read(byte_title_l);
            int title_l = ByteBuffer.wrap(byte_title_l).getInt();
            byte[] byte_name = new byte[title_l];
            in.read(byte_name);
            String title = new String(byte_name);

            byte[] byte_pages_l = new byte[4];
            in.read(byte_pages_l);
            int pages = ByteBuffer.wrap(byte_pages_l).getInt();

            byte[] byte_mass_l = new byte[4];
            in.read(byte_mass_l);
            int size = ByteBuffer.wrap(byte_mass_l).getInt();
            int[] massive = new int[size];

            for (int i = 0; i < size; i++) {
                byte[] element = new byte[4];
                in.read(element);
                massive[i] = ByteBuffer.wrap(element).getInt();
            }
            return createInstance(title, pages, massive);
            /*
            if (what[0] == 0) {
                return new Article(title, pages, massive);
            } else {
                return new Book(title, pages, massive);
            } */

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    //чтение из символьного потока
    static Document read(Reader in) {
        try {
            String str = "";
            int k = in.read();
            if (k!=-1) {
                while (k != -1 && k != '\n') {
                    str += (char) k;
                    k = in.read();
                }
                String[] parts = str.split(" ");
                System.out.println(parts);
                int u = 0;
                int what = Integer.parseInt(parts[u]);
                String title = parts[u + 1];
                int pages = Integer.parseInt(parts[u + 2]);
                int rr = Integer.parseInt(parts[u + 3]);
                int[] massive = new int[rr];
                for (int i = 0; i < rr; i++) {
                    massive[i] = Integer.parseInt(parts[u + i + 4]);
                }
                return createInstance(title, pages, massive);
                /*
                if (what == 0) {
                    return new Article(title, pages, massive);
                } else {
                    return new Book(title, pages, massive);
                }
                */
            }
            else throw new Exception("");
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }
    //вывод сериализованных объектов
    static void serialize(Document o, ObjectOutputStream out) {
        try {
            out.writeObject(o);
        } catch (Exception e) {

        }
    }

    //ввод десериализованного объекта
    static Document deserialize(ObjectInputStream in) {

        try {
            return (Document) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Время вышло");

        }
    }
    //вывод с объектов
    static void writeFormat(Document o, PrintWriter out) {
        try {
            String str = "";
            for (int i = 0; i < o.getLength(); i++)
                str += o.getArrayElement(i)+" ";
            if(o instanceof Article)
            {
                out.printf("Название журнала: %s; Бесполезные страницы: %s; Кол-во страниц разделов: %s\n", o.getTitle(), o.getPagesCount(), str);
            }
            else
            {
                out.printf("Название книги: %s; Бесполезные страницы: %s; Кол-во страниц разделов: %s\n", o.getTitle(), o.getPagesCount(), str);
            }
        }
        catch (Exception e)
        {
            System.out.println("Сломался вывод с объектов");
            System.out.println(e);
        }
    }
    //ввод объекта (параметром метода является объект типа Scanner)
    static Document readFormat(Scanner in) {
        try {
            String str = in.nextLine();
            String[] ani = str.split(" ");
            int what = Integer.parseInt(ani[0]);
            if(what==0 || what==1) {
                String title = ani[1];
                int pages = Integer.parseInt(ani[2]);

                int[] massive = new int[ani.length - 3];
                for (int i = 0; i < massive.length; i++) {
                    massive[i] = Integer.parseInt(ani[i + 3]);
                }
                return createInstance(title, pages, massive);

                /*
                if (what == 0) return new Article(title, pages, massive);
                else return new Book(title, pages, massive);
                */
            }
            else throw new Exception("Ошибка! Неизвестно что");
        }
        catch (Exception e)
        {
            System.out.println("Сломался ввод объектов");
            System.out.println(e);
        }
        return null;
    }
    public static Document synchronizedDocument(Document i) {
        return new SynchronizedDocument(i);
    }
    public static Document unmodifiableProduct(Document o) { return new UnmodifiableDocument(o); }
}
