import java.util.Arrays;
import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.impl.RelationBuilderImplementation;

/**
 * File to test the method of the implementations.
 */
public class Tester {
  /**
   * Main entry point of the file.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    testOne();
    testTwo();
    testThree();
    testFour();
    testFive();
    testSix();
    testSeven();
    testEight();
    testNine();
    testTen();
    testEleven();
    testTwelve();
    testThirteen();
    testFourteenth();
    testFifteen();
    testSixteen();
    testSeventeen();
  }

  /**
   * Checks if nameis not an empty string.
   */
  private static void testOne() {
    System.out.println("Test 1: Checks if name is not an empty string.");
    try {
      new RelationBuilderImplementation().newRelation("", Arrays.asList("ID"),
          Arrays.asList(Type.INTEGER));
      System.err.println("Test 1: FAILED");
      assert false : "name is an empty string.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 1: PASS");
      System.out.println();
    }
  }

  /**
   * Checks if name is non-alphanumeric.
   */
  private static void testTwo() {
    System.out.println("Test 2: Checks if name is non-alphanumeric.");
    try {
      new RelationBuilderImplementation().newRelation("Test!", Arrays.asList("ID"),
          Arrays.asList(Type.INTEGER));
      System.err.println("Test 2: FAILED");
      assert false : "name is non-alphanumeric.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 2: PASS");
      System.out.println();
    }
  }

  /**
   * Checks if attrs is not empty.
   */
  private static void testThree() {
    System.out.println("Test 3: Checks if attrs is not empty.");
    try {
      new RelationBuilderImplementation().newRelation("Test", Arrays.asList(),
          Arrays.asList(Type.INTEGER));
      System.err.println("Test 3: FAILED");
      assert false : "attrs is empty.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 3: PASS");
      System.out.println();
    }
  }

  /**
   * Checks if types is not empty.
   */
  private static void testFour() {
    System.out.println("Test 4: Checks if types is not empty.");
    try {
      new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID"),
          Arrays.asList());
      System.err.println("Test 4: FAILED");
      assert false : "types is empty.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 4: PASS");
      System.out.println();
    }
  }

  /**
   * Checks if types and attrs have the same size.
   */
  private static void testFive() {
    System.out.println("Test 5: Checks if types and attrs have the same size.");
    try {
      new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
          Arrays.asList(Type.INTEGER));
      System.err.println("Test 5: FAILED");
      assert false : "types and attrs does not have the same size.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 5: PASS");
      System.out.println();
    }
  }

  /**
   * Checks if attrs has an empty string.
   */
  private static void testSix() {
    System.out.println("Test 6: Checks if attrs has an empty string.");
    try {
      new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", ""),
          Arrays.asList(Type.INTEGER, Type.STRING));
      System.err.println("Test 6: FAILED");
      assert false : "attrs has an empty string.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 6: PASS");
      System.out.println();
    }
  }

  /**
   * Checks if attrs has a non-alphanumeric string.
   */
  private static void testSeven() {
    System.out.println("Test 7: Checks if attrs has a non-alphanumeric string.");
    try {
      new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name*"),
          Arrays.asList(Type.INTEGER, Type.STRING));
      System.err.println("Test 7: FAILED");
      assert false : "attrs has a non-alphanumeric string.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 7: PASS");
      System.out.println();
    }
  }

  /**
   * Checks if a valid relation works.
   */
  private static void testEight() {
    System.out.println("Test 8: Checks if a valid relation works");
    try {
      new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
          Arrays.asList(Type.INTEGER, Type.STRING));
      System.out.println("Test 8: PASS");
      System.out.println();
    } catch (IllegalArgumentException e) {
      System.err.println("Test 8: FAILED");
      assert false : "Inserting valid relation rejected.";
    }
  }

  /**
   * Checks if the {@code hasAttr} method works in your
   * own implmentation of the {@code Relation} interface.
   */
  private static void testNine() {
    System.out.println("Test 10: Checks hasAttr method.");
    Relation r = new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    if (!r.hasAttr("ID") || !r.hasAttr("Name")) {
      System.out.println("Test 10: FAILED");
      assert false : "hasAttr method failed.";
    }
    System.out.println("Test 10: PASS");
    System.out.println();
  }

  /**
   * Checks {@code insert} varargs when number of cells does not equal number of
   * attrs.
   */
  private static void testTen() {
    System.out.println("Test 10: Checks insert varargs when number of cells does not equal number of attrs.");
    Relation r = new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    try {
      r.insert(new Cell(1), new Cell("A"), new Cell("B"));
      System.err.println("Test 10a: FAILED");
      assert false : "Too many cells";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 10a: PASS");
    }
    try {
      r.insert(new Cell(1));
      System.err.println("Test 10b: FAILED");
      assert false : "Not enough cells";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 10b: PASS");
    }

    try {
      r.insert(new Cell(1), new Cell("A"));
      System.out.println("Test 10c: PASS");
      System.out.println();
    } catch (IllegalArgumentException e) {
      System.err.println("Test 10c: FAILED");
      assert false : "Row is suppose to insert correctly but did not.";
    }
  }

  /**
   * Checks {@code insert} list when number of cells does not equal number of
   * attrs.
   */
  private static void testEleven() {
    System.out.println("Test 11: Checks insert list when number of cells does not equal number of attrs.");
    Relation r = new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    try {
      r.insert(Arrays.asList(new Cell(1), new Cell("A"), new Cell("B")));
      System.err.println("Test 11a: FAILED");
      assert false : "Too many cells";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 11a: PASS");
    }
    try {
      r.insert(Arrays.asList(new Cell(1)));
      System.err.println("Test 11b: FAILED");
      assert false : "Not enough cells";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 11b: PASS");
    }

    try {
      r.insert(Arrays.asList(new Cell(1), new Cell("A")));
      System.out.println("Test 11c: PASS");
      System.out.println();
    } catch (IllegalArgumentException e) {
      System.err.println("Test 11c: FAILED");
      assert false : "Row is suppose to insert correctly but did not.";
    }
  }

  /**
   * Checks {@code insert} varargs when cell types do not correspond to the
   * attribute
   * types in the relation.
   */
  private static void testTwelve() {
    System.out.println(
        "Test 12: Check insert varargs when cell types do not correspond to the attribute types in the relation.");
    Relation r = new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    try {
      r.insert(new Cell(1), new Cell(2));
      System.err.println("Test 12a: FAILED");
      assert false : "Cell types do not correspond to the attribute types in the relation.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 12a: PASS");
    }

    try {
      r.insert(Arrays.asList(new Cell(1), new Cell("A")));
      System.out.println("Test 12b: PASS");
      System.out.println();
    } catch (IllegalArgumentException e) {
      System.err.println("Test 12b: FAILED");
      assert false : "Row is suppose to insert correctly but did not.";
    }
  }

  /**
   * Checks {@code insert} list when cell types do not correspond to the attribute
   * types in the relation.
   */
  private static void testThirteen() {
    System.out.println(
        "Test 12: Check insert list when cell types do not correspond to the attribute types in the relation.");
    Relation r = new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    try {
      r.insert(Arrays.asList(new Cell(1), new Cell(2)));
      System.err.println("Test 13a: FAILED");
      assert false : "Cell types do not correspond to the attribute types in the relation.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 13a: PASS");
    }

    try {
      r.insert(Arrays.asList(new Cell(1), new Cell("A")));
      System.out.println("Test 13b: PASS");
      System.out.println();
    } catch (IllegalArgumentException e) {
      System.err.println("Test 13b: FAILED");
      assert false : "Row is suppose to insert correctly but did not.";
    }
  }

  /**
   * Check {@code insert} varargs when row already exists.
   */
  private static void testFourteenth() {
    System.out.println("Test 14: Check insert varargs when row already exists.");
    Relation r = new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    r.insert(new Cell(1), new Cell("A"));
    try {
      r.insert(new Cell(1), new Cell("A"));
      System.err.println("Test 14: FAILED");
      assert false : "Row already exists.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 14: PASS");
      System.out.println();
    }
  }

  /**
   * Check {@code insert} list when row already exists.
   */
  private static void testFifteen() {
    System.out.println("Test 15: Check insert list when row already exists.");
    Relation r = new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    r.insert(Arrays.asList(new Cell(1), new Cell("A")));
    try {
      r.insert(Arrays.asList(new Cell(1), new Cell("A")));
      System.err.println("Test 15: FAILED");
      assert false : "Row already exists.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 15: PASS");
      System.out.println();
    }
  }

  /**
   * Checks {@code getAttrIndex} when element exists.
   */
  private static void testSixteen() {
    System.out.println("Test 16: Check getAttrIndex when element exists.");
    Relation r = new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    try {
      if (r.getAttrIndex("Name") != 1)
        throw new IllegalArgumentException();
      System.out.println("Test 16: PASS");
      System.out.println();
    } catch (IllegalArgumentException e) {
      System.err.println("Test 16: FAILED");
      assert false : "getAttrIndex does not work";
    }
  }

  /**
   * Checks {@code getAttrIndex} when element does not exist.
   */
  private static void testSeventeen() {
    System.out.println("Test 17: Check getAttrIndex when element does not exist.");
    Relation r = new RelationBuilderImplementation().newRelation("Test", Arrays.asList("ID", "Name"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    try {
      r.getAttrIndex("A");
      System.err.println("Test 17: FAILED");
      assert false : "Element should not exist";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 17: PASS");
      System.out.println();
    }
  }
}
