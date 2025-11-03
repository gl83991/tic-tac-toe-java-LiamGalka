package org.example;

import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private InputHandler createInputHandler (String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        return new InputHandler();
    }

    @Test
    void testWinCounters() {
        String inputs = "1\n2\n4\n5\n7\n"; // X wins
        InputHandler inputHandler = createInputHandler(inputs);
        Game game = new Game(inputHandler);
        Player loser = game.runSingleGame();
        assertEquals(1, game.xWins);
        assertEquals(0, game.oWins);
        assertEquals(0, game.ties);
    } //Still figuring this test out

    @Test
    void testTieCounter() {
        String inputs = "1\n2\n3\n5\n4\n6\n8\n7\n9\n"; // Tie
        InputHandler inputHandler = createInputHandler(inputs);
        Game game = new Game(inputHandler);
        Player loser = game.runSingleGame();
        assertEquals(0, game.xWins);
        assertEquals(0, game.oWins);
        assertEquals(1, game.ties);
    } //Still figuring this test out

    @Test
    void testLoserGoesFirstNextGame() {
        String inputs = "1\n2\n4\n5\n7\n"; // X wins
        InputHandler inputHandler = createInputHandler(inputs);
        Game game = new Game(inputHandler);
        Player loser = game.runSingleGame();
        game.firstPlayer = loser; // Simulate updating first player
        assertEquals("Player O", game.firstPlayer.getName());
    } //Still figuring this test out

    @Test
    void testLogFileCreation() throws IOException {
        String inputs = "1\n2\n4\n5\n7\nn\n"; // X wins, then no more games
        InputHandler inputHandler = createInputHandler(inputs);
        Game game = new Game(inputHandler);
        game.runSingleGame();
        game.saveLogToFile();
        File file = new File("game.txt");
        assertTrue(file.exists());
        // Clean up
    } //Adding to this test
}