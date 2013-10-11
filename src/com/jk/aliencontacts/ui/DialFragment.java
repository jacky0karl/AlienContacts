package com.jk.aliencontacts.ui;

import com.jk.aliencontacts.R;
import com.jk.aliencontacts.ui.widget.TextActionBar;
import com.jk.aliencontacts.ui.widget.TextActionBar.Action;
import com.jk.aliencontacts.ui.widget.TextActionBar.DrawableAction;
import com.jk.aliencontacts.ui.widget.TextActionBar.TextAction;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DialFragment extends Fragment implements OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_dial, container, false);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextActionBar actionBar = (TextActionBar) getActivity().findViewById(R.id.actionbar);      
        actionBar.setTitle("全部记录");

        Intent in = new Intent(this.getActivity(), ProfileActivity.class);
        in.putExtra("contact_id", (long) 270);

        DrawableAction shareAction = new DrawableAction(this.getActivity(), in, R.drawable.icon);
        actionBar.addAction(shareAction);
        
        TextAction shareAction1 = new TextAction(this.getActivity(), in, "设置");
        actionBar.addAction(shareAction1);
        
        actionBar.setOnTitleClickListener(this);
        //actionBar.setHomeAction(shareAction);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "全部记录 hah", Toast.LENGTH_SHORT).show();
        
    }
}
