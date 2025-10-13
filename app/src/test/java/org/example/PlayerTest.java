package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    void testGetNameAndMark() {
        Player player = new Player("Alice", Constants.PLAYER_X);
        assertEquals("Alice", player.getName());
        assertEquals(Constants.PLAYER_X, player.getMark());
    }

    @Test
    void testToString() {
        Player player = new Player("Bob", Constants.PLAYER_O);
        assertEquals("Bob (O)", player.toString());
    }
}
