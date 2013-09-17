package com.jk.aliencontacts.adapter;

import java.util.ArrayList;
import java.util.List;

import com.jk.aliencontacts.R;
import com.jk.aliencontacts.data.CalllogInfo;
import com.jk.aliencontacts.data.ContactsProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalllogAdapter extends BaseAdapter {
    protected Context mContext;
    protected LayoutInflater mInflater;
    
    //private long mContactId;
    private List<CalllogInfo> mCalllogList;
    
    public CalllogAdapter(Context context, String phoneNum) {
        //mContactId = contactId;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        
        mCalllogList = ContactsProvider.getInstance().getCalllogList(context, phoneNum);
    }

    @Override
    public int getCount() {
        return mCalllogList.size();
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
        convertView = mInflater.inflate(R.layout.item_calllog, null);
        TextView tv = (TextView) convertView.findViewById(R.id.calllog_number);
        tv.setText(mCalllogList.get(position).getDate() + "  " + mCalllogList.get(position).getDuration());
        return convertView;
    }

}
