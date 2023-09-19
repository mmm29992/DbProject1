package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import java.util.List;

/**
 * An implementation of the {@code RelationBuilder} interface.
 */
public class RelationBuilderImplementation implements RelationBuilder {

  /**
   * Creates an empty relation with attribute names attrs and
   * attribute types types.
   *
   * @param name  name of relation
   * @param attrs attributes of the relations
   * @param types types of the {@code attrs}
   * @throws IllegalArgumentException if attrs and types have different counts
   *                                  or attrs have empty or non-alphanumeric
   *                                  attribute names.
   */
  @Override
  public Relation newRelation(String name, List<String> attrs, List<Type> types) {
    if (attrs.size() != types.size())
      throw new IllegalArgumentException("attrs and types have different counts.");
    if (attrsContainEmptyString(attrs))
      throw new IllegalArgumentException("attrs contain an empty string.");
    if (attrsContainNonAlphanumericString(attrs))
      throw new IllegalArgumentException("attrs have non-alphanumeric attribute names.");
    return new RelationImplementation(name, attrs, types);
  }

  /**
   * Returns {@code true} if {@code attrs} contains
   * an empty string.
   *
   * @param attrs attributes of the relation
   * @return {@code true} if {@code attrs} contains an empty string
   */
  private boolean attrsContainEmptyString(List<String> attrs) {
    for (String attr : attrs) {
      if (attr.isEmpty())
        return true;
    }
    return false;
  }

  /**
   * Returns {@code true} if {@code attrs} contain
   * a non-alphanumeric string.
   *
   * @param attrs attributes of the relation
   * @return {@code true} if {@code attrs} contain a
   *         non-alphanumeric string
   */
  private boolean attrsContainNonAlphanumericString(List<String> attrs) {
    for (String attr : attrs) {
      for (char c : attr.toCharArray()) {
        if (!Character.isLetterOrDigit(c))
          return true;
      }
    }
    return false;
  }
}
