package com.jk.aliencontacts.adapter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jk.aliencontacts.R;
import com.jk.aliencontacts.data.CalllogInfo;
import com.jk.aliencontacts.data.ContactsProvider;
import com.jk.aliencontacts.data.EventInfo;
import com.jk.aliencontacts.data.PhoneInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PropertyAdapter extends BaseAdapter {

    protected Context mContext;
    protected LayoutInflater mInflater;

    private long mContactId;
    private Map<String, List> mPropertiesMap;

    public PropertyAdapter(Context context, long contactId) {
        mContactId = contactId;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

        List<PhoneInfo> phoneList = ContactsProvider.getInstance().getPhoneList(mContext,
                mContactId);
        List<EventInfo> eventList = ContactsProvider.getInstance()
                .getEventList(mContext, contactId);
        List<CalllogInfo> calllogList = ContactsProvider.getInstance().getCalllogList(mContext,
                phoneList.get(0).getNumber());

        mPropertiesMap = new HashMap<String, List>();
        mPropertiesMap.put("phone", phoneList);
        mPropertiesMap.put("event", eventList);
        mPropertiesMap.put("calllog", calllogList);
    }

    public List<PhoneInfo> getPhoneList() {
        return mPropertiesMap.get("phone");
    }

    @Override
    public int getCount() {
        int size = 0;
        Collection<List> collection = mPropertiesMap.values();
        Iterator<List> iterator = collection.iterator();
        while (iterator.hasNext()) {
            List list = iterator.next();
            if (list.size() != 0) {
                size += list.size() + 1;
            }           
        }
        return size - 1;
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
        List<PhoneInfo> phoneList = mPropertiesMap.get("phone");
        if (phoneList.size() != 0) {
            if (position < phoneList.size()) { // show phoneList
                convertView = mInflater.inflate(R.layout.item_phone, null);
                TextView tv = (TextView) convertView.findViewById(R.id.phone_content);
                tv.setText(phoneList.get(position).getNumber());
                return convertView;
            } else if (position == phoneList.size()) { // show divider
                convertView = mInflater.inflate(R.layout.item_divider, null);
                return convertView;
            }
            position = position - phoneList.size() - 1;
        }

        List<EventInfo> eventList = mPropertiesMap.get("event");
        if (eventList.size() != 0) {
            if (position < eventList.size()) { // show event
                convertView = mInflater.inflate(R.layout.item_event, null);
                TextView tv = (TextView) convertView.findViewById(R.id.event_content);
                tv.setText(eventList.get(position).getStartDate());
                return convertView;
            } else if (position == eventList.size()) { // show divider
                convertView = mInflater.inflate(R.layout.item_divider, null);
                return convertView;
            }
            position = position - eventList.size() - 1;
        }

        List<CalllogInfo> calllogList = mPropertiesMap.get("calllog");
        if (calllogList.size() != 0) {
            if (position < calllogList.size()) { // show calllog
                convertView = mInflater.inflate(R.layout.item_calllog, null);
                TextView tv = (TextView) convertView.findViewById(R.id.calllog_number);

                tv.setText(calllogList.get(position).getDate() + "  "
                        + calllogList.get(position).getDuration());
            }
        }
        return convertView;
    }
    
  
}
