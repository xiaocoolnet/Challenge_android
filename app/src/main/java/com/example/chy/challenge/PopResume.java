package com.example.chy.challenge;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.chy.challenge.Fragment.Talent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77588 on 2016/9/10.
 */
public class PopResume implements PopupWindow.OnDismissListener{
    private String objectid;
    private  ListView experience;
    private Button cancel,complete;
    private Talent activity;
    private PopupWindow popupwindow;
    private Context mcontext;
    private List<String> item;
    private ArrayAdapter<String> itemAdepter;

    public PopResume(Context mcontext,ArrayList list) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.resume_popupwindow,null);
        initview(view);

        this.item = list;
        itemAdepter = new ArrayAdapter<String>(mcontext,android.R.layout.simple_list_item_1,item);
        experience.setAdapter(itemAdepter);


        popupwindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置动画效果
        popupwindow.setAnimationStyle(R.style.popWindow_anim_style);
        popupwindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        popupwindow.setOnDismissListener(this);
    }

    private void initview(View view) {
        experience = (ListView) view.findViewById(R.id.popExperience);
    }

    @Override
    public void onDismiss() {

    }
    /**
     * 消除弹窗
     */
    public void dissmiss() {
        popupwindow.dismiss();
    }

    /**
     * 弹窗显示的位置
     */
    public void showAsDropDown(View parent, String objectid) {
        this.objectid = objectid;
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        WindowManager manager = (WindowManager) mcontext.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int screenHeight = display.getHeight();
        popupwindow.showAtLocation(parent, Gravity.BOTTOM, 0, screenHeight - location[1]);
        popupwindow.setFocusable(true);
        popupwindow.setOutsideTouchable(true);
        popupwindow.update();
    }

}
