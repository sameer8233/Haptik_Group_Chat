package com.haptik_test.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haptik_test.R;
import com.haptik_test.adapter.ChatAdapter;
import com.haptik_test.adapter.ChatDetailsAdapter;
import com.haptik_test.model.Message;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sameer on 7/2/2016.
 */
public class ChatDetailFragment extends Fragment implements ChatAdapter.onUpdated {
    private Context mContext;
    private HashMap<String, ArrayList<Message>> chatdetailsHashMap;
    private ChatDetailsAdapter chatDetailsAdapter;
    private View rootView;
    private RecyclerView chatdetailRecyclerView;

    public ChatDetailFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static ChatDetailFragment newInstance() {

        return new ChatDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.chatdetailsfragment, container, false);

        chatdetailRecyclerView = (RecyclerView) rootView.findViewById(R.id.chatdetail_recycler_view);

        chatDetailsAdapter = new ChatDetailsAdapter(mContext);
        LinearLayoutManager ll = new LinearLayoutManager(mContext);
        ll.setOrientation(LinearLayoutManager.VERTICAL);
        chatdetailRecyclerView.setLayoutManager(ll);
        chatdetailRecyclerView.setHasFixedSize(true);
        chatdetailRecyclerView.setAdapter(chatDetailsAdapter);

        return rootView;
    }


    @Override
    public void updateChatDetails(HashMap<String, ArrayList<Message>> hashMap) {
        chatdetailsHashMap = hashMap;
        chatDetailsAdapter.setSummaryHashMap(mContext, chatdetailsHashMap);
        chatDetailsAdapter.notifyDataSetChanged();
    }

}
