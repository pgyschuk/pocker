package com.aliceplatform.poker.input;

import com.aliceplatform.poker.output.ConsoleOutputWriter;
import com.aliceplatform.poker.output.OutputWriter;
import com.aliceplatform.poker.player.Player;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {
    private Scanner scanner = new Scanner(System.in);
    private OutputWriter outputWriter = new ConsoleOutputWriter();

    @Override
    public Player.Action readPlayerAction(Player player) {
        int intAction = -1;
        Player.Action action = null;
        while (intAction < 0 || intAction > 2) {
            try {
                outputWriter.writeMessage("%s, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE", player.getIdentifier());
                intAction = Integer.valueOf(scanner.next());
                action = Player.Action.values()[intAction];
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                outputWriter.writeMessage("Invalid value");
            }
        }
        return action;
    }

    @Override
    public int readPlayerRaiseAmount(Player player) {
        int raiseValue = 0;
        while (raiseValue == 0) {
            try {
                outputWriter.writeMessage("%s, What is your raise amount? Enter integer number", player.getIdentifier());
                raiseValue = Integer.valueOf(scanner.next());
            } catch (NumberFormatException e) {
                outputWriter.writeMessage("Invalid value");
            }
        }
        return raiseValue;
    }

    @Override
    public PlayerInput readPlayersConfiguration() {
        int numberOfMachinePlayers = 0;
        int numberOfRealPlayers = 0;
        PlayerInput result = null;
        outputWriter.writeMessage("Max number of players is 22");
        while (numberOfMachinePlayers + numberOfRealPlayers < 2 || numberOfMachinePlayers + numberOfRealPlayers > 22) {
            try {
                outputWriter.writeMessage("How many machine players do you want to invite?");
                numberOfMachinePlayers = Integer.valueOf(scanner.next());
                outputWriter.writeMessage("How many real players do you want to invite?");
                numberOfRealPlayers = Integer.valueOf(scanner.next());
                result = new PlayerInput(numberOfMachinePlayers, numberOfRealPlayers);
            } catch (NumberFormatException e) {
                outputWriter.writeMessage("Invalid value");
            }
        }
        return result;
    }

}
