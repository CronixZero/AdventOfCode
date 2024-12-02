package xyz.cronixzero.common;

public abstract class Day {

  private final int day;

  public Day(int day) {
    this.day = day;
  }

  public abstract String partOne();

  public abstract String partTwo();

  public String getInput(int part) {
    return InputParser.parseInput(day, part);
  }

}
