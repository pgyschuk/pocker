package com.aliceplatform.poker.ranker;

import com.aliceplatform.poker.cards.Card;
import com.aliceplatform.poker.cards.Rank;

import java.util.List;

/**
 * Card ranker chain which combines {@link CardSuitRanker} and {@link CardRankRanker} as chain
 */
public class CardRanker implements Ranker {
    Ranker ranker = new CardSuitRanker(new CardRankRanker());

    @Override
    public Rank rank(List<Card> cards) {
        return ranker.rank(cards);
    }
}
