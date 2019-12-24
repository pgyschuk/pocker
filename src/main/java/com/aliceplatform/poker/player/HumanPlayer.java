package com.aliceplatform.poker.player;

import java.util.Scanner;

/**
 * Every action of player will be entered from console
 */
public class HumanPlayer extends Player {
    Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public Action doAction() {
        System.out.println(String.format("%s, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE", this.identifier));
        int intAction = scanner.nextInt();
        Action action = Action.values()[intAction];
        switch (action) {
            case RAISE: {
                int raiseAmount = raiseAmount();
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

    private int raiseAmount() {
        System.out.println("What is your raise amount? Enter integer number");
        int raiseValue = scanner.nextInt();
        return raiseValue;
    }
}
