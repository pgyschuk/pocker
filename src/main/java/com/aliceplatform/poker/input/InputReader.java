package com.aliceplatform.poker.input;

import com.aliceplatform.poker.player.Player;

public interface InputReader {

    /**
     * Reading player action for each step of game e.g
     * {@link Player.Action#FOLD}
     * {@link Player.Action#CHECK_OR_CALL}
     * {@link Player.Action#RAISE}
     *
     * @param player for which have to input value
     * @return player action
     */
    Player.Action readPlayerAction(Player player);

    /**
     * Reading amount of money which Player want to raise in case of  {@link Player.Action#RAISE} action
     * @param player
     * @return
     */
    int readPlayerRaiseAmount(Player player);

    /**
     * Reading initial player configuration. Number of Machine and Human players
     * @return object with information about number of players for next game
     */
    PlayerInput readPlayersConfiguration();

    /**
     * Reading player decision about next round of game
     * @return true if player want to continue and false when player want to stop game
     */
    boolean readContinueGame();
}
