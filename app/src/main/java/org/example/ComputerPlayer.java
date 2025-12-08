package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
    private final Random random = new Random();

    public ComputerPlayer(String name, char mark) {
        super(name, mark);
    }

    public int chooseMove(Board board, char opponentMark) {
        if (board.countMarks() == 0) {
            int[] corners = {
                1, 3, 7, 9
            };
            return corners[random.nextInt(corners.length)];
        }

        if (board.countMarks() == 1 && board.getCell(5) == Constants.EMPTY) {
            return 5;
        }

        // Check for winning move
        for (int i = 1; i <= 9; i++) {
            if (board.getCell(i) == Constants.EMPTY) {
                board.placeTempMark(i, getMark());
                boolean win = board.hasWon(getMark());
                board.undoTempMark(i);
                if (win) {
                    return i;
                }
            }
        }

        // Check for blocking move
        for (int j = 1; j <= 9; j++) {
            if (board.getCell(j) == Constants.EMPTY) {
                board.placeTempMark(j, opponentMark);
                boolean win = board.hasWon(opponentMark);
                board.undoTempMark(j);
                if (win) {
                    return j;
                }
            }
        }

        // Choose random open cell
        List<Integer> open = new ArrayList<>();
        for (int k = 1; k <= 9; k++) {
            if (board.getCell(k) == Constants.EMPTY) {
                open.add(k);
            }
        }
        return open.get(random.nextInt(open.size()));
    }
    
}
