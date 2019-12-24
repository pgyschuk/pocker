package com.aliceplatform.poker.ranker;

import com.aliceplatform.poker.cards.Card;
import com.aliceplatform.poker.cards.Rank;

import java.util.List;

public interface Ranker {
    Rank rank(List<Card> cards);
}
