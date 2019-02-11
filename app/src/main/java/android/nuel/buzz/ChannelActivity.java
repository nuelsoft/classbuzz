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
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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

    public static AlertDialog alertDialog;
    public static View view;
    static AlertDialog.Builder builder;
    static LayoutInflater layoutInflater;


    public static int position;

    private static final String TAG = "ChannelActivity";
    public static String currentChannel = "";
    ResourceBox resourceBox = new ResourceBox();

    public Context ChannelActivity() {
        return this;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

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


        layoutInflater = this.getLayoutInflater();
        view = layoutInflater.inflate(R.layout.newsletter_full, null);

        builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setCancelable(true);
        alertDialog = builder.create();

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

    public void openNews(int viewPosition) {
        TextView newsDetail = view.findViewById(R.id.newsFullDetail);
        newsDetail.setMovementMethod(new ScrollingMovementMethod());

        String newsTitle = "<br /><b>" +
                resourceBox.ChannelResource.get(position).channelBuzzes.get(viewPosition).getNewsTitle().toUpperCase() + "</b>";

        String publisher = "<b><i>" +
                resourceBox.ChannelResource.get(position).channelBuzzes.get(viewPosition).getAuthor() +
                "</i></b>";
        String newsProper = htmlFormatText(resourceBox.ChannelResource.get(position).channelBuzzes.get(viewPosition).getBuzzDetail());

        String newsLetter = newsTitle + "<p>"
                + newsProper + "<p> " +
                "Published by " + publisher + "</p> </p>";

        newsDetail.setText(Html.fromHtml(newsLetter));

        alertDialog.show();
    }

    public String htmlFormatText(String toFormat) {

        String htmlFormat = "";
        char newLine = '\n';

        for (int i = 0; i < toFormat.length(); i++) {
            if ((int) toFormat.charAt(i) == (int) newLine) {
                htmlFormat += "<br />";

                Log.d(TAG, "htmlFormatText: " + htmlFormat);
            } else {
                htmlFormat += toFormat.substring(i, i + 1);
            }
        }

        return htmlFormat;
    }


}
