package edu.io.token;

public class PickaxeToken extends Token {

    private final double gainFactor;
    private final int initialDurability;
    private int durability;

    public PickaxeToken() {
        this(1.5, 3);
    }

    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
    }

    public PickaxeToken(double gainFactor, int durability) {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor <= 0) {
            throw new IllegalArgumentException("gainFactor must be > 0");
        }
        if (durability <= 0) {
            throw new IllegalArgumentException("durability must be > 0");
        }
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

    public void repair() {
        this.durability = initialDurability;
    }

    public UseResult useWith(Token token) {
        if (isBroken()) {
            return new UseResult(State.BROKEN);
        }

        if (token instanceof GoldToken) {
            use(); // уменьшили прочность
            return new UseResult(isBroken() ? State.BROKEN : State.WORKING);
        }

        return new UseResult(State.IDLE);
    }


    public enum State {
        WORKING, BROKEN, IDLE
    }

    public static class UseResult {
        private final State state;

        public UseResult(State state) {
            this.state = state;
        }

        public UseResult ifWorking(Runnable action) {
            if (state == State.WORKING && action != null) {
                action.run();
            }
            return this;
        }

        public UseResult ifBroken(Runnable action) {
            if (state == State.BROKEN && action != null) {
                action.run();
            }
            return this;
        }

        public UseResult ifIdle(Runnable action) {
            if (state == State.IDLE && action != null) {
                action.run();
            }
            return this;
        }
    }
}
