package com.jk.aliencontacts.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.Toast;

public class ContactsProvider {

    static private ContactsProvider mSelf = null;

    // private Context mContext;
    private List<ContactInfo> mContactsList;

    static public ContactsProvider getInstance() {
        if (mSelf == null) {
            mSelf = new ContactsProvider();
        }
        return mSelf;
    }

    // public void setContext(Context context) {
    // mContext = context;
    // }

    private ContactsProvider() {
    }

    public List<ContactInfo> getContactsList() {
        if (mContactsList == null) {
            mContactsList = new ArrayList<ContactInfo>();
        }
        return mContactsList;
    }

    public boolean ensureCursor(Cursor cursor, Context context) {
        if (cursor == null) {
            Toast.makeText(context, "cursor is null!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cursor.getCount() == 0) {
            // Toast.makeText(context, "cursor count is 0!",
            // Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public List<PhoneInfo> getPhoneList(Context context, long contactId) {
        String[] projection = new String[] { Phone._ID, Phone.NUMBER, Phone.TYPE, Phone.LABEL };
        String selection = Data.MIMETYPE + "='" + CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'"
                + " AND " + Data.RAW_CONTACT_ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(contactId) };

        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(Phone.CONTENT_URI, projection, selection, selectionArgs,
                null);

        List<PhoneInfo> phoneList = new ArrayList<PhoneInfo>();
        if (!ensureCursor(cursor, context)) {
            return phoneList;
        }
      
        while (cursor.moveToNext()) {
            PhoneInfo phoneInfo = new PhoneInfo(cursor.getLong(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getString(3));
            phoneList.add(phoneInfo);
        }
        cursor.close();
        return phoneList;
    }

    public List<EventInfo> getEventList(Context context, long contactId) {
        String[] projection = new String[] { Event._ID, Event.START_DATE, Event.TYPE, Event.LABEL };
        String selection = Data.MIMETYPE + "='" + Event.CONTENT_ITEM_TYPE + "'" + " AND "
                + Data.RAW_CONTACT_ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(contactId) };

        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Data.CONTENT_URI, projection, selection,
                selectionArgs, null);

        List<EventInfo> eventList = new ArrayList<EventInfo>();
        if (!ensureCursor(cursor, context)) {
            return eventList;
        }
        
        while (cursor.moveToNext()) {
            EventInfo EventInfo = new EventInfo(cursor.getLong(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getString(3));
            eventList.add(EventInfo);
        }
        cursor.close();
        return eventList;
    }

    public List<CalllogInfo> getCalllogList(Context context, String phoneNum) {
        String[] projection = new String[] { Calls._ID, Calls.NUMBER, Calls.TYPE, Calls.DATE,
                Calls.DURATION, Calls.IS_READ };
        String selection = Calls.NUMBER + "=?";
        String[] selectionArgs = new String[] { phoneNum };

        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(Calls.CONTENT_URI, projection, selection, selectionArgs,
                null);

        List<CalllogInfo> calllogList = new ArrayList<CalllogInfo>();
        if (!ensureCursor(cursor, context)) {
            return calllogList;
        }

        while (cursor.moveToNext()) {
            CalllogInfo calllogInfo = new CalllogInfo(cursor.getLong(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getLong(3), cursor.getLong(4), cursor.getInt(5));
            calllogList.add(calllogInfo);
        }
        cursor.close();
        Collections.reverse(calllogList);
        return calllogList;
    }
}
