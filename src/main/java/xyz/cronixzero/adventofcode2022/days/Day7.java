package xyz.cronixzero.adventofcode2022.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import xyz.cronixzero.adventofcode2022.days.Day7.NonBinaryFileTree.Node;
import xyz.cronixzero.adventofcode2022.days.setup.InputParser;

public class Day7 {

  public static void main(String[] args) {
    String input = InputParser.parseInput(7);
    NonBinaryFileTree tree = parseInput(input);

    partOne(tree);
    partTwo(tree);
  }


  private static void partOne(NonBinaryFileTree tree) {
    List<Long> allNodes = new ArrayList<>();

    estimateSizeAndAdd(tree.getHead(), allNodes);

    long total = 0;
    for (long l : allNodes) {
      if (l <= 100_000) {
        total += l;
      }
    }

    System.out.printf("Part 1: %d%n", total);
  }

  private static void partTwo(NonBinaryFileTree tree) {
    List<Long> allNodes = new ArrayList<>();

    long maxSpace = 70_000_000;
    long neededSpace = 30_000_000;
    long currentUsed = estimateSizeAndAdd(tree.getHead(), allNodes);
    long freeSpace = maxSpace - currentUsed;
    long neededFreeSpace = neededSpace - freeSpace;

    allNodes.sort(Comparator.naturalOrder());

    for (long l : allNodes) {
      if (l >= neededFreeSpace) {
        System.out.printf("Part 2: %d%n", l);
        break;
      }
    }
  }

  private static long estimateSize(Node head) {
    if (head.children() == null) {
      return head.value().size();
    }

    long total = 0;
    for (Node n : head.children()) {
      if (n.children() == null) {
        total += n.value().size();
        continue;
      }

      long estimated = estimateSize(n);
      total += estimated;
    }

    return total;
  }

  private static long estimateSizeAndAdd(Node head, List<Long> set) {
    if (head.children() == null) {
      return head.value().size();
    }

    long total = 0;
    for (Node n : head.children()) {
      if (n.children() == null) {
        total += n.value().size();
        continue;
      }

      long estimated = estimateSizeAndAdd(n, set);
      total += estimated;
    }

    set.add(total);
    return total;
  }

  private static NonBinaryFileTree parseInput(String input) {
    NonBinaryFileTree files = new NonBinaryFileTree();
    files.add(new File("/", 0), true);
    boolean lineReading = false;

    for (String line : input.lines().toList()) {

      /* LINE READING */
      if (lineReading && line.startsWith("$")) {
        lineReading = false;
      }

      if (lineReading) {
        String[] data = line.split(" ");

        if (line.startsWith("dir")) {
          files.add(new File(data[1], 0), false);
          continue;
        }

        files.add(new File(data[1], Long.parseLong(data[0])), false);

        continue;
      }

      /* COMMANDS */
      if (line.startsWith("$ cd") && !line.endsWith("/")) {
        if (line.endsWith("..")) {
          files.goUp();
        } else {
          files.goDown(line.split(" ")[2]);
        }
      }

      if (line.startsWith("$ ls")) {
        lineReading = true;
      }
    }

    return files;
  }

  /*
   * STRUCTURE
   * */

  private record File(String name, long size) {

  }

  static class NonBinaryFileTree {

    private Node head;
    private Node current;

    public void add(File file, boolean newCurrent) {

      // Create Tree Head
      if (head == null) {
        head = new Node(file.name(), file, null, null);
        current = head;
        return;
      }

      // Add File to current Node
      if (current.children != null && Arrays.stream(current.children()).anyMatch(c -> c.name().equals(file.name()))) {
        return;
      }

      Node node = new Node(file.name(), file, current, null);
      current.addChildren(node);

      if (newCurrent) {
        current = node;
      }
    }

    /* NAVIGATION */

    public void goToHead() {
      current = head;
    }

    public void goUp() {
      current = current.parent();
    }

    public void goDown(String name) {
      Node newNode = Arrays.stream(current.children()).filter(f -> f.name().equals(name)).findFirst().orElse(null);

      if (newNode == null) {
        throw new IllegalArgumentException("No such Node " + name);
      }

      current = newNode;
    }

    public void setCurrent(Node node) {
      current = node;
    }

    /* GETTER */

    public Node getHead() {
      return head;
    }

    public Node getCurrent() {
      return current;
    }

    /* GENERAL METHODS */
    public String toString() {
      return head.toString();
    }

    static final class Node {

      private final String name;
      private final File value;
      private final Node parent;
      private Node[] children;

      private Node(String name, File value, Node parent, Node[] children) {
        this.name = name;
        this.value = value;
        this.parent = parent;
        this.children = children;
      }

      public String name() {
        return name;
      }

      public File value() {
        return value;
      }

      public Node parent() {
        return parent;
      }

      public Node[] children() {
        return children;
      }

      public void addChildren(Node... children) {
        if (this.children == null) {
          this.children = children;
          return;
        }

        this.children = Stream.concat(Stream.of(this.children), Stream.of(children)).toArray(Node[]::new);
      }

      @Override
      public String toString() {
        return "Node[" +
            "name=" + name + ", " +
            "value=" + value + ", " +
            "parent=" + (parent == null ? "none" : parent.name()) + ", " +
            "children=" + (children == null ? "" : Arrays.toString(children)) + ']';
      }
    }
  }
}
