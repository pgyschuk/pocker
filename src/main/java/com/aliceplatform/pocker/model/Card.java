package com.aliceplatform.pocker.model;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final Character character;

    public Card(Suit suit, Character character) {
        this.suit = suit;
        this.character = character;
    }

    public Suit getSuit() {
        return suit;
    }

    public Character getCharacter() {
        return character;
    }

    public int compareTo(Card o) {
        return this.character.value - o.character.value;
    }

    public enum Suit {
        DIAMOND, HEART, CLUB, SPADE
    }

    public enum Character {
        ACE(14), KING(13), QUEEN(12),
        JACK(11), TEN(10), NINE(9),
        EIGHT(8), SEVEN(7), SIX(6),
        FIVE(5), FOUR(4), THREE(3),
        TWO(2);
        private int value;

        Character(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
