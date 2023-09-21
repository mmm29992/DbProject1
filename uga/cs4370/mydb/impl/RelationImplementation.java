package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Cell;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * An implementation of the {@code Relation} interface.
 */
public class RelationImplementation implements Relation {

  private String name;
  private List<String> attrs;
  private List<Type> types;
  private List<List<Cell>> rows;

  /**
   * Creates an instance of a {@code Relation}.
   *
   * @param name  name of relation
   * @param attrs attributes of the relations
   * @param types types of the {@code attrs}
   */
  RelationImplementation(String name, List<String> attrs, List<Type> types) {
    this.name = name;
    this.attrs = new ArrayList<>(attrs);
    this.types = new ArrayList<>(types);
    rows = new ArrayList<>();
  }

  /**
   * Returns the name of the relation.
   *
   * @return name of the relation
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns the row count of the relation.
   *
   * @return the row count of the relation
   */
  @Override
  public int getSize() {
    return rows.size();
  }

  /**
   * Gets the rows of the relation.
   * Returns a deep copy of the rows to avoid
   * modifications to the rows by the callers of this method.
   *
   * @return the rows of the relation
   */
  @Override
  public List<List<Cell>> getRows() {
    List<List<Cell>> deepCopyRows = new ArrayList<>();
    for (List<Cell> row : rows) {
      deepCopyRows.add(new ArrayList<>(row));
    }
    return deepCopyRows;
  }

  /**
   * Return the type of each column in a list.
   *
   * @return the type of each column in a list
   */
  @Override
  public List<Type> getTypes() {
    return new ArrayList<>(types);
  }

  /**
   * Returns the list of attributes of the relation.
   *
   * @return the list of attributes of the relation.
   */
  @Override
  public List<String> getAttrs() {
    return new ArrayList<>(attrs);
  }

  /**
   * Returns {@code true} if {@code attr} is in
   * {@code attrs}.
   *
   * @param attr attribute to check
   * @return {@code true} if {@code attr} is in {@code attrs}
   */
  @Override
  public boolean hasAttr(String attr) {
    for (String attribute : attrs) {
      if (attribute.equalsIgnoreCase(attr))
        return true;
    }
    return false;
  }

  /**
   * Returns the index of the attr.
   *
   * @param attr attribute to check
   * @return the index of the attr
   * @throws IllegalArgumentException if attr does not
   *                                  exist in the relation.
   */
  @Override
  public int getAttrIndex(String attr) {
    for (int i = 0; i < attrs.size(); i++) {
      if (attrs.get(i).equalsIgnoreCase(attr))
        return i;
    }
    throw new IllegalArgumentException("attr does not exist in the relation.");
  }

  /**
   * Inserts a row in the relation.
   *
   * @param cells newly inserted row
   * @throws IllegalArgumentException if the cell types do not correspond
   *                                  to the attribute types of the relation or if
   *                                  the row already exists.
   */
  public void insert(Cell... cells) {
    insert(Arrays.asList(cells));
  }

  /**
   * Inserts a row in the relation.
   *
   * @param cells newly inserted row
   * @throws IllegalArgumentException if the cell types do not correspond
   *                                  to the attribute types of the relation or if
   *                                  the row already exists.
   */
  public void insert(List<Cell> cells) {
    if (cells.size() != attrs.size())
      throw new IllegalArgumentException("The number of cells does not match the number of attributes.");
    if (!rowCorrespondsToAttributeTypes(cells))
      throw new IllegalArgumentException("The cell types do not correspond to the attribute types of the relation.");
    if (rowExists(cells))
      throw new IllegalArgumentException("The row already exists in the relation.");

    rows.add(new ArrayList<>(cells));
  }

  /**
   * Returns {@code true} if each cell type in
   * the row corresponds to the attribute types.
   *
   * @param cells newly inserted row
   * @return {@code true} if each cell type in the
   *         row corresponds to the attribute types
   */
  private boolean rowCorrespondsToAttributeTypes(List<Cell> cells) {
    for (int i = 0; i < types.size(); i++) {
      Type type = types.get(i);
      Cell cell = cells.get(i);
      try {
        switch (type) {
          case INTEGER:
            cell.getAsInt();
            break;
          case DOUBLE:
            cell.getAsDouble();
            break;
          case STRING:
            cell.getAsString();
            break;
          default:
            throw new UnsupportedOperationException("No valid attribute type.");
        }
      } catch (RuntimeException e) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns {@code true} if the row already
   * exists in the relation.
   *
   * @param cells newly inserted row
   * @return {@code true} if the row already
   *         exists in the relation
   */
  private boolean rowExists(List<Cell> cells) {
    for (List<Cell> row : rows) {
      for (int i = 0; i < row.size(); i++) {
        if (!row.get(i).equals(cells.get(i)))
          return false;
      }
    }
    return !rows.isEmpty();
  }

  public void print() {
    
  }
}
