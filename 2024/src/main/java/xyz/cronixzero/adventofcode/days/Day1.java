package xyz.cronixzero.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import xyz.cronixzero.adventofcode.common.Day;

public class Day1 extends Day {

  public Day1() {
    super(1);
  }

  @Override
  public String partOne() {
    String input = getInput(1);

    List<Integer> listOne = new ArrayList<>();
    List<Integer> listTwo = new ArrayList<>();

    input.lines().forEach(line -> {
      String[] split = line.split(" ");
      int[] numbers = new int[2];
      int current = 0;
      for (String s : split) {
        if(s.isEmpty()) {
          continue;
        }

        numbers[current] = Integer.parseInt(s);
        current++;
      }

      listOne.add(numbers[0]);
      listTwo.add(numbers[1]);
    });

    listOne.sort(Integer::compareTo);
    listTwo.sort(Integer::compareTo);

    int[] differences = new int[listOne.size()];
    for (int i = 0; i < listOne.size(); i++) {
      int one = listOne.get(i);
      int two = listTwo.get(i);

      int difference = Math.abs(one - two);
      differences[i] = difference;
    }

    return String.valueOf(Arrays.stream(differences).sum());
  }

  @Override
  public String partTwo() {
    String input = getInput(1);

    List<Integer> listOne = new ArrayList<>();
    List<Integer> listTwo = new ArrayList<>();

    input.lines().forEach(line -> {
      String[] split = line.split(" ");
      int[] numbers = new int[2];
      int current = 0;
      for (String s : split) {
        if(s.isEmpty()) {
          continue;
        }

        numbers[current] = Integer.parseInt(s);
        current++;
      }

      listOne.add(numbers[0]);
      listTwo.add(numbers[1]);
    });

    int similarityScore = 0;
    for (int one : listOne) {
      int matches = 0;

      for (int two : listTwo) {
        if (one == two) {
          matches++;
        }
      }

      similarityScore += one*matches;
    }

    return String.valueOf(similarityScore);
  }
}
