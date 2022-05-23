package ru.otus.spring.davlks.common;

public enum Entries {

     QUIZ("Dear student! Summer is coming! It's a good time to relax, spend time with your dearest and nearest " +
             "or make some money. But don't forget to keep your brain in a good shape! This quiz will help you to rack " +
             "your brains and have fun with your friends! Enjoy!");

    Entries(String entryText) {
        this.entryText=entryText;
    }

    private String entryText;

    public String getEntryText() {
        return entryText;
    }

}


