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
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
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

    public void initDialpad() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialpad, null);

        Button b1 = (Button) layout.findViewById(R.id.num1);

        SpannableStringBuilder style = new SpannableStringBuilder("2 ABC");
        style.setSpan(new RelativeSizeSpan((float) 1.5), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        style.setSpan(new RelativeSizeSpan((float) 0.5), 2, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        b1.setText(style);

        PopupWindow menuWindow1 = new PopupWindow(layout, LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        // menuWindow1.setFocusable(true);
        // menuWindow1.setOutsideTouchable(true);
        // menuWindow1.update();
        // menuWindow1.setBackgroundDrawable(new BitmapDrawable());

        menuWindow1.showAsDropDown(actionBar);
    }
}
