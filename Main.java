import java.util.Arrays;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.impl.*;

/**
 * File that tests the implementation of
 * relations and relational algebra.
 */
public class Main {
  /**
   * Main entry of the file.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    RelationBuilderImplementation rb = new RelationBuilderImplementation();
    Relation relation = rb.newRelation("Students", Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));

  }
}
