package com.example.laba3;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class DocumentSynchronizer {
    private Document collection;
    private TextArea area;
    private volatile int current = -1;

    private volatile boolean sem = false;
    private volatile boolean isEnd;
    public DocumentSynchronizer(Document col, TextArea area) {
        collection = col;
        this.area = area;
        isEnd = col.getPages().length == 0;
    }
    public void read() throws InterruptedException {
        while (!isEnd) {
            synchronized (collection) {
                while (!isEnd && !sem) {
                    collection.wait();
                }

                if (!isEnd) {
                    var arr = collection.getPages();
                    String str = "\nRead: " + arr[current] + " from position " + current;
                    Platform.runLater(() -> area.appendText(str));
                    sem = false;
                }

                collection.notifyAll();
            }
        }

        Platform.runLater(() -> area.appendText("\nEnd read"));
    }
    public void write() throws InterruptedException {
        while (!isEnd) {
            synchronized (collection) {
                while (!isEnd && sem) {
                    collection.wait();
                }

                current++;

                var arr = collection.getPages();
                isEnd = current >= arr.length;

                if (!isEnd) {
                    arr[current] = (int) (Math.random() * 100 + 1);
                    String str = "\nWrite: " + arr[current] + " to position " + current;
                    Platform.runLater(() -> area.appendText(str));
                    sem = true;
                }

                collection.notifyAll();
            }
        }

        Platform.runLater(() -> area.appendText("\nEnd write"));
    }
}
