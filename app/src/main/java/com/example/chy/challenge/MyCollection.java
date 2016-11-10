package com.example.chy.challenge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.chy.challenge.Adepter.TalentAdepter;
import com.example.chy.challenge.Adepter.TalentResumeInfo;
import com.example.chy.challenge.NetInfo.UserRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77588 on 2016/11/8.
 */

public class MyCollection extends Activity implements View.OnClickListener {
    private List<TalentResumeInfo.DataBean> listData = new ArrayList<>();
    private List<TalentResumeInfo.DataBean.EducationBean> listEdu;
    private List<TalentResumeInfo.DataBean.ProjectBean> listPro;
    private List<TalentResumeInfo.DataBean.WorkBean> listWor;
    private Context mContext;
    private final int KEY = 1;
    private TalentAdepter talenadapter;
    private RelativeLayout back;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_collection);
        mContext = this;
        initview();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, TalentResumeMore.class);
                intent.putExtra("data", listData.get(position));
                startActivity(intent);
            }
        });
        new UserRequest(mContext, handler).GetFavoriteList("301", "2", KEY);
    }

    private void initview() {
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.collection_list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case KEY:
                    if (msg.obj != null) {
                        String result = (String) msg.obj;
                        listData.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if ("success".equals(jsonObject.optString("status"))) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                if (jsonArray != null && jsonArray.length() > 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject j = jsonArray.getJSONObject(i);
                                        TalentResumeInfo.DataBean talentResumeInfo = new TalentResumeInfo.DataBean();
                                        talentResumeInfo.setUserid(j.getString("userid"));
                                        talentResumeInfo.setRealname(j.getString("realname"));
                                        talentResumeInfo.setUsertype(j.getString("usertype"));
                                        talentResumeInfo.setPhone(j.getString("phone"));
                                        talentResumeInfo.setPassword(j.getString("password"));
                                        talentResumeInfo.setSex(j.getString("sex"));
                                        talentResumeInfo.setEmail(j.getString("email"));
                                        talentResumeInfo.setQq(j.getString("qq"));
                                        talentResumeInfo.setWeixin(j.getString("weixin"));
                                        talentResumeInfo.setPhone(j.getString("phone"));
                                        talentResumeInfo.setDevicestate(j.getString("devicestate"));
                                        talentResumeInfo.setCity(j.getString("city"));
                                        talentResumeInfo.setWeibo(j.getString("weibo"));
                                        talentResumeInfo.setWork_life(j.getString("work_life"));
                                        talentResumeInfo.setCompany(j.getString("company"));
                                        talentResumeInfo.setMyjob(j.getString("myjob"));
                                        talentResumeInfo.setResumes_id(j.getString("resumes_id"));
                                        talentResumeInfo.setWork_property(j.getString("work_property"));
                                        talentResumeInfo.setAddress(j.getString("address"));
                                        talentResumeInfo.setPosition_type(j.getString("position_type"));
                                        talentResumeInfo.setCategories(j.getString("categories"));
                                        talentResumeInfo.setWantsalary(j.getString("wantsalary"));
                                        talentResumeInfo.setJobstate(j.getString("jobstate"));
                                        talentResumeInfo.setAdvantage(j.getString("advantage"));
                                        listEdu = new ArrayList<>();
                                        listEdu.clear();
                                        JSONArray education = j.getJSONArray("education");
                                        for (int n = 0; n < education.length(); n++) {
                                            TalentResumeInfo.DataBean.EducationBean educationBean = new TalentResumeInfo.DataBean.EducationBean();
                                            JSONObject Edu = education.getJSONObject(n);
                                            educationBean.setUserid(Edu.getString("userid"));
                                            educationBean.setSchool(Edu.getString("school"));
                                            educationBean.setMajor(Edu.getString("major"));
                                            educationBean.setDegree(Edu.getString("degree"));
                                            educationBean.setTime(Edu.getString("time"));
                                            educationBean.setExperience(Edu.getString("experience"));
                                            educationBean.setCreate_time(Edu.getString("create_time"));
                                            listEdu.add(educationBean);
                                        }
                                        talentResumeInfo.setEducation(listEdu);
                                        listWor = new ArrayList<>();
                                        JSONArray work = j.getJSONArray("work");
                                        for (int n = 0; n < work.length(); n++) {
                                            TalentResumeInfo.DataBean.WorkBean workBean = new TalentResumeInfo.DataBean.WorkBean();
                                            JSONObject Wor = work.getJSONObject(n);
                                            workBean.setUserid(Wor.getString("userid"));
                                            workBean.setCompany_name(Wor.getString("company_name"));
                                            workBean.setCompany_industry(Wor.getString("company_industry"));
                                            workBean.setJobtype(Wor.getString("jobtype"));
                                            workBean.setSkill(Wor.getString("skill"));
                                            workBean.setWork_period(Wor.getString("work_period"));
                                            workBean.setCreate_time(Wor.getString("create_time"));
                                            workBean.setContent(Wor.getString("content"));
                                            listWor.add(workBean);
                                        }
                                        talentResumeInfo.setWork(listWor);
                                        listPro = new ArrayList<>();
                                        JSONArray project = j.getJSONArray("project");
                                        for (int n = 0; n < project.length(); n++) {
                                            TalentResumeInfo.DataBean.ProjectBean projectBean = new TalentResumeInfo.DataBean.ProjectBean();
                                            JSONObject Pro = project.getJSONObject(n);
                                            projectBean.setUserid(Pro.getString("userid"));
                                            projectBean.setProject_name(Pro.getString("project_name"));
                                            projectBean.setStart_time(Pro.getString("start_time"));
                                            projectBean.setEnd_time(Pro.getString("end_time"));
                                            projectBean.setDescription_project(Pro.getString("description_project"));
                                            projectBean.setCreate_time(Pro.getString("create_time"));
                                            listPro.add(projectBean);
                                        }
                                        talentResumeInfo.setProject(listPro);
                                        listData.add(talentResumeInfo);
                                    }
                                }
                                if (listData != null) {
                                    talenadapter = new TalentAdepter(mContext, R.layout.talent_adepter, listData);
                                    listView.setAdapter(talenadapter);
                                }
                            } else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    };
}
