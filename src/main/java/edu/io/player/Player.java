package edu.io.player;

import edu.io.token.AnvilToken;
import edu.io.token.GoldToken;
import edu.io.token.PickaxeToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public class Player {


    public Gold gold = new Gold();

    private PlayerToken token;
    private final Shed shed = new Shed();

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken g) {
            double value = g.amount();
            var tool = shed.getTool();
            if (tool instanceof PickaxeToken p && !p.isBroken()) {
                value = value * p.gainFactor();
                p.use();
            }
            gold.gain(value);
        }
        else if (token instanceof PickaxeToken p) {
            shed.add(p);
        }
        else if (token instanceof AnvilToken) {
            var tool = shed.getTool();
            if (tool instanceof PickaxeToken p) {
                p.repair();
            }
        }

    }
}
