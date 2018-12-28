package android.nuel.buzz;

public class Course {
    private String courseTitle;
    private String courseCode;
    private String lecturerName;
    private String lecturerOffice;
    private int lectureUnitLoad;

    public Course(String courseTitle, String courseCode, String lecturerName, String lecturerOffice, int lectureUnitLoad) {
        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
        this.lecturerName = lecturerName;
        this.lecturerOffice = lecturerOffice;
        this.lectureUnitLoad = lectureUnitLoad;
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
}