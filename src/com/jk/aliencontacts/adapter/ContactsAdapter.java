package com.jk.aliencontacts.adapter;

import java.util.ArrayList;
import java.util.List;

import com.jk.aliencontacts.R;
import com.jk.aliencontacts.data.ContactInfo;
import com.jk.aliencontacts.data.ContactsProvider;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ContactsAdapter extends BaseAdapter {

    protected Context mContext;
    protected LayoutInflater mInflater;

    public ContactsAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        init();
    }

    public List<ContactInfo> getContactsList() {
        return ContactsProvider.getInstance().getContactsList();
    }

    @Override
    public int getCount() {
        return getContactsList().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_contact, null);

            holder = new ViewHolder();
            holder.letterTitle = (TextView) convertView.findViewById(R.id.letter_title);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.displayname = (TextView) convertView.findViewById(R.id.displayname);
            convertView.setTag(R.id.tag_holder, holder);
        } else {
            holder = (ViewHolder) convertView.getTag(R.id.tag_holder);
        }

        convertView.setTag(R.id.tag_id, getContactsList().get(position).getId());
        holder.displayname.setText(getContactsList().get(position).mDisplayName);
        String letter = getContactsList().get(position).getFirstLetter();
        if (position == 0) {
            holder.letterTitle.setVisibility(View.VISIBLE);
            holder.letterTitle.setText(letter);
        } else {
            String lastLetter = getContactsList().get(position - 1).getFirstLetter();
            if (letter.equalsIgnoreCase(lastLetter)) {
                holder.letterTitle.setVisibility(View.GONE);
            } else {
                holder.letterTitle.setVisibility(View.VISIBLE);
                holder.letterTitle.setText(letter);
            }
        }
        return convertView;
    }

    public final class ViewHolder {
        TextView letterTitle;
        ImageView avatar;
        TextView displayname;
    }

    public void init() {
        String[] projection = new String[] { Contacts._ID, Contacts.DISPLAY_NAME,
                Contacts.SORT_KEY_PRIMARY };
        String selection = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
                + Contacts.HAS_PHONE_NUMBER + "=1))";

        ContentResolver resolver = mContext.getContentResolver();
        long a = System.currentTimeMillis();
        Cursor cursor = resolver.query(Contacts.CONTENT_URI, projection, selection, null,
                Contacts.SORT_KEY_PRIMARY);

        if (!ContactsProvider.getInstance().ensureCursor(cursor, mContext)) {
            return;
        }

        while (cursor.moveToNext()) {
            // Log.i("Alien***************", cursor.getString(0) + " | " + cursor.getString(1));
            ContactInfo contactInfo = new ContactInfo(cursor.getLong(0), cursor.getString(1),
                    cursor.getString(2));
            getContactsList().add(contactInfo);
        }
        cursor.close();
        long b = System.currentTimeMillis();
        Log.i("Alien***************time", String.valueOf(b - a));
    }
}
