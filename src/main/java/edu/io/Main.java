package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        Player player = new Player();

        game.join(player);
        Board board = getBoard(game);

        System.out.println("Start");
        board.display();
        System.out.println("Player gold: " + player.gold());
        System.out.println();

        board.placeToken(3, 2, new GoldToken(3.0));

        System.out.println("Gold placed at (3,2)");
        board.display();
        System.out.println();

        PlayerToken token = player.token();

        int targetCol = 3;
        int targetRow = 2;

        if (board.peekToken(targetCol, targetRow) instanceof GoldToken g) {
            player.interactWithToken(g);
        }

        while (token.pos().col() < targetCol) token.move(PlayerToken.Move.RIGHT);
        while (token.pos().col() > targetCol) token.move(PlayerToken.Move.LEFT);
        while (token.pos().row() < targetRow) token.move(PlayerToken.Move.DOWN);
        while (token.pos().row() > targetRow) token.move(PlayerToken.Move.UP);

        System.out.println("After collecting gold");
        board.display();
        System.out.println("Player gold: " + player.gold());
    }


    // Pomocnicza funkcja do pobrania planszy z obiektu Game
    private static Board getBoard(Game game) {
        try {
            for (var f : Game.class.getDeclaredFields()) {
                if (f.getType().equals(Board.class)) {
                    f.setAccessible(true);
                    return (Board) f.get(game);
                }
            }
        } catch (Exception ignored) {}
        return null;
    }
}
