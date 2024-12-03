package xyz.cronixzero.adventofcode.days;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import xyz.cronixzero.adventofcode.common.Day;

public class Day3 extends Day {

  // Regex that matches mul(<number with 1-3 places>,<number with 1-3 places>)
  private static final Pattern MULTIPLY_PATTERN = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
  private static final Pattern DO_PATTERN = Pattern.compile("do\\(\\)");
  private static final Pattern DONT_PATTERN = Pattern.compile("don't\\(\\)");

  public Day3() {
    super(3);
  }

  @Override
  public String partOne() {
    String input = getInput(1);
    int result = 0;

    for (String line : input.lines().toList()) {
      Matcher matcher = generateMatcher(line);

      while (matcher.find()) {
        int first = Integer.parseInt(matcher.group(1));
        int second = Integer.parseInt(matcher.group(2));

        result += first * second;
      }
    }

    return String.valueOf(result);
  }

  @Override
  public String partTwo() {
    String input = getInput(2);

    Map<Integer, String> instructions = new TreeMap<>();

    String flattened = input.lines().collect(Collectors.joining());

    Matcher doMatcher = generateDoMatcher(flattened);
    Matcher dontMatcher = generateDontMatcher(flattened);
    Matcher matcher = generateMatcher(flattened);

    while (doMatcher.find()) {
      instructions.put(doMatcher.start(), doMatcher.group());
    }
    while (dontMatcher.find()) {
      instructions.put(dontMatcher.start(), dontMatcher.group());
    }
    while (matcher.find()) {
      instructions.put(matcher.start(), matcher.group());
    }

    int result = 0;
    boolean allow = true;

    for (String instruction : instructions.values()) {
      switch (instruction) {
        case "do()":
          allow = true;
          break;

        case "don't()":
          allow = false;
          break;

        default:
          break;
      }

      if (!allow) {
        continue;
      }

      Matcher instructionMatcher = generateMatcher(instruction);
      if (instructionMatcher.find()) {
        int first = Integer.parseInt(instructionMatcher.group(1));
        int second = Integer.parseInt(instructionMatcher.group(2));

        result += first * second;
      }
    }

    return String.valueOf(result);
  }

  public Matcher generateMatcher(String input) {
    return MULTIPLY_PATTERN.matcher(input);
  }

  public Matcher generateDoMatcher(String input) {
    return DO_PATTERN.matcher(input);
  }

  public Matcher generateDontMatcher(String input) {
    return DONT_PATTERN.matcher(input);
  }
}
