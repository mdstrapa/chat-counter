package marcosoft.chatcounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.IOException;


public class App {

    final static SystemStrings ss = new SystemStrings();

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        final SystemComponents sc = new SystemComponents();

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame window = new JFrame(ss.PRODUCT_TILE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(700, 150);
        window.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - window.getWidth() - 10);
        int y = (screenSize.height - window.getHeight() - 55);
        window.setLocation(x, y);

        window.setLayout(new GridLayout(3, 1, 0, 0));

        JPanel panelItems = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JPanel panelTotal = new JPanel(new GridLayout(1,10,0,0));

        Font bigFont = new Font("Arial", Font.BOLD,18);

        JLabel simpleLbl = new JLabel("Simple Question:");
        simpleLbl.setBackground(Color.decode(ss.COLOR1));
        simpleLbl.setOpaque(true);
        JLabel bossLbl = new JLabel("Boss:");
        bossLbl.setBackground(Color.decode(ss.COLOR2));
        bossLbl.setOpaque(true);
        JLabel difficultLbl = new JLabel("Difficult Question:");
        difficultLbl.setBackground(Color.decode(ss.COLOR3));
        difficultLbl.setOpaque(true);
        JLabel notLbl = new JLabel("No Action:");
        notLbl.setBackground(Color.decode(ss.COLOR4));
        notLbl.setOpaque(true);
        JLabel totalLbl = new JLabel("Total:");

        JLabel simpleValue = new JLabel("0");
        simpleValue.setBackground(Color.decode(ss.COLOR1));
        simpleValue.setOpaque(true);
        JLabel bossValue = new JLabel("0");
        bossValue.setBackground(Color.decode(ss.COLOR2));
        bossValue.setOpaque(true);
        JLabel difficultValue = new JLabel("0");
        difficultValue.setBackground(Color.decode(ss.COLOR3));
        difficultValue.setOpaque(true);
        JLabel notValue = new JLabel("0");
        notValue.setBackground(Color.decode(ss.COLOR4));
        notValue.setOpaque(true);
        JLabel totalValue = new JLabel("0");
        totalValue.setForeground(Color.RED);
        JLabel currentDate = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        currentDate.setFont(bigFont);
        totalValue.setFont(bigFont);
        totalLbl.setFont(bigFont);

        JButton simpleBtn = new JButton("+");
        JButton bossBtn = new JButton("+");
        JButton difficultBtn = new JButton("+");
        JButton notBtn = new JButton("+");
        JButton saveBtn = new JButton("Save");
        JButton reportBtn = new JButton("Report");

        JPanel panelSave = new JPanel(new GridLayout(1,2,0,0));
        JLabel lastSavedLbl = sc.customLabel("Last saved: - ");
        JLabel hasChangesLbl = sc.customLabel(ss.NO_CHAGES);

        panelSave.add(lastSavedLbl);
        panelSave.add(hasChangesLbl);

        panelItems.add(simpleLbl,gbc);
        panelItems.add(simpleValue,gbc);
        panelItems.add(simpleBtn,gbc);

        panelItems.add(bossLbl,gbc);
        panelItems.add(bossValue,gbc);
        panelItems.add(bossBtn,gbc);

        panelItems.add(difficultLbl,gbc);
        panelItems.add(difficultValue,gbc);
        panelItems.add(difficultBtn,gbc);

        panelItems.add(notLbl,gbc);
        panelItems.add(notValue,gbc);
        panelItems.add(notBtn,gbc);
        panelItems.setBackground(Color.WHITE);
        panelItems.setOpaque(true);

        panelTotal.add(currentDate);

        for(int c = 0;c<2;c++){
            panelTotal.add(new JLabel());
        }

        panelTotal.setBackground(Color.WHITE);
        panelTotal.setOpaque(true);
        panelTotal.add(totalLbl);
        panelTotal.add(totalValue);
        panelTotal.add(saveBtn);
        panelTotal.add(reportBtn);

        window.add(panelTotal);
        window.add(panelItems);
        window.add(panelSave);

        simpleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markChange(hasChangesLbl);
                increaseValue(simpleValue);
                increaseValue(totalValue);
            }
        });

        bossBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markChange(hasChangesLbl);
                increaseValue(bossValue);
                increaseValue(totalValue);
            }
        });

        difficultBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markChange(hasChangesLbl);
                increaseValue(difficultValue);
                increaseValue(totalValue);
            }
        });

        notBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markChange(hasChangesLbl);
                increaseValue(notValue);
                increaseValue(totalValue);
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Path filePath = Path.of(ss.DATABASE_TEXT_FILE);

                String currentDay = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                String newContent = "\n"
                        + currentDay + ";"
                        + simpleValue.getText() + ";" + bossValue.getText() + ";" + difficultValue.getText() + ";" + notValue.getText();

                try{

                    Files.write(filePath,newContent.getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
                    JOptionPane.showMessageDialog(null,"Data recorded to file " + filePath.getFileName(),"Information",JOptionPane.INFORMATION_MESSAGE);
                    hasChangesLbl.setText(ss.NO_CHAGES);
                    hasChangesLbl.setForeground(Color.BLACK);
                    lastSavedLbl.setText("Last saved: " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
                }catch (IOException ex){
                    ex.printStackTrace();
                }

            }
        });

        reportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CounterReport cr = new CounterReport();
                cr.showWindow();
            }
        });

        window.setVisible(true);
    }

    private static void increaseValue(JLabel labelValue){
        int value = Integer.parseInt(labelValue.getText());
        value++;
        labelValue.setText(Integer.toString(value));
    }

    private static void markChange(JLabel hasChangesLbl){
        hasChangesLbl.setText(ss.UNSAVED_CHANGES);
        hasChangesLbl.setForeground(Color.RED);
    }

}
