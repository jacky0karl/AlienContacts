package com.jk.aliencontacts.ui;



import com.jk.aliencontacts.R;
import com.jk.aliencontacts.adapter.PropertyAdapter;
import com.jk.aliencontacts.data.ContactsProvider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.TextView;
import android.widget.ListView;

public class ProfileActivity extends ListActivity {

    private PropertyAdapter mPropertyAdapter;
    private long mContactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mContactId = getIntent().getLongExtra(ContactsFragment.CANTACT_ID, 0);
        mPropertyAdapter = new PropertyAdapter(this, mContactId);
        setListAdapter(mPropertyAdapter);
        init();
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
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

}
