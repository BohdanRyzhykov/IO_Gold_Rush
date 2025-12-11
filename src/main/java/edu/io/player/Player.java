package edu.io.player;

import edu.io.token.*;

public class Player {


    public final Gold gold = new Gold();

    private edu.io.token.PlayerToken token;
    private final Shed shed = new Shed();

    public void assignToken(edu.io.token.PlayerToken token) {
        this.token = token;
    }

    public edu.io.token.PlayerToken token() {
        return token;
    }


    public void interactWithToken(Token token) {
        if (token == null) return;

        switch (token) {
            case GoldToken g -> usePickaxeOnGold(g);
            case PickaxeToken p -> shed.add(p);
            case AnvilToken a -> {
                Token tool = shed.getTool();
                if (tool instanceof PickaxeToken pick) {
                    pick.repair();
                }
            }
            default -> {

            }
        }
    }


    private void usePickaxeOnGold(GoldToken g) {
        double value = g.amount();

        Token tool = shed.getTool();
        if (tool instanceof PickaxeToken p && !p.isBroken()) {
            value = value * p.gainFactor();
            p.use();
        }

        gold.gain(value);
    }
}
