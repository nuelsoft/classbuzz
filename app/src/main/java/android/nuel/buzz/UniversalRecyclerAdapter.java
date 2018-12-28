package android.nuel.buzz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UniversalRecyclerAdapter extends RecyclerView.Adapter<UniversalRecyclerAdapter.ViewHolder> {

    private ArrayList<Channel> Channels = new ArrayList<>();
    Context channelContext;

    private ArrayList<Lecture> lectureSchedules = new ArrayList<>();
    private ArrayList<Lecture> lecturesInDay = new ArrayList<>();

    private ArrayList<Course> Courses = new ArrayList<>();

    private ArrayList<Buzzer> Buzzers = new ArrayList<>();

    String filter;


    public class ViewHolder extends RecyclerView.ViewHolder {
        //ChannelFragmentDeclaration
        public TextView channelName;
        public TextView channelTag;
        public TextView channelMembers;
        public TextView currentNewNotifications;
        public TextView channelLocation;
        public View layout;

        //LectureFragmentDeclaration
        public TextView courseTitle;
        public TextView courseCode;
        public TextView duration;
        public TextView timeDifference;
        public TextView isFixed;
        public TextView location;
        public TextView unitLoad;

        //CourseFragmentDeclaration
        public TextView courses_courseTitle;
        public TextView courses_courseCode;
        public TextView lecturerName;
        public TextView lecturerOffice;
        public TextView courseUnitLoad;

        //BuzzFragmentDeclaration
        public TextView buzzDescription;
        public TextView buzzTime;
        public ImageView buzzCategory;

        public ViewHolder(View v) {
            super(v);
            //channelFragmentInstantiate
            layout = v;
            channelName = v.findViewById(R.id.channelName);
            channelTag = v.findViewById(R.id.channelTag);
            channelLocation = v.findViewById(R.id.channelLocation);
            channelMembers = v.findViewById(R.id.membership);
            currentNewNotifications = v.findViewById(R.id.newNotifications);

            //LectureFragmentInstantiate
            courseTitle = v.findViewById(R.id.courseTitle);
            courseCode = v.findViewById(R.id.courseCode);
            duration = v.findViewById(R.id.duration);
            timeDifference = v.findViewById(R.id.timeDifference);
            isFixed = v.findViewById(R.id.isFixed);
            location = v.findViewById(R.id.venue);
            unitLoad = v.findViewById(R.id.unit);

            //CoursesFragmentInitiate
            courses_courseTitle = v.findViewById(R.id.course_course_title);
            courses_courseCode = v.findViewById(R.id.course_course_code);
            lecturerName = v.findViewById(R.id.course_lecturer_name);
            lecturerOffice = v.findViewById(R.id.course_lecturer_office);
            courseUnitLoad = v.findViewById(R.id.course_course_unit);

            //BuzzFragmentInitiate
            buzzCategory = v.findViewById(R.id.buzz_category);
            buzzDescription = v.findViewById(R.id.buzz_detail);
            buzzTime = v.findViewById(R.id.buzz_time);
        }
    }

    public ArrayList<Channel> instantiateChannel() {
        Channels.add(new Channel("Computer Science 021", "CS_021", 149,
                "University Of Nigeria", 5));

        return Channels;
    }

    public ArrayList<Lecture> instantiateLecture(String lecture) {
//        lectureSchedules.add(new Lecture("Introduction to Life", "LFE101", "12:00",
//                "14:00", "FPSLT", 2, 2, false, "mon"));
//        lectureSchedules.add(new Lecture("Introduction to Programming", "COS101", "12:00",
//                "14:00", "FPSLT", 3, 2, true, "sat"));
//        lectureSchedules.add(new Lecture("Introduction to Life", "LFE101", "12:00",
//                "16:00", "Carver Building", 4, 4, true, "sun"));
//        lectureSchedules.add(new Lecture("General Studies", "GS101", "12:00",
//                "14:00", "GS Building", 2, 2, false, "wed"));
//        lectureSchedules.add(new Lecture("Electronics Functions", "EFI208", "9:00",
//                "10:00", "New Engineering Annex", 1, 1, false, "thu"));
        if (lectureSchedules.size() >= 1){
            for (int i = 0; i < lectureSchedules.size(); i++) {
                if (lectureSchedules.get(i).getDayOfTheWeek() == lecture) {
                    lecturesInDay.add(lectureSchedules.get(i));
                }
            }
        }

        return lecturesInDay;
    }

    public ArrayList<Course> instantiateCourse() {
//        Courses.add(new Course("Introduction to Programming II", "COS207",
//                "Mr Ojukwu Anele John", "Room 311 Abuja Building", 4));
//        Courses.add(new Course("Agricultural Practices III", "AGR251",
//                "Mrs Elizabeth Macbeth", "Room 307 Carver Building", 2));
//        Courses.add(new Course("General Studies Programme I", "GSP201",
//                "Mr Stephen Pius", "Room 101 GS Building", 2));
//        Courses.add(new Course("Physical Matter Properties and Nature I", "GSP205",
//                "Mr Odemegwu Titus Paul", "Room 307 GS Building", 5));
//        Courses.add(new Course("Introduction to Philosophy", "PHY211",
//                "Miss Nnenna Helen", "Room 120 Arts Complex", 3));
//        Courses.add(new Course("Introduction to Programming III", "COS211",
//                "Mrs Amarachi Omemonu", "Room 300 Abuja Building", 5));
//        Courses.add(new Course("Introduction to Python II", "COS301",
//                "Mr Peters Jone John", "Room 400 Carver Building", 4));

        return Courses;
    }

    public ArrayList<Buzzer> instantiateBuzz() {
//        Buzzers.add(new Buzzer("cancel", "COS101 scheduled to hold on Monday has been canceled",
//                "19:04"));
//        Buzzers.add(new Buzzer("birthday", "It's Bright Michael's Birthday today! Wish him well",
//                "19:50"));
//        Buzzers.add(new Buzzer("fixed", "GS207 fixed class to hold this Monday, in the New Engineering Annex by 12:00",
//                "20:20"));
//        Buzzers.add(new Buzzer("other", "Compulsory Class Meeting scheduled to hold on the 25th of August, under the Mango Tree",
//                " 23:00"));

        return Buzzers;
    }


    public void add(int position, Channel channel) {
        Channels.add(position, channel);
        notifyItemInserted(position);
    }

    public void add(int position, Lecture lecture) {
        lecturesInDay.add(position, lecture);
        notifyItemInserted(position);
    }

    public void add(int position, Course course) {
        Courses.add(position, course);
        notifyItemInserted(position);
    }

    public void add(int position, Buzzer buzzer) {
        Buzzers.add(position, buzzer);
        notifyItemInserted(position);
    }

    public UniversalRecyclerAdapter(String filter, @Nullable String lectureDayOfTheWeek, @Nullable Context context) {

        this.filter = filter.toLowerCase();

        if (filter.toLowerCase().equals("channel")) {
            this.channelContext = context;
            instantiateChannel();
        } else if (filter.toLowerCase().equals("lecture")) {
            instantiateLecture(lectureDayOfTheWeek);
        } else if (filter.toLowerCase().equals("course")) {
            instantiateCourse();
        } else if (filter.toLowerCase().equals("buzz")) {
            instantiateBuzz();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v;
        if (filter.equals("channel")) {
            v = inflater.inflate(R.layout.channel, parent, false);
        } else if (filter.equals("lecture")) {
            v = inflater.inflate(R.layout.lecture_item, parent, false);
        } else if (filter.equals("course")) {
            v = inflater.inflate(R.layout.courses_item, parent, false);
        } else {
            v = inflater.inflate(R.layout.buzz_item, parent, false);
        }

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (filter.equals("channel")) {
            if (Channels.size() != 0) {
                holder.channelName.setText(Channels.get(position).getChannelName());
                holder.channelTag.setText(Channels.get(position).getChannelTag());
                holder.channelMembers.setText(Channels.get(position).getChannelMembers() + " classmate(s).");
                holder.channelLocation.setText(Channels.get(position).getChannelLocation());
                if (Channels.get(position).getCurrentUnseenNotifications() == 0) {
                    holder.currentNewNotifications.setText("No new Notifications");
                } else {
                    holder.currentNewNotifications.setText(Channels.get(position).getCurrentUnseenNotifications() + " new notification(s)");
                }

                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity) channelContext).startIntent(position);
                    }
                });

            }
        } else if (filter.equals("lecture")) {
            if (lecturesInDay.size() != 0) {
                holder.courseTitle.setText(lecturesInDay.get(position).getCourseTitle());
                holder.courseCode.setText(lecturesInDay.get(position).getCourseCode());
                holder.unitLoad.setText(lecturesInDay.get(position).getUnitLoad() + " unit(s)");
                holder.location.setText(lecturesInDay.get(position).getLocation());
                holder.duration.setText(lecturesInDay.get(position).getLectureDuration() + "hr(s)");
                holder.timeDifference.setText(lecturesInDay.get(position).getStartTime() + " - " + lectureSchedules.get(position).getEndTime());

                if (lecturesInDay.get(position).isFixed()) {
                    holder.isFixed.setText("fixed");
                } else {
                    holder.isFixed.setText("regular");
                }
            }
        } else if (filter.equals("course")) {
            if (Courses.size() != 0) {
                holder.courses_courseTitle.setText(Courses.get(position).getCourseTitle());
                holder.courses_courseCode.setText(Courses.get(position).getCourseCode());
                holder.lecturerName.setText(Courses.get(position).getLecturerName());
                holder.lecturerOffice.setText(Courses.get(position).getLecturerOffice());
                holder.courseUnitLoad.setText(Courses.get(position).getLectureUnitLoad() + " unit(s)");

            }
        } else if (filter.equals("buzz")) {
            if (Buzzers.size() != 0) {
                holder.buzzDescription.setText(Buzzers.get(position).getBuzzDetail());
                holder.buzzTime.setText(Buzzers.get(position).getBuzzTime());

                if (Buzzers.get(position).getCategory().toLowerCase() == "birthday") {
                    holder.buzzCategory.setImageResource(R.drawable.ic_cake_black_24dp);

                } else if (Buzzers.get(position).getCategory().toLowerCase() == "fixed") {
                    holder.buzzCategory.setImageResource(R.drawable.ic_offline_pin_black_24dp);

                } else if (Buzzers.get(position).getCategory().toLowerCase() == "cancel") {
                    holder.buzzCategory.setImageResource(R.drawable.ic_cancel_black_24dp);

                } else {
                    holder.buzzCategory.setImageResource(R.drawable.ic_error_black_24dp);

                }
            }
        }

    }

    @Override
    public int getItemCount() {
        if (filter == "channel") {
            return Channels.size();
        } else if (filter == "lecture") {
            return lecturesInDay.size();
        } else if (filter == "course") {
            return Courses.size();
        }
        return Buzzers.size();
    }
}
