package com.look.book.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentStateAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list=new ArrayList<>();

    public void setList(List<Fragment> list) {
        this.list = list;
    }
    public FragmentStateAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
}
