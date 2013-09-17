package com.jk.aliencontacts.ui;

import com.jk.aliencontacts.R;
import com.jk.aliencontacts.util.DummyTabContent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class HomeActivity extends FragmentActivity implements OnTabChangeListener {

    // Tab tags
    public static final String DIAL = "dial";
    public static final String CONTACTS = "contacts";
    public static final String SMS = "sms";
    public static final String SETTINGS = "settings";

    private TabHost mTabHost;

    private DialFragment mDialFragment;
    private ContactsFragment mContactsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabHost.setOnTabChangedListener(this);
        initTab();
    }

    public void initTab() {
        TabHost.TabSpec dail = mTabHost.newTabSpec(DIAL);
        dail.setIndicator(DIAL);
        dail.setContent(new DummyTabContent(getBaseContext()));
        mTabHost.addTab(dail);

        TabHost.TabSpec contacts = mTabHost.newTabSpec(CONTACTS);
        contacts.setIndicator(CONTACTS);
        contacts.setContent(new DummyTabContent(getBaseContext()));
        mTabHost.addTab(contacts);
        mTabHost.setCurrentTab(1);
    }

    public void TabDial(FragmentTransaction ft) {
        if (mDialFragment == null) {
            mDialFragment = new DialFragment();
            ft.add(R.id.realtabcontent, mDialFragment, DIAL);
        } else {
            ft.attach(mDialFragment);
        }
    }

    public void TabContacts(FragmentTransaction ft) {
        if (mContactsFragment == null) {
            mContactsFragment = new ContactsFragment();
            ft.add(R.id.realtabcontent, mContactsFragment, CONTACTS);
        } else {
            ft.attach(mContactsFragment);
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        Log.i("Alien***************onTabChanged", String.valueOf(tabId));
        FragmentManager fm = getSupportFragmentManager();     
        FragmentTransaction ft = fm.beginTransaction();

        if (mDialFragment != null) {
            Log.i("Alien***************dialFragment", String.valueOf(tabId));
            ft.detach(mDialFragment);
        }            

        if (mContactsFragment != null) {
            Log.i("Alien***************contactsFragment", String.valueOf(tabId));
            ft.detach(mContactsFragment);
        }           

        if (tabId.equalsIgnoreCase(DIAL)) {
            TabDial(ft);
        } else if (tabId.equalsIgnoreCase(CONTACTS)) {
            TabContacts(ft);
        }
        ft.commit();
    }
}
