package xyz.cronixzero.adventofcode2022.days;

import java.util.Arrays;

import xyz.cronixzero.adventofcode2022.days.setup.InputParser;

public class Day4 {

  public static void main(String[] args) {
String input = InputParser.parseInput(4);

    partOne(input);
    partTwo(input);
  }

  public static void partOne(String input) {
    int containments = 0;

    for (String s : input.lines().toList()) {
      // 11(sA0)-20(sA1),12(sB0)-17(sB1)
      String[] ranges = s.split(",");
      int[] splitA = Arrays.stream(ranges[0].split("-")).mapToInt(Integer::parseInt).toArray();
      int[] splitB = Arrays.stream(ranges[1].split("-")).mapToInt(Integer::parseInt).toArray();

      // 1-2,0-2
      if ((splitA[0] <= splitB[0] && splitA[1] >= splitB[1]) || (splitB[0] <= splitA[0] && splitB[1] >= splitA[1])) {
        System.out.printf("CONTAINMENT! (%d) %s,%s%n", ++containments, ranges[0], ranges[1]);
      }

      System.out.println(containments);
    }
  }

  public static void partTwo(String input) {
    int overlaps = 0;

    for (String s : input.lines().toList()) {
      String[] ranges = s.split(",");

      int[] splitA = rangeToArray(Arrays.stream(ranges[0].split("-")).mapToInt(Integer::parseInt).toArray());
      int[] splitB = rangeToArray(Arrays.stream(ranges[1].split("-")).mapToInt(Integer::parseInt).toArray());

      System.out.println(Arrays.toString(splitA));
      System.out.println(Arrays.toString(splitB));

      for (int curr : splitA) {
        if (Arrays.stream(splitB).anyMatch(i -> i == curr)) {
          overlaps++;
          break;
        }
      }
    }

    System.out.printf("Overlaps: %d%n", overlaps);
  }

  private static int[] rangeToArray(int[] range) {
    int size = range[0] - range[1];
    size = (size < 0 ? size * (-1) : size) + 1;

    int current = range[0];
    int[] array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = current;
      current++;
      System.out.printf("Current: %d (%d)%n", current, i);
    }

    return array;
  }
}
