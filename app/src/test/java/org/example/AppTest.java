package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
  @Test
  void appHasAGreeting() {
    Constants classUnderTest = new Constants();
    assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
  }
} // Make all testers for all the classes to check logic of the game
