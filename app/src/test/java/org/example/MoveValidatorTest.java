package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class MoveValidatorTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testValidMove() {
        assertTrue(MoveValidator.isValidMove(board, 1));
        board.placeMark(1, Constants.PLAYER_X);
        assertFalse(MoveValidator.isValidMove(board, 1));
    }

    @Test
    void testInvalidMovePosition() {
        assertFalse(MoveValidator.isValidMove(board, 0));
        assertFalse(MoveValidator.isValidMove(board, 10));
        assertFalse(MoveValidator.isValidMove(board, -1));
    }
}
