package org.example;

public class MoveValidator {
    public static boolean isValidMove(Board board, int position) {
        if (position < 1 || position > 9) return false;
        return board.getCell(position) == Constants.EMPTY;
    }
    // Additional validation methods can be added here
    //Adding more stuff here
}
