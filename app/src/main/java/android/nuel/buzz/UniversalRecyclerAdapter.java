package android.nuel.buzz;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class UniversalRecyclerAdapter extends RecyclerView.Adapter<UniversalRecyclerAdapter.ViewHolder> {

    ResourceBox resourceBox = new ResourceBox();

    Context channelContext;
    String filter;
    int currentIndex;
    private ArrayList<Lecture> lecturesInDay = new ArrayList<>();
    private ArrayList<Channel> dataSet;

    private static final String TAG = "UniversalRecyclerAdater";

    ChannelActivity channelActivity = new ChannelActivity();
    MainActivity mainActivity = new MainActivity();

    public class ViewHolder extends RecyclerView.ViewHolder {

        //ChannelFragmentDeclaration
        public TextView channelName;
        public TextView channelTag;
        public TextView channelMembers;
        public TextView currentNewNotifications;
        public TextView channelLocation;
        public ImageView isSelected;
        public View layout;



        //LectureFragmentDeclaration
        public TextView courseTitle;
        public TextView courseCode;
        public TextView duration;
        public TextView timeDifference;
        public TextView isFixed;
        public TextView location;
        public TextView unitLoad;
        private TextView lectureNullMessage;

        //CourseFragmentDeclaration
        public TextView course_courseTitle;
        public TextView course_courseCode;
        public TextView lecturerName;
        public TextView lecturerOffice;
        public TextView courseUnitLoad;
        private TextView courseNullMessage;

        //BuzzFragmentDeclaration
        public TextView buzzDescription;
        public TextView buzzTime;
        public ImageView buzzCategory;
        private TextView buzzNullMessage;

        public ViewHolder(View v) {
            super(v);
            //channelFragmentInstantiate
            layout = v;
            channelName = v.findViewById(R.id.channelName);
            channelTag = v.findViewById(R.id.channelTag);
            channelLocation = v.findViewById(R.id.channelLocation);
            channelMembers = v.findViewById(R.id.membership);
            currentNewNotifications = v.findViewById(R.id.newNotifications);
            isSelected = v.findViewById(R.id.isSelected);

            //LectureFragmentInstantiate
            courseTitle = v.findViewById(R.id.courseTitle);
            courseCode = v.findViewById(R.id.courseCode);
            duration = v.findViewById(R.id.duration);
            timeDifference = v.findViewById(R.id.timeDifference);
            isFixed = v.findViewById(R.id.isFixed);
            location = v.findViewById(R.id.venue);
            unitLoad = v.findViewById(R.id.unit);
            lectureNullMessage = v.findViewById(R.id.nullMessage);

            //Channels.get(currentIndex).channelCourses.ragmentInitiate
            course_courseTitle = v.findViewById(R.id.course_course_title);
            course_courseCode = v.findViewById(R.id.course_course_code);
            lecturerName = v.findViewById(R.id.course_lecturer_name);
            lecturerOffice = v.findViewById(R.id.course_lecturer_office);
            courseUnitLoad = v.findViewById(R.id.course_course_unit);
            courseNullMessage = v.findViewById(R.id.nullMessage);

            //BuzzFragmentInitiate
            buzzCategory = v.findViewById(R.id.buzz_category);
            buzzDescription = v.findViewById(R.id.buzz_detail);
            buzzTime = v.findViewById(R.id.buzz_time);
        }
    }


    public UniversalRecyclerAdapter(String filter, @Nullable String lectureDayOfTheWeek, @Nullable Context context, ArrayList dataSet) {

        this.filter = filter.toLowerCase();
        this.dataSet = dataSet;
        this.currentIndex = new MainActivity().itemClicked;


        if (filter.toLowerCase().equals("channel")) {
            this.channelContext = context;
            returnChannel();
        } else if (filter.toLowerCase().equals("lecture")) {
            instantiateLecture(lectureDayOfTheWeek);
        } else if (filter.toLowerCase().equals("course")) {
            returnCourse();
        } else if (filter.toLowerCase().equals("buzz")) {
            returnBuzz();
        }


    }

    public void addCourse(String courseTitle, String courseCode, String lecturerName, String lecturerOffice,
                          int lectureUnitLoad, String channelTag) {

        Course newCourse = new Course(courseTitle, courseCode, lecturerName,
                lecturerOffice, lectureUnitLoad, channelTag);

        if (channelTag == dataSet.get(currentIndex).getChannelTag()) {
            dataSet.get(currentIndex).channelCourses.add(newCourse);
        }

    }

    public ArrayList<Lecture> instantiateLecture(String lecture) {

        if (dataSet.get(currentIndex).channelLectures.size() >= 1) {

            lecturesInDay = new ArrayList<>();

            for (int i = 0; i < dataSet.get(currentIndex).channelLectures.size(); i++) {
                if (dataSet.get(currentIndex).channelLectures.get(i).getDayOfTheWeek() == lecture) {
                    lecturesInDay.add(dataSet.get(currentIndex).channelLectures.get(i));
                }
            }
        }

        return lecturesInDay;
    }

    private ArrayList<Channel> returnChannel(){
        return dataSet;
    }
    private ArrayList<Course> returnCourse(){
        return dataSet.get(currentIndex).channelCourses;
    }
    private ArrayList<Buzzer> returnBuzz(){
        return dataSet.get(currentIndex).channelBuzzes;
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
            if (dataSet.size() != 0) {
                holder.channelName.setText(dataSet.get(position).getChannelName());
                holder.channelTag.setText(dataSet.get(position).getChannelTag());
                if (dataSet.get(position).getChannelMembers() == 1) {
                    holder.channelMembers.setText(dataSet.get(position).getChannelMembers() + " classmate.");
                }else{
                    holder.channelMembers.setText(dataSet.get(position).getChannelMembers() + " classmates.");
                }

                holder.channelLocation.setText(dataSet.get(position).getChannelLocation());
                if (dataSet.get(position).getCurrentUnseenNotifications() == 0) {
                    holder.currentNewNotifications.setText("No new Notifications");
                } else if (dataSet.get(position).getCurrentUnseenNotifications() == 1) {
                    holder.currentNewNotifications.setText(dataSet.get(position).getCurrentUnseenNotifications() + " new notification.");
                }else{
                    holder.currentNewNotifications.setText(dataSet.get(position).getCurrentUnseenNotifications() + " new notifications.");

                }

                holder.layout.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (mainActivity.getLayoutId().size() < 1){
                            channelActivity.currentChannel = dataSet.get(position).getChannelName();
                            ((MainActivity) channelContext).startIntent(position);

                        }else{
                            mainActivity.channelAfterLongClick(holder.isSelected, holder.layout, position);
                        }

                    }
                });

                holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {


                        boolean outcome = mainActivity.channelOnLongCLick(holder.isSelected, holder.layout, position);

                        if (outcome){
                            return outcome;
                        }else{
                            return outcome;
                        }

//                        if(selected < 1){
//
//                            selectedItemPositions = new ArrayList<>();
//                            selectedItemViews = new ArrayList<>();
//
//                            holder.layout.setBackgroundResource(R.drawable.ripple_mask_select);
//                            selected += 1;
//
//                            mainActivity.channelsTitle.setVisibility(View.INVISIBLE);
//                            mainActivity.numberSelected.setText(selected + " Selected");
//                            mainActivity.numberSelected.setVisibility(View.VISIBLE);
//                            mainActivity.cancelSelect.setVisibility(View.VISIBLE);
//                            mainActivity.leaveChannels.setVisibility(View.VISIBLE);
//
//                            selectedItemPositions.add(position);
//                            selectedItemViews.add(holder.layout);
//
//                            Channels.get(position).setTaken(true);
//                            mainActivity.leaveChannels.setText("leave channel");
//
//                            return true;
//                        }
//                        return false;
//                    }
//                });
//
//                mainActivity.cancelSelect.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (selectedItemViews.size() != 0){
//                            for (int i = 0; i < selectedItemPositions.size(); i++){
//                                selectedItemViews.get(i).setBackgroundResource(R.drawable.ripple_mask);
//                            }
//                        }
//                        mainActivity.channelsTitle.setVisibility(View.VISIBLE);
//                        mainActivity.numberSelected.setVisibility(View.INVISIBLE);
//                        mainActivity.cancelSelect.setVisibility(View.INVISIBLE);
//                        mainActivity.leaveChannels.setVisibility(View.INVISIBLE);
//
//
//                        selected = 0;
//                        selectedItemViews = new ArrayList<>();
//                        selectedItemPositions = new ArrayList<>();
//
//
//                    }
//                });
//
//                mainActivity.leaveChannels.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for(int i = 0; i < selectedItemPositions.size(); i++){
//                            selected -= 1;
//
//                            Channels.remove((int) selectedItemPositions.get(i));
//                            notifyItemRemoved(selectedItemPositions.get(i));
//                            notifyItemRangeChanged(selectedItemPositions.get(i), Channels.size());
//
//
//
////                            THIS IS THE PART WHERE THE SERVER IS NOTIFIED THAT AN ITEM HAS REMOVED
////                            FROM THE ORIGINAL LIST
//
//                        }
////
//                        mainActivity.channelsTitle.setVisibility(View.VISIBLE);
//                        mainActivity.numberSelected.setVisibility(View.INVISIBLE);
//                        mainActivity.cancelSelect.setVisibility(View.INVISIBLE);
//                        mainActivity.leaveChannels.setVisibility(View.INVISIBLE);
//
//
//                        selected = 0;
//                        selectedItemViews = new ArrayList<>();
//                        selectedItemPositions = new ArrayList<>();
                        }
                });

            }

        } else if (filter.equals("lecture")) {
            if (lecturesInDay.size() != 0) {
                holder.courseTitle.setText(lecturesInDay.get(position).getCourseTitle());
                holder.courseCode.setText(lecturesInDay.get(position).getCourseCode());
                if (lecturesInDay.get(position).getUnitLoad() != 1){
                    holder.unitLoad.setText(lecturesInDay.get(position).getUnitLoad() + " units");
                }else{
                    holder.unitLoad.setText(lecturesInDay.get(position).getUnitLoad() + " unit");
                }
                holder.location.setText(lecturesInDay.get(position).getLocation());
                if (lecturesInDay.get(position).getLectureDuration() != 1){
                    holder.duration.setText(lecturesInDay.get(position).getLectureDuration() + "hrs");
                }else{
                    holder.duration.setText(lecturesInDay.get(position).getLectureDuration() + "hr");
                }
                holder.timeDifference.setText(lecturesInDay.get(position).getStartTime() + " - " + dataSet.get(currentIndex).channelLectures.get(position).getEndTime());

                if (lecturesInDay.get(position).isFixed()) {
                    holder.isFixed.setText("fixed");
                } else {
                    holder.isFixed.setText("regular");
                }
            }else{
                holder.lectureNullMessage.setText("No Lectures today click the + button to add");
            }
        } else if (filter.equals("course")) {

            final CoursesFragmentActivity coursesFragmentActivity = new CoursesFragmentActivity();

            final View modalView = coursesFragmentActivity.view;
            final TextView title = modalView.findViewById(R.id.addCourseTitle);
            final TextView lecturerName = modalView.findViewById(R.id.addLecturerName);
            final TextView lecturerOffice = modalView.findViewById(R.id.addLecturerOffice);
            final TextView courseCode = modalView.findViewById(R.id.addCourseCode);
            final TextView courseUnit = modalView.findViewById(R.id.addCourseUnit);

//            modalView.findViewById(R.id.addCourse).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    coursesFragmentActivity.alertDialog.hide();
//
//                    Course newCourse = new Course(title.getText().toString(),
//                            courseCode.getText().toString(),
//                            lecturerName.getText().toString(),
//                            lecturerOffice.getText().toString(),
//                            1, Channels.get(currentIndex).getChannelTag());
//
//
//                    Channels.get(currentIndex).channelCourses.add(newCourse);
//                    notifyItemInserted(Channels.get(currentIndex).channelCourses.size()-1);
//
//
//                    if (noCourse){
//                        Channels.get(currentIndex).channelCourses.remove(0);
//                        notifyItemRemoved(0);
////                        notifyItemRangeChanged(0,  Channels.get(currentIndex).channelCourses.size());
//
//                        noCourse = false;
//
//                    }
//
////                    notifyItemRangeChanged(0, Channels.get(currentIndex).channelCourses.size());
////                        notifyItemInserted(0);
//
//
//                    //Empty the modal
//                    title.setText(""); lecturerName.setText(""); lecturerOffice.setText(""); courseCode.setText(""); courseUnit.setText("");
//
//                }
//            });
//
//
//
//
//            if (!noCourse) {
//
////                holder.course_courseTitle.setText(Channels.get(currentIndex).channelCourses.get(position).getCourseTitle());
////                holder.course_courseCode.setText(Channels.get(currentIndex).channelCourses.get(position).getCourseCode());
////                holder.lecturerName.setText(Channels.get(currentIndex).channelCourses.get(position).getLecturerName());
////                holder.lecturerOffice.setText(Channels.get(currentIndex).channelCourses.get(position).getLecturerOffice());
////                if (Channels.get(currentIndex).channelCourses.get(position).getLectureUnitLoad() != 1){
////                    holder.courseUnitLoad.setText(Channels.get(currentIndex).channelCourses.get(position).getLectureUnitLoad() + " units");
////                }else{
////                    holder.courseUnitLoad.setText(Channels.get(currentIndex).channelCourses.get(position).getLectureUnitLoad() + " unit");
////                }
//
//
//            }else{
//                Log.d(TAG, "onBindViewHolder: Got to the else part of the course");
//                holder.courseNullMessage.setText("No Courses Added yet click the + button to add!");
//            }
        } else if (filter.equals("buzz")) {
            if (dataSet.get(currentIndex).channelBuzzes.size() != 0) {
                holder.buzzDescription.setText(dataSet.get(currentIndex).channelBuzzes.get(position).getBuzzDetail());
                holder.buzzTime.setText(dataSet.get(currentIndex).channelBuzzes.get(position).getBuzzTime());

                if (dataSet.get(currentIndex).channelBuzzes.get(position).getCategory().toLowerCase() == "birthday") {
                    holder.buzzCategory.setImageResource(R.drawable.ic_cake_black_24dp);

                } else if (dataSet.get(currentIndex).channelBuzzes.get(position).getCategory().toLowerCase() == "fixed") {
                    holder.buzzCategory.setImageResource(R.drawable.ic_offline_pin_black_24dp);

                } else if (dataSet.get(currentIndex).channelBuzzes.get(position).getCategory().toLowerCase() == "cancel") {
                    holder.buzzCategory.setImageResource(R.drawable.ic_cancel_black_24dp);

                } else if (dataSet.get(currentIndex).channelBuzzes.get(position).getCategory().toLowerCase() == "newsletter") {
                    holder.buzzCategory.setImageResource(R.drawable.ic_assignment_black_24dp);
                    holder.buzzDescription.setText(dataSet.get(currentIndex).channelBuzzes.get(position).getAuthor() + " published a news letter: " +
                            dataSet.get(currentIndex).channelBuzzes.get(position).getNewsTitle().toUpperCase());

                    holder.layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            channelActivity.openNews(position);
                        }
                    });


                } else {
                    holder.buzzCategory.setImageResource(R.drawable.ic_error_black_24dp);

                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (filter == "channel") {
            return dataSet.size();
        } else if (filter == "lecture") {
            return lecturesInDay.size();
        } else if (filter == "course") {
            return dataSet.get(currentIndex).channelCourses.size();
        }
        return dataSet.get(currentIndex).channelBuzzes.size();
    }
}
