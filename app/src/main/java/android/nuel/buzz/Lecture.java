package android.nuel.buzz;

public class Lecture {
    private String courseTitle;
    private String courseCode;
    private String startTime;
    private String endTime;
    private String location;
    private String dayOfTheWeek;
    private int unitLoad;
    private int  lectureDuration;
    private boolean isFixed;


    public Lecture(String courseTitle, String courseCode, String startTime, String endTime, String location,
                   int unitLoad, int lectureDuration, boolean isFixed, String dayOfTheWeek) {
        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.unitLoad = unitLoad;
        this.lectureDuration = lectureDuration;
        this.isFixed = isFixed;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public int getUnitLoad() {
        return unitLoad;
    }

    public int getLectureDuration() {
        return lectureDuration;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}
