package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {

    private final Token[][] board;
    private final int size;

    public Board() {
        this.size = 5;
        board = new Token[size][size];
        clean();
    }

    public int size() {
        return size;
    }

    public void placeToken(int col, int row, Token token) {
        board[row][col] = token;
    }

    public Token peekToken(int col, int row) {
        return board[row][col];
    }

    public void clean() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new EmptyToken();
            }
        }
    }

    public void display() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(board[row][col].label());
            }
            System.out.println();
        }
    }


    public static final class Coords {
        private final int col;
        private final int row;

        public Coords(int col, int row) {
            this.col = col;
            this.row = row;
        }

        public int col() { return col; }
        public int row() { return row; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coords)) return false;
            Coords c = (Coords) o;
            return col == c.col && row == c.row;
        }
    }
    public Coords getAvailableSquare() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] instanceof EmptyToken) {
                    return new Coords(col, row);
                }
            }
        }
        throw new IllegalStateException("Board is full");
    }



}
