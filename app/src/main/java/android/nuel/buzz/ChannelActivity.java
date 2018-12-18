package android.nuel.buzz;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

public class ChannelActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    public Context ChannelActivity(){
        return this;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        viewPager = findViewById(R.id.channelviewpager);
        tabLayout = findViewById(R.id.channelTabLayout);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(@NonNull ViewPager viewPager) {
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new LecturesFragmentActivity(), "Lectures");
        pagerAdapter.addFragment(new CoursesFragmentActivity(), "Courses");
        pagerAdapter.addFragment(new BuzzFragmentActivity(), "Buzz");
        pagerAdapter.addFragment(new BirthdayFragmentActivity(), "Birthdays");
        viewPager.setAdapter(pagerAdapter);

    }

    public GridLayoutManager setupGridLayoutManager(){
        return new GridLayoutManager(this,1);
    }

}
