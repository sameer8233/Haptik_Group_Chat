package com.haptik_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.haptik_test.R;
import com.haptik_test.model.Message;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sameer on 7/2/2016.
 */
public class ChatDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HashMap<String, ArrayList<Message>> chatdetailsHashMap = new HashMap<>();
    private Context mContext;
    private String[] mHashMapKeys;

    public void setSummaryHashMap(Context context, HashMap<String, ArrayList<Message>> hashMap) {
        mContext = context;
        chatdetailsHashMap = hashMap;
        mHashMapKeys = chatdetailsHashMap.keySet().toArray(new String[hashMap.size()]);

    }

    public ChatDetailsAdapter(Context context) {
        mContext = context;

    }


    class TextViewHolder extends RecyclerView.ViewHolder {

        private TextView message, fav, name;
        private ImageView profile_pic;

        public TextViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message);
            fav = (TextView) itemView.findViewById(R.id.date);
            name = (TextView) itemView.findViewById(R.id.name);
            profile_pic=(ImageView)itemView.findViewById(R.id.profile_pic);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_detail_layout, parent, false);

        viewHolder = new TextViewHolder(v);

        return viewHolder;
    }

    public ArrayList<Message> getItem(int position) {
        return chatdetailsHashMap.get(mHashMapKeys[position]);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ArrayList<Message> messageList = getItem(position);

        ((TextViewHolder) holder).message.setText("Total Messages: "+messageList.size()+"");
        ((TextViewHolder) holder).name.setText(messageList.get(0).getName());

        if (!TextUtils.isEmpty(messageList.get(0).getImageUrl()))
            Picasso.with(mContext).load(messageList.get(0).getImageUrl()).placeholder(R.drawable.default_pic)
                    .error(R.drawable.default_pic).into( ((TextViewHolder) holder).profile_pic);
        else
            ((TextViewHolder) holder).profile_pic.setBackgroundResource(R.drawable.default_pic);


        int favCount = 0;
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i).getIsFavorite())
                favCount++;
        }
        ((TextViewHolder) holder).fav.setText("Total Favourite: "+favCount);

    }

    @Override
    public int getItemCount() {
        return chatdetailsHashMap.size();
    }
}
