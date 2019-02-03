package android.nuel.buzz;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CoursesFragmentActivity extends Fragment{
    public CoursesFragmentActivity() {
    }


    private static final String TAG = "CoursesFragmentActivity";
    
    
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerLayout;
    TextView nullCourses;
    FloatingActionButton addCourses;
    Context context = new ChannelActivity();

    public static AlertDialog alertDialog;
    public static View view;
    AlertDialog.Builder builder;
    LayoutInflater layoutInflater;

    ChannelActivity channelActivity = new ChannelActivity();
    ResourceBox resourceBox = new ResourceBox();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.courses_fragment, container, false);

        recyclerView = v.findViewById(R.id.courses_recyclerView);
        nullCourses = v.findViewById(R.id.nullCourses);
        recyclerAdapter = new UniversalRecyclerAdapter("course",null,null, new ChannelActivity().position);
        addCourses = v.findViewById(R.id.coursesFragmentfab);

        if (recyclerAdapter.getItemCount() == 0){
            nullCourses.setVisibility(View.VISIBLE);
        }

        if (resourceBox.ChannelResource.get(channelActivity.position).isAdmin()){
            addCourses.setVisibility(View.VISIBLE);
        }else{
            addCourses.setVisibility(View.INVISIBLE);
        }

        layoutInflater = this.getLayoutInflater();
        view = layoutInflater.inflate(R.layout.add_course_form, null);
        builder = new AlertDialog.Builder(this.getContext());
        builder.setView(view);
        builder.setCancelable(false);
        alertDialog = builder.create();


        view.findViewById(R.id.cancelCourseAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        addCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startModal();
            }
        });


        recyclerView.setAdapter(recyclerAdapter);
        recyclerLayout = new GridLayoutManager(context,1);
        recyclerView.setLayoutManager(recyclerLayout);

        return v;
    }

    public void startModal(){
        alertDialog.show();
    }
}
