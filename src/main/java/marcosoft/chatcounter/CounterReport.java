package marcosoft.chatcounter;

import marcosoft.chatcounter.model.DayChatCounter;
import marcosoft.chatcounter.model.ChatCounterReport;

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



    private ChatCounterReport generateReport(){
        List<DayChatCounter> dayChatCounterList = getDayChatCounter();

        //getting total by type
        int totalSimple = 0;
        int totalBoss = 0;
        int totalDifficult = 0;
        int totalNot = 0;
        int totalChats = 0;
        assert dayChatCounterList != null;
        for(DayChatCounter day : dayChatCounterList){
            totalSimple = totalSimple + day.getSimpleChat();
            totalBoss = totalBoss + day.getBoss();
            totalDifficult = totalDifficult + day.getDifficultChat();
            totalNot = totalNot + day.getNotForMe();
            totalChats = totalChats + day.getSimpleChat() + day.getBoss() + day.getDifficultChat() + day.getNotForMe();
        }

        ChatCounterReport report = new ChatCounterReport();

        report.setTotalDays(dayChatCounterList.size());
        report.setTotalChats(totalChats);
        report.setAverage(Math.round((float) totalChats / report.getTotalDays()));
        report.setSimplePercent(Math.round((float) totalSimple / report.getTotalDays()));
        report.setBossPercent(Math.round((float) totalBoss / report.getTotalDays()));
        report.setDifficultPercent(Math.round((float) totalDifficult / report.getTotalDays()));
        report.setNotPercent(Math.round((float) totalNot / report.getTotalDays()));

        return report;

    }

    public void showWindow(){

        ChatCounterReport report = generateReport();

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
        JLabel averageValue = customLabel(report.getAverage());
        JLabel percentDifficultValue = customLabel(report.getDifficultPercent());
        averageLbl.setFont(mainFont);
        percentDifficultLbl.setFont(mainFont);
        averageValue.setFont(mainFont);
        averageValue.setForeground(Color.RED);
        percentDifficultValue.setFont(mainFont);
        percentDifficultValue.setForeground(Color.RED);

        JPanel content = new JPanel(new GridLayout(5,2,0,0));
        content.setBackground(Color.decode(ss.COLOR4));

        JLabel simpleLbl = customLabel("% of Simple Chats");
        JLabel bossLbl = customLabel("% of Boss Chats");
        JLabel notLbl = customLabel("% of Not For Me Chats");
        JLabel totalDaysLbl = customLabel("Total Days");
        JLabel totalChatsLbl = customLabel("Total Chats");

        JLabel simpleValue = customLabel(report.getSimplePercent());
        JLabel bossValue = customLabel(report.getBossPercent());
        JLabel notValue = customLabel(report.getNotPercent());
        JLabel totalDaysValue = customLabel(String.valueOf(report.getTotalDays()));
        JLabel totalChatsValue = customLabel(String.valueOf(report.getTotalChats()));

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

        content.add(totalDaysLbl);
        content.add(totalDaysValue);
        content.add(totalChatsLbl);
        content.add(totalChatsValue);
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
