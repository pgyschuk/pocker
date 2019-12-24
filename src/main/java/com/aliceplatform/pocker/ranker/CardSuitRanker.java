package com.aliceplatform.pocker.ranker;

import com.aliceplatform.pocker.model.Card;
import com.aliceplatform.pocker.model.Player;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardSuitRanker implements Ranker {

    private Ranker nextRanker = new CardRankRanker();

    @Override
    public Player.Rank rank(List<Card> cards) {
        List<Map.Entry<Card.Suit, List<Card>>> cardGroups = cards
                .stream().collect(Collectors.groupingBy(Card::getSuit)).entrySet()
                .stream().sorted(Map.Entry.comparingByValue((o1, o2) -> o2.size() - o1.size()))
                .collect(Collectors.toList());
        List<Card> cardList = cardGroups.get(0).getValue();
        boolean ordered = true;
        if (cardList.size() >= 5) {
            Collections.sort(cardList, (card1, card2) -> card2.getCardRank().getValue() - card1.getCardRank().getValue());
            for (int i = 0; i < 4; i++) {
                if (cardList.get(i).getCardRank().getValue() - cardList.get(i + 1).getCardRank().getValue() != 1) {
                    ordered = false;
                    break;
                }
            }
            if (cardList.get(0).getCardRank() == Card.CardRank.ACE && ordered) {
                return new Player.Rank(Player.HandRank.ROYAL_FLASH, Card.CardRank.ACE);
            } else if (ordered) {
                return new Player.Rank(Player.HandRank.STRAIGHT_FLASH, cardList.get(0).getCardRank());
            } else {
                return new Player.Rank(Player.HandRank.FLUSH, cardList.get(0).getCardRank());
            }
        } else {
            return nextRanker.rank(cards);
        }
    }
}
