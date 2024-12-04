package xyz.cronixzero.adventofcode;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.cronixzero.adventofcode.days.Day4;

@Log4j2
class Day4Test {

  private final Day4 day4 = new Day4();

  @Test
  void testPartOne() {
    String result = day4.partOne();

    Assertions.assertEquals("18", result);
  }

  @Test
  void testPartTwo() {
    String result = day4.partTwo();

    Assertions.assertEquals("9", result);
  }

  @Test
  void testMatchHorizontalForwardsSuccessful() {
    String[] input = {"X", "M", "A", "S"};

    Assertions.assertTrue(day4.isMatchHorizontalForwards(input, 0));
  }

  @Test
  void testMatchHorizontalBackwardsSuccessful() {
    String[] input = {"S", "A", "M", "X"};

    Assertions.assertTrue(day4.isMatchHorizontalBackwards(input, 3));
  }

  @Test
  void testMatchVerticalForwardsSuccessful() {
    String[][] inputs = {
        {"X", "M", "A", "X"},
        {"M", "X", "A", "S"},
        {"A", "M", "X", "S"},
        {"S", "M", "A", "X"}
    };

    Assertions.assertTrue(day4.isMatchVerticalForwards(inputs, 0, 0));
  }

  @Test
  void testMatchVerticalBackwardsSuccessful() {
    String[][] inputs = {
        {"S", "M", "A", "X"},
        {"A", "X", "A", "S"},
        {"M", "M", "X", "S"},
        {"X", "M", "A", "X"}
    };

    Assertions.assertTrue(day4.isMatchVerticalBackwards(inputs, 3, 0));
  }

  // Multiple Tests for all Diagonal possibilities
  @Test
  void testMatchDiagonalDownLeftSuccessful() {
    String[][] inputs = {
        {"S", "A", "M", "X"},
        {"A", "M", "M", "S"},
        {"M", "A", "A", "S"},
        {"S", "M", "A", "S"}
    };

    Assertions.assertTrue(day4.isMatchDiagonalDownLeft(inputs, 0, 3));
  }

  @Test
  void testMatchDiagonalDownRightSuccessful() {
    String[][] inputs = {
        {"X", "M", "A", "S"},
        {"M", "M", "A", "S"},
        {"A", "M", "A", "S"},
        {"S", "M", "A", "S"}
    };

    Assertions.assertTrue(day4.isMatchDiagonalDownRight(inputs, 0, 0));
  }

  @Test
  void testMatchDiagonalUpLeftSuccessful() {
    String[][] inputs = {
        {"S", "M", "A", "S"},
        {"M", "A", "A", "S"},
        {"A", "M", "M", "S"},
        {"S", "M", "A", "X"}
    };

    Assertions.assertTrue(day4.isMatchDiagonalUpLeft(inputs, 3, 3));
  }

  @Test
  void testMatchDiagonalUpRightSuccessful() {
    String[][] inputs = {
        {"S", "M", "A", "S"},
        {"A", "M", "A", "S"},
        {"M", "M", "A", "S"},
        {"X", "M", "A", "S"}
    };

    Assertions.assertTrue(day4.isMatchDiagonalUpRight(inputs, 3, 0));
  }
}
