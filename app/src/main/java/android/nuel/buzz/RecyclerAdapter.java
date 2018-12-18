package android.nuel.buzz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.nio.channels.Channels;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Channel> Channels = new ArrayList<>();

    public ArrayList<Channel> getChannels() {
        return Channels;
    }

    private int width = 4;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView channelName;
        public TextView channelTag;
        public TextView channelMembers;
        public TextView currentNewNotifications;
        public TextView channelLocation;
        public View layout;
//        public ConstraintLayout rowLayout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            channelName = v.findViewById(R.id.channelName);
            channelTag = v.findViewById(R.id.channelTag);
            channelLocation = v.findViewById(R.id.channelLocation);
            channelMembers = v.findViewById(R.id.membership);
            currentNewNotifications = v.findViewById(R.id.newNotifications);
        }

    }

    public ArrayList<Channel> numberOfApps(int width) {
        for (int i = 0; i < width; i++) {
            Channels.add(new Channel("Computer Science 021","CS_021",149,
                    "University Of Nigeria",5));

        }
        return Channels;
    }

    public void add(int position, Channel channel) {
        Channels.add(position, channel);
        notifyItemInserted(position);
    }

    public RecyclerAdapter(Context context) {
        numberOfApps(width);
        this.context = context;

    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.channel, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (Channels.size() != 0) {
            holder.channelName.setText(Channels.get(position).getChannelName());
            holder.channelTag.setText(Channels.get(position).getChannelTag());
            holder.channelMembers.setText(Channels.get(position).getChannelMembers() + " member(s).");
            holder.channelLocation.setText(Channels.get(position).getChannelLocation());
            if (Channels.get(position).getCurrentUnseenNotifications() == 0){
                holder.currentNewNotifications.setText("No new Notifications");
            }else{
                holder.currentNewNotifications.setText(Channels.get(position).getCurrentUnseenNotifications() + " new notification(s)");
            }

        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).startIntent(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Channels.size();
    }
}
