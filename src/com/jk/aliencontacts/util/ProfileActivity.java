package com.jk.aliencontacts.util;

import com.jk.aliencontacts.R;
import com.jk.aliencontacts.adapter.CalllogAdapter;
import com.jk.aliencontacts.adapter.PropertyAdapter;
import com.jk.aliencontacts.data.ContactsProvider;
import com.jk.aliencontacts.data.PhoneInfo;
import com.jk.aliencontacts.ui.ContactsFragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ListView;

@Deprecated
public class ProfileActivity extends MultiListActivity {
    private long mContactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mContactId = getIntent().getLongExtra(ContactsFragment.CANTACT_ID, 0);

//        ListView propertyList = (ListView) findViewById(R.id.property_list);
//        PropertyAdapter propertyAdapter = new PropertyAdapter(this, mContactId);       
//
//        List<PhoneInfo> phoneList = propertyAdapter.getPhoneList();
//        ListView calllogList = (ListView) findViewById(R.id.calllog_list);
//        CalllogAdapter calllogAdapter = new CalllogAdapter(this, phoneList.get(0).getNumber());
//        
//        addList("phone", propertyList, propertyAdapter);
//        addList("calllog", calllogList, calllogAdapter);
//        init();
    }

    public void init() {
        String[] projection = new String[] { Contacts.DISPLAY_NAME, Contacts.SORT_KEY_PRIMARY };
        String selection = "(" + Contacts._ID + "=" + mContactId + ")";

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(Contacts.CONTENT_URI, projection, selection, null,
                Contacts.SORT_KEY_PRIMARY);

        if (!ContactsProvider.getInstance().ensureCursor(cursor, this)) {
            return;
        }
        cursor.moveToNext();

        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setText(cursor.getString(0));

        cursor.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    // @Override
    // protected void onRestoreInstanceState(Bundle state) {
    // ensureList();
    // super.onRestoreInstanceState(state);
    // }

    // private void ensureList() {
    // if (mPropertyList != null && mCalllogList != null) {
    // return;
    // }
    // setContentView(R.layout.activity_profile);
    // }
}
