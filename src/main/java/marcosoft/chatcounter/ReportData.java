package marcosoft.chatcounter;

import marcosoft.chatcounter.model.DayChatCounterTableModel;
import marcosoft.chatcounter.repository.DayChatCounterRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ReportData {

    SystemStrings ss = new SystemStrings();
    SystemComponents sc = new SystemComponents();

    DayChatCounterRepository dayChatCounterRepository = new DayChatCounterRepository();

    public void showWindow(){
        JFrame window = new JFrame(ss.PRODUCT_TILE + " - Report Data");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(650, 500);
        window.setResizable(false);
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



        JScrollPane scrollPane = new JScrollPane(data);

        content.add(scrollPane);

        window.add(content);

        window.setVisible(true);
    }

}