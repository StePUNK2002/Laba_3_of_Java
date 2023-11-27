package com.example.laba3;

public class SyncReaderDocument implements Runnable {
    private DocumentSynchronizer sync;

    public SyncReaderDocument(DocumentSynchronizer s) {
        sync = s;
    }

    @Override
    public void run() {
        try {
            sync.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
