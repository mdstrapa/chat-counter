package marcosoft.chatcounter.model;

import java.time.LocalDate;

public class DayChatCounter {
    LocalDate day;
    int simpleChat;
    int boss;
    int difficultChat;
    int notForMe;

    public DayChatCounter(LocalDate day, int simpleChat, int boss, int difficultChat, int notForMe) {
        this.day = day;
        this.simpleChat = simpleChat;
        this.boss = boss;
        this.difficultChat = difficultChat;
        this.notForMe = notForMe;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public int getSimpleChat() {
        return simpleChat;
    }

    public void setSimpleChat(int simpleChat) {
        this.simpleChat = simpleChat;
    }

    public int getBoss() {
        return boss;
    }

    public void setBoss(int boss) {
        this.boss = boss;
    }

    public int getDifficultChat() {
        return difficultChat;
    }

    public void setDifficultChat(int difficultChat) {
        this.difficultChat = difficultChat;
    }

    public int getNotForMe() {
        return notForMe;
    }

    public void setNotForMe(int notForMe) {
        this.notForMe = notForMe;
    }
}
