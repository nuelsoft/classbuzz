package android.nuel.buzz;

public class Course {
    private String courseTitle;
    private String courseCode;
    private String lecturerName;
    private String lecturerOffice;
    private int lectureUnitLoad;
    private String channelTag;

    public Course(String courseTitle, String courseCode, String lecturerName, String lecturerOffice, int lectureUnitLoad, String channelTag) {
        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
        this.lecturerName = lecturerName;
        this.lecturerOffice = lecturerOffice;
        this.lectureUnitLoad = lectureUnitLoad;
        this.channelTag = channelTag;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public String getLecturerOffice() {
        return lecturerOffice;
    }

    public int getLectureUnitLoad() {
        return lectureUnitLoad;
    }

    public String getChannelTag() {
        return channelTag;
    }

    //This Method is for the purpose of validation, to make sure an Item isn't added
    //  ...twice

    public String compile(){
        String courseCompilation = courseTitle + courseCode + lecturerName +
                 lecturerOffice +  lectureUnitLoad +channelTag;

        return courseCompilation;
    }

}