package com.aliceplatform.pocker.ranker;

import com.aliceplatform.pocker.model.Card;
import com.aliceplatform.pocker.model.Player;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardRankRanker implements Ranker {

    @Override
    public Player.Rank rank(List<Card> cards) {
        Player.Rank rank;
        List<Map.Entry<Card.CardRank, List<Card>>> cardGroups = cards
                .stream().collect(Collectors.groupingBy(Card::getCardRank)).entrySet()
                .stream().sorted(Map.Entry.comparingByValue((cardList1, cardList2) -> cardList2.size() - cardList1.size()))
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Card.CardRank::getValue)))
                .collect(Collectors.toList());
        if (cardGroups.get(0).getValue().size() == 4) {
            rank = new Player.Rank(Player.HandRank.FOUR_OF_A_KIND, cardGroups.get(0).getKey());
        } else if (cardGroups.get(0).getValue().size() == 3) {
            if (cardGroups.get(1).getValue().size() >= 2) {
                rank = new Player.Rank(Player.HandRank.FULL_HOUSE, cardGroups.get(0).getKey());
            } else {
                rank = new Player.Rank(Player.HandRank.TREE_OF_A_KIND, cardGroups.get(0).getKey());
            }
        } else if (cardGroups.get(0).getValue().size() == 2) {
            if (cardGroups.get(1).getValue().size() == 2) {
                rank = new Player.Rank(Player.HandRank.TWO_PAIR, cardGroups.get(0).getKey());
            } else {
                rank = new Player.Rank(Player.HandRank.ONE_PAIR, cardGroups.get(0).getKey());
            }
        } else {
            Collections.sort(cards, (card1, card2) -> card2.getCardRank().getValue() - card1.getCardRank().getValue());
            int numberOfOrderedCards = 1;
            for (int i = 0; i < cards.size() - 1; i++) {
                if (cards.get(i).getCardRank().getValue() - cards.get(i + 1).getCardRank().getValue() == 1) {
                    numberOfOrderedCards++;
                }
            }
            if (numberOfOrderedCards >= 5) {
                rank = new Player.Rank(Player.HandRank.STRAIGHT, cards.get(0).getCardRank());
            } else {
                rank = new Player.Rank(Player.HandRank.HIGH_CARD, cards.get(0).getCardRank());
            }

        }
        return rank;
    }

}
