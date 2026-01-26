package edu.io.player;

public class Vitals {

    private int hydration = 100;
    private Runnable onDeath = () -> {};

    public int hydration() {
        return hydration;
    }

    public boolean isAlive() {
        return hydration > 0;
    }

    public void hydrate(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("amount must be >= 0");

        hydration = Math.min(100, hydration + amount);
    }

    public void dehydrate(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("amount must be >= 0");

        hydration = Math.max(0, hydration - amount);

        if (hydration == 0) {
            onDeath.run();
        }
    }

    public void setOnDeathHandler(Runnable handler) {
        if (handler == null)
            throw new NullPointerException("handler cannot be null");
        this.onDeath = handler;
    }
}
