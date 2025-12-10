package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Board board = new Board();
        board.setPlacementStrategy(new RandomPlacementStrategy());

        Player player = new Player();
        PlayerToken token = new PlayerToken(player, board);


        int goldCount = 5;
        for (int i = 0; i < goldCount; i++) {
            Board.Coords c = board.getAvailableSquare();
            board.placeToken(c.col(), c.row(), new GoldToken(1.0));
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== BOARD ===");
            board.display();
            System.out.println("Gold: " + player.gold());
            System.out.println("Move: LEFT / RIGHT / UP / DOWN / EXIT");
            System.out.print("> ");

            String cmd = scanner.nextLine().trim().toUpperCase();

            if (cmd.equals("EXIT")) {
                System.out.println("Game over.");
                break;
            }

            PlayerToken.Move move;
            try {
                move = PlayerToken.Move.valueOf(cmd);
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown command");
                continue;
            }

            int col = token.pos().col();
            int row = token.pos().row();
            int nextCol = col;
            int nextRow = row;

            switch (move) {
                case LEFT -> nextCol--;
                case RIGHT -> nextCol++;
                case UP -> nextRow--;
                case DOWN -> nextRow++;
            }

            try {

                if (board.peekToken(nextCol, nextRow) instanceof GoldToken g) {
                    player.interactWithToken(g);
                }

                token.move(move);
            } catch (IllegalArgumentException e) {
                System.out.println("You can't move there!");
            }
        }
    }
}
