import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class App {

    public static void main(String[] args) {

        final String color1 = "#966b9d";
        final String color2 = "#c98686";
        final String color3 = "#f2b880";
        final String color4 = "#fff4ec";

        JFrame window = new JFrame("Marcosoft - Chat Counter");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(700, 120);
        window.setResizable(false);
        window.setLayout(new GridLayout(2, 1, 0, 0));

        JPanel panelItems = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JPanel panelTotal = new JPanel(new GridLayout(1,10,0,0));

        JLabel simpleLbl = new JLabel("Simple Question:");
        simpleLbl.setBackground(Color.decode(color1));
        simpleLbl.setOpaque(true);
        JLabel bossLbl = new JLabel("Boss:");
        bossLbl.setBackground(Color.decode(color2));
        bossLbl.setOpaque(true);
        JLabel difficultLbl = new JLabel("Difficult Things:");
        difficultLbl.setBackground(Color.decode(color3));
        difficultLbl.setOpaque(true);
        JLabel notLbl = new JLabel("Not For Me:");
        notLbl.setBackground(Color.decode(color4));
        notLbl.setOpaque(true);
        JLabel totalLbl = new JLabel("Total:");

        JLabel simpleValue = new JLabel("0");
        simpleValue.setBackground(Color.decode(color1));
        simpleValue.setOpaque(true);
        JLabel bossValue = new JLabel("0");
        bossValue.setBackground(Color.decode(color2));
        bossValue.setOpaque(true);
        JLabel difficultValue = new JLabel("0");
        difficultValue.setBackground(Color.decode(color3));
        difficultValue.setOpaque(true);
        JLabel notValue = new JLabel("0");
        notValue.setBackground(Color.decode(color4));
        notValue.setOpaque(true);
        JLabel totalValue = new JLabel("0");
        totalValue.setForeground(Color.RED);
        JLabel currentDate = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        JButton simpleBtn = new JButton("+");
        JButton bossBtn = new JButton("+");
        JButton difficultBtn = new JButton("+");
        JButton notBtn = new JButton("+");
        JButton saveBtn = new JButton("Save");

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

        for(int c = 0;c<6;c++){
            panelTotal.add(new JLabel());
        }

        panelTotal.setBackground(Color.WHITE);
        panelTotal.setOpaque(true);
        panelTotal.add(totalLbl);
        panelTotal.add(totalValue);
        panelTotal.add(saveBtn);

        window.add(panelTotal);
        window.add(panelItems);


        simpleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseValue(simpleValue);
                increaseValue(totalValue);
            }
        });

        bossBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseValue(bossValue);
                increaseValue(totalValue);
            }
        });

        difficultBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseValue(difficultValue);
                increaseValue(totalValue);
            }
        });

        notBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseValue(notValue);
                increaseValue(totalValue);
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Path filePath = Path.of("C:\\Marcosoft\\chat-counter\\Chat-Counter.txt");

                String newContent = "\n"
                        + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ";"
                        + simpleValue.getText() + ";" + bossValue.getText() + ";" + difficultValue.getText() + ";" + notValue.getText();

                try{
                    Files.write(filePath,newContent.getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
                    JOptionPane.showMessageDialog(null,"Data recorded to file " + filePath.getFileName(),"Information",JOptionPane.INFORMATION_MESSAGE);
                }catch (IOException ex){
                    ex.printStackTrace();
                }

            }
        });

        window.setVisible(true);
    }

    private static void increaseValue(JLabel labelValue){
        int value = Integer.parseInt(labelValue.getText());
        value++;
        labelValue.setText(Integer.toString(value));
    }

}
