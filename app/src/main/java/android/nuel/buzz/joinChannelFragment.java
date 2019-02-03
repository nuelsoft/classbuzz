package android.nuel.buzz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class joinChannelFragment extends Fragment {

    public joinChannelFragment() {
    }

    EditText searchChannel;
    RecyclerView searchOutput;
    TextView nullFound;
    View parentAppBar;
    ImageView backbutton;
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        parentAppBar.setVisibility(View.VISIBLE);
//    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.join_channel_fragment, container, false);

        searchChannel = v.findViewById(R.id.search);
        searchOutput = v.findViewById(R.id.channelOutput);
        nullFound = v.findViewById(R.id.nullChannelOutput);
        backbutton = v.findViewById(R.id.innerChannelBackButton);

        parentAppBar = getActivity().findViewById(R.id.channelsAppBarLayout);
        parentAppBar.setVisibility(View.GONE);

        searchChannel.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        return v;
    }


    public void goBack(){
        getView().setVisibility(View.INVISIBLE);
        getActivity().dispatchKeyEvent(new KeyEvent( KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        getActivity().dispatchKeyEvent(new KeyEvent( KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
//        parentAppBar.setVisibility(View.VISIBLE);
    }

}
