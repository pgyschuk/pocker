package com.aliceplatform.pocker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>(56);

        for(Card.Suit suit: Card.Suit.values()){
            for(Card.CardRank cardRank : Card.CardRank.values()){
                cards.add(new Card(suit, cardRank));
            }
        }
        Collections.shuffle(cards);
    }

}
