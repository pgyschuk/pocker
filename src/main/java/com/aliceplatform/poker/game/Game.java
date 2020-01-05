package com.aliceplatform.poker.game;

import com.aliceplatform.poker.input.ConsoleInputReader;
import com.aliceplatform.poker.input.InputReader;
import com.aliceplatform.poker.input.PlayerInput;
import com.aliceplatform.poker.player.HumanPlayer;
import com.aliceplatform.poker.player.MachinePlayer;
import com.aliceplatform.poker.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Game Entry point
 */
public class Game {
    private InputReader inputReader = new ConsoleInputReader();

    private List<Player> players = new ArrayList<>();
    private GameRound gameRound = new GameRound();


    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void start() {
        invitePlayers();
        boolean continueGame;
        do {
            gameRound.startRound(players);
            continueGame = inputReader.readContinueGame();
        } while (continueGame);
    }

    private void invitePlayers() {
        PlayerInput playerConfiguration = inputReader.readPlayersConfiguration();
        for (int i = 0; i < playerConfiguration.getNumberOfMachinePlayers(); i++) {
            Player player = new MachinePlayer("MachinePlayer-" + i);
            players.add(player);
        }
        for (int i = playerConfiguration.getNumberOfMachinePlayers(); i < playerConfiguration.getNumberOfMachinePlayers() + playerConfiguration.getNumberOfRealPlayers(); i++) {
            Player player = new HumanPlayer("HumanPlayer-" + i);
            players.add(player);
        }
    }

}
