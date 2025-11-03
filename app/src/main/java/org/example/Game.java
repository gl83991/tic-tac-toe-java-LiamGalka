package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class Game {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private final InputHandler input;
    // New counter and first player tracking
    private int xWins = 0;
    private int oWins = 0;
    private int ties = 0;
    private Player firstPlayer;

    public Game(InputHandler input) {
        this.board = new Board();
        this.input = input;
        this.playerX = new Player("Player X", Constants.PLAYER_X);
        this.playerO = new Player("Player O", Constants.PLAYER_O);
        this.firstPlayer = playerX; // X always starts first
    }
    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        boolean playAgain = true;
        while (playAgain) {
            board.clear();
            Player loser = runSingleGame();
            // Determine winner and update stats
            System.out.println("\nCurrent game stats: ");
            System.out.println("Player X wins: " + xWins);
            System.out.println("Player O wins: " + oWins);
            System.out.println("Ties: " + ties);
            
            Boolean yn;
            do { 
            yn = input.readYesNo("Play again? (y/n): ");
            if (yn == null) {
                System.out.println("Invalid input, try again.");
            }
        } while (yn == null);
        playAgain = yn;

        // Update first player for next game
        if (loser != null) {
            firstPlayer = loser;
            System.out.println(firstPlayer.getName() + " will go first next game.");
        }
    }
        // After exiting the loop, save the log
        saveLogToFile();

        System.out.println("Thanks for playing!");
    }

    // Method to save game log to a file
    private Player runSingleGame() {
        Player current = firstPlayer;
        while (true) {
            System.out.println();
            System.out.println(board.render());
            System.out.println();

            int pos = input.readCellPosition("What is your move? ");
            if (!MoveValidator.isValidMove(board, pos)) {
                System.out.println();
                System.out.println("Invalid move, try again.");
                continue;
            }
            board.placeMark(pos, current.getMark());
            if (board.hasWon(current.getMark())) {
                System.out.println();
                System.out.println(board.render());
                System.out.println();
                System.out.println("Player " + current.getName() + " wins!");

                // Update win counters and loser tracking
                if (current == playerX) xWins++;
                else oWins++;
                return (current == playerX) ? playerO : playerX;
            }
            if (board.isFull()) {
                System.out.println();
                System.out.println(board.render());
                System.out.println();
                System.out.println("It's a draw!");
                ties++;
                return null;
            }
            current = (current == playerX) ? playerO : playerX;
        }
    }

    // Method to save game log to a file
    private void saveLogToFile() {
        try (FileWriter writer = new FileWriter("game.txt")){
            writer.write("Final Game State:\n");
            writer.write("Player X wins: " + xWins + "\n");
            writer.write("Player O wins: " + oWins + "\n");
            writer.write("Ties: " + ties + "\n");
            System.out.println("Game log saved to game.txt");
        } catch (IOException e) {
            System.out.println("Error saving game log: " + e.getMessage());
        }
    }
}