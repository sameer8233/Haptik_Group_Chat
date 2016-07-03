package com.haptik_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haptik_test.R;
import com.haptik_test.model.Message;
import com.haptik_test.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sameer on 7/2/2016.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Message> chatMessageList = new ArrayList<>();
    private Context mContext;
    private HashMap<String, ArrayList<Message>> userChatDetail = new HashMap<>();
    private onUpdated mCallback;


    public ChatAdapter(Context mContext) {
        this.mContext = mContext;
        try {
            mCallback = (onUpdated) mContext;
        } catch (ClassCastException e) {
            throw new ClassCastException(mContext.toString());
        }
    }

    public void setMessageList(ArrayList<Message> messageList) {
        chatMessageList = messageList;

    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        private TextView message, date, name;
        private ImageView profile_pic, favourite;

        public TextViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message);
            date = (TextView) itemView.findViewById(R.id.date);
            name = (TextView) itemView.findViewById(R.id.name);
            profile_pic = (ImageView) itemView.findViewById(R.id.profile_pic);
            favourite = (ImageView) itemView.findViewById(R.id.favourite);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_message_layout, parent, false);

        viewHolder = new TextViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Message c = chatMessageList.get(position);
        ArrayList<Message> messageList = new ArrayList<>();
        if (userChatDetail.containsKey(c.getUsername())) {
            messageList = userChatDetail.get(c.getUsername());
        }
        if (!messageList.contains(c))
            messageList.add(c);

        userChatDetail.put(c.getUsername(), messageList);

        ((TextViewHolder) holder).date.setText(c.getMessageTime().substring(c.getMessageTime().indexOf('T') + 1));
        ((TextViewHolder) holder).message.setText(c.getBody());
        ((TextViewHolder) holder).name.setText(c.getName());

        if (!TextUtils.isEmpty(c.getImageUrl()))
            Picasso.with(mContext).load(c.getImageUrl()).placeholder(R.drawable.default_pic)
                    .error(R.drawable.default_pic).into(((TextViewHolder) holder).profile_pic);
        else
            ((TextViewHolder) holder).profile_pic.setBackgroundResource(R.drawable.default_pic);

        if (!c.getIsFavorite()) {
            ((TextViewHolder) holder).favourite.setBackgroundResource(android.R.drawable.btn_star_big_off);
        } else {
            ((TextViewHolder) holder).favourite.setBackgroundResource(android.R.drawable.btn_star_big_on);
        }

        ((TextViewHolder) holder).favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c.getIsFavorite()) {
                    c.setIsFavorite(false);
                    ((TextViewHolder) holder).favourite.setBackgroundResource(android.R.drawable.btn_star_big_off);
                    Toast.makeText(mContext, Constants.FAVOURITE_REMOVED, Toast.LENGTH_LONG).show();

                } else {
                    c.setIsFavorite(true);
                    ((TextViewHolder) holder).favourite.setBackgroundResource(android.R.drawable.btn_star_big_on);
                    Toast.makeText(mContext, Constants.FAVOURITE_ADDED, Toast.LENGTH_LONG).show();
                }

                //chat detail fragment will update at every click
                mCallback.updateChatDetails(userChatDetail);
            }
        });
        mCallback.updateChatDetails(userChatDetail);

    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

    public interface onUpdated {
        void updateChatDetails(HashMap<String, ArrayList<Message>> hashMap);

    }

}
