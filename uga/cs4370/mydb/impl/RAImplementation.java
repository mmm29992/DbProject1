package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    return join(rel1, rel2, row -> true, rel1.getName() + "CartesianProduct" + rel2.getName());
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
    return !getCommonAttrs(rel1, rel2).isEmpty();
  }

  /**
   * Returns the common attributes from {@code rel1} and
   * {@code rel2}.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return the common attributes from {@code rel1} and {@code rel2}
   */
  private List<String> getCommonAttrs(Relation rel1, Relation rel2) {
    Set<String> set = new HashSet<>(rel1.getAttrs());
    List<String> commonAttrs = new ArrayList<>();
    for (String attr : rel2.getAttrs()) {
      if (set.contains(attr))
        commonAttrs.add(attr);
    }
    return commonAttrs;
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
    if (!relationsHaveACommonAttr(rel1, rel2))
      return cartesianProduct(rel1, rel2);
    List<String> commonAttrs = getCommonAttrs(rel1, rel2);
    Map<String, Integer> attrIndexMap1 = getAttrIndexMap(rel1, commonAttrs);
    Map<String, Integer> attrIndexMap2 = getAttrIndexMap(rel2, commonAttrs);
    Relation result = new RelationBuilderImplementation().newRelation(rel1.getName() + "NaturalJoin" + rel2.getName(),
        getCombinedAttrs(rel1, rel2), getCombinedTypes(rel1, rel2));

    List<List<Cell>> rows1 = rel1.getRows();
    List<List<Cell>> rows2 = rel2.getRows();
    for (List<Cell> row1 : rows1) {
      for (List<Cell> row2 : rows2) {
        boolean isCombinedRow = true;
        for (String attr : commonAttrs) {
          int index1 = attrIndexMap1.get(attr);
          int index2 = attrIndexMap2.get(attr);
          if (!row1.get(index1).equals(row2.get(index2))) {
            isCombinedRow = false;
            break;
          }
        }
        if (isCombinedRow)
          result.insert(getCombinedRow(row1, row2, attrIndexMap2));
      }
    }
    return result;
  }

  /**
   * Returns the attributes combined from {@code rel1} and {@code rel2}
   * without duplicates.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return the attributes combined from {@code rel1} and {@code rel2}
   *         without duplicates
   */
  private List<String> getCombinedAttrs(Relation rel1, Relation rel2) {
    List<String> combinedAttrs = rel1.getAttrs();
    for (String attr : rel2.getAttrs()) {
      if (!combinedAttrs.contains(attr))
        combinedAttrs.add(attr);
    }
    return combinedAttrs;
  }

  /**
   * Returns the types of the attributes combined from {@code rel1}
   * and {@code rel2} without duplicates.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @return the types of the attributes combined from {@code rel1}
   *         and {@code rel2} without duplicates
   */
  private List<Type> getCombinedTypes(Relation rel1, Relation rel2) {
    List<Type> combinedTypes = rel1.getTypes();
    List<Type> types2 = rel2.getTypes();
    List<String> attrs1 = rel1.getAttrs();
    List<String> attrs2 = rel2.getAttrs();
    for (int i = 0; i < attrs1.size(); i++) {
      if (!attrs1.contains(attrs2.get(i)))
        combinedTypes.add(types2.get(i));
    }
    return combinedTypes;
  }

  /**
   * Returns a map that maps an attribute from {@code attrs}
   * to its given index in {@code rel}.
   *
   * @param rel   relation to get the index of the attributes
   * @param attrs attributes to get the index of
   * @return a ap that maps an attribute from {@code attrs}
   *         to its given index in {@code rel}
   */
  private Map<String, Integer> getAttrIndexMap(Relation rel, List<String> attrs) {
    Map<String, Integer> map = new HashMap<>();
    for (String attr : attrs) {
      map.put(attr, rel.getAttrIndex(attr));
    }
    return map;
  }

  /**
   * Returns the row combined from {@code row1} and {@code row2}
   * without duplicate attributes.
   *
   * @param row1          row from the first relation
   * @param row2          row from the second relation
   * @param attrIndexMap2 second relation map that maps the attributes
   *                      to its index
   * @return the row combined from {@code row1} and {@code row2}
   *         without the duplicate attribute
   */
  private List<Cell> getCombinedRow(List<Cell> row1, List<Cell> row2, Map<String, Integer> attrIndexMap2) {
    List<Cell> combinedRow = new ArrayList<>(row1);
    Set<Integer> commonAttrIndices2 = new HashSet<>(attrIndexMap2.values());
    for (int i = 0; i < row2.size(); i++) {
      if (!commonAttrIndices2.contains(i))
        combinedRow.add(row2.get(i));
    }
    return combinedRow;
  }

  /**
   * Performs theta join on relations {@code rel1} and {@code rel2}
   * with predicate {@code p}.
   *
   * @param rel1 first relation
   * @param rel2 second relation
   * @param p    predicate that joins {@code rel1} and {@code rel2}
   * @return The resulting relation after applying theta join.
   */
  public Relation join(Relation rel1, Relation rel2, Predicate p) {
    return join(rel1, rel2, p, rel1.getName() + "Join" + rel2.getName());
  }

  /**
   * Performs theta join on relations {@code rel1} and {@code rel2}
   * with predicate {@code p}.
   *
   * @param rel1         first relation
   * @param rel2         second relation
   * @param p            predicate that joins {@code rel1} and {@code rel2}
   * @param relationName name of the new relation
   * @return The resulting relation after applying theta join.
   */
  private Relation join(Relation rel1, Relation rel2, Predicate p, String relationName) {
    Relation result = new RelationBuilderImplementation().newRelation(relationName, getCombinedAttrs(rel1, rel2),
        getCombinedTypes(rel1, rel2));

    List<List<Cell>> rows1 = rel1.getRows();
    List<List<Cell>> rows2 = rel2.getRows();
    for (List<Cell> row1 : rows1) {
      for (List<Cell> row2 : rows2) {
        if (p.check(row1) && p.check(row2)) {
          List<Cell> newRow = new ArrayList<>(row1);
          newRow.addAll(row2);
          result.insert(newRow);
        }
      }
    }
    return result;
  }
}
