package org.example;
import java.util.Arrays;

public class Board {
    private final char[] cells;
    public Board() {
        this.cells = new char[9];
        clear();
    }
    public void clear() {
        Arrays.fill(cells, Constants.EMPTY);
    }
    public boolean placeMark(int position, char mark) {
        if (position < 1 || position > 9) return false;
        int idx = position - 1;
        if (cells[idx] != Constants.EMPTY) return false;
        cells[idx] = mark;
        return true;
    }
    public char getCell(int position) {
        if (position < 1 || position > 9)
        throw new IllegalArgumentException("Position must be between 1 and 9");
        return cells[position - 1];
    }
    public boolean isFull() {
        for (char c : cells) {
            if (c == Constants.EMPTY) 
            return false;
        }
        return true;
    }
    public boolean hasWon(char mark) {
        if (cells[0] == mark && cells[1] == mark && cells[2] == mark) return true;
        if (cells[3] == mark && cells[4] == mark && cells[5] == mark) return true;
        if (cells[6] == mark && cells[7] == mark && cells[8] == mark) return true;

        if (cells[0] == mark && cells[3] == mark && cells[6] == mark) return true;
        if (cells[1] == mark && cells[4] == mark && cells[7] == mark) return true;
        if (cells[2] == mark && cells[5] == mark && cells[8] == mark) return true;

        if (cells[0] == mark && cells[4] == mark && cells[8] == mark) return true;
        if (cells[2] == mark && cells[4] == mark && cells[6] == mark) return true;
        
        return false;
    }
    public String render() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 3; row++){
            int base = row * 3;
            for (int col = 0; col < 3; col++) {
                int pos = base + col;
                char c = cells[pos];
                if (c == Constants.EMPTY) 
                sb.append(String.format(" %d ", pos +1));
                else
                sb.append(String.format(" %c ", c));
                if (col < 2) sb.append("|");
            }
            sb.append(System.lineSeparator());
            if (row < 2)
            sb.append("-----+-----+-----").append(System.lineSeparator());
        }
        return sb.toString();
    }
}
