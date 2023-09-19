package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import java.util.List;
import java.util.ArrayList;

/**
 * An implementation of the {@code Relation} interface.
 */
public class RelationImplementation implements Relation {

  private String name;
  private List<String> attrs;
  private List<Type> types;

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
  }

  /**
   * Returns the name of the relation.
   *
   * @returns name of the relation
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Return the type of each column in a list.
   *
   * @returns the type of each column in a list
   */
  @Override
  public List<Type> getTypes() {
    return new ArrayList<>(types);
  }

  /**
   * Returns the list of attributes of the relation.
   *
   * @returns the list of attributes of the relation.
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
   * @returns {@code true} if {@code attr} is in {@code attrs}
   */
  @Override
  public boolean hasAttr(String attr) {
    for (String attribute : attrs) {
      if (attribute.equalsIgnoreCase(attr))
        return true;
    }
    return false;
  }

  @Override
  public int getAttrIndex(String attr) {
    for (int i = 0; i < attrs.size(); i++) {
      if (attrs.get(i).equalsIgnoreCase(attr)) return i;
    }
    throw new IllegalArgumentException("attr does not exist in the relation.");
  }
}
