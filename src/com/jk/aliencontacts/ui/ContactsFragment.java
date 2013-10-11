package com.jk.aliencontacts.ui;

import com.jk.aliencontacts.R;
import com.jk.aliencontacts.adapter.ContactsAdapter;
import com.jk.aliencontacts.ui.widget.AlphaSideBar;
import com.jk.aliencontacts.ui.widget.AlphaSideBar.OnTouchingLetterChangedListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ContactsFragment extends ListFragment implements OnTouchingLetterChangedListener {

    static public String CANTACT_ID = "contact_id";
    
    private TextView mLetter;
    private AlphaSideBar mSideBar;
    private ContactsAdapter mAdapter;
    private ShowLetterHelper mShowLetterHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    } 

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLetter = (TextView) getActivity().findViewById(R.id.letter);
        mLetter.setVisibility(View.GONE);
        mSideBar = (AlphaSideBar) getActivity().findViewById(R.id.sidebar);
        mSideBar.setOnTouchingLetterChangedListener(this);
        mAdapter = new ContactsAdapter(getActivity());
        setListAdapter(mAdapter);

        // list.setTextFilterEnabled(true);

    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        long contactId = (Long) v.getTag(R.id.tag_id);

        Intent in = new Intent(getActivity(), ProfileActivity.class);
        in.putExtra(CANTACT_ID, contactId);
        getActivity().startActivity(in);
    }

    @Override
    public void onTouchingLetterChanged(String chosenLetter) {
        mLetter.setText(chosenLetter);
        mLetter.setVisibility(View.VISIBLE);
        setListPosition(chosenLetter);

        if (mShowLetterHelper == null) {
            mShowLetterHelper = new ShowLetterHelper();
        }
        mShowLetterHelper.removeMessages(0);
        mShowLetterHelper.sendEmptyMessageDelayed(0, 1000);
    }

    public void setListPosition(String letter) {
        int position = 0;
        for (int i = 0; i < mAdapter.getContactsList().size(); i++) {
            if (mAdapter.getContactsList().get(i).getFirstLetter().equalsIgnoreCase(letter)) {
                position = i;
                break;
            }
        }

        if (position > 0) {
            getListView().setSelection(position);
        }
    }

    public class ShowLetterHelper extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mLetter.setVisibility(View.GONE);
        }
    }
}
