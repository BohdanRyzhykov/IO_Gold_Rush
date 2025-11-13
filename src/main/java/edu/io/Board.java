package edu.io;

public class Board {

    private final Token[][] board;
    public final int size = 5;

    public Board() {
        board = new Token[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = Token.EMPTY;
            }
        }
    }

    public void placeToken(int row, int col, Token token) {
        board[row][col] = token;
    }

    
    public Token square(int row, int col) {
        return board[row][col];
    }

    public void clean() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = Token.EMPTY;
            }
        }
    }

    public void display() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(board[row][col].getLabel());
            }
            System.out.println();
        }
    }
}
