package com.example.chy.challenge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Adepter.EducationAdepter;
import com.example.chy.challenge.Adepter.TalentResumeInfo;
import com.example.chy.challenge.Adepter.WorkAdepter;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.Utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 77588 on 2016/11/5.
 */

public class TalentResumeMore extends Activity implements View.OnClickListener {
    private final int KEY_CHECK_COLLECTION = 1;
    private final int KEY_ADD_FAVORITE = 2;
    private ImageView collection;
    private Context mContext;
    private TalentResumeInfo.DataBean data;
    private Button btnGetPhone;
    private RelativeLayout back;
    private TextView realname, realname2, jobstate, city, work_life, degree, work_property, position_type, wantsalary, categories;
    private ListView eduListView, workListView;
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
        collection = (ImageView) findViewById(R.id.collection);
        collection.setOnClickListener(this);

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
        EducationAdepter eduAdepter = new EducationAdepter(mContext, R.layout.education_adepter, data.getEducation());
        eduListView.setAdapter(eduAdepter);
        WorkAdepter workAdepter = new WorkAdepter(mContext, R.layout.work_adepter, data.getWork());
        workListView.setAdapter(workAdepter);
        advantage.setText(data.getAdvantage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btnGetPhone:

                break;
            case R.id.collection:
                new UserRequest(mContext,handler).CheckHadFavorite(data.getUserid(), data.getResumes_id(), "2", KEY_CHECK_COLLECTION);
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case KEY_CHECK_COLLECTION:
                    LogUtils.e("Tip","开始检测");
                    String flag = (String) msg.obj;
                    try {
                        JSONObject jsonObject = new JSONObject(flag);
                        if ("success".equals(jsonObject.optString("status"))) {
                            Toast.makeText(mContext, "该简历已收藏", Toast.LENGTH_SHORT).show();
                        } else {
                            new UserRequest(mContext, handler).AddFavoriteList(data.getUserid(), data.getResumes_id(), "2", KEY_ADD_FAVORITE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case KEY_ADD_FAVORITE:
                    LogUtils.e("Tip","开始添加");
                    String result = (String) msg.obj;
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if ("had".equals(jsonObject.optString("data"))){
                                Toast.makeText(mContext,"该简历已收藏",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mContext,"收藏成功",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
