package android.nuel.buzz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LecturesFragmentActivity extends Fragment {
    public LecturesFragmentActivity() {
    }

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lectures_fragment, container, false);

        viewPager = v.findViewById(R.id.lectures_viewPager);
        tabLayout = v.findViewById(R.id.daySelectionTab);


        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        // Inflate the layout for this fragment
        return v;
    }
    private void setupViewPager(@NonNull ViewPager viewPager) {
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(this.getFragmentManager());

        pagerAdapter.addFragment(new LecturesSundayFragment(), "Sun");
        pagerAdapter.addFragment(new LecturesMondayFragment(), "Mon");
        pagerAdapter.addFragment(new LecturesTuesdayFragment(), "Tue");
        pagerAdapter.addFragment(new LecturesWednesdayFragment(), "Wed");
        pagerAdapter.addFragment(new LecturesThursdayFragment(), "Thu");
        pagerAdapter.addFragment(new LecturesFridayFragment(), "Fri");
        pagerAdapter.addFragment(new LecturesSaturdayFragment(), "Sat");
        viewPager.setAdapter(pagerAdapter);

    }
}
