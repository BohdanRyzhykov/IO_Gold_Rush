package edu.io;

public interface PlacementStrategy {
    Board.Coords findAvailableSquare(Board board);
}
