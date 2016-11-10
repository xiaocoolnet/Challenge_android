package com.example.chy.challenge;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.chy.challenge.Adepter.HiringRecordAdepter;
import com.example.chy.challenge.Models.CompanyInfo;

import java.util.List;

/**
 * Created by 77588 on 2016/11/10.
 */

public class MineMyHiringRecord extends Activity{
    private Context mContext;
    private RelativeLayout back;
    private ListView listView;
    private List<CompanyInfo.DataBean.JobsBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_my_hiring_record);
        mContext = this;
        initview();
        CompanyInfo.DataBean dataBean = (CompanyInfo.DataBean) getIntent().getSerializableExtra("data");
        data = dataBean.getJobs();
        HiringRecordAdepter hiringRecordAdepter = new HiringRecordAdepter(mContext,R.layout.my_hiring_adepter,data);
        listView.setAdapter(hiringRecordAdepter);
    }

    private void initview() {
        listView = (ListView) findViewById(R.id.hiring_list);
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView = (ListView) findViewById(R.id.hiring_list);
    }
}