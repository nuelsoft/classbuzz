package android.nuel.buzz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LecturesSundayFragment extends Fragment {
    public LecturesSundayFragment() {
    }

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerLayout;
    Context context = new ChannelActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lectures_sunday, container, false);

        recyclerView = v.findViewById(R.id.sunRecycler);

        recyclerAdapter = new LecturesRecyclerAdapter(this,"sun");
        recyclerView.setAdapter(recyclerAdapter);
        recyclerLayout = new GridLayoutManager(this.getActivity(),1);
        recyclerView.setLayoutManager(recyclerLayout);

        // Inflate the layout for this fragment
        return v;
    }
}
