package edu.io;
import edu.io.Board;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public class Main {
    public static void main(String[] args) {


        Board board = new Board();


        PlayerToken player = new PlayerToken(board);

        System.out.println("Start");
        board.display();

        System.out.println(" ");

        player.move(PlayerToken.Move.DOWN);
        board.display();

        System.out.println(" ");

        player.move(PlayerToken.Move.RIGHT);
        board.display();


        System.out.println("\nCoords: ");
        Board.Coords p = player.pos();
        System.out.println("col = " + p.col() + ", row = " + p.row());
    }
}
