package android.nuel.buzz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LecturesRecyclerAdapter extends RecyclerView.Adapter<LecturesRecyclerAdapter.ViewHolder> {

    private ArrayList<Lecture> lectureSchedules = new ArrayList<>();

    public ArrayList<Lecture> getLectureSchedules() {
        return lectureSchedules;
    }

    private ArrayList<Lecture> lecturesInDay = new ArrayList<>();


    private Fragment context;
    private String dayOfTheWeek;
    View layout;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTitle;
        public TextView courseCode;
        public TextView duration;
        public TextView timeDifference;
        public TextView isFixed;
        public TextView location;
        public TextView unitLoad;
//        public ConstraintLayout rowLayout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            courseTitle = v.findViewById(R.id.courseTitle);
            courseCode = v.findViewById(R.id.courseCode);
            duration = v.findViewById(R.id.duration);
            timeDifference = v.findViewById(R.id.timeDifference);
            isFixed = v.findViewById(R.id.isFixed);
            location = v.findViewById(R.id.location);
            unitLoad = v.findViewById(R.id.unitLoad);
        }

    }


    public LecturesRecyclerAdapter(Fragment context, String dayOfTheWeek) {
        numberOfApps();
        this.dayOfTheWeek = "mon";
        this.context = context;

    }

    public ArrayList<Lecture> numberOfApps() {
        lectureSchedules.add(new Lecture("Introduction to Life","LFE101","12:00",
                "14:00","FPSLT",2,2, false, "mon"));
        lectureSchedules.add(new Lecture("Introduction to Programming","COS101","12:00",
                "14:00","FPSLT",3,2, true, "sat"));
        lectureSchedules.add(new Lecture("Introduction to Life","LFE101","12:00",
                "16:00","Carver Building",4,4, true, "sun"));
        lectureSchedules.add(new Lecture("General Studies","GS101","12:00",
                "14:00","GS Building",2,2, false, "wed"));
        lectureSchedules.add(new Lecture("Electronics Functions","EFI208","9:00",
                "10:00","New Engineering Annex",1,1, false, "thu"));

        for (int i = 0; i < lectureSchedules.size(); i++){
            if(lectureSchedules.get(i).getDayOfTheWeek() == this.dayOfTheWeek){
                lecturesInDay.add(lectureSchedules.get(i));
            }
        }

        return lecturesInDay;
    }

    public void add(int position, Lecture lecture) {
        lecturesInDay.add(position, lecture);
        notifyItemInserted(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.lecture_item, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final LecturesRecyclerAdapter.ViewHolder holder, final int position) {

        if (lecturesInDay.size() != 0) {
                holder.courseTitle.setText(lecturesInDay.get(position).getCourseTitle());
                holder.courseCode.setText(lecturesInDay.get(position).getCourseCode());
//                holder.unitLoad.setText(lectureSchedules.get(position).getUnitLoad());
                holder.location.setText(lecturesInDay.get(position).getLocation());
                holder.duration.setText(lecturesInDay.get(position).getStartTime() + " - " + lectureSchedules.get(position).getEndTime());
                holder.timeDifference.setText(lecturesInDay.get(position).getLectureDuration() + "hrs");

                if (lecturesInDay.get(position).isFixed()){
                    holder.isFixed.setVisibility(View.VISIBLE);
                }else{
                    holder.isFixed.setVisibility(View.GONE);
                }

        }

        holder.courseTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity) context).startIntent(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lecturesInDay.size();
    }
}
