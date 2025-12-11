package edu.io;

import edu.io.player.Player;
import edu.io.token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Random RNG = new Random();

    public static void main(String[] args) {
        Board board = new Board();
        Player player = new Player();
        PlayerToken token = new PlayerToken(player, board);
        player.assignToken(token);


        placeRandomGold(board, 6);
        placeRandomTool(board, new PickaxeToken());
        placeRandomTool(board, new AnvilToken());

        Scanner scanner = new Scanner(System.in);
        printHelp();

        while (true) {
            System.out.println();
            System.out.println("=== BOARD ===");
            board.display();
            System.out.printf("Gold: %.2f%n", player.gold.amount());
            System.out.println("Enter command (LEFT/RIGHT/UP/DOWN/PICK/SHOW/SPAWN n/HELP/EXIT):");
            System.out.print("> ");

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String cmd = parts[0].toUpperCase();

            if ("EXIT".equals(cmd)) {
                System.out.println("Goodbye!");
                break;
            } else if ("HELP".equals(cmd)) {
                printHelp();
                continue;
            } else if ("SHOW".equals(cmd)) {
                System.out.printf("Gold: %.2f%n", player.gold.amount());
                continue;
            } else if ("SPAWN".equals(cmd)) {
                int n = 1;
                if (parts.length >= 2) {
                    try { n = Integer.parseInt(parts[1]); } catch (NumberFormatException ignored) {}
                }
                placeRandomGold(board, n);
                System.out.printf("Spawned %d gold tokens.%n", n);
                continue;
            } else if ("PICK".equals(cmd)) {
                Board.Coords pos = token.pos();
                Token t = safePeek(board, pos.col(), pos.row());
                if (t instanceof EmptyToken) {
                    System.out.println("Nothing to pick here.");
                } else {
                    player.interactWithToken(t);
                    board.placeToken(pos.col(), pos.row(), new EmptyToken());
                    System.out.println("Picked up: " + t.getClass().getSimpleName());
                }
                continue;
            }


            PlayerToken.Move move;
            try {
                move = PlayerToken.Move.valueOf(cmd);
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown command. Type HELP for commands.");
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
                default -> {}
            }

            try {

                Token t = safePeek(board, nextCol, nextRow);
                if (!(t instanceof EmptyToken)) {
                    player.interactWithToken(t);
                    board.placeToken(nextCol, nextRow, new EmptyToken());
                    System.out.println("Auto-picked: " + t.getClass().getSimpleName());
                }

                token.move(move);
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid move: " + ex.getMessage());
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid move: outside board");
            }
        }

        scanner.close();
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  LEFT / RIGHT / UP / DOWN  - move");
        System.out.println("  PICK                      - interact with token on current cell");
        System.out.println("  SPAWN <n>                 - spawn n gold tokens randomly");
        System.out.println("  SHOW                      - show board and gold amount");
        System.out.println("  HELP                      - this help");
        System.out.println("  EXIT                      - quit");
    }


    private static Token safePeek(Board board, int col, int row) {
        if (col < 0 || row < 0 || col >= board.size() || row >= board.size()) {
            return new EmptyToken();
        }
        return board.peekToken(col, row);
    }


    private static Board.Coords getRandomFreeCell(Board board) {
        List<Board.Coords> free = new ArrayList<>();
        for (int r = 0; r < board.size(); r++) {
            for (int c = 0; c < board.size(); c++) {
                if (board.peekToken(c, r) instanceof EmptyToken) {
                    free.add(new Board.Coords(c, r));
                }
            }
        }
        if (free.isEmpty()) throw new IllegalStateException("Board is full");
        return free.get(RNG.nextInt(free.size()));
    }

    private static void placeRandomGold(Board board, int n) {
        for (int i = 0; i < n; i++) {
            try {
                Board.Coords c = getRandomFreeCell(board);
                board.placeToken(c.col(), c.row(), new GoldToken(1.0));
            } catch (IllegalStateException e) {
                break;
            }
        }
    }

    private static void placeRandomTool(Board board, Token tool) {
        try {
            Board.Coords c = getRandomFreeCell(board);
            board.placeToken(c.col(), c.row(), tool);
        } catch (IllegalStateException ignored) {}
    }
}
