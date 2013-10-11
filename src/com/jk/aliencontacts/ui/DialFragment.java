package com.jk.aliencontacts.ui;

import com.jk.aliencontacts.R;
import com.jk.aliencontacts.ui.widget.TextActionBar;
import com.jk.aliencontacts.ui.widget.TextActionBar.Action;
import com.jk.aliencontacts.ui.widget.TextActionBar.DrawableAction;
import com.jk.aliencontacts.ui.widget.TextActionBar.TextAction;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class DialFragment extends Fragment implements OnClickListener {

    TextActionBar actionBar;
    private PopupWindow menuWindow = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dial, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        actionBar = (TextActionBar) getActivity().findViewById(R.id.actionbar);
        actionBar.setTitle("全部记录");

        actionBar.setOnTitleClickListener(this);
        initPopupMenu();
    }

    @Override
    public void onClick(View v) {
        popupMenu();
    }

    private void initPopupMenu() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.popup_menu, null);

        menuWindow = new PopupWindow(layout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        menuWindow.setFocusable(true);
        menuWindow.setOutsideTouchable(true);
        menuWindow.update();
        menuWindow.setBackgroundDrawable(new BitmapDrawable());

        TextView mMainbtn = (TextView) layout.findViewById(R.id.menu_main_btn);
        TextView mHistorybtn = (TextView) layout.findViewById(R.id.menu_history_btn);
        TextView mHelpbtn = (TextView) layout.findViewById(R.id.menu_help_btn);

        mMainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menuWindow.dismiss();
            }
        });
        mHelpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menuWindow.dismiss();
            }
        });
        mHistorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menuWindow.dismiss();
            }
        });
    }

    private void popupMenu() {
        if (menuWindow.isShowing()) {
            menuWindow.dismiss();
        } else {
            menuWindow.showAsDropDown(actionBar);
        }
    }
}
