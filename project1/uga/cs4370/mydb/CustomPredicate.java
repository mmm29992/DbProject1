package uga.cs4370.mydb; 

import java.util.List; 

public class CustomPredicate implements Predicate {
  private int colIndex;
  private Cell valToCompare;
  private String condition;

  public CustomPredicate (int colIndex, Cell valToCompare, String condition)
  {
    this.colIndex = colIndex;
    this.valToCompare = valToCompare;
    this.condition = condition;
  }

@Override
public boolean check(List<Cell> row)
{
  if (colIndex < 0 || colIndex >= row.size())
  {
    throw new IllegalArgumentException("Invalid column index");
  }
  Cell cell = row.get(colIndex);
  if (condition.equals("equals"))
  {
    return cell.equals(valToCompare); 
  } else if (condition.equals("notEquals"))
  {
    return !cell.equals(valToCompare);
  } else 
  {
    throw new IllegalArgumentException("Unsupported condition: " + condition); 
  }
}
}


