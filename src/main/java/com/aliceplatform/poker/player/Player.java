package com.aliceplatform.poker.player;

import com.aliceplatform.poker.cards.Card;
import com.aliceplatform.poker.game.GameRound;
import com.aliceplatform.poker.output.ConsoleOutputWriter;
import com.aliceplatform.poker.output.OutputWriter;

import java.util.List;
import java.util.Objects;

/**
 * Base implementation of game player.
 * On each step of game player can do:
 * {@link #fold()},  {@link #checkOrCall()} or {@link #raise(int)}}
 */
public abstract class Player {
    private OutputWriter outputWriter = new ConsoleOutputWriter();
    protected int account = 10000;
    protected final String identifier;
    protected List<Card> cards;
    protected GameRound gameRound;

    public Player(String identifier) {
        this.identifier = identifier;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * @param cards - Player hand cards
     */
    public void setCards(List<Card> cards) {
        outputWriter.writeMessage("%s cards: [%s, %s]$", identifier, cards.get(0), cards.get(1));
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
     *
     * @return {@link Action#FOLD}, {@link Action#CHECK_OR_CALL} or {@link Action#CHECK_OR_CALL} depends on Player decision
     */
    public abstract Action doAction();

    /**
     * Method for subtraction from Player account
     *
     * @param amount - value which will be subtracted from current balance
     */
    public void subtractAccount(int amount) {
        account -= amount;
    }

    /**
     * Method for replenishment of Player account
     *
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
     * @return player identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Fold action is freewill decision of {@link Player} when Player do not want to continue game.
     * Placed Bids will be lost
     *
     * @return {@link Action#FOLD}
     */
    protected Action fold() {
        gameRound.unregisterPlayer(this);
        outputWriter.writeMessage("Player: %s fold", identifier);
        return Action.FOLD;
    }

    /**
     * CheckOrCall action is freewill decision of {@link Player} when Player wants to be in game with minimal investment
     *
     * @return {@link Action#CHECK_OR_CALL} In case when Player have enough balance to cover maximum round bid or {@link Action#FOLD}
     */
    protected Action checkOrCall() {
        int needToCall = gameRound.getMaxBid() - gameRound.getPlayerRoundBid(this);
        if (needToCall <= account) {
            gameRound.addToBank(needToCall);
            gameRound.addPlayerRoundBid(this, needToCall);
            subtractAccount(needToCall);
            outputWriter.writeMessage("Player: %s call adding %s$", identifier, needToCall);
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
        int needToCall = gameRound.getMaxBid() - gameRound.getPlayerRoundBid(this);
        if (amount > needToCall && amount <= account && !this.equals(gameRound.getLastRaisedPlayer())) {
            gameRound.addToBank(amount);
            gameRound.addPlayerRoundBid(this, amount);
            gameRound.setMaxBid(gameRound.getPlayerRoundBid(this));
            subtractAccount(amount);
            gameRound.setLastRaisedPlayer(this);
            outputWriter.writeMessage("Player: %s raised %s$", identifier, amount);
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
        return "{" + identifier + ": account=" + account + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(identifier, player.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
