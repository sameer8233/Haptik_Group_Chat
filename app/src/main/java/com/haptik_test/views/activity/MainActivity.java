package com.haptik_test.views.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import com.haptik_test.R;
import com.haptik_test.adapter.ChatAdapter;
import com.haptik_test.adapter.ChatPagerAdapter;
import com.haptik_test.model.Message;
import com.haptik_test.views.fragment.ChatDetailFragment;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ChatAdapter.onUpdated {

    private Toolbar toolbar;
    private ChatPagerAdapter chatPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chatPagerAdapter = new ChatPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(chatPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //getting data from chat adapter (chat fragment)
    @Override
    public void updateChatDetails(HashMap<String, ArrayList<Message>> hashMap) {
        //sending data to chat details fragment
        ChatDetailFragment chatDetailFragment = (ChatDetailFragment) getSupportFragmentManager().getFragments().get(1);
        chatDetailFragment.updateChatDetails(hashMap);
    }
}
