package android.nuel.buzz;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ChannelActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    BottomNavigationView bottomNavigationView;
    FrameLayout includedFragment;
    Bundle receivedIntent;


    TextView titleBar;
    ImageView backButton;

    public static int position;

    private static final String TAG = "ChannelActivity";
    public static String currentChannel = "";

    public Context ChannelActivity() {
        return this;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        receivedIntent = getIntent().getExtras();
//        position = receivedIntent.getInt("CURRENT_INTENT");

        bottomNavigationView = findViewById(R.id.bottom_nav_layout);
        includedFragment = findViewById(R.id.includedFragment);
        backButton = findViewById(R.id.innerChannelBackButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            goBack();
            }
        });

        titleBar = findViewById(R.id.channelTitle);
        titleBar.setText(currentChannel);


        startLectureFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action1:
                        startLectureFragment();
                        return true;
                    case R.id.action2:
                        startCoursesFragment();
                        return true;
                    case R.id.action3:
                        startBuzzFragment();
                        return true;
                        default:
                            return false;
                }
            }
        });



    }

    FragmentManager fragmentManager = getSupportFragmentManager();

    private void startLectureFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        includedFragment.removeAllViews();
        fragmentTransaction.replace(R.id.includedFragment, new LecturesFragmentActivity());

        fragmentTransaction.commit();

    }
    private void startCoursesFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        includedFragment.removeAllViews();
        fragmentTransaction.replace(R.id.includedFragment, new CoursesFragmentActivity());
        fragmentTransaction.commit();
    }
    private void startBuzzFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        includedFragment.removeAllViews();
        fragmentTransaction.replace(R.id.includedFragment, new BuzzFragmentActivity());
        fragmentTransaction.commit();
    }

    private void goBack(){
        this.dispatchKeyEvent(new KeyEvent( KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        this.dispatchKeyEvent(new KeyEvent( KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));

    }

//
//    private void setupViewPager(@NonNull ViewPager viewPager) {
//        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
//
//        pagerAdapter.addFragment(new LecturesFragmentActivity(), "Lectures");
//        pagerAdapter.addFragment(new CoursesFragmentActivity(), "Courses");
//        pagerAdapter.addFragment(new BuzzFragmentActivity(), "Buzz");
//        viewPager.setAdapter(pagerAdapter);
//
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home){
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu_resource, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }


}
