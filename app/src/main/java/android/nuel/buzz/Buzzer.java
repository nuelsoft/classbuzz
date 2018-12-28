package android.nuel.buzz;

public class Buzzer {
    private String category;
    private String buzzDetail;
    private String buzzTime;

    public Buzzer(String category, String buzzDetail, String buzzTime) {
        this.category = category;
        this.buzzDetail = buzzDetail;
        this.buzzTime = buzzTime;
    }

    public String getCategory() {
        return category;
    }

    public String getBuzzDetail() {
        return buzzDetail;
    }

    public String getBuzzTime() {
        return buzzTime;
    }
}

