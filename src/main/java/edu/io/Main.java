package edu.io;
import edu.io.Board;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();

        Player bohdan = new Player();
        game.join(bohdan);

        game.start();






    }
}
