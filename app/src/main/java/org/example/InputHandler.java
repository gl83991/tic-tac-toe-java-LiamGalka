package org.example;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int readCellPosition(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine();
        if (line == null) return -1;
        line = line.trim();
        if (line.isEmpty()) return -1;

        
        for (char c : line.toCharArray()) {
            if (!Character.isDigit(c)) {
                return -1;
            } 
        }
        try {
            int pos = Integer.parseInt(line);
            if (pos < 1 || pos > 9) return -1;
            return pos;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public Boolean readYesNo(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine();
        if (line == null) return null;
        line = line.trim().toLowerCase();
        if (line.equals("y") || line.equals("yes")) return true;
        if (line.equals("n") || line.equals("no")) return false;
        return null;
    }
}
