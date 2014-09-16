package gameBoard;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import antworld.data.AntAction;
import antworld.data.AntType;
import antworld.data.FoodType;
import antworld.data.NestNameEnum;
import antworld.data.TeamNameEnum;
import controller.Ant;
 
/**
 * A table model implementation for a list of Ant objects.
 * 
 */
public class AntTableModel extends AbstractTableModel {
    private static final int COLUMN_ID      = 0;
    private static final int COLUMN_Type    = 1;
    private static final int COLUMN_X     = 2;
    private static final int COLUMN_Y     = 3;
    private static final int COLUMN_FOOD     = 4;
    private static final int COLUMN_HEALTH     = 5;
    private static final int COLUMN_ALIVE     = 6;
//    private static final int COLUMN_     = 3;


     
    private String[] columnNames = 
      { "ID", "Type", "X", "Y", "FOOD", "HEALTH", "Alive" };
    private List<Ant> listAnts;


    
    /*public AntAction nextAction;
    public NestNameEnum nest;
    public TeamNameEnum team;
    public AntType antType;
    public int id;
    public int xPos, yPos;
    public FoodType carryType;
    public int carryUnits;
    public int ticksUntilNextAction;
    public int health;
    public boolean underground = true;
    public boolean alive = true;
    */
    
    public AntTableModel() {
      // need the right ant controller
        this.listAnts = AntController.getAntList();
         
        
    }
 
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount() {
        return listAnts.size();
    }
     
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
     
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (listAnts.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ant Ant = listAnts.get(rowIndex);
        Object returnValue = null;
         
        switch (columnIndex) {
        case COLUMN_ID:
            returnValue = Ant.id;
            break;
        case COLUMN_Type:
            returnValue = Ant.antType;
            break;
        case COLUMN_X:
            returnValue = Ant.xPos;
            break;
        case COLUMN_Y:
            returnValue = Ant.yPos;
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
    
    public void addAnt(Ant ant){
      listAnts.add(ant);
      fireTableRowsInserted(listAnts.size()-1, listAnts.size()-1);
    }
     
    
 
}