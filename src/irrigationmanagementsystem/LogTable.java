/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package irrigationmanagementsystem;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sandeepa Madhusanka
 */
public class LogTable extends AbstractTableModel{
    private static final String[] columnNames={"Page","Item ID","Item name","Serial No","Item description"};
    private static ArrayList<NewItemDetails> list;

    public LogTable(ArrayList<NewItemDetails> itemList) {
        list=itemList;
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
                return list.get(rowIndex).getPage();
            case 1:
                return list.get(rowIndex).getItemID();
            case 2:
                return list.get(rowIndex).getItem();
            case 3:
                return list.get(rowIndex).getSerialNo();
            case 4:
                return list.get(rowIndex).getItemDescription();
            default:
                return "Error";
        }
    }
}
