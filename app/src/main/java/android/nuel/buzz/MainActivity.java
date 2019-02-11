package android.nuel.buzz;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ResourceBox resourceBox = new ResourceBox();
    private static boolean selectMode = false;

    TextView nullChannel;

    FloatingActionButton toggleOptions;
    FloatingActionButton createChannel;
    FloatingActionButton joinChannel;
    TextView createChannelText;
    TextView joinChannelText;
    View appBar;

    public boolean isFabMenuOpen;

    public static TextView channelsTitle;
    public static ImageView cancelSelect;
    public static TextView numberSelected;
    public static Button leaveChannels;
    private static ArrayList<Integer> layoutId = new ArrayList<>();
    private static ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();

    ChannelActivity channelActivity = new ChannelActivity();

    RecyclerView channelRecyclerView;

    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    View fragmentContainer;

    public static int itemClicked;


    Intent intent;
    //    String CURRENT_INTENT;
    private static final String TAG = "MainActivity";

    @Override
    public void onBackPressed() {

        Log.d(TAG, "onBackPressed: selectMode returns " + selectMode);

        if (isFabMenuOpen) {
            closeFabMenu();

        } else if (fragmentContainer.getVisibility() == View.VISIBLE) {
            appBar.setVisibility(View.VISIBLE);
            fragmentContainer.setVisibility(View.GONE);
            unfreezeFabMenu();
        }else if (selectMode){

            deselectMode(true);

        } else if (!isFabMenuOpen && fragmentContainer.getVisibility() != View.VISIBLE && !selectMode) {

            super.onBackPressed();

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resourceBox.instantiateChannel();

        nullChannel = findViewById(R.id.noChannels);

        fragmentContainer = findViewById(R.id.fragmentContainer);

        appBar = findViewById(R.id.channelsAppBarLayout);
        channelsTitle = appBar.findViewById(R.id.Title);
        cancelSelect = appBar.findViewById(R.id.stopSelect);
        numberSelected = appBar.findViewById(R.id.currentSelect);
        leaveChannels = appBar.findViewById(R.id.leave);

        toggleOptions = findViewById(R.id.mainFab);
        createChannel = findViewById(R.id.create_channel);
        joinChannel = findViewById(R.id.join_channel);
        createChannelText = findViewById(R.id.createChannelText);
        joinChannelText = findViewById(R.id.joinChannelText);
        isFabMenuOpen = false;

        toggleOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFabMenuOpen) {
                    showFabMenu();
                } else {
                    closeFabMenu();
                }
            }
        });


        channelRecyclerView = findViewById(R.id.channelRecyclerView);


        recyclerAdapter = new UniversalRecyclerAdapter("channel", null, this, resourceBox.ChannelResource);
        channelRecyclerView.setAdapter(recyclerAdapter);
        layoutManager = new GridLayoutManager(this, 1);
        channelRecyclerView.setLayoutManager(layoutManager);

        if (recyclerAdapter.getItemCount() != 0) {
            nullChannel.setVisibility(View.GONE);
        }

        joinChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ConstraintLayout fragContainer = findViewById(R.id.fragmentContainer);
                fragContainer.removeAllViews();
                fragContainer.setVisibility(View.VISIBLE);
                freezeFabMenu();

                fragmentTransaction.replace(R.id.fragmentContainer, new joinChannelFragment());
                fragmentTransaction.commit();


            }
        });


//        rippleMaskSelect = getResources().getDrawable(R.drawable.ripple_mask_select).getConstantState();
//        rippleMask = getResources().getDrawable(R.drawable.ripple_mask).getConstantState();

        leaveChannels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectChannel();
                deselectMode(false);
            }
        });

        cancelSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectMode(true);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_resource, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void startIntent(int position) {
        if (!isFabMenuOpen && fragmentContainer.getVisibility() != View.VISIBLE) {
            intent = new Intent(this, ChannelActivity.class);
            Log.d(TAG, "startIntent: position being parsed is : " + position);
            channelActivity.position = position;

            itemClicked = position;
            startActivity(intent);

        }
    }


    private void showFabMenu() {
        toggleOptions.animate().rotation(45);

        channelRecyclerView.animate().alpha(0.20f);

        nullChannel.animate().alpha(0.20f);

        joinChannel.animate().translationY(-getResources().getDimension(R.dimen.offset_one));
        joinChannelText.animate().translationY(-getResources().getDimension(R.dimen.offset_one));
        joinChannelText.setVisibility(View.VISIBLE);

        createChannel.animate().translationY(-getResources().getDimension(R.dimen.offset_two));
        createChannelText.animate().translationY(-getResources().getDimension(R.dimen.offset_two));
        createChannelText.setVisibility(View.VISIBLE);

        isFabMenuOpen = true;
    }

    private void closeFabMenu() {
        toggleOptions.animate().rotation(0);

        channelRecyclerView.animate().alpha(1);

        nullChannel.animate().alpha(1);

        joinChannel.animate().translationY(0);
        joinChannelText.animate().translationY(0);

        createChannel.animate().translationY(0);

        joinChannelText.setVisibility(View.INVISIBLE);

        createChannelText.animate().translationY(0);
        createChannelText.setVisibility(View.INVISIBLE);

        isFabMenuOpen = false;
    }

    public void freezeFabMenu() {
        if (isFabMenuOpen) {
            closeFabMenu();
        }
        toggleOptions.setVisibility(View.GONE);
        joinChannel.setVisibility(View.GONE);
        createChannel.setVisibility(View.GONE);
    }

    public void unfreezeFabMenu() {
        toggleOptions.setVisibility(View.VISIBLE);
        joinChannel.setVisibility(View.VISIBLE);
        createChannel.setVisibility(View.VISIBLE);
    }

    public boolean channelOnLongCLick(ImageView isSelected, View layout, int position) {

        if (isSelected.getVisibility() == View.INVISIBLE) {
            layout.setBackgroundResource(R.drawable.ripple_mask_select);
            isSelected.setVisibility(View.VISIBLE);
            selectMode();

            layoutId.add(position);
            recyclerItems.add(new RecyclerItem(layout, isSelected));

            numberSelected.setText(layoutId.size() + " Selected");

            if (layoutId.size() > 1) {
                leaveChannels.setText("Leave Channels");
            } else {
                leaveChannels.setText("Leave Channel");
            }
            return true;
        }
        return false;


    }

    public boolean channelAfterLongClick(ImageView isSelected, View layout, int position) {


        if (isSelected.getVisibility() == View.VISIBLE) {

            layout.setBackgroundResource(R.drawable.ripple_mask);

            isSelected.setVisibility(View.INVISIBLE);


            for (int i = 0; i < layoutId.size(); i++) {
                if (layoutId.get(i) == position) {
                    layoutId.remove(i);
                    recyclerItems.remove(i);
                }
            }

            if (layoutId.size() != 0) {
                numberSelected.setText(layoutId.size() + " Selected");

                if (layoutId.size() > 1) {
                    leaveChannels.setText("Leave Channels");
                } else {
                    leaveChannels.setText("Leave Channel");
                }
            } else {
                deselectMode(false);
            }


            return false;

        } else {
            layout.setBackgroundResource(R.drawable.ripple_mask_select);
            isSelected.setVisibility(View.VISIBLE);

            layoutId.add(position);
            recyclerItems.add(new RecyclerItem(layout, isSelected));

            numberSelected.setText(layoutId.size() + " Selected");

            if (layoutId.size() > 1) {
                leaveChannels.setText("Leave Channels");
            } else {
                leaveChannels.setText("Leave Channel");

            }
            return true;
        }
    }

    public void deleteSelectChannel() {
        sort(layoutId);
        for (int i = 0; i < layoutId.size(); i++) {

            resourceBox.ChannelResource.remove((int) layoutId.get(i));
            recyclerAdapter.notifyItemRemoved(layoutId.get(i));
            recyclerAdapter.notifyItemRangeChanged((int) layoutId.get(i), resourceBox.ChannelResource.size());

        }
        layoutId = new ArrayList<>();
        recyclerItems = new ArrayList<>();
    }


    public void selectMode() {

        channelsTitle.setVisibility(View.INVISIBLE);
        cancelSelect.setVisibility(View.VISIBLE);
        numberSelected.setVisibility(View.VISIBLE);
        leaveChannels.setVisibility(View.VISIBLE);
        selectMode = true;
        Log.d(TAG, "selectMode: selectMode has been set to " + selectMode);

    }

    public void deselectMode(boolean goFurther) {

        channelsTitle.setVisibility(View.VISIBLE);
        cancelSelect.setVisibility(View.INVISIBLE);
        numberSelected.setVisibility(View.INVISIBLE);
        leaveChannels.setVisibility(View.INVISIBLE);
        selectMode = false;
        Log.d(TAG, "deselectMode: selectMode has been set to " + selectMode);

        if (goFurther) {
            for (int i = 0; i < recyclerItems.size(); i++) {
                recyclerItems.get(i).getIsSelected().setVisibility(View.INVISIBLE);
                recyclerItems.get(i).getLayout().setBackgroundResource(R.drawable.ripple_mask);
            }

        }

        layoutId = new ArrayList<>();
        recyclerItems = new ArrayList<>();

    }

    public ArrayList<Integer> getLayoutId() {
        return layoutId;
    }

    private void sort(ArrayList<Integer> arrayList) {

        int[] arr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            arr[i] = arrayList.get(i);
        }
        int temp;


        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr.length - 1; k++) {
                if (arr[k] < arr[k + 1]) {
                    temp = arr[k];
                    arr[k] = arr[k + 1];
                    arr[k + 1] = temp;
                }
            }
        }


        layoutId = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            layoutId.add(arr[i]);
        }

    }
}