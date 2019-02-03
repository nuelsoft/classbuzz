package android.nuel.buzz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LecturesFridayFragment extends Fragment {
    public LecturesFridayFragment() {
    }

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerLayout;
    Context context = new ChannelActivity();
    TextView nullLecture;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.days, container, false);

        recyclerView = v.findViewById(R.id.dayRecycler);
        nullLecture = v.findViewById(R.id.nullLecture);

        recyclerAdapter = new UniversalRecyclerAdapter("lecture","fri",null, new ChannelActivity().position);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerLayout = new GridLayoutManager(context,1);
        recyclerView.setLayoutManager(recyclerLayout);

        if (recyclerAdapter.getItemCount() != 0){
            nullLecture.setVisibility(View.GONE);
        }


        // Inflate the layout for this fragment
        return v;
    }
}
