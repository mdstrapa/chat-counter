package marcosoft.chatcounter.forms;

import marcosoft.chatcounter.config.SystemComponents;
import marcosoft.chatcounter.config.SystemStrings;
import marcosoft.chatcounter.model.DayChatCounterTableModel;
import marcosoft.chatcounter.repository.DayChatCounterRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ReportData {

    final SystemStrings ss = new SystemStrings();
    final SystemComponents sc = new SystemComponents();

    final DayChatCounterRepository dayChatCounterRepository = new DayChatCounterRepository();

    public void showWindow(){
        JFrame window = new JFrame(ss.PRODUCT_TILE + " - Report Data");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(650, 500);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setLayout(new GridLayout(1, 1, 0, 0));

        JPanel content = new JPanel(new GridLayout(1,1,0,0));

        DayChatCounterTableModel tableModel = new DayChatCounterTableModel(dayChatCounterRepository.getDayChatCounterList());
        JTable data = new JTable(tableModel);

        JTableHeader header = data.getTableHeader();
        header.setFont(sc.tableHeaderFont());
        Dimension headerDimension = header.getPreferredSize();
        header.setPreferredSize(new Dimension(headerDimension.width, 40));
        header.revalidate();
        header.repaint();

        data.setRowHeight(30);
        data.setFont(sc.contentFont());
        data.setGridColor(Color.LIGHT_GRAY);
        data.setShowVerticalLines(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        data.setDefaultRenderer(Object.class, centerRenderer);

        TableColumn totalColumn = data.getColumnModel().getColumn(5);

        DefaultTableCellRenderer totalColumnRenderer = new DefaultTableCellRenderer();
        totalColumnRenderer.setBackground(Color.decode(ss.COLOR2));
        totalColumnRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        totalColumn.setCellRenderer(totalColumnRenderer);

        JScrollPane scrollPane = new JScrollPane(data);

        content.add(scrollPane);

        window.add(content);

        window.setVisible(true);
    }

}