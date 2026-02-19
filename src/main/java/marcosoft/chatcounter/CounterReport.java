package marcosoft.chatcounter;

import marcosoft.chatcounter.model.DayChatCounter;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class CounterReport {

    final SystemStrings ss = new SystemStrings();

    private JLabel customLabel(String value){
        return new JLabel(value,SwingConstants.CENTER);
    }

    private List<DayChatCounter> getDayChatCounter(){
        List<DayChatCounter> dayChatCounterList = new ArrayList<>();
        Path filePath = Path.of(ss.DATABASE_TEXT_FILE);
        try{
            List<String> lines = Files.readAllLines(filePath);

            for(String line : lines){
                String[] lineContent = line.split(";");
                dayChatCounterList.add(new DayChatCounter(LocalDate.parse(lineContent[0]),
                        Integer.parseInt(lineContent[1]),
                        Integer.parseInt(lineContent[2]),
                        Integer.parseInt(lineContent[3]),
                        Integer.parseInt(lineContent[4])));
            }

            return dayChatCounterList;

        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    //generateReport(){

    //}/

    public void showWindow(){

        Font headerFont = new Font("Arial", Font.BOLD,25);
        Font mainFont = new Font("Arial", Font.BOLD,18);
        Font contentFont = new Font("Arial", Font.BOLD,12);

        JFrame window = new JFrame(ss.PRODUCT_TILE);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(450, 400);
        window.setResizable(false);
        window.setLayout(new GridLayout(3, 1, 0, 0));

        JPanel header = new JPanel(new GridLayout(1,1,0,0));
        header.setBackground(Color.decode(ss.COLOR2));
        JLabel headerLbl = customLabel("Chats Counter Report");
        headerLbl.setFont(headerFont);;
        header.add(headerLbl);

        JPanel main = new JPanel(new GridLayout(2,2,0,0));
        main.setBackground(Color.decode(ss.COLOR3));

        JLabel averageLbl = customLabel("Average Chats Per Day");
        JLabel percentDifficultLbl = customLabel("% of Difficult Chats");
        JLabel averageValue = customLabel("36");
        JLabel percentDifficultValue = customLabel("%11");
        averageLbl.setFont(mainFont);
        percentDifficultLbl.setFont(mainFont);
        averageValue.setFont(mainFont);
        averageValue.setForeground(Color.RED);
        percentDifficultValue.setFont(mainFont);
        percentDifficultValue.setForeground(Color.RED);

        JPanel content = new JPanel(new GridLayout(3,2,0,0));
        content.setBackground(Color.decode(ss.COLOR4));

        JLabel simpleLbl = customLabel("% of Simple Chats");
        JLabel bossLbl = customLabel("% of Boss Chats");
        JLabel notLbl = customLabel("% of Not For Me Chats");

        JLabel simpleValue = customLabel("%40");
        JLabel bossValue = customLabel("%11");
        JLabel notValue = customLabel("%38");

        simpleLbl.setFont(contentFont);
        bossLbl.setFont(contentFont);
        notLbl.setFont(contentFont);
        simpleValue.setFont(contentFont);
        bossValue.setFont(contentFont);
        notValue.setFont(contentFont);


        main.add(averageLbl);
        main.add(percentDifficultLbl);
        main.add(averageValue);
        main.add(percentDifficultValue);

        content.add(simpleLbl);
        content.add(simpleValue);
        content.add(bossLbl);
        content.add(bossValue);
        content.add(notLbl);
        content.add(notValue);


        window.add(header);
        window.add(main);
        window.add(content);

        window.setVisible(true);

    }

}
