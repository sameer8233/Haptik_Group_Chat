package com.haptik_test.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haptik_test.R;
import com.haptik_test.adapter.ChatAdapter;
import com.haptik_test.model.Message;
import com.haptik_test.presenter.ChatPresenter;

import java.util.ArrayList;

/**
 * Created by Sameer on 7/2/2016.
 */
public class ChatFragment extends Fragment {
    private Context mContext;

    private View rootView;
    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private static ChatPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public ChatFragment() {
    }

    public static ChatFragment newInstance() {
        return  new ChatFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chatAdapter = new ChatAdapter(mContext);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.chatfragment, container, false);

        chatRecyclerView = (RecyclerView) rootView.findViewById(R.id.chat_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        chatRecyclerView.setLayoutManager(llm);
        chatRecyclerView.setHasFixedSize(true);
        chatRecyclerView.setAdapter(chatAdapter);


        if (presenter == null) {
           //call one time to get data from server.
            presenter = new ChatPresenter();
            presenter.onTakeView(this);
        } else {
            // in case of screen is rotation this method called.
            // getting data from presenter.
            // NO API HIT TO SERVER.
            presenter.publish(this);

        }


        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (!(getActivity()).isChangingConfigurations()) {
            presenter = null;
        }
    }


    // getting data from presenter.
    public void onItemNext(ArrayList<Message> items) {
        //setting data to chat adapter
        chatAdapter.setMessageList(items);
        chatAdapter.notifyDataSetChanged();
    }
}
