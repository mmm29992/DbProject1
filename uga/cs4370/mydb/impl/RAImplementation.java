package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Relation;

/**
 * An implementation of the {@code RA} interface.
 */
public class RAImplementation implements RA {

  /**
   * Performs the select operation on the relation rel
   * by applying the predicate p.
   *
   * @param rel relation to perform {@code select} on
   * @param p   predicate to test for each row
   * @return The resulting relation after applying the select operation.
   */
  @Override
  public Relation select(Relation rel, Predicate p) {
    Relation result = new RelationBuilderImplementation().newRelation(rel.getName(), rel.getAttrs(), rel.getTypes());
    for (List<Cell> row : rel.getRows()) {
      if (p.check(row))
        result.insert(new ArrayList<>(row));
    }
    return result;
  }

  /**
   * Performs the project operation on the relation rel
   * given the attributes list attrs.
   *
   * @param rel   relation to perform {@code project} on
   * @param attrs attrs for the result of the new relation
   * @return The resulting relation after applying the project operation.
   * @throws IllegalArgumentException If attributes in attrs are not
   *                                  present in rel.
   */
  @Override
  public Relation project(Relation rel, List<String> attrs) {
    if (!attrsPresentInRel(rel, attrs))
      throw new IllegalArgumentException("Attributes in attrs are not present in rel.");
    Relation result = new RelationBuilderImplementation().newRelation(rel.getName(), attrs,
        getTypesFromAttrs(rel, attrs));
    for (List<Cell> row : getProjectRows(rel, attrs))
      result.insert(row);
    return result;
  }

  /**
   * Returns {@code true} if each {@code attr} in {@code attrs}
   * exists in {@code rel}.
   *
   * @param rel   relation to check
   * @param attrs list of attributes to check
   * @return {@code true} if each {@code attr} in {@code attrs}
   *         exists in {@code rel}
   */
  private boolean attrsPresentInRel(Relation rel, List<String> attrs) {
    for (String attr : attrs) {
      if (!rel.hasAttr(attr))
        return false;
    }
    return true;
  }

  /**
   * Returns the types of {@code attrs} from {@code rel}.
   *
   * @param rel   relation to check the types
   * @param attrs list of attributes to get the types
   * @return the types of {@code attrs} from {@code rel}
   */
  private List<Type> getTypesFromAttrs(Relation rel, List<String> attrs) {
    List<Type> relTypes = rel.getTypes();
    List<Type> newTypes = new ArrayList<>();
    for (String attr : attrs) {
      int index = rel.getAttrIndex(attr);
      newTypes.add(relTypes.get(index));
    }
    return newTypes;
  }

  /**
   * Returns the new rows when the {@code project}
   * operation is called.
   *
   * @param rel   relation to filter
   * @param attrs attributes of the resulting relation
   * @return the new rows when the {@code project}
   *         operation is called
   */
  private List<List<Cell>> getProjectRows(Relation rel, List<String> attrs) {
    List<List<Cell>> newRows = new ArrayList<>();
    for (List<Cell> row : rel.getRows()) {
      List<Cell> newRow = new ArrayList<>();
      for (int i = 0; i < attrs.size(); i++) {
        int index = rel.getAttrIndex(attrs.get(i));
        newRow.add(row.get(index));
      }
      newRows.add(newRow);
    }
    return newRows;
  }

  /**
   * Performs the union operation on the relations {@code rel1} and {@code rel2}.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return The resulting relation after applying the union operation.
   * @throws IllegalArgumentException If {@code rel1} and {@code rel2} are not
   *                                  compatible.
   */
  @Override
  public Relation union(Relation rel1, Relation rel2) {
    if (rel1.getAttrs().size() != rel2.getAttrs().size())
      throw new IllegalArgumentException("rel1 and rel2 do not have the same number of attributes.");
    if (!sameAttrsAndTypes(rel1, rel2))
      throw new IllegalArgumentException("rel1 and rel2 have do not have the same attrs or types.");
    Relation result = new RelationBuilderImplementation().newRelation(rel1.getName() + "Union" + rel2.getName(),
        rel1.getAttrs(), rel1.getTypes());
    for (List<Cell> row : rel1.getRows()) {
      result.insert(row);
    }

    List<String> attrs2 = rel2.getAttrs();
    for (List<Cell> row : rel2.getRows()) {
      Cell[] organizedRow = new Cell[row.size()];
      for (int i = 0; i < row.size(); i++) {
        int index = rel1.getAttrIndex(attrs2.get(i));
        organizedRow[index] = row.get(i);
      }
      result.insert(organizedRow);
    }
    return result;

  }

  /**
   * Performs the set difference operaion on the relations rel1 and rel2.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return The resulting relation after applying the set difference operation.
   * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
   */
  @Override
  public Relation diff(Relation rel1, Relation rel2) {
    if (rel1.getAttrs().size() != rel2.getAttrs().size())
      throw new IllegalArgumentException("rel1 and rel2 do not have the same number of attributes.");
    if (!sameAttrsAndTypes(rel1, rel2))
      throw new IllegalArgumentException("rel1 and rel2 have do not have the same attrs or types.");
    Relation result = new RelationBuilderImplementation().newRelation(rel1.getName() + "Diff" + rel2.getName(),
        rel1.getAttrs(), rel1.getTypes());
    for (List<Cell> row : rel1.getRows()) {
      if (!rowExists(rel2, row))
        result.insert(row);
    }
    return result;
  }

  /**
   * Returns {@code true} if {@code rel1} and {@code rel2}
   * have the same attributes with the same types.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return {@code true} if {@code rel1} and {@code rel2}
   *         have the same attributes with the same types
   */
  private boolean sameAttrsAndTypes(Relation rel1, Relation rel2) {
    List<String> attrs1 = rel1.getAttrs();
    List<String> attrs2 = rel2.getAttrs();
    List<Type> types1 = rel1.getTypes();
    List<Type> types2 = rel2.getTypes();

    for (int i = 0; i < attrs1.size(); i++) {
      if (!rel1.hasAttr(attrs2.get(i)))
        return false;
      int index = rel1.getAttrIndex(attrs2.get(i));
      if (!types1.get(index).equals(types2.get(i)))
        return false;
    }

    return true;
  }

  /**
   * Returns {@code true} if {@code row} exists in {@code rel}.
   *
   * @param rel relation to see if {@code row} exists
   * @param row row to check for
   */
  private boolean rowExists(Relation rel, List<Cell> row) {
    Set<Cell> rowSet = new HashSet<>(row);
    for (List<Cell> currentRow : rel.getRows()) {
      if (new HashSet<>(currentRow).equals(rowSet))
        return true;
    }
    return false;
  }

  /**
   * Renames the attributes in origAttr of relation rel to corresponding
   * names in renamedAttr.
   *
   * @param rel         relation containing the attributes
   * @param origAttr    original attributes of {@code rel}
   * @param renamedAttr the new attributes name
   * @return The resulting relation after renaming the attributes.
   * @throws IllegalArgumentException If attributes in origAttr are not present in
   *                                  rel or origAttr and renamedAttr do not have
   *                                  matching argument counts.
   */
  @Override
  public Relation rename(Relation rel, List<String> origAttr, List<String> renamedAttr) {
    if (!attrsPresentInRel(rel, origAttr))
      throw new IllegalArgumentException("Attributes in origAttr are not present in rel.");
    if (origAttr.size() != renamedAttr.size())
      throw new IllegalArgumentException("origAttr and renamedAttr do not have the same size.");

    List<String> newAttrs = rel.getAttrs();
    for (int i = 0; i < origAttr.size(); i++) {
      int index = newAttrs.indexOf(origAttr.get(i));
      newAttrs.set(index, renamedAttr.get(i));
    }
    Relation result = new RelationBuilderImplementation().newRelation(rel.getName(), newAttrs, rel.getTypes());
    for (List<Cell> row : rel.getRows()) {
      result.insert(row);
    }
    return result;
  }

  /**
   * Performs cartisian product on relations rel1 and rel2.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return The resulting relation after applying cartisian product.
   * @throws IllegalArgumentException if rel1 and rel2 have common attributes.
   */
  @Override
  public Relation cartesianProduct(Relation rel1, Relation rel2) {
    if (relationsHaveACommonAttr(rel1, rel2))
      throw new IllegalArgumentException("rel1 and rel2 have common attributes.");
    List<String> newAttrs = rel1.getAttrs();
    newAttrs.addAll(rel2.getAttrs());
    List<Type> newTypes = rel1.getTypes();
    newTypes.addAll(rel2.getTypes());
    Relation result = new RelationBuilderImplementation()
        .newRelation(rel1.getName() + "CartesianProduct" + rel2.getName(), newAttrs, newTypes);

    List<List<Cell>> rows1 = rel1.getRows();
    List<List<Cell>> rows2 = rel2.getRows();
    for (int i = 0; i < rel1.getSize(); i++) {
      for (int j = 0; j < rel2.getSize(); j++) {
        List<Cell> newRow = new ArrayList<>(rows1.get(i));
        newRow.addAll(rows2.get(j));
        result.insert(newRow);
      }
    }

    return result;
  }

  /**
   * Returns {@code true} if {@code rel1} and {@code rel2}
   * have a common attribute.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return {@code true} if {@code rel1} and {@code rel2}
   *         have a common attribute
   */
  private boolean relationsHaveACommonAttr(Relation rel1, Relation rel2) {
    List<String> attrs1 = rel1.getAttrs();
    List<String> attrs2 = rel2.getAttrs();
    Set<String> set = new HashSet<>(attrs1);
    set.addAll(attrs2);
    return set.size() != attrs1.size() + attrs2.size();
  }

  /**
   * Peforms natural join on relations rel1 and rel2.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return The resulting relation after applying natural join.
   */
  @Override
  public Relation join(Relation rel1, Relation rel2) {
    throw new UnsupportedOperationException();
  }

  /**
   * Performs theta join on relations rel1 and rel2 with predicate p.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @param p    predicate that joins {@code rel1} and {@code rel2}
   * @return The resulting relation after applying theta join.
   */
  public Relation join(Relation rel1, Relation rel2, Predicate p) {
    throw new UnsupportedOperationException();
  }
}
