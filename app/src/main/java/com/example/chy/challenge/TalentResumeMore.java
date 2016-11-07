package com.example.chy.challenge;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chy.challenge.Adepter.EducationAdepter;
import com.example.chy.challenge.Adepter.TalentResumeInfo;
import com.example.chy.challenge.Adepter.WorkAdepter;

/**
 * Created by 77588 on 2016/11/5.
 */

public class TalentResumeMore extends Activity implements View.OnClickListener{
    private Context mContext;
    private TalentResumeInfo.DataBean data;
    private Button btnGetPhone;
    private RelativeLayout back;
    private TextView realname,realname2,jobstate,city,work_life,degree,work_property,position_type,wantsalary,categories;
    private ListView eduListView,workListView;
    private TextView advantage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talent_resume_more);
        mContext = this;
        data = (TalentResumeInfo.DataBean) getIntent().getSerializableExtra("data");
        initview();
        initLoad();
    }

    private void initview() {
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        btnGetPhone = (Button) findViewById(R.id.btnGetPhone);
        btnGetPhone.setOnClickListener(this);

        realname = (TextView) findViewById(R.id.realname);
        realname2 = (TextView) findViewById(R.id.realname2);
        jobstate = (TextView) findViewById(R.id.jobstate);
        city = (TextView) findViewById(R.id.city);
        work_life = (TextView) findViewById(R.id.work_life);
        degree = (TextView) findViewById(R.id.degree);
        work_property = (TextView) findViewById(R.id.work_property);
        position_type = (TextView) findViewById(R.id.position_type);
        wantsalary = (TextView) findViewById(R.id.wantsalary);
        categories = (TextView) findViewById(R.id.categories);
        eduListView = (ListView) findViewById(R.id.edu_list_view);
        workListView = (ListView) findViewById(R.id.work_list_view);
        advantage = (TextView) findViewById(R.id.advantage);
    }

    private void initLoad() {
        realname.setText(data.getRealname());
        realname2.setText(data.getRealname());
        jobstate.setText(data.getJobstate());
        city.setText(data.getCity());
        work_life.setText(data.getWork_life());
        degree.setText(data.getEducation().get(0).getDegree());
        work_property.setText(data.getWork_property());
        position_type.setText(data.getPosition_type());
        wantsalary.setText(data.getWantsalary());
        categories.setText(data.getCategories());
        EducationAdepter eduAdepter = new EducationAdepter(mContext,R.layout.education_adepter,data.getEducation());
        eduListView.setAdapter(eduAdepter);
        WorkAdepter workAdepter = new WorkAdepter(mContext,R.layout.work_adepter,data.getWork());
        workListView.setAdapter(workAdepter);
        advantage.setText(data.getAdvantage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.btnGetPhone:

                break;
        }
    }
}
