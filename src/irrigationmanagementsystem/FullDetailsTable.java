
package irrigationmanagementsystem;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class FullDetailsTable extends AbstractTableModel{
    private static final String[] columnNames={"Item","Date","Received from","Issued to","NO of way bill","Received quantity","Issued quantity","Balance","Order no","Order code","Storehouse no","Rack&row no"};
    private static ArrayList<ItemDetails> list;

    public FullDetailsTable(ArrayList<ItemDetails> newItemList) {
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
        String received=null;
        String issued=null;
        int x=list.get(rowIndex).getRecived();
        if(x==0){
            issued=list.get(rowIndex).getRecivedOrIssued();
        }else{
            received=list.get(rowIndex).getRecivedOrIssued();
        }
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getItem();
            case 1:
                return list.get(rowIndex).getDate();
            case 2:
                return received;
            case 3:
                return  issued;
            case 4:
                return list.get(rowIndex).getNoOfBill();
            case 5:
                return list.get(rowIndex).getRecived();
            case 6:
                return list.get(rowIndex).getIssued();
            case 7:
                return list.get(rowIndex).getBalance();
            case 8:
                return list.get(rowIndex).getOrderCode();
            case 9:
                return list.get(rowIndex).getOrderNo();
            case 10:
                return list.get(rowIndex).getStorehouseNo();
            case 11:
                return list.get(rowIndex).getRackNo();
            default:
                return "Error";
        }
    }
}
