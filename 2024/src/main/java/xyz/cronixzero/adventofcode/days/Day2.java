package xyz.cronixzero.adventofcode.days;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiPredicate;
import xyz.cronixzero.adventofcode.common.Day;

public class Day2 extends Day {

  public Day2() {
    super(2);
  }

  @Override
  public String partOne() {
    String input = getInput(1);

    Queue<Report> reports = generateReportsQueue(input);

    int safeReports = 0;
    for (Report report : reports) {
      Policy policy = determinePolicy(report.levels);

      if (mismatches(report, policy)) {
        continue;
      }

      safeReports++;
    }

    return String.valueOf(safeReports);
  }

  @Override
  public String partTwo() {
    String input = getInput(1);

    Queue<Report> reports = generateReportsQueue(input);
    int safeReports = 0;

    for (Report report : reports) {
      Policy policy = determinePolicy(report.levels);

      if (mismatches(report, policy)) {
        try {
          Integer[] newLevels = remove(report.initialLevels.clone(), report.iteration);
          Report clone = new Report(report.initialSize, report.initialLevels, report.iteration + 1,
              newLevels);
          reports.add(clone);
        } catch (IndexOutOfBoundsException e) {
          // Fall through
        }
        continue;
      }

      safeReports++;
    }

    return String.valueOf(safeReports);
  }

  public boolean mismatches(Report report, Policy policy) {
    if (report.levels.length <= 1) {
      return false;
    }

    int lastValue = report.levels[0];

    for (Integer value : Arrays.stream(report.levels).skip(1).toList()) {
      int difference = Math.abs(lastValue - value);

      if (!policy.test(lastValue, value) || difference > 3 || difference < 1) {
        return true;
      }

      lastValue = value;
    }

    return false;
  }

  public Queue<Report> generateReportsQueue(String input) {
    return new ConcurrentLinkedQueue<>(input.lines().map(report -> {
      Integer[] values = Arrays.stream(report.split(" ")).map(Integer::valueOf)
          .toArray(Integer[]::new);

      return new Report(values.length, values, 0, values);
    }).toList());
  }

  public Policy determinePolicy(Integer[] levels) {
    int decreases = 0;
    int increases = 0;
    Integer lastValue = null;

    for (Integer value : levels) {
      if (lastValue == null) {
        lastValue = value;
        continue;
      }

      if (Policy.DECREASE.test(lastValue, value)) {
        decreases++;
      } else {
        increases++;
      }
    }

    return decreases > increases ? Policy.DECREASE : Policy.INCREASE;
  }

  private Integer[] remove(Integer[] array, int index) {
    Integer[] newArray = new Integer[array.length - 1];
    for (int i = 0, j = 0; i < array.length; i++) {
      if (i == index) {
        continue;
      }

      newArray[j++] = array[i];
    }

    return newArray;
  }

  public enum Policy {
    INCREASE((a, b) -> a < b), DECREASE((a, b) -> a > b);

    private final BiPredicate<Integer, Integer> predicate;

    Policy(BiPredicate<Integer, Integer> predicate) {
      this.predicate = predicate;
    }

    public boolean test(int a, int b) {
      return predicate.test(a, b);
    }
  }

  public record Report(int initialSize, Integer[] initialLevels, int iteration, Integer[] levels) {

  }
}
