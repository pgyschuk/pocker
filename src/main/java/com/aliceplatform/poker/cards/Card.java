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
        return this.cardRank.value - o.cardRank.value;
    }

    public enum Suit {
        DIAMOND, HEART, CLUB, SPADE
    }

    public enum CardRank {
        ACE(14), KING(13), QUEEN(12),
        JACK(11), TEN(10), NINE(9),
        EIGHT(8), SEVEN(7), SIX(6),
        FIVE(5), FOUR(4), THREE(3),
        TWO(2);
        private int value;

        CardRank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Override
    public String toString() {
        return "{" + suit + ":" + cardRank + '}';
    }
}
