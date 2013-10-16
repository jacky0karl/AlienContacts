package com.jk.aliencontacts.ui;

import com.jk.aliencontacts.R;
import com.jk.aliencontacts.ui.widget.TextActionBar;
import com.jk.aliencontacts.ui.widget.TextActionBar.Action;
import com.jk.aliencontacts.ui.widget.TextActionBar.DrawableAction;
import com.jk.aliencontacts.ui.widget.TextActionBar.TextAction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

public class DialFragment extends Fragment implements OnClickListener {

    TextActionBar actionBar;
    private PopupWindow menuWindow = null;
    SlidingDrawer mSlidingDrawer = null;

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
        initDialpad();
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

    private void initDialpad() {
        Button b1 = (Button) getActivity().findViewById(R.id.num1);
        customizeButton(b1, "1     ");
        Button b2 = (Button) getActivity().findViewById(R.id.num2);
        customizeButton(b2, "2 ABC ");
        Button b3 = (Button) getActivity().findViewById(R.id.num3);
        customizeButton(b3, "3 DEF ");
        Button b4 = (Button) getActivity().findViewById(R.id.num4);
        customizeButton(b4, "4 GHI ");
        Button b5 = (Button) getActivity().findViewById(R.id.num5);
        customizeButton(b5, "5 JKL ");
        Button b6 = (Button) getActivity().findViewById(R.id.num6);
        customizeButton(b6, "6 MNO ");
        Button b7 = (Button) getActivity().findViewById(R.id.num7);
        customizeButton(b7, "7 PQRS");
        Button b8 = (Button) getActivity().findViewById(R.id.num8);
        customizeButton(b8, "8 TUV ");
        Button b9 = (Button) getActivity().findViewById(R.id.num9);
        customizeButton(b9, "9 WXYZ");
        Button bStar = (Button) getActivity().findViewById(R.id.num_star);
        customizeButton(bStar, "*     ");
        Button b0 = (Button) getActivity().findViewById(R.id.num0);
        customizeButton(b0, "0     ");
        Button b_ = (Button) getActivity().findViewById(R.id.num_);
        customizeButton(b_, "#     ");

        mSlidingDrawer = (SlidingDrawer) getActivity().findViewById(R.id.slidingDrawer);
        mSlidingDrawer.open();
    }

    private void customizeButton(Button button, String text) {
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new RelativeSizeSpan((float) 2), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.GRAY), 2, 6, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //style.setSpan(new RelativeSizeSpan((float) 0.5), 2, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        button.setText(style);
    }

    public void dialpadToggle() {
        mSlidingDrawer.toggle();
    }
}
