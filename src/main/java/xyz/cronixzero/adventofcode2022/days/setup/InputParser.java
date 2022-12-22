package xyz.cronixzero.adventofcode2022.days.setup;

import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import xyz.cronixzero.adventofcode2022.days.Day5;

public class InputParser {

  public static String parseInput(int day) {
    return parseGeneralInput(day, null);
  }

  public static String parseInput(int day, String additional) {
    return parseGeneralInput(day, additional);
  }

  private static String parseGeneralInput(int day, String additional) {
    if (additional == null) {
      additional = "";
    }

    if (!additional.isEmpty()) {
      additional = "_" + additional;
    }

    try {
      return CharStreams.toString(new InputStreamReader(
          Objects.requireNonNull(Day5.class.getResourceAsStream("/day%d%s.txt".formatted(day, additional))),
          StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.printf("Error while parsing input for day %d%n", day);
    }

    return null;
  }
}
