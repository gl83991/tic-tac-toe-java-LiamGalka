package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class BoardTest {
  private Board board;

  @BeforeEach
  void setUp() {
    board = new Board();
  }

    @Test
    void testClear() {
      board.placeMark(1, Constants.PLAYER_X);
      board.clear();
      for (int i = 1; i <= 9; i++) {
        assertEquals(Constants.EMPTY, board.getCell(i));
      }
    }
    
    @Test
    void testPlaceMarkValid() {
      boolean success = board.placeMark(1, Constants.PLAYER_O);
      assertTrue(success);
      assertEquals(Constants.PLAYER_O, board.getCell(1));
    }

    @Test
    void testPlaceMarkInvalid() {
      board.placeMark(1, Constants.PLAYER_X);
      boolean success = board.placeMark(1, Constants.PLAYER_O);
      assertFalse(success);
      assertEquals(Constants.PLAYER_X, board.getCell(1));
    }

    @Test
    void testHasWonRows() {
      board.placeMark(1, Constants.PLAYER_X);
      board.placeMark(2, Constants.PLAYER_X);
      board.placeMark(3, Constants.PLAYER_X);
      assertTrue(board.hasWon(Constants.PLAYER_X));
    }

    @Test
    void testHasWonColumns() {
      board.placeMark(1, Constants.PLAYER_O);
      board.placeMark(4, Constants.PLAYER_O);
      board.placeMark(7, Constants.PLAYER_O);
      assertTrue(board.hasWon(Constants.PLAYER_O));
    }

    @Test
    void testHasWonDiagonals() {
      board.placeMark(1, Constants.PLAYER_X);
      board.placeMark(5, Constants.PLAYER_X);
      board.placeMark(9, Constants.PLAYER_X);
      assertTrue(board.hasWon(Constants.PLAYER_X));

      board.clear();

      board.placeMark(3, Constants.PLAYER_O);
      board.placeMark(5, Constants.PLAYER_O);
      board.placeMark(7, Constants.PLAYER_O);
      assertTrue(board.hasWon(Constants.PLAYER_O));
    }

    @Test
    void testIsFull() {
      for(int i = 1; i<= 9; i++) {
        board.placeMark(i, Constants.PLAYER_O);
      }
      assertTrue(board.isFull());
    }
  }
