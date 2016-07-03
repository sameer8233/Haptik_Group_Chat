package com.haptik_test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.haptik_test.utils.Constants;
import com.haptik_test.views.fragment.ChatDetailFragment;
import com.haptik_test.views.fragment.ChatFragment;

/**
 * Created by Sameer on 7/2/2016.
 */

public class ChatPagerAdapter extends FragmentPagerAdapter {


    public ChatPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ChatFragment.newInstance();

            case 1:
                return ChatDetailFragment.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return Constants.N0_OF_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Constants.FIRST_TAB_TITLE;
            case 1:
                return Constants.SECOND_TAB_TITLE;


        }
        return null;
    }
}
