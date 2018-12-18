package android.nuel.buzz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LecturesWednesdayFragment extends Fragment {
    public LecturesWednesdayFragment() {
    }
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerLayout;
    Context context = new ChannelActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lectures_wednesday, container, false);

        recyclerView = v.findViewById(R.id.wedRecycler);

        recyclerAdapter = new LecturesRecyclerAdapter(this,"wed");
        recyclerView.setAdapter(recyclerAdapter);
        recyclerLayout = new GridLayoutManager(this.getContext(),1);
        recyclerView.setLayoutManager(recyclerLayout);

        // Inflate the layout for this fragment
        return v;
    }
}
