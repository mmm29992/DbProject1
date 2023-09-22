import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.impl.RelationBuilderImplementation;
import uga.cs4370.mydb.impl.RAImplementation;

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
    test1();
    test2();
    test3();
    test4();
    test5();
    test6();
    test7();
    test8();
    test9();
    test10();
    test11();
    test12();
    test13();
    test14();
    test15();
    test16();
    test17();
    test18();
    test19();
    test20();
    test21();
    test22();
    test23();
    test24();
    test25();
    test26();
    test27();
    test28();
    test29();
    test30();
    test31();
    test32();
  }

  /**
   * Checks if nameis not an empty string.
   */
  private static void test1() {
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
  private static void test2() {
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
  private static void test3() {
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
  private static void test4() {
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
  private static void test5() {
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
  private static void test6() {
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
  private static void test7() {
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
  private static void test8() {
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
  private static void test9() {
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
  private static void test10() {
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
  private static void test11() {
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
  private static void test12() {
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
  private static void test13() {
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
  private static void test14() {
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
  private static void test15() {
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
  private static void test16() {
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
  private static void test17() {
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

  /**
   * Checks {@code select} method from RA.
   */
  private static void test18() {
    System.out.println("Test 18: Checks select method from RA.");
    Relation students = getStudentsRelation();
    RA ra = new RAImplementation();
    Relation result = ra.select(students, row -> row.get(1).getAsString().contains("e"));
    Relation solution = new RelationBuilderImplementation().newRelation("Solution",
        Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));
    solution.insert(new Cell(9012), new Cell("Darren"), new Cell("Thammavong"), new Cell("2003-04-01"),
        new Cell("Computer Science"));
    solution.insert(new Cell(1234), new Cell("Michael"), new Cell("Macheers"), new Cell("2003-08-08"),
        new Cell("Computer Science"));
    if (equals(result, solution)) {
      System.out.println("Test 18: PASS");
      System.out.println();
    } else {
      System.err.println("Test 18: FAILED");
      assert false : "select method did not select the correct rows.";
    }
  }

  /**
   * Checks project method to see if an attribute is not in the relation.
   */
  private static void test19() {
    System.out.println("Test 19: Checks project method to see if an attribute is not in the relation.");
    Relation students = getStudentsRelation();
    RA ra = new RAImplementation();
    try {
      ra.project(students, Arrays.asList("Test"));
      System.err.println("Test 19: FAILED");
      assert false : "Attribute should not exist in relation";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 19: PASS");
      System.out.println();
    }
  }

  /**
   * Checks project method implementation.
   */
  private static void test20() {
    System.out.println("Test 20: Checks project method implementation.");
    Relation students = getStudentsRelation();
    RA ra = new RAImplementation();
    Relation result = ra.project(students, Arrays.asList("LName", "StudentID", "FName"));
    Relation solution = new RelationBuilderImplementation().newRelation("Solution",
        Arrays.asList("StudentID", "FName", "LName"), Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING));
    solution.insert(new Cell(1234), new Cell("Michael"), new Cell("Macheers"));
    solution.insert(new Cell(3690), new Cell("Ami"), new Cell("Yin"));
    solution.insert(new Cell(5678), new Cell("Jason"), new Cell("Ouyang"));
    solution.insert(new Cell(5792), new Cell("Hannah"), new Cell("Kwak"));
    solution.insert(new Cell(9012), new Cell("Darren"), new Cell("Thammavong"));
    if (equals(result, solution)) {
      System.out.println("Test 20: PASS");
      System.out.println();
    } else {
      System.err.println("Test 20: FAILED");
      assert false : "Project method does not work.";
    }
  }

  /**
   * Checks union method with different attribute sizes.
   */
  private static void test21() {
    System.out.println("Test 21: Checks union method with different attribute sizes.");
    Relation rel1 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("A"),
        Arrays.asList(Type.INTEGER));
    Relation rel2 = new RelationBuilderImplementation().newRelation("Relation2", Arrays.asList("A", "B"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    RA ra = new RAImplementation();
    try {
      ra.union(rel1, rel2);
      System.err.println("Test 21: FAILED");
      assert false : "Union should not work with different attribute sizes.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 21: PASS");
      System.out.println();
    }
  }

  /**
   * Checks {@code union} method with different types.
   */
  private static void test22() {
    System.out.println("Test 22: Checks union method with different types.");
    Relation rel1 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("B", "A"),
        Arrays.asList(Type.INTEGER, Type.INTEGER));
    Relation rel2 = new RelationBuilderImplementation().newRelation("Relation2", Arrays.asList("A", "B"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    try {
      new RAImplementation().union(rel1, rel2);
      System.err.println("Test 22: FAILED");
      assert false : "Union method should not work with different types.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 22: PASS");
      System.out.println();
    }
  }

  /**
   * Checks {@code union} method with different attributes.
   */
  private static void test23() {
    System.out.println("Test 23: Checks union method with different attributes.");
    Relation rel1 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("B", "C"),
        Arrays.asList(Type.INTEGER, Type.INTEGER));
    Relation rel2 = new RelationBuilderImplementation().newRelation("Relation2", Arrays.asList("A", "B"),
        Arrays.asList(Type.INTEGER, Type.INTEGER));
    try {
      new RAImplementation().union(rel1, rel2);
      System.err.println("Test 23: FAILED");
      assert false : "Union method should not work with different attributes.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 23: PASS");
      System.out.println();
    }
  }

  /**
   * Checks {@code union} method implementation.
   */
  private static void test24() {
    System.out.println("Test 24: Checks union method implementation");
    Relation rel1 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("A", "B", "C"),
        Arrays.asList(Type.INTEGER, Type.DOUBLE, Type.STRING));
    Relation rel2 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("C", "A", "B"),
        Arrays.asList(Type.STRING, Type.INTEGER, Type.DOUBLE));
    rel1.insert(new Cell(10), new Cell(10.2), new Cell("number"));
    rel1.insert(new Cell(18), new Cell(18.5), new Cell("armadillo"));
    rel2.insert(new Cell("reference"), new Cell(76), new Cell(85.2));
    rel2.insert(new Cell("75"), new Cell(22), new Cell(52.6));
    Relation result = new RAImplementation().union(rel1, rel2);
    Relation solution = new RelationBuilderImplementation().newRelation("Solution", Arrays.asList("B", "C", "A"),
        Arrays.asList(Type.DOUBLE, Type.STRING, Type.INTEGER));
    solution.insert(new Cell(10.2), new Cell("number"), new Cell(10));
    solution.insert(new Cell(18.5), new Cell("armadillo"), new Cell(18));
    solution.insert(new Cell(85.2), new Cell("reference"), new Cell(76));
    solution.insert(new Cell(52.6), new Cell("75"), new Cell(22));
    if (equals(result, solution)) {
      System.out.println("Test 24: PASS");
      System.out.println();
    } else {
      System.err.println("Test 24: FAILED");
      assert false : "Union method does not work.";
    }
  }

  /**
   * Checks {@code union} method implementation.
   */
  private static void test25() {
    System.out.println("Test 25: Checks union method implementation.");
    Relation rel1 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("A", "B", "C"),
        Arrays.asList(Type.INTEGER, Type.DOUBLE, Type.STRING));
    Relation rel2 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("C", "A", "B"),
        Arrays.asList(Type.STRING, Type.INTEGER, Type.DOUBLE));
    Relation result = new RAImplementation().union(rel1, rel2);
    Relation solution = new RelationBuilderImplementation().newRelation("Solution", Arrays.asList("B", "C", "A"),
        Arrays.asList(Type.DOUBLE, Type.STRING, Type.INTEGER));
    if (equals(result, solution)) {
      System.out.println("Test 25: PASS");
      System.out.println();
    } else {
      System.err.println("Test 25: FAILED");
      assert false : "Union method does not work.";
    }
  }

  /**
   * Checks diff method with different types.
   */
  private static void test26() {
    System.out.println("Test 26: Checks diff method with different types.");
    Relation rel1 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("B", "A"),
        Arrays.asList(Type.INTEGER, Type.INTEGER));
    Relation rel2 = new RelationBuilderImplementation().newRelation("Relation2", Arrays.asList("A", "B"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    try {
      new RAImplementation().diff(rel1, rel2);
      System.err.println("Test 26: FAILED");
      assert false : "Diff method should not work with different types.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 26: PASS");
      System.out.println();
    }
  }

  /**
   * Checks diff method with different attributes.
   */
  private static void test27() {
    System.out.println("Test 27: Checks diff method with different attributes.");
    Relation rel1 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("B", "C"),
        Arrays.asList(Type.INTEGER, Type.INTEGER));
    Relation rel2 = new RelationBuilderImplementation().newRelation("Relation2", Arrays.asList("A", "B"),
        Arrays.asList(Type.INTEGER, Type.INTEGER));
    try {
      new RAImplementation().diff(rel1, rel2);
      System.err.println("Test 27: FAILED");
      assert false : "Diff method should not work with different attributes.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 27: PASS");
      System.out.println();
    }
  }

  /**
   * Checks {@code diff} method implementation.
   */
  private static void test28() {
    System.out.println("Test 28: Checks diff method implementation.");
    Relation rel1 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("A", "B", "C"),
        Arrays.asList(Type.INTEGER, Type.DOUBLE, Type.STRING));
    Relation rel2 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("C", "A", "B"),
        Arrays.asList(Type.STRING, Type.INTEGER, Type.DOUBLE));
    rel1.insert(new Cell(10), new Cell(10.2), new Cell("number"));
    rel1.insert(new Cell(18), new Cell(18.5), new Cell("armadillo"));
    rel1.insert(new Cell(23), new Cell(95.23), new Cell("crazy"));
    rel2.insert(new Cell("reference"), new Cell(76), new Cell(85.2));
    rel2.insert(new Cell("75"), new Cell(22), new Cell(52.6));
    rel2.insert(new Cell("number"), new Cell(10), new Cell(10.2));
    Relation result = new RAImplementation().diff(rel1, rel2);
    Relation solution = new RelationBuilderImplementation().newRelation("Solution", Arrays.asList("B", "C", "A"),
        Arrays.asList(Type.DOUBLE, Type.STRING, Type.INTEGER));
    solution.insert(new Cell(18.5), new Cell("armadillo"), new Cell(18));
    solution.insert(new Cell(95.23), new Cell("crazy"), new Cell(23));
    if (equals(result, solution)) {
      System.out.println("Test 28: PASS");
      System.out.println();
    } else {
      System.err.println("Test 28: FAILED");
      assert false : "Diff method does not work";
    }
  }

  /**
   * Checks {@code diff} method implementation.
   */
  private static void test29() {
    System.out.println("Test 29: Checks diff method implementation.");
    Relation rel1 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("A", "B", "C"),
        Arrays.asList(Type.INTEGER, Type.DOUBLE, Type.STRING));
    Relation rel2 = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("C", "A", "B"),
        Arrays.asList(Type.STRING, Type.INTEGER, Type.DOUBLE));
    rel1.insert(new Cell(10), new Cell(10.2), new Cell("number"));
    rel1.insert(new Cell(18), new Cell(18.5), new Cell("armadillo"));
    rel2.insert(new Cell("number"), new Cell(10), new Cell(10.2));
    rel2.insert(new Cell("armadillo"), new Cell(18), new Cell(18.5));
    rel2.insert(new Cell("reference"), new Cell(76), new Cell(85.2));
    rel2.insert(new Cell("75"), new Cell(22), new Cell(52.6));
    Relation result = new RAImplementation().diff(rel1, rel2);
    Relation solution = new RelationBuilderImplementation().newRelation("Solution", Arrays.asList("B", "C", "A"),
        Arrays.asList(Type.DOUBLE, Type.STRING, Type.INTEGER));
    if (equals(result, solution)) {
      System.out.println("Test 29: PASS");
      System.out.println();
    } else {
      System.err.println("Test 29: FAILED");
      assert false : "Diff method does not work";
    }
  }

  /**
   * Checks {@code rename} method with non-existing attributes.
   */
  private static void test30() {
    System.out.println("Test 30: Checks rename method with non-existing attributes.");
    Relation rel = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("A", "B", "C"),
        Arrays.asList(Type.INTEGER, Type.DOUBLE, Type.STRING));
    try {
      new RAImplementation().rename(rel, Arrays.asList("D"), Arrays.asList("E"));
      System.err.println("Test 30: FAILED");
      assert false : "Cannot rename with non-existing attribute.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 30: PASS");
      System.out.println();
    }
  }

  /**
   * Checks {@code rename} method with different attribute sizes.
   */
  private static void test31() {
    System.out.println("Test 31: Checks rename method with different attribute sizes.");
    Relation rel = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("A", "B", "C"),
        Arrays.asList(Type.INTEGER, Type.DOUBLE, Type.STRING));
    try {
      new RAImplementation().rename(rel, Arrays.asList("A", "B"), Arrays.asList("D"));
      System.err.println("Test 31a: FAILED");
      assert false : "origAttr has more attributes than renamedAttr.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 31a: PASS");
    }

    try {
      new RAImplementation().rename(rel, Arrays.asList("A"), Arrays.asList("D", "E"));
      System.err.println("Test 31b: FAILED");
      assert false : "renamedAttr has more attributes than origAttr.";
    } catch (IllegalArgumentException e) {
      System.out.println("Test 31b: PASS");
      System.out.println();
    }
  }

  /**
   * Checks {@code rename} method implementation.
   */
  private static void test32() {
    System.out.println("Test 32: Checks rename method implementation");
    Relation rel = new RelationBuilderImplementation().newRelation("Relation1", Arrays.asList("A", "B", "C"),
        Arrays.asList(Type.INTEGER, Type.DOUBLE, Type.STRING));
    rel.insert(new Cell(10), new Cell(10.2), new Cell("number"));
    rel.insert(new Cell(18), new Cell(18.5), new Cell("armadillo"));
    Relation result = new RAImplementation().rename(rel, Arrays.asList("C", "B"), Arrays.asList("String", "Double"));
    Relation solution = new RelationBuilderImplementation().newRelation("Solution",
        Arrays.asList("Double", "String", "A"),
        Arrays.asList(Type.DOUBLE, Type.STRING, Type.INTEGER));
    solution.insert(new Cell(10.2), new Cell("number"), new Cell(10));
    solution.insert(new Cell(18.5), new Cell("armadillo"), new Cell(18));
    if (equals(result, solution)) {
      System.out.println("Test 32: PASS");
      System.out.println();
    } else {
      System.err.println("Test 32: FAILED");
      assert false : "Rename method does not work.";
    }
  }

  /**
   * Returns {@code true} if two relations are equal.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return {@code true} if two relations are equal
   */
  private static boolean equals(Relation rel1, Relation rel2) {
    return attrsEqual(rel1, rel2) && rowsEqual(rel1, rel2);
  }

  /**
   * Returns {@code true} if {@code rel1} has the
   * same attributes as {@code rel2}.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return {@code true} if {@code rel1} has the
   *         same attributes as {@code rel2}
   */
  private static boolean attrsEqual(Relation rel1, Relation rel2) {
    return new HashSet<>(rel1.getAttrs()).equals(new HashSet<>(rel2.getAttrs()));
  }

  /**
   * Returns {@code true} if {@code rel1} has the same
   * rows as {@code rel2}.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return {@code true} if {@code rel1} has the same
   *         rows as {@code rel2}
   */
  private static boolean rowsEqual(Relation rel1, Relation rel2) {
    Set<Set<Cell>> rows1 = new HashSet<>();
    Set<Set<Cell>> rows2 = new HashSet<>();
    for (List<Cell> row : rel1.getRows()) {
      rows1.add(new HashSet<>(row));
    }
    for (List<Cell> row : rel2.getRows()) {
      rows2.add(new HashSet<>(row));
    }
    return rows1.equals(rows2);
  }

  /**
   * Creates a {@code Students} relation
   * with rows inputted.
   *
   * @return {@code Students} relation
   */
  private static Relation getStudentsRelation() {
    Relation students = new RelationBuilderImplementation().newRelation("Students",
        Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));
    students.insert(new Cell(1234), new Cell("Michael"), new Cell("Macheers"), new Cell("2003-08-08"),
        new Cell("Computer Science"));
    students.insert(new Cell(5678), new Cell("Jason"), new Cell("Ouyang"), new Cell("2002-10-15"),
        new Cell("Computer Science"));
    students.insert(new Cell(9012), new Cell("Darren"), new Cell("Thammavong"), new Cell("2003-04-01"),
        new Cell("Computer Science"));
    students.insert(new Cell(5792), new Cell("Hannah"), new Cell("Kwak"), new Cell("2003-03-12"),
        new Cell("Computer Science"));
    students.insert(new Cell(3690), new Cell("Ami"), new Cell("Yin"), new Cell("2002-11-18"), new Cell("Biology"));
    return students;
  }
}
