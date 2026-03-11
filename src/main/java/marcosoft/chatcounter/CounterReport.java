package marcosoft.chatcounter;

import marcosoft.chatcounter.model.DayChatCounter;
import marcosoft.chatcounter.model.ChatCounterReport;
import marcosoft.chatcounter.repository.DayChatCounterRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CounterReport {

    final SystemStrings ss = new SystemStrings();
    final SystemComponents sc = new SystemComponents();

    DayChatCounterRepository dayChatCounterRepository = new DayChatCounterRepository();

    private ChatCounterReport generateReport(){
        List<DayChatCounter> dayChatCounterList = dayChatCounterRepository.getDayChatCounterList();

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

        JFrame window = new JFrame(ss.PRODUCT_TILE + " - Report");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(450, 500);
        window.setResizable(false);
        window.setLayout(new GridLayout(4, 1, 0, 0));

        JPanel header = new JPanel(new GridLayout(1,1,0,0));
        header.setBackground(Color.decode(ss.COLOR2));
        JLabel headerLbl = sc.customLabel("Chats Counter Report");
        headerLbl.setFont(sc.headerFont());;
        header.add(headerLbl);

        JPanel main = new JPanel(new GridLayout(2,2,0,0));
        main.setBackground(Color.decode(ss.COLOR3));

        JLabel averageLbl = sc.customLabel("Average Chats Per Day");
        JLabel percentDifficultLbl = sc.customLabel("% of Difficult Questions");
        JLabel averageValue = sc.customLabel(report.getAverage());
        JLabel percentDifficultValue = sc.customLabel(report.getDifficultPercent());
        averageLbl.setFont(sc.mainFont());
        percentDifficultLbl.setFont(sc.mainFont());
        averageValue.setFont(sc.mainFont());
        averageValue.setForeground(Color.RED);
        percentDifficultValue.setFont(sc.mainFont());
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

        simpleLbl.setFont(sc.contentFont());
        bossLbl.setFont(sc.contentFont());
        notLbl.setFont(sc.contentFont());
        simpleValue.setFont(sc.contentFont());
        bossValue.setFont(sc.contentFont());
        notValue.setFont(sc.contentFont());


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

        JPanel footer = new JPanel(new GridLayout(1,1,0,0));

        JButton showDataBtn = new JButton(ss.BTN_SHOW_REPORT_DATA);

        footer.add(showDataBtn);

        window.add(footer);

        window.setVisible(true);

        showDataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ReportData rd = new ReportData();
               rd.showWindow();
            }
        });


    }

}
