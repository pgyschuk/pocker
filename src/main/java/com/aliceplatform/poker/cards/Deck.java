package com.aliceplatform.poker.cards;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * State machine representation for deck of playing cards
 */
public class Deck {

    private List<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>();
        for(Card.Suit suit: Card.Suit.values()){
            for(Card.CardRank cardRank : Card.CardRank.values()){
                cards.add(new Card(suit, cardRank));
            }
        }
        Collections.shuffle(cards);
    }

    /**
     * Returned and deleted from deck {@see numberOfCards} cards
     * @param numberOfCards to be returned
     * @return top number of cards
     */
    public List<Card> getCards(int numberOfCards){
        List<Card> result = List.copyOf(cards.subList(0, numberOfCards));
        cards.removeAll(result);
        return result;
    }

}
