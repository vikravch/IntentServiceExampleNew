package ua.com.kistudio.intentserviceexample.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Вiталя on 16.03.2016.
 */
public class SectionPagerAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT = 3;

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        FragmentListView fragmentListView = new FragmentListView();
        return fragmentListView;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Contacts";
            case 1:
                return "Profile";
            case 2:
                return "Feedback";
        }
        return "";
    }
}
