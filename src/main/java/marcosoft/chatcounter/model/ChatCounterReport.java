package marcosoft.chatcounter.model;

public class ChatCounterReport {
    int average;
    int difficultPercent;
    int simplePercent;
    int bossPercent;
    int notPercent;

    public ChatCounterReport() {
    }

    public ChatCounterReport(int average, int difficultPercent, int simplePercent, int bossPercent, int notPercent) {
        this.average = average;
        this.difficultPercent = difficultPercent;
        this.simplePercent = simplePercent;
        this.bossPercent = bossPercent;
        this.notPercent = notPercent;
    }

    public String getAverage() {
        return String.valueOf(average);
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public String getDifficultPercent() {
        return String.valueOf(difficultPercent) + "%";
    }

    public void setDifficultPercent(int difficultPercent) {
        this.difficultPercent = difficultPercent;
    }

    public String getSimplePercent() {
        return String.valueOf(simplePercent) + "%";
    }

    public void setSimplePercent(int simplePercent) {
        this.simplePercent = simplePercent;
    }

    public String getBossPercent() {
        return String.valueOf(bossPercent) + "%";
    }

    public void setBossPercent(int bossPercent) {
        this.bossPercent = bossPercent;
    }

    public String getNotPercent() {
        return String.valueOf(notPercent) + "%";
    }

    public void setNotPercent(int notPercent) {
        this.notPercent = notPercent;
    }
}
