package irrigationmanagementsystem;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ReportTable extends AbstractTableModel{
    private static final String[] columnNames={"SBook","Item","Date","Received from","Issued to","NO of way bill","Received quantity","Issued quantity","Balance"};
    private static ArrayList<ItemDetails> list;

    public ReportTable(ArrayList<ItemDetails> newItemList) {
        list=newItemList;
    }
    
    @Override
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
                return list.get(rowIndex).getsBook();
            case 1:
                return list.get(rowIndex).getItem();
            case 2:
                return list.get(rowIndex).getDate();
            case 3:
                return received;
            case 4:
                return  issued;
            case 5:
                return list.get(rowIndex).getNoOfBill();
            case 6:
                return list.get(rowIndex).getRecived();
            case 7:
                return list.get(rowIndex).getIssued();
            case 8:
                return list.get(rowIndex).getBalance();
            default:
                return "Error";
        }
    }
}
