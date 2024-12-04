package xyz.cronixzero.adventofcode.days;

import lombok.extern.log4j.Log4j2;
import xyz.cronixzero.adventofcode.common.Day;

@Log4j2
public class Day4 extends Day {

  public Day4() {
    super(4);
  }

  @Override
  public String partOne() {
    String input = getInput(1);

    int matches = 0;
    int matchBefore = 0;
    String[][] chars = generateInputMatrix(input);

    for (int line = 0; line < chars.length; line++) {
      for (int character = 0; character < chars[line].length; character++) {
        String current = chars[line][character];

        if (!current.equals("X")) {
          continue;
        }

        if (isMatchHorizontalForwards(chars[line], character)) {
          matches++;
        }
        if (isMatchHorizontalBackwards(chars[line], character)) {
          matches++;
        }
        if (isMatchVerticalForwards(chars, line, character)) {
          matches++;
        }
        if (isMatchVerticalBackwards(chars, line, character)) {
          matches++;
        }
        if (isMatchDiagonalUpRight(chars, line, character)) {
          matches++;
        }
        if (isMatchDiagonalUpLeft(chars, line, character)) {
          matches++;
        }
        if (isMatchDiagonalDownRight(chars, line, character)) {
          matches++;
        }
        if (isMatchDiagonalDownLeft(chars, line, character)) {
          matches++;
        }

        if (matches > matchBefore) {
          log.info("{} Match(es) found at line: {} and character: {}", matches - matchBefore,
              line + 1, character + 1);
          matchBefore = matches;
        }
      }
    }

    return String.valueOf(matches);
  }

  public String[][] generateInputMatrix(String input) {
    return input.lines().map(line -> line.split("")).toArray(String[][]::new);
  }

  @Override
  public String partTwo() {
    String input = getInput(2);

    int matches = 0;
    String[][] chars = generateInputMatrix(input);

    for (int line = 0; line < chars.length; line++) {
      for (int character = 0; character < chars[line].length; character++) {
        String current = chars[line][character];

        if (!current.equals("A") || line < 1 || character < 1 || line + 1 >= chars.length || character + 1 >= chars[line + 1].length) {
          continue;
        }

        if (chars[line - 1][character - 1].equals("M")
            && chars[line - 1][character + 1].equals("S")
            && chars[line + 1][character - 1].equals("M")
            && chars[line + 1][character + 1].equals("S")) {
          matches++;
        }

        if (chars[line - 1][character - 1].equals("M")
            && chars[line - 1][character + 1].equals("M")
            && chars[line + 1][character - 1].equals("S")
            && chars[line + 1][character + 1].equals("S")) {
          matches++;
        }

        if (chars[line - 1][character - 1].equals("S")
            && chars[line - 1][character + 1].equals("M")
            && chars[line + 1][character - 1].equals("S")
            && chars[line + 1][character + 1].equals("M")) {
          matches++;
        }

        if (chars[line - 1][character - 1].equals("S")
            && chars[line - 1][character + 1].equals("S")
            && chars[line + 1][character - 1].equals("M")
            && chars[line + 1][character + 1].equals("M")) {
          matches++;
        }
      }
    }

    return String.valueOf(matches);
  }

  public boolean isMatchHorizontalForwards(String[] chars, int spot) {

    return chars.length > spot + 3
        && chars[spot].equals("X")
        && chars[spot + 1].equals("M")
        && chars[spot + 2].equals("A")
        && chars[spot + 3].equals("S");
  }

  public boolean isMatchHorizontalBackwards(String[] chars, int spot) {

    return chars.length > spot
        && spot >= 3
        && chars[spot].equals("X")
        && chars[spot - 1].equals("M")
        && chars[spot - 2].equals("A")
        && chars[spot - 3].equals("S");
  }

  public boolean isMatchVerticalForwards(String[][] inputs, int line, int spot) {

    return inputs.length > line + 3
        && inputs[line].length > spot
        && inputs[line][spot].equals("X")
        && inputs[line + 1][spot].equals("M")
        && inputs[line + 2][spot].equals("A")
        && inputs[line + 3][spot].equals("S");
  }

  public boolean isMatchVerticalBackwards(String[][] inputs, int line, int spot) {

    return inputs.length > line
        && line >= 3
        && inputs[line].length > spot
        && inputs[line][spot].equals("X")
        && inputs[line - 1][spot].equals("M")
        && inputs[line - 2][spot].equals("A")
        && inputs[line - 3][spot].equals("S");
  }

  public boolean isMatchDiagonalDownLeft(String[][] inputs, int line, int spot) {

    return inputs.length > line + 3
        && inputs[line].length > spot
        && spot >= 3
        && inputs[line][spot].equals("X")
        && inputs[line + 1][spot - 1].equals("M")
        && inputs[line + 2][spot - 2].equals("A")
        && inputs[line + 3][spot - 3].equals("S");
  }

  public boolean isMatchDiagonalDownRight(String[][] inputs, int line, int spot) {

    return inputs.length > line + 3
        && inputs[line].length > spot + 3
        && inputs[line][spot].equals("X")
        && inputs[line + 1][spot + 1].equals("M")
        && inputs[line + 2][spot + 2].equals("A")
        && inputs[line + 3][spot + 3].equals("S");
  }

  public boolean isMatchDiagonalUpLeft(String[][] inputs, int line, int spot) {

    return inputs.length > line
        && inputs[line].length > spot - 3
        && line >= 3
        && spot >= 3
        && inputs[line][spot].equals("X")
        && inputs[line - 1][spot - 1].equals("M")
        && inputs[line - 2][spot - 2].equals("A")
        && inputs[line - 3][spot - 3].equals("S");
  }

  public boolean isMatchDiagonalUpRight(String[][] inputs, int line, int spot) {

    return inputs.length > line
        && inputs[line].length > spot + 3
        && line >= 3
        && inputs[line][spot].equals("X")
        && inputs[line - 1][spot + 1].equals("M")
        && inputs[line - 2][spot + 2].equals("A")
        && inputs[line - 3][spot + 3].equals("S");
  }
}
