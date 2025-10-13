package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;

public class InputHandlerTest {

    @Test
    void testReadCellPositionValid() {
        String input = "5\n";
        InputHandler handler = new InputHandler();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int pos = handler.readCellPosition("Enter move: ");
        assertEquals(5, pos);
    }

    @Test
    void testReadYesNoValidYes() {
        String input = "yes\n";
        InputHandler handler = new InputHandler();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Boolean response = handler.readYesNo("Play again? (y/n): ");
        assertTrue(response);
    }

    @Test
    void testReadYesNoValidNo() {
        String input = "n\n";
        InputHandler handler = new InputHandler();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Boolean response = handler.readYesNo("Play again? (y/n): ");
        assertFalse(response);
    }
}
