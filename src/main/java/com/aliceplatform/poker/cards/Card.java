package com.aliceplatform.poker.cards;

/**
 * Model representation of Playing card which contains combinations of {@link Suit} and {@link Rank}
 */
public class Card implements Comparable<Card> {
    private final Suit suit;
    private final CardRank cardRank;

    public Card(Suit suit, CardRank cardRank) {
        this.suit = suit;
        this.cardRank = cardRank;
    }

    public Suit getSuit() {
        return suit;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public int compareTo(Card o) {
        return this.cardRank.ordinal() - o.cardRank.ordinal();
    }

    public enum Suit {
        DIAMOND, HEART, CLUB, SPADE
    }

    public enum CardRank {
        TWO, THREE, FOUR,
        FIVE, SIX, SEVEN,
        EIGHT, NINE, TEN,
        JACK, QUEEN, KING,
        ACE;

    }

    @Override
    public String toString() {
        return "{" + suit + ":" + cardRank + '}';
    }
}
