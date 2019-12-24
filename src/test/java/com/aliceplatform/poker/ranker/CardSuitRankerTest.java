package com.aliceplatform.poker.ranker;

import com.aliceplatform.poker.cards.Card;
import com.aliceplatform.poker.cards.Rank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardSuitRankerTest {
    private Ranker cardRankRanker = new CardSuitRanker(cards -> {
        throw new UnsupportedOperationException("SuitRanker can not identify any combination");
    });

    @Test
    public void royalFlush_with_3_KING_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.ACE),
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.CLUB, Card.CardRank.QUEEN),
                new Card(Card.Suit.CLUB, Card.CardRank.JACK),
                new Card(Card.Suit.CLUB, Card.CardRank.TEN),
                new Card(Card.Suit.HEART, Card.CardRank.KING),
                new Card(Card.Suit.DIAMOND, Card.CardRank.KING)
        );

        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.ROYAL_FLUSH, Card.CardRank.ACE), rank);
    }

    @Test
    public void straightFlush_with_3_QUEEN_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.NINE),
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.CLUB, Card.CardRank.QUEEN),
                new Card(Card.Suit.CLUB, Card.CardRank.JACK),
                new Card(Card.Suit.CLUB, Card.CardRank.TEN),
                new Card(Card.Suit.HEART, Card.CardRank.QUEEN),
                new Card(Card.Suit.DIAMOND, Card.CardRank.QUEEN)
        );

        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.STRAIGHT_FLUSH, Card.CardRank.KING), rank);
    }

    @Test
    public void straightFlush_with_3_QUEEN_and_4_ordered_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.NINE),
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.CLUB, Card.CardRank.QUEEN),
                new Card(Card.Suit.CLUB, Card.CardRank.JACK),
                new Card(Card.Suit.CLUB, Card.CardRank.TWO),
                new Card(Card.Suit.HEART, Card.CardRank.QUEEN),
                new Card(Card.Suit.DIAMOND, Card.CardRank.QUEEN)
        );

        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.FLUSH, Card.CardRank.KING), rank);
    }

    @Test
    public void combination_which_can_not_be_detected_by_card_suite_ranker_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.QUEEN),
                new Card(Card.Suit.CLUB, Card.CardRank.JACK),
                new Card(Card.Suit.CLUB, Card.CardRank.SEVEN),
                new Card(Card.Suit.SPADE, Card.CardRank.NINE),
                new Card(Card.Suit.CLUB, Card.CardRank.EIGHT),
                new Card(Card.Suit.DIAMOND, Card.CardRank.ACE),
                new Card(Card.Suit.HEART, Card.CardRank.TWO)
        );
        Assertions.assertThrows(UnsupportedOperationException.class, () -> cardRankRanker.rank(CARDS));
    }
}