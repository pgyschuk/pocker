package com.aliceplatform.poker.player;

import com.aliceplatform.poker.input.ConsoleInputReader;
import com.aliceplatform.poker.input.InputReader;

/**
 * Every action of player will be entered from console
 */
public class HumanPlayer extends Player {
    private InputReader inputReader = new ConsoleInputReader();

    public HumanPlayer(String identifier) {
        super(identifier);
    }

    @Override
    public Action doAction() {
        Action action = inputReader.readPlayerAction(this);
        switch (action) {
            case RAISE: {
                int raiseAmount = inputReader.readPlayerRaiseAmount(this);
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

}
