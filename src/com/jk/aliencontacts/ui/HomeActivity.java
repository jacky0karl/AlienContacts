package com.jk.aliencontacts.ui;

import java.util.List;

import com.jk.aliencontacts.R;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TabPageIndicator.OnTabReselectedListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class HomeActivity extends FragmentActivity implements OnTabReselectedListener {

    // Tab tags
    public static final String DIAL = "dial";
    public static final String CONTACTS = "contacts";
    public static final String SMS = "sms";
    public static final String SETTINGS = "settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        indicator.setOnTabReselectedListener(this);
    }

    class HomeAdapter extends FragmentPagerAdapter {
        public HomeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment item = null;
            switch (position) {
            case 0:
                item = new DialFragment();
                break;
            case 1:
                item = new ContactsFragment();
                break;
            }
            return item;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            switch (position) {
            case 0:
                title = DIAL;
                break;
            case 1:
                title = CONTACTS;
                break;
            }
            return title;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public void onTabReselected(int position) {
        if (position == 0) {          
            List<Fragment> f = getSupportFragmentManager().getFragments();
            for (int i=0; i<f.size(); i++) {
                if (f.get(i) instanceof DialFragment) {
                    ((DialFragment) f.get(i)).dialpadToggle();
                }
            }
        }
    }
}
