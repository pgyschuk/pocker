package com.aliceplatform.pocker.model;

import java.util.ArrayList;
import java.util.List;

public class GameRound {
    private List<Card> flop = new ArrayList<Card>(5);
    private float bank = 0;
    private List<Player> players;
    private Deck deck = new Deck();

    public GameRound(List<Player> players) {
        this.players = players;
    }
}
