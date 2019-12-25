package com.aliceplatform.poker.output;

public interface OutputWriter {
    void writeMessage(String message, Object... args);
}
