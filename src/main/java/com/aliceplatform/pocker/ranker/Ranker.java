package com.aliceplatform.pocker.ranker;

import com.aliceplatform.pocker.model.Card;
import com.aliceplatform.pocker.model.Player;

import java.util.List;

public interface Ranker {
    Player.HandRanking rank(List<Card> cards);
}
