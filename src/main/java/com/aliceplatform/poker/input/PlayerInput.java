package com.aliceplatform.poker.input;

public class PlayerInput {
    private final int numberOfMachinePlayers;
    private final int numberOfRealPlayers;

    public PlayerInput(int numberOfMachinePlayers, int numberOfRealPlayers) {
        this.numberOfMachinePlayers = numberOfMachinePlayers;
        this.numberOfRealPlayers = numberOfRealPlayers;
    }

    public int getNumberOfMachinePlayers() {
        return numberOfMachinePlayers;
    }

    public int getNumberOfRealPlayers() {
        return numberOfRealPlayers;
    }
}
