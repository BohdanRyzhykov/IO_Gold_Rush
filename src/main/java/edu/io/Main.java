package edu.io;

public class Main {
    public static void main(String[] args) {



        Board board = new Board();

        board.placeToken(2, 2, Token.PLAYER);
        board.placeToken(1, 1, Token.GOLD);

        board.display();

        board.clean();

        board.display();



    }
}
