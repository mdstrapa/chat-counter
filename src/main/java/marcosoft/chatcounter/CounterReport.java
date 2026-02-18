package marcosoft.chatcounter;

import javax.swing.*;
import java.awt.*;

public class CounterReport {

    public void showWindow(){

        final SystemStrings ss = new SystemStrings();

        JFrame window = new JFrame(ss.PRODUCT_TILE);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400, 400);
        window.setResizable(false);
        window.setLayout(new GridLayout(2, 1, 0, 0));

        JLabel label = new JLabel("Eu sou um texto");

        window.add(label);

        window.setVisible(true);

    }

}
