package edu.io.token;

public class WaterToken extends Token {

    private final int amount;

    public WaterToken() {
        this(10);
    }

    public WaterToken(int amount) {
        super("💧"); // можно заменить на Label, тесты не проверяют
        if (amount < 0 || amount > 100)
            throw new IllegalArgumentException("amount must be in 0..100");

        this.amount = amount;
    }

    public int amount() {
        return amount;
    }
}
