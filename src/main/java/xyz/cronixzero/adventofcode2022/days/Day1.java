package xyz.cronixzero.adventofcode2022.days;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import xyz.cronixzero.adventofcode2022.days.setup.InputParser;

public class Day1 {

  public static void main(String[] args) {
    String input = InputParser.parseInput(1);

    partOne(input);
    partTwo(input);
  }

  public static void partOne(String input) {
    int most = 0;
    int current = 0;

    for (String s : input.lines().toList()) {
      if (!s.isEmpty()) {
        current += Integer.parseInt(s);
        continue;
      }

      if (current > most) {
        most = current;
      }

      current = 0;
    }

    System.out.println(most);
  }

  public static void partTwo(String input) {
    List<Integer> computed = new ArrayList<>();

    int current = 0;

    for (String s : input.lines().toList()) {
      if (s.isEmpty()) {
        computed.add(current);
        current = 0;
        continue;
      }

      current += Integer.parseInt(s);
    }

    computed.sort(Comparator.naturalOrder());

    ListIterator<Integer> iterator = computed.listIterator(computed.size());
    int finalCalories = 0;

    for (int i = 0; i < 3; i++) {
      int calories = iterator.previous();
      finalCalories += calories;
      System.out.printf("Calories %d: %d%n", finalCalories, calories);
    }

    System.out.println(finalCalories);
  }
}
