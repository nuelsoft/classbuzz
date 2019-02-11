package android.nuel.buzz;

import android.support.annotation.Nullable;

public class Buzzer {
    private String category;
    private String buzzDetail;
    private String buzzTime;
    private String channelTag;

    private String author;
    private String newsTitle;

    public Buzzer(String category, String buzzDetail, String buzzTime, String channelTag, @Nullable String author, @Nullable String newsTitle) {
        this.category = category;
        this.buzzDetail = buzzDetail;
        this.buzzTime = buzzTime;
        this.channelTag = channelTag;
        this.author = author;
        this.newsTitle = newsTitle;
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


    public String getAuthor() {
        return author;
    }

    public String getNewsTitle() {
        return newsTitle;
    }
    //This Method is for the purpose of validation, to make sure an Item isn't added
    //  ...twice


    public String compile(){
        String buzzCompilation = category + buzzDetail + buzzTime + channelTag;

        return buzzCompilation;
    }
}

