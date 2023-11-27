package com.example.laba3;

public class SyncWriterDocument implements Runnable {
    private DocumentSynchronizer sync;

    public SyncWriterDocument(DocumentSynchronizer s) {
        sync = s;
    }

    @Override
    public void run() {
        try {
            sync.write();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
