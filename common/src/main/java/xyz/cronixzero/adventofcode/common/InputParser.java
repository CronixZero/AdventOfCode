package xyz.cronixzero.adventofcode.common;

import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class InputParser {

  public static String parseInput(int day) {
    return parseGeneralInput(day, 1);
  }

  public static String parseInput(int day, int part) {
    return parseGeneralInput(day, part);
  }

  private static String parseGeneralInput(int day, int part) {
    try (InputStream stream = ClassLoader.getSystemClassLoader()
        .getResourceAsStream("days/day%d_part%d.txt".formatted(day, part))) {

      if(stream == null) {
        System.out.printf("Error while parsing input for day %d%n", day);
        return null;
      }

      return CharStreams.toString(new InputStreamReader(stream, StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.printf("Error while parsing input for day %d%n", day);
    }

    return null;
  }
}
