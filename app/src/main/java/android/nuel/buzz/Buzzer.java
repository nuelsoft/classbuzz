package android.nuel.buzz;

public class Buzzer {
    private String category;
    private String buzzDetail;
    private String buzzTime;
    private String channelTag;

    public Buzzer(String category, String buzzDetail, String buzzTime, String channelTag) {
        this.category = category;
        this.buzzDetail = buzzDetail;
        this.buzzTime = buzzTime;
        this.channelTag = channelTag;
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

    public String getChannelTag() {
        return channelTag;
    }

    //This Method is for the purpose of validation, to make sure an Item isn't added
    //  ...twice

    public String compile(){
        String buzzCompilation = category + buzzDetail + buzzTime + channelTag;

        return buzzCompilation;
    }
}

