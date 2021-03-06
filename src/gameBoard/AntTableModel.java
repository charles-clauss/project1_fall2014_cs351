package gameBoard;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import antworld.data.AntData;
///import controller.Ant;
import controller.AntController;

/**
 * A table model implementation for a list of Ant objects.
 */
public class AntTableModel extends AbstractTableModel
{
  /**
	 * 
	 */
  private static final long serialVersionUID = 4801313980601341505L;
  private static final int COLUMN_ID = 0;
  private static final int COLUMN_Type = 1;
  private static final int COLUMN_X = 2;
  private static final int COLUMN_Y = 3;
  private static final int COLUMN_FOOD = 4;
  private static final int COLUMN_HEALTH = 5;
  private static final int COLUMN_ALIVE = 6;
  // private static final int COLUMN_ = 3;

  private String[] columnNames =
  { "ID", "Type", "X", "Y", "FOOD", "HEALTH", "Alive" };
  private List<AntData> listAnts;

  public AntTableModel()
  {
    // need the right ant controller
  }

  public void getAntList(AntController ac)
  {
    this.listAnts = ac.getAntList();
  }

  @Override
  public int getColumnCount()
  {
    return columnNames.length;
  }

  @Override
  public int getRowCount()
  {
    return listAnts.size();
  }

  @Override
  public String getColumnName(int columnIndex)
  {
    return columnNames[columnIndex];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex)
  {
    if (listAnts.isEmpty())
    {
      return Object.class;
    }
    return getValueAt(0, columnIndex).getClass();
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    AntData Ant = listAnts.get(rowIndex);
    Object returnValue = null;

    switch (columnIndex)
    {
      case COLUMN_ID:
        returnValue = Ant.id;
        break;
      case COLUMN_Type:
        returnValue = Ant.antType;
        break;
      case COLUMN_X:
        returnValue = Ant.gridX;
        break;
      case COLUMN_Y:
        returnValue = Ant.gridY;
        break;
      case COLUMN_FOOD:
        returnValue = Ant.carryType;
        break;
      case COLUMN_HEALTH:
        returnValue = Ant.health;
        break;
      case COLUMN_ALIVE:
        returnValue = Ant.alive;
        break;

      default:
        throw new IllegalArgumentException("Invalid column");
    }

    return returnValue;
  }

  public void addAnt(AntData ant)
  {
    listAnts.add(ant);
    fireTableRowsInserted(listAnts.size() - 1, listAnts.size() - 1);
  }

}