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
    final SystemComponents sc = new SystemComponents();

    private List<DayChatCounter> getDayChatCounter(){
        List<DayChatCounter> dayChatCounterList = new ArrayList<>();
        Path filePath = Path.of(ss.DATABASE_TEXT_FILE);
        try{
            List<String> lines = Files.readAllLines(filePath);

            for(String line : lines){
                String[] lineContent = line.split(";");

                // before adding the new day to the day list
                // we have to check if it already exists
                // if so, we first have to remove it
                // and them adding the new version

                if(!dayChatCounterList.isEmpty() && dayChatCounterList.get(dayChatCounterList.size() - 1).getDay().equals(LocalDate.parse(lineContent[0]))){
                    dayChatCounterList.remove(dayChatCounterList.size() - 1);
                }

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
        report.setSimplePercent(Math.round(((float) totalSimple / report.getTotalChats()) * 100));
        report.setBossPercent(Math.round(((float) totalBoss / report.getTotalChats()) * 100));
        report.setDifficultPercent(Math.round(((float) totalDifficult / report.getTotalChats()) * 100));
        report.setNotPercent(Math.round(((float) totalNot / report.getTotalChats()) * 100));

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
        JLabel headerLbl = sc.customLabel("Chats Counter Report");
        headerLbl.setFont(headerFont);;
        header.add(headerLbl);

        JPanel main = new JPanel(new GridLayout(2,2,0,0));
        main.setBackground(Color.decode(ss.COLOR3));

        JLabel averageLbl = sc.customLabel("Average Chats Per Day");
        JLabel percentDifficultLbl = sc.customLabel("% of Difficult Questions");
        JLabel averageValue = sc.customLabel(report.getAverage());
        JLabel percentDifficultValue = sc.customLabel(report.getDifficultPercent());
        averageLbl.setFont(mainFont);
        percentDifficultLbl.setFont(mainFont);
        averageValue.setFont(mainFont);
        averageValue.setForeground(Color.RED);
        percentDifficultValue.setFont(mainFont);
        percentDifficultValue.setForeground(Color.RED);

        JPanel content = new JPanel(new GridLayout(5,2,0,0));
        content.setBackground(Color.decode(ss.COLOR4));

        JLabel simpleLbl = sc.customLabel("% of Simple Chats");
        JLabel bossLbl = sc.customLabel("% of Boss Chats");
        JLabel notLbl = sc.customLabel("% of No Action");
        JLabel totalDaysLbl = sc.customLabel("Total Days");
        JLabel totalChatsLbl = sc.customLabel("Total Chats");

        JLabel simpleValue = sc.customLabel(report.getSimplePercent());
        JLabel bossValue = sc.customLabel(report.getBossPercent());
        JLabel notValue = sc.customLabel(report.getNotPercent());
        JLabel totalDaysValue = sc.customLabel(String.valueOf(report.getTotalDays()));
        JLabel totalChatsValue = sc.customLabel(String.valueOf(report.getTotalChats()));

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
