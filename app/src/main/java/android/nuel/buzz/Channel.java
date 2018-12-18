package android.nuel.buzz;

public class Channel {
    private String channelName;
    private String channelTag;
    private int channelMembers;
    private String channelLocation;
    private int currentUnseenNotifications;

    public Channel(String channelName, String channelTag, int channelMembers, String channelLocation, int currentUnseenNotifications) {
        this.channelName = channelName;
        this.channelTag = channelTag;
        this.channelMembers = channelMembers;
        this.channelLocation = channelLocation;
        this.currentUnseenNotifications = currentUnseenNotifications;
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
