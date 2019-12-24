package com.aliceplatform.poker.ranker;

import com.aliceplatform.poker.cards.Card;
import com.aliceplatform.poker.cards.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Card ranker responsible for identification of combinations based on {@link Card.CardRank}:
 * {@link Rank.HandRank#FOUR_OF_A_KIND},
 * {@link Rank.HandRank#FULL_HOUSE},
 * {@link Rank.HandRank#TREE_OF_A_KIND},
 * {@link Rank.HandRank#TWO_PAIR},
 * {@link Rank.HandRank#ONE_PAIR},
 * {@link Rank.HandRank#STRAIGHT},
 * {@link Rank.HandRank#HIGH_CARD}
 */
class CardRankRanker implements Ranker {

    @Override
    public Rank rank(List<Card> cards) {
        Rank rank;
        List<Map.Entry<Card.CardRank, List<Card>>> cardGroups = cards
                .stream().collect(Collectors.groupingBy(Card::getCardRank)).entrySet()
                .stream().sorted((entry1, entry2) -> {
                    int result = entry2.getValue().size() - entry1.getValue().size();
                    if (result == 0) {
                        result = entry2.getKey().ordinal() - entry1.getKey().ordinal();
                    }
                    return result;
                }).collect(Collectors.toList());
        if (cardGroups.get(0).getValue().size() == 4) {
            rank = new Rank(Rank.HandRank.FOUR_OF_A_KIND, cardGroups.get(0).getKey());
        } else if (cardGroups.get(0).getValue().size() == 3) {
            if (cardGroups.get(1).getValue().size() >= 2) {
                rank = new Rank(Rank.HandRank.FULL_HOUSE, cardGroups.get(0).getKey());
            } else {
                rank = new Rank(Rank.HandRank.TREE_OF_A_KIND, cardGroups.get(0).getKey());
            }
            Rank straightOrHighCard = checkForStraight(cards);
            if (straightOrHighCard.compareTo(rank) > 0) {
                rank = straightOrHighCard;
            }
        } else if (cardGroups.get(0).getValue().size() == 2) {
            if (cardGroups.get(1).getValue().size() == 2) {
                rank = new Rank(Rank.HandRank.TWO_PAIR, cardGroups.get(0).getKey());
            } else {
                rank = new Rank(Rank.HandRank.ONE_PAIR, cardGroups.get(0).getKey());
            }
            Rank straightOrHighCard = checkForStraight(cards);
            if (straightOrHighCard.compareTo(rank) > 0) {
                rank = straightOrHighCard;
            }
        } else {
            rank = checkForStraight(cards);
        }
        return rank;
    }

    private Rank checkForStraight(List<Card> cards) {
        List<Card> sortedCards = new ArrayList<>(cards);
        Collections.sort(sortedCards, (card1, card2) -> card2.getCardRank().ordinal() - card1.getCardRank().ordinal());
        int numberOfOrderedCards = 1;
        Card highCard = null;
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            if (sortedCards.get(i).getCardRank().ordinal() - sortedCards.get(i + 1).getCardRank().ordinal() == 1) {
                numberOfOrderedCards++;
                if (numberOfOrderedCards == 5) {
                    highCard = sortedCards.get(i - 3);
                }
            }
        }
        if (numberOfOrderedCards >= 5) {
            return new Rank(Rank.HandRank.STRAIGHT, highCard.getCardRank());
        } else {
            return new Rank(Rank.HandRank.HIGH_CARD, sortedCards.get(0).getCardRank());
        }
    }

}
