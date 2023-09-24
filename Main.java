import java.util.Arrays;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.impl.*;

/**
 * File that tests the implementation of
 * relations and relational algebra.
 */
public class Main {

  private static final RA RA = new RAImplementation();
  private static final RelationBuilder BUILDER = new RelationBuilderImplementation();
  private static final Relation STUDENTS = getStudentsRelation();
  private static final Relation COURSES = getCoursesRelation();
  private static final Relation ENROLLMENT = getEnrollmentRelation();
  private static final Relation PROFESSORS = getProfessorsRelation();
  private static final Relation TEACHES = getTeachesRelation();

  /**
   * Main entry of the file.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    printDatabase();
    demonstrateRAOperations();
    questionAnswers();
  }

  /**
   * Prints the database including the
   * tables and their data.
   */
  private static void printDatabase() {
    System.out.println("Students Relation");
    getStudentsRelation().print();
    System.out.println("Courses Relation");
    getCoursesRelation().print();
    System.out.println("Enrollment Relation");
    getEnrollmentRelation().print();
    System.out.println("Professors Relation");
    getProfessorsRelation().print();
    System.out.println("Teaches Relation");
    getTeachesRelation().print();
  }

  /**
   * Demonstrate all RA operations implemented.
   */
  private static void demonstrateRAOperations() {
    select();
    project();
    union();
    diff();
    rename();
    cartesianProduct();
    naturalJoin();
    thetaJoin();
  }

  /**
   * Answers to the questions.
   */
  private static void questionAnswers() {
    q1();
    q2();
    q3();
    q4();
    q5();
    q6();
    q7();
    q8();
  }

  /**
   * Creates a {@code Students} relation
   * with rows inputted.
   *
   * @return {@code Students} relation
   */
  private static Relation getStudentsRelation() {
    Relation students = BUILDER.newRelation("Students",
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
    Relation courses = BUILDER.newRelation("Courses",
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
    Relation enrollment = BUILDER.newRelation("Enrollment",
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
    Relation professors = BUILDER.newRelation("Professors",
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
    Relation teaches = BUILDER.newRelation("Teaches",
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

  /**
   * Answer to question 1.
   */
  private static void q1() {
    System.out.println("Q1: Retrieve all course IDs where a student with ID 1234 is enrolled in.");
    Relation select = RA.select(ENROLLMENT, row -> row.get(1).getAsInt() == 1234);
    Relation project = RA.project(select, Arrays.asList("CourseID"));
    project.print();
  }

  /**
   * Answer to question 2.
   */
  private static void q2() {
    System.out.println("Q2: All student names and IDs who majors in computer science.");
    Relation select = RA.select(STUDENTS, row -> row.get(4).getAsString().equals("Computer Science"));
    Relation project = RA.project(select, Arrays.asList("StudentID", "FName", "LName"));
    project.print();
  }

  /**
   * Answer to question 3.
   */
  private static void q3() {
    System.out.println("Q3: Retrieve all course names a student with ID 1234 is enrolled in.");
    Relation join = RA.join(COURSES, ENROLLMENT);
    Relation select = RA.select(join, row -> row.get(4).getAsInt() == 1234);
    Relation project = RA.project(select, Arrays.asList("CName"));
    project.print();
  }

  /**
   * Answer to question 4.
   */
  private static void q4() {
    System.out.println("Q4: List of professors who teach courses of more than 2 credits.");
    Relation join = RA.join(PROFESSORS, TEACHES);
    join = RA.join(join, COURSES);
    Relation select = RA.select(join, row -> row.get(7).getAsInt() > 2);
    Relation project = RA.project(select, Arrays.asList("ProfessorID"));
    project.print();
  }

  /**
   * Answer to question 5.
   */
  private static void q5() {
    System.out.println("Q5: Student names and ids who have not enrolled in any course.");
    Relation studentsProject = RA.project(STUDENTS, Arrays.asList("StudentID"));
    Relation enrollmentProject = RA.project(ENROLLMENT, Arrays.asList("StudentID"));
    Relation diff = RA.diff(studentsProject, enrollmentProject);
    Relation join = RA.join(STUDENTS, diff);
    Relation project = RA.project(join, Arrays.asList("StudentID", "FName", "LName"));
    project.print();
  }

  /**
   * Answer to question 6.
   */
  private static void q6() {
    System.out.println("Q6: Courses that no professor teaches.");
    Relation coursesProject = RA.project(COURSES, Arrays.asList("CourseID"));
    Relation teachesProject = RA.project(TEACHES, Arrays.asList("CourseID"));
    Relation diff = RA.diff(coursesProject, teachesProject);
    Relation join = RA.join(COURSES, diff);
    Relation project = RA.project(join, Arrays.asList("CourseID"));
    project.print();
  }

  /**
   * Answer to question 7.
   */
  private static void q7() {
    System.out.println("Q7: All students who major in computer science who got an 'F'");
    Relation join = RA.join(STUDENTS, ENROLLMENT);
    Relation select = RA.select(join,
        row -> row.get(7).getAsString().equals("F") && row.get(4).getAsString().equals("Computer Science"));
    Relation project = RA.project(select, Arrays.asList("StudentID"));
    project.print();
  }

  /**
   * Answer to question 8.
   */
  private static void q8() {
    System.out.println("Q8: Professors who teach students doing computer science major.");
    Relation studentsProject = RA.project(STUDENTS, Arrays.asList("StudentID", "Major"));
    Relation join = RA.join(PROFESSORS, TEACHES);
    join = RA.join(join, ENROLLMENT);
    join = RA.join(join, studentsProject);
    Relation select = RA.select(join, row -> row.get(9).getAsString().equals("Computer Science"));
    Relation project = RA.project(select, Arrays.asList("ProfessorID"));
    project.print();
  }

  /**
   * Demonstrating {@code select} method.
   */
  private static void select() {
    System.out.println("Demonstrating Select Method");
    System.out.println("Original Students Relation");
    STUDENTS.print();
    System.out.println("Select Students who were born in 2003");
    RA.select(STUDENTS, row -> row.get(3).getAsString().startsWith("2003")).print();
  }

  /**
   * Demonstrating {@code project} method.
   */
  private static void project() {
    System.out.println("Demonstrating Project Method");
    System.out.println("Original Students Relation");
    STUDENTS.print();
    System.out.println("Choose StudentID, FName, and LName.");
    RA.project(STUDENTS, Arrays.asList("StudentID", "FName", "LName")).print();
  }

  /**
   * Demonstrating {@code union} method.
   */
  private static void union() {
    System.out.println("Demonstrating Union Method");
    Relation rel1 = BUILDER.newRelation("Rel1", Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));
    Relation rel2 = BUILDER.newRelation("Rel1", Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));
    rel1.insert(new Cell(1234), new Cell("Michael"), new Cell("Macheers"), new Cell("2003-08-08"),
        new Cell("Computer Science"));
    rel1.insert(new Cell(5678), new Cell("Jason"), new Cell("Ouyang"), new Cell("2002-10-15"),
        new Cell("Computer Science"));
    rel1.insert(new Cell(9012), new Cell("Darren"), new Cell("Thammavong"), new Cell("2003-04-01"),
        new Cell("Computer Science"));
    rel2.insert(new Cell(5792), new Cell("Hannah"), new Cell("Kwak"), new Cell("2003-03-12"),
        new Cell("Computer Science"));
    rel2.insert(new Cell(3690), new Cell("Ami"), new Cell("Yin"), new Cell("2002-11-18"), new Cell("Biology"));
    rel2.insert(new Cell(9012), new Cell("Darren"), new Cell("Thammavong"), new Cell("2003-04-01"),
        new Cell("Computer Science"));

    System.out.println("First Student Relation");
    rel1.print();
    System.out.println("Second Student Relation");
    rel2.print();
    System.out.println("Union");
    RA.union(rel1, rel2).print();
  }

  /**
   * Demonstrating {@code diff} method.
   */
  private static void diff() {
    System.out.println("Demonstrating Diff Method");
    Relation rel1 = BUILDER.newRelation("Rel1", Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));
    Relation rel2 = BUILDER.newRelation("Rel1", Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));
    rel1.insert(new Cell(1234), new Cell("Michael"), new Cell("Macheers"), new Cell("2003-08-08"),
        new Cell("Computer Science"));
    rel1.insert(new Cell(5678), new Cell("Jason"), new Cell("Ouyang"), new Cell("2002-10-15"),
        new Cell("Computer Science"));
    rel1.insert(new Cell(9012), new Cell("Darren"), new Cell("Thammavong"), new Cell("2003-04-01"),
        new Cell("Computer Science"));
    rel2.insert(new Cell(1234), new Cell("Michael"), new Cell("Macheers"), new Cell("2003-08-08"),
        new Cell("Computer Science"));
    rel2.insert(new Cell(5678), new Cell("Jason"), new Cell("Ouyang"), new Cell("2002-10-15"),
        new Cell("Computer Science"));
    rel2.insert(new Cell(3690), new Cell("Ami"), new Cell("Yin"), new Cell("2002-11-18"), new Cell("Biology"));
    System.out.println("First Student Relation");
    rel1.print();
    System.out.println("Second Student Relation");
    rel2.print();
    System.out.println("Diff");
    RA.diff(rel1, rel2).print();
  }

  /**
   * Demonstrating {@code rename} method.
   */
  private static void rename() {
    System.out.println("Demonstrating Rename Method");
    System.out.println("Original Students Relation");
    STUDENTS.print();
    System.out.println("Rename FName to FirstName and LName to LastName");
    RA.rename(STUDENTS, Arrays.asList("FName", "LName"), Arrays.asList("FirstName", "LastName")).print();
  }

  /**
   * Demonstrating {@code cartesianProduct} method.
   */
  private static void cartesianProduct() {
    System.out.println("Demonstrating Cartesian Product");
    System.out.println("Students Relation");
    STUDENTS.print();
    System.out.println("Courses Relation");
    COURSES.print();
    System.out.println("Students x Courses");
    RA.cartesianProduct(STUDENTS, COURSES).print();
  }

  /**
   * Demonstrating natural {@code join} method.
   */
  private static void naturalJoin() {
    System.out.println("Demonstrating Natural Join");
    System.out.println("Students Relation");
    STUDENTS.print();
    System.out.println("Enrollment Relation");
    ENROLLMENT.print();
    System.out.println("Students NATURAL JOIN Enrollment");
    RA.join(STUDENTS, ENROLLMENT).print();
  }

  /**
   * Demonstrating theta {@code join} method.
   */
  private static void thetaJoin() {
    System.out.println("Demonstrating Theta Join");
    System.out.println("Students Relation");
    STUDENTS.print();
    System.out.println("Courses Relation");
    COURSES.print();
    System.out.println("Students THETA JOIN Courses where StudentID and CourseID are even numbers");
    RA.join(STUDENTS, COURSES, row -> row.get(0).getAsInt() % 2 == 0).print();
  }
}
