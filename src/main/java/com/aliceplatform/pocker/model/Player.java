package com.aliceplatform.pocker.model;

import java.util.List;

public class Player {
    private float money = 10000;
    private final String name;
    private final List<Card> cards;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public enum HandRank {
        ROYAL_FLASH, STRAIGHT_FLASH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, TREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD
    }

    public static class Rank {
        private final HandRank handRank;
        private final Card.CardRank highCardRank;

        public Rank(HandRank handRank, Card.CardRank highCardRank) {
            this.handRank = handRank;
            this.highCardRank = highCardRank;
        }
    }

}
