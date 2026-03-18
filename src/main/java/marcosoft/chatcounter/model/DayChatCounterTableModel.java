package marcosoft.chatcounter.model;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DayChatCounterTableModel extends AbstractTableModel {

    private final String[] columns = {"Day", "Simple Chat", "Boss ", "Difficult Chat", "No Action", "Total"};

    private final List<DayChatCounter> dayChatCounterList;

    public DayChatCounterTableModel(List<DayChatCounter> dayChatCounterList) {
        this.dayChatCounterList = dayChatCounterList;
    }

    @Override
    public int getRowCount() {
        return dayChatCounterList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DayChatCounter d = dayChatCounterList.get(rowIndex);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(columnIndex==0) return d.getDay().format(formatter);
        if(columnIndex==1) return d.getSimpleChat();
        if(columnIndex==2) return d.getBoss();
        if(columnIndex==3) return d.getDifficultChat();
        if(columnIndex==4) return d.getNotForMe();
        if(columnIndex==5) return d.getSimpleChat() + d.getBoss() + d.getDifficultChat() + d.getNotForMe();
        return null;

    }
}
