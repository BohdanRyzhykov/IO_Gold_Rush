package edu.io.player;

public class Gold {

    private double amount;

    public Gold() {
        this(0.0);
    }

    public Gold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("gold amount must be >= 0");
        }
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public void gain(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("gain amount must be >= 0");
        }
        amount += value;
    }

    public void lose(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("lose amount must be >= 0");
        }
        if (value > amount) {
            throw new IllegalArgumentException("not enough gold");
        }
        amount -= value;
    }
}
