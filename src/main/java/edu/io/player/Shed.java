package edu.io.player;

import edu.io.token.Token;

public class Shed {

    private Token tool;

    public Shed() {
        this.tool = new NoTool();
    }

    public boolean isEmpty() {
        return tool instanceof NoTool;
    }

    public Token getTool() {
        return tool;
    }

    public void add(Token tool) {
        if (tool == null) {
            throw new IllegalArgumentException("tool cannot be null");
        }
        this.tool = tool;
    }

    public void dropTool() {
        this.tool = new NoTool();
    }
}
