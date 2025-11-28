package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public class Player {

    private PlayerToken token;
    private double gold = 0.0;

    public void assignToken(PlayerToken token){
        this.token = token;
    }

    public PlayerToken token(){
        return this.token;
    }

    public double gold() {
        return gold;
    }

    public void gainGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("gain amount must be non-negative");
        }
        gold += amount;
    }

    public void loseGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("lose amount must be non-negative");
        }
        if (amount > gold) {
            throw new IllegalArgumentException("not enough gold");
        }
        gold -= amount;
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken) {
            GoldToken g = (GoldToken) token;
            gainGold(g.amount());
        }

    }

}
