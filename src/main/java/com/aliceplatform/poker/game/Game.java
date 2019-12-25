package com.aliceplatform.poker.game;

import com.aliceplatform.poker.player.HumanPlayer;
import com.aliceplatform.poker.player.MachinePlayer;
import com.aliceplatform.poker.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Game Entry point
 */
public class Game {
    private List<Player> players = new ArrayList<>();
    private GameRound gameRound = new GameRound();
    int numberOfMachinePlayers;
    int numberOfRealPlayers;


    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void start() {
        invitePlayers();
        Scanner scanner = new Scanner(System.in);
        String continueGame = "Y";
        while (continueGame.equalsIgnoreCase("y")){
            gameRound.startRound(players);
            System.out.println("Do you want to continue Game?");
            continueGame = scanner.next();
        }
        System.out.println("Good bye!");
    }

    private void invitePlayers() {
        while (numberOfMachinePlayers + numberOfRealPlayers < 2 || numberOfMachinePlayers + numberOfRealPlayers > 23) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("How many machine players do you want to invite?");
            numberOfMachinePlayers = scanner.nextInt();
            System.out.println("How many real players do you want to invite?");
            numberOfRealPlayers = scanner.nextInt();
        }
        for (int i = 0; i < numberOfMachinePlayers; i++) {
            Player player = new MachinePlayer("MachinePlayer-" + i);
            players.add(player);
        }
        for (int i = numberOfMachinePlayers; i < numberOfMachinePlayers + numberOfRealPlayers; i++) {
            Player player = new HumanPlayer("HumanPlayer-" + i);
            players.add(player);
        }
    }

}
