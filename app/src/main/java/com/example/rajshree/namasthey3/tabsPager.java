package com.example.rajshree.namasthey3;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

public class tabsPager extends FragmentStatePagerAdapter{


    String[] titles= new String[]{"First","Contact", "Third"};

    public tabsPager(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
switch (position){

    case 0:
        BlankFragment bf = new BlankFragment();
        return bf;
    case 1:
        LstFragment listfrag= new LstFragment();
        return listfrag;
    case 2:
        BlankFragment bf3= new BlankFragment();
        return bf3;

}
        return null;
    }
    @Override
    public int getCount() {
        return 3;
    }
}
