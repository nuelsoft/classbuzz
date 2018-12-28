package android.nuel.buzz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CoursesFragmentActivity extends Fragment{
    public CoursesFragmentActivity() {
    }
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerLayout;
    TextView nullCourses;
    Context context = new ChannelActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.courses_fragment, container, false);

        recyclerView = v.findViewById(R.id.courses_recyclerView);
        nullCourses = v.findViewById(R.id.nullCourses);
        recyclerAdapter = new UniversalRecyclerAdapter("course",null,null);

        if (recyclerAdapter.getItemCount() == 0){
            nullCourses.setVisibility(View.VISIBLE);
            return v;
        }

        recyclerView.setAdapter(recyclerAdapter);
        recyclerLayout = new GridLayoutManager(context,1);
        recyclerView.setLayoutManager(recyclerLayout);

        return v;
    }
}
