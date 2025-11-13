package edu.io;

public class Token {

    private final String label;

    public static final Token EMPTY = new Token("・");
    public static final Token PLAYER = new Token("웃");
    public static final Token GOLD = new Token("💰︎");

    public Token(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
