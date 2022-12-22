package xyz.cronixzero.adventofcode2022.days;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import xyz.cronixzero.adventofcode2022.days.setup.InputParser;

public class Day3 {

  public static void main(String[] args) {
String input = InputParser.parseInput(3);

    partOne(input);
    partTwo(input);
  }

  public static void partOne(String input) {
    int total = 0;

    for (String s : input.lines().toList()) {
      String[] compartments = new String[]{
          s.substring(0, s.length() / 2),
          s.substring(s.length() / 2)
      };

      for (char c : compartments[0].toCharArray()) {
        if (!compartments[1].contains(String.valueOf(c))) {
          continue;
        }

        System.out.println(compartments[0] + " | " + compartments[1]);
        if (c > 90) { // Lowercase
          total += c - 96;
          System.out.printf("Found %s, total is now %d (+%d)%n", c, total, c - 96);
        } else { // Uppercase
          total += c - 38;
          System.out.printf("Found %s, total is now %d (+%d)%n", c, total, c - 38);
        }

        break;
      }
    }

    System.out.printf("Part one: %d%n", total);
  }

  public static void partTwo(String input) {
    int total = 0;
    ListIterator<String> iterator = input.lines().toList().listIterator();
    List<String[]> rucksacks = new ArrayList<>();

    /* RUCKSACK CONSTRUCTIONS */
    String[] current = new String[3];
    for (int i = 0; i < 3; i++) {
      if (!iterator.hasNext()) {
        break;
      }

      current[i] = iterator.next();

      if (i == 2) {
        rucksacks.add(current);
        current = new String[3];
        i = -1;
      }
    }

    /* RUCKSACK CHECKS*/
    for (String[] rucksack : rucksacks) {
      for (char c : rucksack[0].toCharArray()) {
        if (rucksack[1].contains(String.valueOf(c)) && rucksack[2].contains(String.valueOf(c))) {
          if (c > 90) { // Lowercase
            total += c - 96;
            System.out.printf("Found %s, total is now %d (+%d)%n", c, total, c - 96);
          } else { // Uppercase
            total += c - 38;
            System.out.printf("Found %s, total is now %d (+%d)%n", c, total, c - 38);
          }

          break;
        }
      }
    }

    System.out.printf("Part two: %d%n", total);
    "".split("", 2);
  }
}
