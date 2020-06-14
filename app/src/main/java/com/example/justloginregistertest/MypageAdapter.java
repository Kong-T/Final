package com.example.justloginregistertest;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MypageAdapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"主页","发现","校园信息"};

    public MypageAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FirstFragment();
        }else if(position == 1){
            return new ShowFragment();
        }else{
            return new InfoFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
