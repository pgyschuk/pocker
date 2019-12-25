package com.aliceplatform.poker.player;

import java.util.Scanner;

/**
 * Every action of player will be entered from console
 */
public class HumanPlayer extends Player {
    Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String identifier) {
        super(identifier);
    }

    @Override
    public Action doAction() {
        Action action = readAction();
        switch (action) {
            case RAISE: {
                int raiseAmount = readRaiseAmount();
                return raise(raiseAmount);
            }
            case CHECK_OR_CALL: {
                return checkOrCall();
            }
            case FOLD:
            default: {
                return fold();
            }
        }
    }

    private int readRaiseAmount() {
        int raiseValue = 0;
        while (raiseValue == 0) {
            try {
                System.out.println("What is your raise amount? Enter integer number");
                raiseValue = Integer.valueOf(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid value");
            }
        }
        return raiseValue;
    }

    private Action readAction() {
        int intAction = -1;
        Action action = null;
        while (intAction < 0 || intAction > 2) {
            try {
                System.out.println(String.format("%s, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE", this.identifier));
                intAction = Integer.valueOf(scanner.next());
                action = Action.values()[intAction];
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid value");
            }
        }
        return action;
    }
}
