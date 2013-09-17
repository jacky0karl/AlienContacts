package com.jk.aliencontacts.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

@Deprecated
public class MultiListActivity extends Activity implements OnItemClickListener {
    private Map<String, ListView> mListsView;
    private Map<String, BaseAdapter> mListsAdapter;

    private Handler mHandler = new Handler();
    private Runnable mRequestFocus = new Runnable() {
        public void run() {
            Collection<ListView> collection = mListsView.values();
            Iterator<ListView> iterator = collection.iterator();
            while (iterator.hasNext()) {
                ListView lv = iterator.next();
                lv.focusableViewAvailable(lv);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListsView = new HashMap<String, ListView>();
        mListsAdapter = new HashMap<String, BaseAdapter>();
    }

    public void addList(String key, ListView listView, BaseAdapter adapter) {
        mListsView.put(key, listView);
        mListsAdapter.put(key, adapter);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void deleteList(String key) {
        mListsView.remove(key);
        mListsAdapter.remove(key);
    }

    public ListView getListView(String key) {
        return mListsView.get(key);
    }

    public BaseAdapter getAdapter(String key) {
        return mListsAdapter.get(key);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRequestFocus);
        super.onDestroy();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mHandler.post(mRequestFocus);
    }
}
