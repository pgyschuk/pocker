package com.aliceplatform.poker.cards;

/**
 * Representation of {@link HandRank} in pair with Highest card for this card combination
 */
public class Rank implements Comparable<Rank> {
    private final HandRank handRank;
    private final Card.CardRank highCardRank;

    public Rank(HandRank handRank, Card.CardRank highCardRank) {
        this.handRank = handRank;
        this.highCardRank = highCardRank;
    }

    @Override
    public int compareTo(Rank otherRank) {
        int result = this.handRank.compareTo(otherRank.handRank);
        if (result == 0) {
            result = this.highCardRank.compareTo(otherRank.highCardRank);
        }
        return result;
    }

    @Override
    public String toString() {
        return "{" + handRank + ": highCardRank=" + highCardRank + "}";
    }

    /**
     * All possible poker combination sorted from highest to lowest
     */
    public enum HandRank {
        ROYAL_FLASH, STRAIGHT_FLASH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, TREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD
    }
}

