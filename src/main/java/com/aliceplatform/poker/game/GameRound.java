package com.aliceplatform.poker.game;

import com.aliceplatform.poker.cards.Card;
import com.aliceplatform.poker.cards.Deck;
import com.aliceplatform.poker.cards.Rank;
import com.aliceplatform.poker.player.Player;
import com.aliceplatform.poker.ranker.CardRanker;
import com.aliceplatform.poker.ranker.Ranker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * State machine for Poker game
 * Represent state of system for each round
 */
public class GameRound {
    private int smallBlind = 1;
    private int bigBlind = smallBlind * 2;
    private int bank = 0;
    private int maxBid = 0;
    private int dealerIndex = -1;
    int lastRaisedIndex = -1;
    private List<Player> activePlayers = new LinkedList<>();

    private List<Card> flop = new ArrayList<>(5);
    private Deck deck = new Deck();

    public void addToBank(int amount) {
        bank += amount;
    }

    public int getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(int bid) {
        this.maxBid = bid;
    }

    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    public int getLastRaisedIndex() {
        return lastRaisedIndex;
    }

    public void setLastRaisedIndex(int lastRaisedIndex) {
        this.lastRaisedIndex = lastRaisedIndex;
    }

    public void registerPlayer(Player player) {
        activePlayers.add(player);
        player.setGameRound(this);
    }

    public void unregisterPlayer(Player player) {
        activePlayers.remove(player);
    }

    public void startRound(List<Player> players) {
        if (++dealerIndex == activePlayers.size()) {
            dealerIndex = 0;
        }
        maxBid = 0;
        bank = 0;
        activePlayers.clear();
        players.stream().forEach(player -> {
            if (player.getAccount() >= smallBlind) {
                player.setCards(deck.getCards(2));
                registerPlayer(player);
            }
        });
        preFlop();
        flop();
        turn();
        river();
        winnerEvaluation();
    }

    private void preFlop() {
        getActivePlayers().get(getSmallBlindIndex()).raise(smallBlind);
        getActivePlayers().get(getBigBlindIndex()).raise(bigBlind);
        doPlayersActions(getBigBlindIndex());
    }

    private void flop() {
        doDealerStepStep(3);
        doPlayersActions(getPrevPlayerIndex(getSmallBlindIndex()));
    }


    private void turn() {
        doDealerStepStep(1);
        doPlayersActions(getPrevPlayerIndex(getSmallBlindIndex()));
    }

    private void river() {
        doDealerStepStep(1);
        doPlayersActions(getPrevPlayerIndex(getSmallBlindIndex()));
    }

    private void doDealerStepStep(int numberOfCard) {
        deck.getCards(1);
        flop.addAll(deck.getCards(numberOfCard));
        System.out.println("Flop Cards:");
        flop.forEach(card -> System.out.println(card));
        lastRaisedIndex = dealerIndex + 1;
    }

    private void doPlayersActions(int startIndex) {
        int currentPlayerIndex = getNextPlayerIndex(startIndex);
        while (getLastRaisedIndex() != currentPlayerIndex) {
            getActivePlayers().get(currentPlayerIndex).doAction();
            currentPlayerIndex = getNextPlayerIndex(currentPlayerIndex);
        }
    }

    private void winnerEvaluation() {
        Ranker ranker = new CardRanker();
        Map<Player, Rank> playerRankMap = activePlayers.stream().collect(Collectors.toMap(player -> player, player -> {
            List<Card> allPlayerCards = new ArrayList<>(7);
            allPlayerCards.addAll(player.getCards());
            allPlayerCards.addAll(flop);
            return ranker.rank(allPlayerCards);
        }));
        List<Map.Entry<Player, Rank>> rankedPlayers = playerRankMap.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue)).collect(Collectors.toList());
        int numberOfWinners = 1;
        for (int i = 0; i < rankedPlayers.size(); i++) {
            if (i + 1 < rankedPlayers.size()) {
                Map.Entry<Player, Rank> entry1 = rankedPlayers.get(i);
                Map.Entry<Player, Rank> entry2 = rankedPlayers.get(i + 1);
                if (entry1.getValue().compareTo(entry2.getValue()) == 0) {
                    numberOfWinners++;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < numberOfWinners; i++) {
            Player winnerPlayer = rankedPlayers.get(i).getKey();
            winnerPlayer.replenishAccount(bank / numberOfWinners);
        }

        for (int i = 0; i < rankedPlayers.size(); i++) {
            System.out.println(String.format("%s. %s - with %s", i + 1, rankedPlayers.get(i).getKey(), rankedPlayers.get(i).getValue()));
        }
    }


    private int getSmallBlindIndex() {
        if (dealerIndex + 1 < getActivePlayers().size()) {
            return dealerIndex + 1;
        } else {
            return 0;
        }
    }

    private int getBigBlindIndex() {
        if (getSmallBlindIndex() + 1 < getActivePlayers().size()) {
            return getSmallBlindIndex() + 1;
        } else {
            return 0;
        }
    }

    private int getNextPlayerIndex(int currentPlayerIndex) {
        if (currentPlayerIndex + 1 < getActivePlayers().size()) {
            return currentPlayerIndex + 1;
        } else {
            return 0;
        }
    }

    private int getPrevPlayerIndex(int currentPlayerIndex) {
        if (currentPlayerIndex - 1 >= 0) {
            return currentPlayerIndex - 1;
        } else {
            return activePlayers.size() - 1;
        }
    }

}
