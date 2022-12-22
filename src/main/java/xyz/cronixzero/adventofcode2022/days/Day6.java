package xyz.cronixzero.adventofcode2022.days;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PrimitiveIterator.OfInt;
import xyz.cronixzero.adventofcode2022.days.setup.InputParser;

public class Day6 {

  public static void main(String[] args) {
    String input = InputParser.parseInput(6);

    partOne(input);
    partTwo(input);
  }

  public static void partOne(String input) {
    OfInt chars = input.chars().iterator();
    System.out.println(extractTotalUntilFirstSign(4, chars));
  }

  public static void partTwo(String input) {
    OfInt chars = input.chars().iterator();
    System.out.println(extractTotalUntilFirstSign(14, chars));
  }

  private static int extractTotalUntilFirstSign(int signMin, OfInt input) {
    LinkedList<Integer> current = new LinkedList<>();
    int total = 0;

    while (input.hasNext()) {
      total++;
      int next = input.nextInt();

      if (total < signMin) {
        current.add(next);
        continue;
      }

      current.add(next);
      if (total > signMin) {
        current.removeFirst();
      }

      if (!hasDuplicate(current.toArray(Integer[]::new))) {
        return total;
      }
    }

    return -1;
  }

  private static boolean hasDuplicate(Integer[] arr) {
    for (int i : arr) {
      if (Arrays.stream(arr).filter(f -> f.equals(i)).count() > 1) {
        return true;
      }
    }

    return false;
  }
}
