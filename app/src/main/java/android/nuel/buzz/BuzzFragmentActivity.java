package android.nuel.buzz;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Time;

public class BuzzFragmentActivity extends Fragment {
    FloatingActionButton sendNews;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerLayout;
    TextView nullBuzz;
    Context context = new ChannelActivity();

    private static final String TAG = "BuzzFragmentActivity";

    ChannelActivity channelActivity = new ChannelActivity();
    public static View view;
    ResourceBox resourceBox = new ResourceBox();
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    LayoutInflater layoutInflater;


    public BuzzFragmentActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.buzz_fragment, container, false);


        recyclerView = v.findViewById(R.id.buzz_recyclerview);
        nullBuzz = v.findViewById(R.id.nullBuzz);
        sendNews = v.findViewById(R.id.sendNewsLetter);

        if (resourceBox.ChannelResource.get(channelActivity.position).isAdmin()) {
            sendNews.setVisibility(View.VISIBLE);
        } else {
            sendNews.setVisibility(View.INVISIBLE);
        }


        recyclerAdapter = new UniversalRecyclerAdapter("buzz", null, null, resourceBox.ChannelResource);


        layoutInflater = this.getLayoutInflater();
        view = layoutInflater.inflate(R.layout.newsletter_alert_edit, null);

        builder = new AlertDialog.Builder(this.getContext());
        builder.setView(view);
        builder.setCancelable(true);
        alertDialog = builder.create();

        sendNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();

            }
        });


        view.findViewById(R.id.publishNews).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView title = view.findViewById(R.id.title);
                TextView newsDetail = view.findViewById(R.id.newsDetails);

                resourceBox.addBuzz("newsletter", newsDetail.getText().toString(), "19 : 00",
                        resourceBox.ChannelResource.get(channelActivity.position).getChannelTag(),
                        "Emmanuel Sunday", title.getText().toString());


                String details;

                if (newsDetail.getText().toString().length() < 100) {
                    details = newsDetail.getText().toString();
                } else {
                    details = newsDetail.getText().toString().substring(0, 100) + "...";
                }


//                resourceBox.ChannelResource.get(channelActivity.position).channelBuzzes.add(new
//                        Buzzer("newsletter", title.getText().toString().toUpperCase() + " - " + details,"19:00",resourceBox.ChannelResource.
//                        get(channelActivity.position).getChannelTag()));

                title.setText("");
                newsDetail.setText("");
                alertDialog.hide();

                channelActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerAdapter.notifyDataSetChanged();
                    }
                });

                getCount();

                for (int i = 0; i < resourceBox.ChannelResource.get(channelActivity.position).channelBuzzes.size(); i++) {
                    Log.d(TAG, "onClick finding out channelBuzzes: " + resourceBox.ChannelResource.get(channelActivity.position).channelBuzzes.get(i).getBuzzDetail());
                }
            }
        });


        recyclerView.setAdapter(recyclerAdapter);
        recyclerLayout = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(recyclerLayout);

        getCount();
        return v;
    }

    public void getCount() {
        if (recyclerAdapter.getItemCount() == 0) {
            nullBuzz.setVisibility(View.VISIBLE);

        } else {
            nullBuzz.setVisibility(View.INVISIBLE);
        }
    }
}
