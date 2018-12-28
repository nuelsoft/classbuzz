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

public class BuzzFragmentActivity extends Fragment{
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerLayout;
    TextView nullBuzz;
    Context context = new ChannelActivity();
    public BuzzFragmentActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.buzz_fragment, container, false);

        recyclerView = v.findViewById(R.id.buzz_recyclerview);
        nullBuzz = v.findViewById(R.id.nullBuzz);

        recyclerAdapter = new UniversalRecyclerAdapter("buzz",null,null);

        if (recyclerAdapter.getItemCount() == 0){
            nullBuzz.setVisibility(View.VISIBLE);
            return v;
        }

        recyclerView.setAdapter(recyclerAdapter);
        recyclerLayout = new GridLayoutManager(context,1);
        recyclerView.setLayoutManager(recyclerLayout);

        return v;
    }
}
