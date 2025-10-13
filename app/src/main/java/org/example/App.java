package org.example;

public class App {
    public static void main(String[] args) {
        InputHandler input = new InputHandler();
        Game game = new Game(input);
        game.start();
    }
}
