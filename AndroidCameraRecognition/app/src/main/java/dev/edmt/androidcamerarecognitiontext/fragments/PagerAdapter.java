package dev.edmt.androidcamerarecognitiontext.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm,  int mNumOfTabs){
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabFragmentVisitor tabVisitor = new TabFragmentVisitor();
                return tabVisitor;
            case 1:
                TabFragmentAsset tabAsset = new TabFragmentAsset();
                return tabAsset;
            case 2:

                TabFragmentOptions tabOption = new TabFragmentOptions();
                return tabOption;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
