package android.nuel.buzz;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView nullChannel;
    FloatingActionButton floatingActionButton;
    RecyclerView channelRecyclerView;

    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;

    Intent intent;
    String CURRENT_INTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nullChannel = findViewById(R.id.noChannels);
        floatingActionButton = findViewById(R.id.fab);
        channelRecyclerView = findViewById(R.id.channelRecyclerView);



        recyclerAdapter = new UniversalRecyclerAdapter("channel",null,this);
        channelRecyclerView.setAdapter(recyclerAdapter);
        layoutManager = new GridLayoutManager(this,1);
        channelRecyclerView.setLayoutManager(layoutManager);

        if (recyclerAdapter.getItemCount() != 0){
            nullChannel.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_resource, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void startIntent(int position){
        intent = new Intent(this, ChannelActivity.class);
        intent.putExtra(CURRENT_INTENT,position);
        startActivity(intent);


    }

}
