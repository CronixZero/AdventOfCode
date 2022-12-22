package xyz.cronixzero.adventofcode2022.days;

import xyz.cronixzero.adventofcode2022.days.setup.InputParser;

public class Day2 {

  public static void main(String[] args) {
    String input = InputParser.parseInput(2);

    partOne(input);
    partTwo(input);
  }

  public static void partOne(String input) {
    int total = 0;

    for (String s : input.lines().toList()) {
      String[] split = s.split(" ");

      RPS choose = RPS.getRPS(split[1]);

      total += choose.getPoints();
      total += choose.beats(RPS.getRPS(split[0])).getPoints();
    }

    System.out.printf("Part one: %d%n", total);
  }

  public static void partTwo(String input) {
    int total = 0;

    for (String s : input.lines().toList()) {
      String[] split = s.split(" ");

      RPS opponent = RPS.getRPS(split[0]);
      RPS needed = RPS.getNeededOutcome(split[1]);
      RPS choose = RPS.getNeededHand(opponent, needed);

      total += choose.getPoints();
      total += choose.beats(RPS.getRPS(split[0])).getPoints();
    }

    System.out.printf("Part two: %d%n", total);
  }

  public enum RPS {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3),

    LOSS(null, null, 0),
    DRAW(null, null, 3),
    WIN(null, null, 6);

    private final String attack;
    private final String response;
    private final int points;

    RPS(String attack, String response, int points) {
      this.attack = attack;
      this.response = response;
      this.points = points;
    }

    public static RPS getNeededHand(RPS opponent, RPS needed) {
      return switch (needed) {
        case LOSS -> switch (opponent) {
          case ROCK -> SCISSORS;
          case PAPER -> ROCK;
          case SCISSORS -> PAPER;
          default -> throw new IllegalStateException("Unexpected value: " + opponent);
        };
        case DRAW -> opponent;
        case WIN -> switch (opponent) {
          case ROCK -> PAPER;
          case PAPER -> SCISSORS;
          case SCISSORS -> ROCK;
          default -> throw new IllegalStateException("Unexpected value: " + opponent);
        };
        default -> throw new IllegalStateException("Unexpected value: " + needed);
      };
    }

    public RPS beats(RPS other) {
      return switch (this) {
        case ROCK -> other == SCISSORS ? WIN : other == PAPER ? LOSS : DRAW;
        case PAPER -> other == ROCK ? WIN : other == SCISSORS ? LOSS : DRAW;
        case SCISSORS -> other == PAPER ? WIN : other == ROCK ? LOSS : DRAW;
        case WIN, DRAW, LOSS -> other;
      };
    }

    public static RPS getRPS(String s) {
      return switch (s) {
        case "A", "X" -> ROCK;
        case "B", "Y" -> PAPER;
        case "C", "Z" -> SCISSORS;
        default -> null;
      };
    }

    public static RPS getNeededOutcome(String s) {
      return switch (s) {
        case "Z" -> WIN;
        case "Y" -> DRAW;
        case "X" -> LOSS;
        default -> null;
      };
    }

    public String getAttack() {
      return attack;
    }

    public String getResponse() {
      return response;
    }

    public int getPoints() {
      return points;
    }
  }
}
