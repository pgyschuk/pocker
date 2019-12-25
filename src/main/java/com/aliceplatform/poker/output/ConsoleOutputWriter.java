package com.aliceplatform.poker.output;

public class ConsoleOutputWriter implements OutputWriter {

    @Override
    public void writeMessage(String message, Object... args) {
        System.out.format(message, args);
        System.out.println();
    }
}
