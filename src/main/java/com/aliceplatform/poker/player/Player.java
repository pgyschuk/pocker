package com.aliceplatform.poker.player;

import com.aliceplatform.poker.cards.Card;
import com.aliceplatform.poker.game.GameRound;

import java.util.List;

/**
 * Base implementation of game player.
 * On each step of game player can do:
 * {@link #fold()},  {@link #checkOrCall()} or {@link #raise(int)}}
 */
public abstract class Player {
    protected int account = 10000;
    protected final String name;
    protected List<Card> cards;
    protected GameRound gameRound;
    protected int gameRoundBid = 0;

    public Player(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * @param cards - Player hand cards
     */
    public void setCards(List<Card> cards) {
        System.out.println(String.format("%s cards: [%s, %s]$", name, cards.get(0), cards.get(1)));
        this.cards = cards;
    }

    /**
     * @param gameRound - Current state of game
     */
    public void setGameRound(GameRound gameRound) {
        this.gameRound = gameRound;
    }

    /**
     * Player decision
     * @return {@link Action#FOLD}, {@link Action#CHECK_OR_CALL} or {@link Action#CHECK_OR_CALL} depends on Player decision
     */
    public abstract Action doAction();

    /**
     * Method for subtraction from Player account
     * @param amount - value which will be subtracted from current balance
     */
    public void subtractAccount(int amount) {
        account -= amount;
    }

    /**
     * Method for replenishment of Player account
     * @param amount - value which will be added to current balance
     */
    public void replenishAccount(int amount) {
        account += amount;
    }

    /**
     * @return current account balance of {link {@link Player}}
     */
    public int getAccount() {
        return account;
    }

    /**
     * Fold action is freewill decision of {@link Player} when Player do not want to continue game.
     * Placed Bids will be lost
     *
     * @return {@link Action#FOLD}
     */
    protected Action fold() {
        gameRound.unregisterPlayer(this);
        System.out.println(String.format("Player: %s fold", name));
        return Action.FOLD;
    }

    /**
     * CheckOrCall action is freewill decision of {@link Player} when Player wants to be in game with minimal investment
     *
     * @return {@link Action#CHECK_OR_CALL} In case when Player have enough balance to cover maximum round bid or {@link Action#FOLD}
     */
    protected Action checkOrCall() {
        int needToCall = gameRound.getMaxBid() - gameRoundBid;
        if (needToCall <= account) {
            gameRound.addToBank(needToCall);
            gameRoundBid += needToCall;
            subtractAccount(needToCall);
            System.out.println(String.format("Player: %s call adding %s$", name, needToCall));
            return Action.CHECK_OR_CALL;
        } else {
            return fold();
        }
    }

    /**
     * Raise action could be forced by dealer, or could be freewill decision of {@link Player}
     *
     * @param amount - amount of raise. Should be bigger than maximum round bid and smaller or equal available account balance,
     *               otherwise {@link #checkOrCall()} will be executed
     * @return {@link Action#RAISE} or value returned by delegate method
     */
    public Action raise(int amount) {
        int needToCall = gameRound.getMaxBid() - gameRoundBid;
        if (amount > needToCall && amount <= account && gameRound.getActivePlayers().indexOf(this) != gameRound.getLastRaisedIndex()) {
            gameRound.addToBank(amount);
            gameRoundBid += amount;
            gameRound.setMaxBid(gameRoundBid);
            subtractAccount(amount);
            gameRound.setLastRaisedIndex(gameRound.getActivePlayers().indexOf(this));
            System.out.println(String.format("Player: %s raised %s$", name, amount));
            return Action.RAISE;
        } else {
            return checkOrCall();
        }
    }

    /**
     * All actions which Player can do
     */
    public enum Action {
        FOLD, CHECK_OR_CALL, RAISE
    }

    @Override
    public String toString() {
        return "{" + name + ": account=" + account + "}";
    }
}
