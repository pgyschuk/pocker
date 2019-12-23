package com.aliceplatform.pocker.ranker;

import com.aliceplatform.pocker.model.Card;
import com.aliceplatform.pocker.model.Player;

import java.util.Collections;
import java.util.List;

public class RoyalFlashRanker implements Ranker {

    public Player.HandRanking rank(List<Card> cards) {
        return null;
    }

    /**
     * Ordering cards by Suit and by character
     * @param cards
     */
    private void sort(List<Card> cards) {
        Collections.sort(cards, (card1, card2) -> {
            int result = card1.getSuit().ordinal() - card2.getSuit().ordinal();
            if (result != 0) {
                result = card1.getCharacter().getValue() - card2.getCharacter().getValue();
            }
            return result;
        });
    }
}
