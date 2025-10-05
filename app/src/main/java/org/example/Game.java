package org.example;

public class Game {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private final InputHandler input;

    public Game(InputHandler input) {
        this.board = new Board();
        this.input = input;
        this.playerX = new Player("Player X", Constants.PLAYER_X);
        this.playerO = new Player("Player O", Constants.PLAYER_O);
    }
    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        boolean playAgain = true;
        while (playAgain) {
            board.clear();
            runSingleGame();
            Boolean yn = input.readYesNo("Play again? (y/n): ");
            if (yn == null) {
                System.out.println("Invalid input, try again.");
                yn = false; //set reprompter here
            }
            playAgain = yn;
        }
        System.out.println("Thanks for playing!");
    }
    private void runSingleGame() {
        Player current = playerX;
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
                return;
            }
            if (board.isFull()) {
                System.out.println();
                System.out.println(board.render());
                System.out.println();
                System.out.println("It's a draw!");
                return;
            }
            current = (current == playerX) ? playerO : playerX;
        }
    }
    // Additional helper methods can be added here
    // invalid move handling
    // reprompting for yes/no
}
