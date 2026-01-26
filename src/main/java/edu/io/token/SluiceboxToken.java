package edu.io.token;

public class SluiceboxToken extends Token {

    private final int initialDurability;
    private int durability;
    private double gainFactor;

    public SluiceboxToken() {
        this(1.2, 5);
    }

    public SluiceboxToken(double gainFactor, int durability) {
        super(Label.SLUICEBOX_TOKEN_LABEL);
        if (gainFactor <= 0) throw new IllegalArgumentException("gainFactor must be > 0");
        if (durability <= 0) throw new IllegalArgumentException("durability must be > 0");
        this.gainFactor = gainFactor;
        this.initialDurability = durability;
        this.durability = durability;
    }

    public double gainFactor() {
        return gainFactor;
    }

    public int durability() {
        return durability;
    }

    public boolean isBroken() {
        return durability <= 0;
    }


    public void use() {
        if (durability > 0) {
            durability--;
        }
    }

    public UseResult useWith(Token token) {
        if (isBroken()) return new UseResult(State.BROKEN);

        if (token instanceof GoldToken) {
            use();
            gainFactor = Math.max(0.0, gainFactor - 0.04);
            return new UseResult(isBroken() ? State.BROKEN : State.WORKING);
        } else {
            return new UseResult(State.IDLE);
        }
    }

    //  result wrapper (identyczny interfejs jak w PickaxeToken)
    public enum State {
        WORKING, BROKEN, IDLE
    }

    public static class UseResult {
        private final State state;

        public UseResult(State state) {
            this.state = state;
        }

        public UseResult ifWorking(Runnable action) {
            if (state == State.WORKING && action != null) action.run();
            return this;
        }

        public UseResult ifBroken(Runnable action) {
            if (state == State.BROKEN && action != null) action.run();
            return this;
        }

        public UseResult ifIdle(Runnable action) {
            if (state == State.IDLE && action != null) action.run();
            return this;
        }
    }
}
