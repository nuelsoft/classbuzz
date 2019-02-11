package android.nuel.buzz;

import android.support.annotation.Nullable;

import java.util.ArrayList;

public class ResourceBox {

    public static ArrayList<Channel> ChannelResource = new ArrayList<>();



    public  void instantiateChannel(){

        addChannel(new Channel("Human Anatomy 022", "HAN_022", 159,
                "University Of California", 1));

        addChannel(new Channel("Computer Science 021", "CS_021", 149,
                "University Of Nigeria", 5));

        addChannel(new Channel("Psychology 024","PSY_023", 0,
                "University Of Ibadan",0));


        instantiateChannelLecture();
        instantiateChannelCourses();
        instantiateChannelBuzz();
    }
    
    private void instantiateChannelLecture(){
        
        
        addLecture("Introduction to Life", "LFE101", "12:00",
                "14:00", "FPSLT", 2, 2, false, "mon", "CS_021");
        addLecture("Introduction to Programming", "COS101", "12:00",
                "14:00", "FPSLT", 3, 2, true, "sat", "HAN_022");
        addLecture("Introduction to Life", "LFE101", "12:00",
                "16:00", "Carver Building", 4, 4, true, "sun", "CS_021");
        addLecture("General Studies", "GS101", "12:00",
                "14:00", "GS Building", 2, 2, false, "wed", "HAN_022");
        addLecture("Electronics Functions", "EFI208", "9:00",
                "10:00", "New Engineering Annex", 1, 1, false, "thu", "HAN_022");



    }
    
    private void instantiateChannelCourses(){

        addCourse("Introduction to Programming II", "COS207",
                "Mr Ojukwu Anele John", "Room 311 Abuja Building", 4, "CS_021");
        addCourse("Agricultural Practices III", "AGR251",
                "Mrs Elizabeth Macbeth", "Room 307 Carver Building", 2, "HAN_022");
        addCourse("General Studies Programme I", "GSP201",
                "Mr Stephen Pius", "Room 101 GS Building", 1, "CS_021");
        addCourse("Physical Matter Properties and Nature I", "GSP205",
                "Mr Odemegwu Titus Paul", "Room 307 GS Building", 5, "CS_021");
        addCourse("Introduction to Philosophy", "PHY211",
                "Miss Nnenna Helen", "Room 120 Arts Complex", 3, "CS_021");
        addCourse("Introduction to Programming III", "COS211",
                "Mrs Amarachi Omemonu", "Room 300 Abuja Building", 5, "CS_021");
        addCourse("Introduction to Python II", "COS301",
                "Mr Peters Jone John", "Room 400 Carver Building", 4, "HAN_022");

    }
    private void instantiateChannelBuzz(){


        addBuzz("cancel", "COS101 scheduled to hold on Monday has been canceled",
                "19:04", "CS_021", null, null);
        addBuzz("birthday", "It's Bright Michael's Birthday today! Wish him well",
                "19:50", "CS_021", null, null);
        addBuzz("fixed", "GS207 fixed class to hold this Monday, in the New Engineering Annex by 12:00",
                "20:20", "CS_021", null, null);
        addBuzz("other", "Compulsory Class Meeting scheduled to hold on the 25th of August, under the Mango Tree",
                " 23:00", "HAN_022", null, null);

    }


    private void addLecture(String courseTitle, String courseCode, String startTime,
                           String endTime, String location, int unitLoad, int lectureDuration,
                           boolean isFixed, String dayOfWeek, String channelTag) {

        Lecture newLecture = new Lecture(courseTitle, courseCode, startTime,
                endTime, location, unitLoad, lectureDuration, isFixed, dayOfWeek, channelTag);

        for (int i = 0; i < ChannelResource.size(); i++) {
            if (ChannelResource.get(i).getChannelTag() == channelTag){
                ChannelResource.get(i).channelLectures.add(newLecture);
            }
        }
        
        
    }

    private void addCourse(String courseTitle, String courseCode, String lecturerName, String lecturerOffice,
                          int lectureUnitLoad, String channelTag) {

        Course newCourse = new Course(courseTitle, courseCode, lecturerName,
                lecturerOffice, lectureUnitLoad, channelTag);

        for (int i = 0; i < ChannelResource.size(); i++) {
            if (ChannelResource.get(i).getChannelTag() == channelTag){
                ChannelResource.get(i).channelCourses.add(newCourse);
            }
        }
    }

    public void addBuzz(String category, String buzzDetail, String buzzTime, String channelTag, @Nullable String author, @Nullable String newsTitle) {

        Buzzer newBuzz = new Buzzer(category, buzzDetail, buzzTime, channelTag, author, newsTitle);

        for (int i = 0; i < ChannelResource.size(); i++) {
            if (ChannelResource.get(i).getChannelTag() == channelTag){

                if (ChannelResource.get(i).channelBuzzes.size() == 0) {
                    ChannelResource.get(i).channelBuzzes.add(newBuzz);
                } else {
                    ArrayList<Buzzer> tempBuzz = new ArrayList<>();

                    for (int j = 0; j < ChannelResource.get(i).channelBuzzes.size(); j++) {
                        tempBuzz.add(ChannelResource.get(i).channelBuzzes.get(j));
                    }

                    ChannelResource.get(i).channelBuzzes = new ArrayList<>();

                    ChannelResource.get(i).channelBuzzes.add(newBuzz);
                    for (int k = 0; k < tempBuzz.size(); k++) {
                        ChannelResource.get(i).channelBuzzes.add(tempBuzz.get(k));
                    }
                }



            }
        }

    }

    public void addChannel(Channel channel){
        if (ChannelResource.size() == 0){
            ChannelResource.add(channel);
        }else{
             ArrayList<Channel> tempChannel = new ArrayList<>();

            for (int i = 0; i < ChannelResource.size(); i++) {
                tempChannel.add(ChannelResource.get(i));
            }

            ChannelResource = new ArrayList<>();

            ChannelResource.add(channel);
            for (int i = 0; i < tempChannel.size(); i++) {
                ChannelResource.add(tempChannel.get(i));
            }
        }
    }
    
}
