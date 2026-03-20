package marcosoft.chatcounter;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class SystemComponents {

    public JLabel customLabel(String value){
        return new JLabel(value,SwingConstants.CENTER);
    }

    public Font headerFont(){
        return new Font("Arial", Font.BOLD,25);
    }

    public Font tableHeaderFont(){
        return new Font("Arial", Font.BOLD,12);
    }

    public Font mainFont(){
        return new Font("Arial", Font.BOLD,18);
    }

    public Font contentFont(){
        return new Font("Arial", Font.PLAIN,12);
    }
}
