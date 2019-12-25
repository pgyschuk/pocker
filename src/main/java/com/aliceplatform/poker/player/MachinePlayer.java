package com.aliceplatform.poker.player;

/**
 * Computer player which trying to play with minimal risk.
 */
public class MachinePlayer extends Player {

    public MachinePlayer(String identifier) {
        super(identifier);
    }

    @Override
    public Action doAction() {
        return checkOrCall();
    }

}
