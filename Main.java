import java.util.Arrays;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Cell;
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
    Relation students = getStudentsRelation();
    Relation courses = getCoursesRelation();
    Relation enrollment = getEnrollmentRelation();
    Relation professors = getProfessorsRelation();
    Relation teaches = getTeachesRelation();
    students.print();
    RAImplementation ra = new RAImplementation();
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

  /**
   * Creates a {@code Courses} relation
   * with rows inputted.
   *
   * @return {@code Courses} relation
   */
  private static Relation getCoursesRelation() {
    Relation courses = new RelationBuilderImplementation().newRelation("Courses",
        Arrays.asList("CourseID", "CName", "Credits"), Arrays.asList(Type.INTEGER, Type.STRING, Type.INTEGER));
    courses.insert(new Cell(1302), new Cell("Software Development"), new Cell(4));
    courses.insert(new Cell(1730), new Cell("Systems Programming"), new Cell(4));
    courses.insert(new Cell(1001), new Cell("Elementary Korean I"), new Cell(4));
    courses.insert(new Cell(2150), new Cell("Introduction to Computational Science"), new Cell(4));
    courses.insert(new Cell(2001), new Cell("Intermediate Korean I"), new Cell(3));
    courses.insert(new Cell(3030), new Cell("Computer, Ethics, and Society"), new Cell(3));
    courses.insert(new Cell(3200), new Cell("Introduction to Higher Mathematics"), new Cell(3));
    return courses;
  }

  /**
   * Creates a {@code Enrollment} relation
   * with rows inputted.
   *
   * @return {@code Enrollment} relation
   */
  private static Relation getEnrollmentRelation() {
    Relation enrollment = new RelationBuilderImplementation().newRelation("Enrollment",
        Arrays.asList("EnrollmentID", "StudentID", "CourseID", "grade"),
        Arrays.asList(Type.INTEGER, Type.INTEGER, Type.INTEGER, Type.STRING));
    enrollment.insert(new Cell(1), new Cell(1234), new Cell(1302), new Cell("B"));
    enrollment.insert(new Cell(2), new Cell(1234), new Cell(1001), new Cell("A"));
    enrollment.insert(new Cell(3), new Cell(1234), new Cell(1730), new Cell("C"));
    enrollment.insert(new Cell(4), new Cell(1234), new Cell(2150), new Cell("B"));
    enrollment.insert(new Cell(5), new Cell(1234), new Cell(3030), new Cell("A"));
    enrollment.insert(new Cell(6), new Cell(3690), new Cell(1001), new Cell("A"));
    enrollment.insert(new Cell(7), new Cell(3690), new Cell(2001), new Cell("A"));
    enrollment.insert(new Cell(8), new Cell(5678), new Cell(3200), new Cell("F"));
    enrollment.insert(new Cell(9), new Cell(5678), new Cell(1001), new Cell("A"));
    enrollment.insert(new Cell(10), new Cell(5678), new Cell(1302), new Cell("B"));
    enrollment.insert(new Cell(11), new Cell(5678), new Cell(1730), new Cell("B"));
    enrollment.insert(new Cell(12), new Cell(5792), new Cell(1302), new Cell("F"));
    enrollment.insert(new Cell(13), new Cell(5792), new Cell(3200), new Cell("F"));
    return enrollment;
  }

  /**
   * Creates a {@code Professors} relation
   * with rows inputted.
   *
   * @return {@code Professors} relation
   */
  private static Relation getProfessorsRelation() {
    Relation professors = new RelationBuilderImplementation().newRelation("Professors",
        Arrays.asList("ProfessorID", "FName", "LName", "department"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING));
    professors.insert(new Cell(101), new Cell("Brad"), new Cell("Barnes"), new Cell("Computer Science"));
    professors.insert(new Cell(102), new Cell("Michael"), new Cell("Cotterell"), new Cell("Computer Science"));
    professors.insert(new Cell(103), new Cell("Hyunjin"), new Cell("Lee"), new Cell("Korean"));
    professors.insert(new Cell(104), new Cell("Sami"), new Cell("Menik"), new Cell("Computer Science"));
    professors.insert(new Cell(105), new Cell("Manijeh"), new Cell("Keshtgari"), new Cell("Computer Science"));
    professors.insert(new Cell(106), new Cell("Peter"), new Cell("Clark"), new Cell("Mathematics"));
    professors.insert(new Cell(107), new Cell("Hao"), new Cell("Peng"), new Cell("Computer Science"));
    return professors;
  }

  /**
   * Creates a {@code Teaches} relation
   * with rows inputted.
   *
   * @return {@code Teaches} relation
   */
  private static Relation getTeachesRelation() {
    Relation teaches = new RelationBuilderImplementation().newRelation("Teaches",
        Arrays.asList("TeachID", "ProfessorID", "CourseID"), Arrays.asList(Type.INTEGER, Type.INTEGER, Type.INTEGER));
    teaches.insert(new Cell(501), new Cell(103), new Cell(1001));
    teaches.insert(new Cell(502), new Cell(103), new Cell(2001));
    teaches.insert(new Cell(503), new Cell(101), new Cell(1302));
    teaches.insert(new Cell(504), new Cell(102), new Cell(1302));
    teaches.insert(new Cell(505), new Cell(102), new Cell(3030));
    teaches.insert(new Cell(506), new Cell(107), new Cell(1730));
    teaches.insert(new Cell(507), new Cell(107), new Cell(3200));
    return teaches;
  }
}
