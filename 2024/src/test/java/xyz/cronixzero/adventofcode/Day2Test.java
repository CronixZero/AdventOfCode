package xyz.cronixzero.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.cronixzero.adventofcode.days.Day2;
import xyz.cronixzero.adventofcode.days.Day2.Policy;
import xyz.cronixzero.adventofcode.days.Day2.Report;

class Day2Test {

  private final Day2 day2 = new Day2();

  @Test
  void testPartOne() {
    String result = day2.partOne();
    Assertions.assertEquals("2", result);
  }

  @Test
  void testDeterminePolicyIncrease() {
    Policy policy = day2.determinePolicy(new Integer[] { 1, 3, 5 });
    Assertions.assertEquals(Policy.INCREASE, policy);
  }

  @Test
  void testDeterminePolicyDecrease() {
    Policy policy = day2.determinePolicy(new Integer[] { 5, 3, 1 });
    Assertions.assertEquals(Policy.DECREASE, policy);
  }

  @Test
  void testSuccessIfNoViolation() {
    Integer[] testData = new Integer[] { 1, 3, 5 };
    Report report = new Report(3, testData, 0, testData);

    Policy policy = day2.determinePolicy(testData);
    boolean result = day2.mismatches(report, policy);

    Assertions.assertFalse(result);
  }

  @Test
  void testMismatchIfPolicyViolation() {
    Integer[] testData = new Integer[] { 5, 3, 5, 1 };
    Report report = new Report(3, testData, 0, testData);

    Policy policy = day2.determinePolicy(testData);
    boolean result = day2.mismatches(report, policy);

    Assertions.assertTrue(result);
  }

  @Test
  void testMismatchIfDifferenceViolation() {
    Integer[] testData = new Integer[] { 10, 3, 1 };
    Report report = new Report(3, testData, 0, testData);

    Policy policy = day2.determinePolicy(testData);
    boolean result = day2.mismatches(report, policy);

    Assertions.assertTrue(result);
  }

  @Test
  void testPartTwo() {
    String result = day2.partTwo();
    Assertions.assertEquals("4", result);
  }

}
