package xyz.cronixzero.adventofcode;

import java.util.regex.Matcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.cronixzero.adventofcode.days.Day3;

class Day3Test {

  Day3 day3 = new Day3();

  @Test
  void testPartOne() {
    String result = day3.partOne();
    Assertions.assertEquals("161", result);
  }

  @Test
  void testPartTwo() {
    String result = day3.partTwo();
    Assertions.assertEquals("7707", result);
  }

  @Test
  void testMultiplicationMatcherCorrectlyStatesTheLocation() {
    String input = "mul(2,4)";

    Matcher matcher = day3.generateMatcher(input);

    Assertions.assertTrue(matcher.find());
    Assertions.assertEquals(0, matcher.start());
    Assertions.assertEquals(8, matcher.end());
  }

  @Test
  void testDoMatcherIsCorrect() {
    String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";

    Matcher matcher = day3.generateDoMatcher(input);

    Assertions.assertEquals(1, matcher.results().count());
  }

  @Test
  void testDontMatcherIsCorrect() {
    String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";

    Matcher matcher = day3.generateDontMatcher(input);

    Assertions.assertEquals(1, matcher.results().count());
  }

  @Test
  void testMatcherIsCorrect() {
    String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";

    Matcher matcher = day3.generateMatcher(input);

    Assertions.assertEquals(4, matcher.results().count());
  }

  @Test
  void testMatcherGroupSizeCorrect() {
    String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";

    Matcher matcher = day3.generateMatcher(input);

    Assertions.assertEquals(2, matcher.groupCount());
  }

  @Test
  void testMatcherGroupValuesCorrect() {
    String input = "mul(2,4)";

    Matcher matcher = day3.generateMatcher(input);
    boolean found = matcher.find();

    Assertions.assertTrue(found);
    Assertions.assertEquals("2", matcher.group(1));
    Assertions.assertEquals("4", matcher.group(2));
  }
}
