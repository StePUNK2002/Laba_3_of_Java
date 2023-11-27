package com.example.laba3;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
public class ReaderDocument extends Thread{
    private Document collection;
    private TextArea area;
    public ReaderDocument(Document col, TextArea area) {
        collection = col;
        this.area = area;
    }
    @Override
    public void run() {
        var arr = collection.getPages();
        for (int i = 0; i < arr.length; i++) {
            String str = "\nRead: " + arr[i] + " from position " + i;
            Platform.runLater(() -> area.appendText(str));
            //System.out.println("Read: " + arr[i] + " from position " + i);
        }
    }
}
