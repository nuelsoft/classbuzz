package android.nuel.buzz;

import java.util.ArrayList;

public class Channel {
    private String channelName;
    private String channelTag;
    private int channelMembers;
    private String channelLocation;
    private int currentUnseenNotifications;

    public ArrayList<Lecture> channelLectures;
    public ArrayList<Buzzer> channelBuzzes;
    public ArrayList<Course> channelCourses;

    private boolean isTaken = false;
    private boolean admin = true;

    public boolean isAdmin() {
        return admin;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public boolean isTaken() {

        return isTaken;
    }

    public Channel(String channelName, String channelTag, int channelMembers, String channelLocation, int currentUnseenNotifications) {
        this.channelName = channelName;
        this.channelTag = channelTag;
        this.channelMembers = channelMembers;
        this.channelLocation = channelLocation;
        this.currentUnseenNotifications = currentUnseenNotifications;

        this.channelLectures = new ArrayList<Lecture>();
        this.channelBuzzes = new ArrayList<Buzzer>();
        this.channelCourses = new ArrayList<Course>();
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelTag() {
        return channelTag;
    }

    public int getChannelMembers() {
        return channelMembers;
    }

    public String getChannelLocation() {
        return channelLocation;
    }

    public int getCurrentUnseenNotifications() {
        return currentUnseenNotifications;
    }
}
