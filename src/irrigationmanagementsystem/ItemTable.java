
package irrigationmanagementsystem;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sandeepa Madhusanka
 */
public class ItemTable extends AbstractTableModel{
    
    
    private static final String[] columnNames={"S/Book","Page","Item ID","Item name","Serial No","Item description"};
    private static ArrayList<NewItemDetails> list;

    public ItemTable(ArrayList<NewItemDetails> newItemList) {
        list=newItemList;
    }
    
    public String getColumnName(int columnIndex){
        return columnNames[columnIndex];
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getsBook();
            case 1:
                return list.get(rowIndex).getPage();
            case 2:
                return list.get(rowIndex).getItemID();
            case 3:
                return list.get(rowIndex).getItem();
            case 4:
                return list.get(rowIndex).getSerialNo();
            case 5:
                return list.get(rowIndex).getItemDescription();
            default:
                return "Error";
        }
    }
    
}
