package com.aliceplatform.poker.ranker;

import com.aliceplatform.poker.cards.Card;
import com.aliceplatform.poker.cards.Rank;

import java.util.Collections;
import java.util.Comparator;
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
                .stream().sorted(Map.Entry.comparingByValue((cardList1, cardList2) -> cardList2.size() - cardList1.size()))
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Card.CardRank::getValue)))
                .collect(Collectors.toList());
        if (cardGroups.get(0).getValue().size() == 4) {
            rank = new Rank(Rank.HandRank.FOUR_OF_A_KIND, cardGroups.get(0).getKey());
        } else if (cardGroups.get(0).getValue().size() == 3) {
            if (cardGroups.get(1).getValue().size() >= 2) {
                rank = new Rank(Rank.HandRank.FULL_HOUSE, cardGroups.get(0).getKey());
            } else {
                rank = new Rank(Rank.HandRank.TREE_OF_A_KIND, cardGroups.get(0).getKey());
            }
        } else if (cardGroups.get(0).getValue().size() == 2) {
            if (cardGroups.get(1).getValue().size() == 2) {
                rank = new Rank(Rank.HandRank.TWO_PAIR, cardGroups.get(0).getKey());
            } else {
                rank = new Rank(Rank.HandRank.ONE_PAIR, cardGroups.get(0).getKey());
            }
        } else {
            Collections.sort(cards, (card1, card2) -> card2.getCardRank().getValue() - card1.getCardRank().getValue());
            int numberOfOrderedCards = 1;
            Card highCard = null;
            for (int i = 0; i < cards.size() - 1; i++) {
                if (cards.get(i).getCardRank().getValue() - cards.get(i + 1).getCardRank().getValue() == 1) {
                    numberOfOrderedCards++;
                    if(numberOfOrderedCards==5){
                        highCard = cards.get(i-4);
                    }
                }
            }
            if (numberOfOrderedCards >= 5) {
                rank = new Rank(Rank.HandRank.STRAIGHT, highCard.getCardRank());
            } else {
                rank = new Rank(Rank.HandRank.HIGH_CARD, cards.get(0).getCardRank());
            }

        }
        return rank;
    }

}
