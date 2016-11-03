package com.example.chy.challenge.AboutResume;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chy.challenge.PopResume;
import com.example.chy.challenge.R;

import java.util.ArrayList;

/**
 * Created by 77588 on 2016/9/10.
 */
public class Objective extends Activity implements View.OnClickListener{
    private ImageView back,compelete;
    private TextView popFlag;
    private LinearLayout workProperty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_objective);
        initview();
    }

    private void initview() {
        back = (ImageView) findViewById(R.id.back );
        back.setOnClickListener(this);
        compelete = (ImageView) findViewById(R.id.compelete);
        compelete.setOnClickListener(this);
        popFlag = (TextView) findViewById(R.id.PopFlag);
        workProperty = (LinearLayout) findViewById(R.id.work_property);
        workProperty.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.work_property:
                ArrayList list = new ArrayList();
                list.add("工作性质");
                list.add("全职");
                list.add("兼职");
                PopResume popResume = new PopResume(this,list);
                popResume.showAsDropDown(popFlag,"");
                break;
            case R.id.compelete:
                finish();
                break;
            default:
                break;
        }
    }
}
