package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.List;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;

/**
 * An implementation of the {@code RA} interface.
 */
public class RAImplementation implements RA {

  /**
   * Performs the select operation on the relation rel
   * by applying the predicate p.
   *
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
}
