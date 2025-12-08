package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class Game {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private final InputHandler input;
    // New counter and first player tracking
    public int xWins = 0;
    public int oWins = 0;
    public int ties = 0;
    public Player firstPlayer;

    private ComputerPlayer computerPlayer;
    private boolean vsComputer = false;
    private boolean computerFirst = false;

    public Game(InputHandler input) {
        this.board = new Board();
        this.input = input;
        this.playerX = new Player("Player X", Constants.PLAYER_X);
        this.playerO = new Player("Player O", Constants.PLAYER_O);
        this.firstPlayer = playerX; // X always starts first
    }
    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        Boolean playAgain = true;
        
        while (playAgain) {
        System.out.println("\nWhat Kind of game would you like to play?");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer");
        System.out.println("3. Computer vs Human");

        int selection = input.readMenuOption("What is your selection? ");

        if (selection == 1) {
            vsComputer = false;
            computerFirst = false;
            computerPlayer = null;
            firstPlayer = playerX;
        }
        else if (selection ==2) {
            vsComputer = true;
            computerFirst = false;
            computerPlayer = new ComputerPlayer("Computer", Constants.PLAYER_O);
            firstPlayer = playerX;
        }
        else if (selection == 3) {
            vsComputer = true;
            computerFirst = true;
            computerPlayer = new ComputerPlayer("Computer", Constants.PLAYER_X);
            firstPlayer = computerPlayer;
        }

        board.clear();

        Player loser = runSingleGame();

        System.out.println("\nCurrent game stats: ");
        System.out.println("Player X wins: " + xWins);
        System.out.println("Player O wins: " + oWins);
        System.out.println("Ties: " + ties);

        Boolean yn;
        do {
            yn = input.readYesNo("Would you like to play again? (y/n): ");
            if (yn == null) {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        } while (yn == null);

        playAgain = yn;

        if(!playAgain) {
            saveLogToFile();
        } else {

        if (loser != null) {
            firstPlayer = loser;
            System.out.println(firstPlayer.getName() + " will go first in the next game.");
                } 
            }
        }
    }

    // Method to save game log to a file
    public Player runSingleGame() {
        Player current = firstPlayer;
        while (true) {
            System.out.println();
            System.out.println(board.render());
            System.out.println();

            int pos;

            if (vsComputer && current instanceof ComputerPlayer) {
                pos = ((ComputerPlayer) current).chooseMove(board, (current.getMark() == Constants.PLAYER_X) ? Constants.PLAYER_O : Constants.PLAYER_X);
                System.out.println("Computer chooses: " + pos);
            } else {
                pos = input.readCellPosition("What is your move? ");
            }
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

            if (vsComputer && computerFirst) {
                if (current.getMark() == computerPlayer.getMark()) {
                }
            }
        }
    }

    // Method to save game log to a file
    public void saveLogToFile() {
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