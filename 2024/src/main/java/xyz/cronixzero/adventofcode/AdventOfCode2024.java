
package xyz.cronixzero.adventofcode;

import lombok.extern.log4j.Log4j2;
import xyz.cronixzero.adventofcode.common.Day;
import xyz.cronixzero.adventofcode.days.Day4;

@Log4j2
public class AdventOfCode2024 {

  public static void main(String[] args) {
    Day day = new Day4();

    log.info("Part 1: {}", day.partOne());
    log.info("Part 2: {}", day.partTwo());
  }

}
