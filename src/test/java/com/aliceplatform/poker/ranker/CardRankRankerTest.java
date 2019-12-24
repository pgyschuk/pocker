package com.aliceplatform.poker.ranker;

import com.aliceplatform.poker.cards.Card;
import com.aliceplatform.poker.cards.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardRankRankerTest {
    private Ranker cardRankRanker = new CardRankRanker();


    @Test
    public void fourOfKind_with_4_ACE_and_mixed_others_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.ACE),
                new Card(Card.Suit.HEART, Card.CardRank.ACE),
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.ACE),
                new Card(Card.Suit.SPADE, Card.CardRank.JACK),
                new Card(Card.Suit.CLUB, Card.CardRank.TWO),
                new Card(Card.Suit.DIAMOND, Card.CardRank.ACE)
        );

        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.FOUR_OF_A_KIND, Card.CardRank.ACE), rank);
    }

    @Test
    public void fourOfKind_with_4_TWO_and_3_KING_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.TWO),
                new Card(Card.Suit.HEART, Card.CardRank.TWO),
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.TWO),
                new Card(Card.Suit.SPADE, Card.CardRank.KING),
                new Card(Card.Suit.HEART, Card.CardRank.KING),
                new Card(Card.Suit.DIAMOND, Card.CardRank.TWO)
        );
        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.FOUR_OF_A_KIND, Card.CardRank.TWO), rank);
    }


    @Test
    public void fullHouse_with_3_KING_and_2_ACE_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.HEART, Card.CardRank.ACE),
                new Card(Card.Suit.HEART, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.ACE),
                new Card(Card.Suit.CLUB, Card.CardRank.TWO),
                new Card(Card.Suit.DIAMOND, Card.CardRank.THREE)
        );
        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.FULL_HOUSE, Card.CardRank.KING), rank);
    }

    @Test
    public void fullHouse_with_3_KING_and_3_ACE_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.HEART, Card.CardRank.ACE),
                new Card(Card.Suit.HEART, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.ACE),
                new Card(Card.Suit.CLUB, Card.CardRank.ACE),
                new Card(Card.Suit.DIAMOND, Card.CardRank.THREE)
        );
        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.FULL_HOUSE, Card.CardRank.ACE), rank);
    }

    @Test
    public void threeOfKind_with_3_KING_and_4_ordered_other_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.HEART, Card.CardRank.QUEEN),
                new Card(Card.Suit.HEART, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.JACK),
                new Card(Card.Suit.CLUB, Card.CardRank.TEN),
                new Card(Card.Suit.DIAMOND, Card.CardRank.TWO)
        );
        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.TREE_OF_A_KIND, Card.CardRank.KING), rank);
    }

    @Test
    public void threeOfKind_with_3_KING_and_4_same_suite_other_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.DIAMOND, Card.CardRank.QUEEN),
                new Card(Card.Suit.HEART, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.KING),
                new Card(Card.Suit.DIAMOND, Card.CardRank.JACK),
                new Card(Card.Suit.DIAMOND, Card.CardRank.TEN),
                new Card(Card.Suit.DIAMOND, Card.CardRank.TWO)
        );
        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.TREE_OF_A_KIND, Card.CardRank.KING), rank);
    }

    @Test
    public void twoPairs_with_2_KING_2_ACE_and_3_same_suite_other_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.DIAMOND, Card.CardRank.ACE),
                new Card(Card.Suit.HEART, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.ACE),
                new Card(Card.Suit.DIAMOND, Card.CardRank.JACK),
                new Card(Card.Suit.DIAMOND, Card.CardRank.TEN),
                new Card(Card.Suit.DIAMOND, Card.CardRank.TWO)
        );
        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.TWO_PAIR, Card.CardRank.ACE), rank);
    }

    @Test
    public void twoPairs_with_2_KING_2_ACE_and_2_QUEEN_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.DIAMOND, Card.CardRank.ACE),
                new Card(Card.Suit.HEART, Card.CardRank.KING),
                new Card(Card.Suit.SPADE, Card.CardRank.ACE),
                new Card(Card.Suit.DIAMOND, Card.CardRank.QUEEN),
                new Card(Card.Suit.DIAMOND, Card.CardRank.TEN),
                new Card(Card.Suit.HEART, Card.CardRank.QUEEN)
        );
        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.TWO_PAIR, Card.CardRank.ACE), rank);
    }

    @Test
    public void onePairs_with_2_KING_4_same_suite_ordered_other_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.KING),
                new Card(Card.Suit.DIAMOND, Card.CardRank.NINE),
                new Card(Card.Suit.HEART, Card.CardRank.KING),
                new Card(Card.Suit.DIAMOND, Card.CardRank.EIGHT),
                new Card(Card.Suit.DIAMOND, Card.CardRank.JACK),
                new Card(Card.Suit.DIAMOND, Card.CardRank.TEN),
                new Card(Card.Suit.HEART, Card.CardRank.TWO)
        );
        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.ONE_PAIR, Card.CardRank.KING), rank);
    }

    @Test
    public void straight_with_QUEEN_to_EIGHT_4_of_5_same_suite_and_2_ACE_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.QUEEN),
                new Card(Card.Suit.CLUB, Card.CardRank.JACK),
                new Card(Card.Suit.CLUB, Card.CardRank.TEN),
                new Card(Card.Suit.SPADE, Card.CardRank.NINE),
                new Card(Card.Suit.CLUB, Card.CardRank.EIGHT),
                new Card(Card.Suit.DIAMOND, Card.CardRank.ACE),
                new Card(Card.Suit.HEART, Card.CardRank.ACE)
        );
        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.STRAIGHT, Card.CardRank.QUEEN), rank);
    }

    @Test
    public void highCard_with_QUEEN_and_4_of_5_same_suite_ordered_others_test() {
        List<Card> CARDS = List.of(
                new Card(Card.Suit.CLUB, Card.CardRank.QUEEN),
                new Card(Card.Suit.CLUB, Card.CardRank.JACK),
                new Card(Card.Suit.CLUB, Card.CardRank.SEVEN),
                new Card(Card.Suit.SPADE, Card.CardRank.NINE),
                new Card(Card.Suit.CLUB, Card.CardRank.EIGHT),
                new Card(Card.Suit.DIAMOND, Card.CardRank.ACE),
                new Card(Card.Suit.HEART, Card.CardRank.TWO)
        );
        Rank rank = cardRankRanker.rank(CARDS);
        assertEquals(new Rank(Rank.HandRank.HIGH_CARD, Card.CardRank.ACE), rank);
    }


}