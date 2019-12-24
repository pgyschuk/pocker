package com.aliceplatform.poker.cards;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return handRank == rank.handRank &&
                highCardRank == rank.highCardRank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(handRank, highCardRank);
    }

    /**
     * All possible poker combination sorted from lowest to highest
     */
    public enum HandRank {
        HIGH_CARD, ONE_PAIR, TWO_PAIR, TREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH
    }
}

