package app.cpe.mushroom.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import app.cpe.mushroom.ui.fragment.FragmentStatusBaked;
import app.cpe.mushroom.ui.fragment.FragmentStatusPlant;

/**
 * Created by DEV on 1/10/2560.
 */

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence titles[];
    //private int NumbOfTabs;

    public FragmentPagerAdapter(FragmentManager fm,CharSequence[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return FragmentStatusBaked.newInstance();
            case 1: return FragmentStatusPlant.newInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
