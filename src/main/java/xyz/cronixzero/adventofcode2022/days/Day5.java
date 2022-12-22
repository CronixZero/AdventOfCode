package xyz.cronixzero.adventofcode2022.days;

import xyz.cronixzero.adventofcode2022.days.setup.InputParser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Day5 {

  public static void main(String[] args) {
    String input = InputParser.parseInput(5, "max"); // Using the 6 MB Input

    LinkedList<Character>[] stacks = fillCrates(input);
    partOne(input, stacks);
    partTwo(input, stacks);
  }

  private static void partOne(String input, LinkedList<Character>[] stacksTrue) {
    boolean skip = true;
    LinkedList<Character>[] stacks = stacksTrue.clone();

    for (int i = 0; i < stacks.length; i++) {
      stacks[i] = new LinkedList<>(stacksTrue[i]);
    }

    for (String s : input.lines().toList()) {
      if (skip) {
        if (s.isEmpty()) {
          skip = false;
          System.out.printf("Skip off%n");
        }
        continue;
      }

      int[] changeData = getChangeData(s);

      int amount = changeData[0];
      LinkedList<Character> from = stacks[changeData[1] - 1];
      LinkedList<Character> to = stacks[changeData[2] - 1];

      for (int i = 0; i < amount; i++) {
        to.addFirst(from.removeFirst());
      }
    }

    StringBuilder result = new StringBuilder();
    for (LinkedList<Character> stack : stacks) {
      result.append(stack.getFirst());
    }

    System.out.printf("Part 1: %s%n", result);
  }

  private static void partTwo(String input, LinkedList<Character>[] stacksTrue) {
    boolean skip = true;
    LinkedList<Character>[] stacks = stacksTrue.clone();

    for (int i = 0; i < stacks.length; i++) {
      stacks[i] = new LinkedList<>(stacksTrue[i]);
    }

    for (String s : input.lines().toList()) {
      if (skip) {
        if (s.isEmpty()) {
          skip = false;
          System.out.printf("Skip off%n");
        }
        continue;
      }

      int[] changeData = getChangeData(s);
      int amount = changeData[0];
      LinkedList<Character> from = stacks[changeData[1] - 1];
      LinkedList<Character> to = stacks[changeData[2] - 1];

      LinkedList<Character> changes = new LinkedList<>();

      for (int i = 0; i < amount; i++) {
        changes.add(from.removeFirst());
      }

      Iterator<Character> it = changes.descendingIterator();
      while (it.hasNext()) {
        Character c = it.next();

        to.addFirst(c);
      }
    }

    StringBuilder result = new StringBuilder();
    for (LinkedList<Character> stack : stacks) {
      result.append(stack.getFirst());
    }

    System.out.printf("Part 2: %s%n", result);
  }

  private static LinkedList<Character>[] fillCrates(String input) {
    List<String> lines = input.lines().toList();
    int stacks = 0;

    for (int i = 0; i < lines.size(); i++) {
      if (!lines.get(i).isEmpty()) {
        continue;
      }

      String stacksString = lines.get(i - 1).trim();

      stacks = Integer.parseInt(stacksString.substring(stacksString.length() - 1));
      break;
    }

    LinkedList<Character>[] crates = new LinkedList[stacks];

    for (String s : lines) {
      if (s.isEmpty()) {
        break;
      }

      for (int i = 0; i < stacks; i++) {
        char sub = s.substring(i * 3 + i, i * 3 + 3 + i).replaceAll("[\\[\\]]", "").charAt(0);
        if (sub == ' ') {
          continue;
        }

        if (crates[i] == null) {
          crates[i] = new LinkedList<>();
        }
        crates[i].add(sub);
      }
    }

    return crates;
  }

  private static int[] getChangeData(String input) {
    // Create a Regex that matches a-b and A-B
    String trimmed = input.replaceAll("([a-zA-Z])", "").trim()
        .replaceAll(" {2}", " ");

    return Arrays.stream(trimmed.split(" ")).mapToInt(Integer::parseInt).toArray();
  }
}
