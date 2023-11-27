package com.example.laba3;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
public class WriterDocument extends Thread {
    private Document collection;
    private TextArea area;
    public WriterDocument(Document col, TextArea area) {
        collection = col;
        this.area = area;
    }
    @Override
    public void run() {
        var arr = collection.getPages();
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = (int) (Math.random() * 100 + 1);
            String str = "\nWrite: " + arr[i] + " from position " + i;
            //обновляем GUI из другого потока
            Platform.runLater(() -> area.appendText(str));
        }
    }
}
