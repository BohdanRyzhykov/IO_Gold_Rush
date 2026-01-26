package edu.io.player;

import edu.io.token.*;

public class Player {

    public final Gold gold = new Gold();
    public final Vitals vitals = new Vitals();

    private PlayerToken token;
    private final Shed shed = new Shed();

    public void assignToken(PlayerToken token) {
        if (token == null)
            throw new NullPointerException();
        this.token = token;
    }

    public Shed shed() {
        return this.shed;
    }


    public PlayerToken token() {
        return token;
    }

    public void interactWithToken(Token token) {
        if (token == null)
            throw new NullPointerException();

        if (!vitals.isAlive())
            throw new IllegalStateException("Player is dead");

        switch (token) {

            case EmptyToken e -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
            }

            case GoldToken g -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
                usePickaxeOnGold(g);
            }

            case PickaxeToken p -> {
                shed.add(p);
            }

            case AnvilToken a -> {
                Token tool = shed.getTool();
                if (tool instanceof PickaxeToken pick) {
                    pick.repair();
                    vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);
                }
            }

            case WaterToken w -> {
                vitals.hydrate(w.amount());
            }

            default -> {}
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
