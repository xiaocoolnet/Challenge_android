package com.example.chy.challenge.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chy.challenge.CompanyInformation;
import com.example.chy.challenge.MineMyHiringRecord;
import com.example.chy.challenge.Models.CompanyInfo;
import com.example.chy.challenge.MyCollection;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77588 on 2016/9/1.
 */
public class MineForCompany extends Fragment implements View.OnClickListener {
    private List<CompanyInfo.DataBean.JobsBean> listJob;
    private CompanyInfo.DataBean companyInfo;
    private LinearLayout company_information, myCollection, myHiringRecord;
    private Intent intent;
    private final int KEY = 1;
    private Context mContext;

    public interface btnSettingListener {
        void onBtnSettingClick();
    }

    private ImageView setting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine_company, container, false);
        initview(rootView);
        mContext = getActivity();
        return rootView;
    }

    private void initview(View rootView) {
        setting = (ImageView) rootView.findViewById(R.id.setting);
        setting.setOnClickListener(this);
        company_information = (LinearLayout) rootView.findViewById(R.id.company_information);
        company_information.setOnClickListener(this);
        myCollection = (LinearLayout) rootView.findViewById(R.id.my_collection);
        myCollection.setOnClickListener(this);
        myHiringRecord = (LinearLayout) rootView.findViewById(R.id.my_hiring_record);
        myHiringRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.setting:
                if (getActivity() instanceof btnSettingListener) {
                    ((btnSettingListener) getActivity()).onBtnSettingClick();
                }
                break;
            case R.id.company_information:
                intent = new Intent(mContext, CompanyInformation.class);
                if (companyInfo == null) {
                    new UserRequest(mContext, handler).GetMyCompanyInfo("301", KEY);
                } else try {
                    intent.putExtra("data", companyInfo);
                    startActivity(intent);
                } catch (Exception e) {
                    new UserRequest(mContext, handler).GetMyCompanyInfo("301", KEY);
                }
                break;
            case R.id.my_collection:
                startActivity(new Intent(mContext, MyCollection.class));
                break;
            case R.id.my_hiring_record:
                intent = new Intent(mContext, MineMyHiringRecord.class);
                if (companyInfo == null) {
                    new UserRequest(mContext, handler).GetMyCompanyInfo("301", KEY);
                } else try {
                    intent.putExtra("data", companyInfo);
                    startActivity(intent);
                } catch (Exception e) {
                    new UserRequest(mContext, handler).GetMyCompanyInfo("301", KEY);
                }
                break;
            default:
                break;
        }
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case KEY:
                    try {
                        JSONObject jsonObject = new JSONObject((String) msg.obj);
                        if ("success".equals(jsonObject.optString("status"))) {
                            JSONObject jdata = jsonObject.getJSONObject("data");
                            if (jdata != null && jdata.length() > 0) {
                                companyInfo = new CompanyInfo.DataBean();
                                companyInfo.setLogo(jdata.getString("logo"));
                                companyInfo.setCompanyid(jdata.getString("companyid"));
                                companyInfo.setCompany_name(jdata.getString("company_name"));
                                companyInfo.setCompany_web(jdata.getString("company_web"));
                                companyInfo.setIndustry(jdata.getString("industry"));
                                companyInfo.setCount(jdata.getString("count"));
                                companyInfo.setFinancing(jdata.getString("financing"));
                                companyInfo.setCreat_time(jdata.getString("creat_time"));
                                companyInfo.setAuthentication(jdata.getString("authentication"));
                                companyInfo.setCompany_score(jdata.getString("company_score"));
                                companyInfo.setDistance(jdata.getString("distance"));
                                companyInfo.setCom_introduce(jdata.getString("com_introduce"));
                                companyInfo.setProdute_info(jdata.getString("produte_info"));
                                listJob = new ArrayList<CompanyInfo.DataBean.JobsBean>();
                                JSONArray jobs = jdata.getJSONArray("jobs");
                                for (int i = 0; i < jobs.length(); i++) {
                                    JSONObject Job = jobs.getJSONObject(i);
                                    CompanyInfo.DataBean.JobsBean jobsBean = new CompanyInfo.DataBean.JobsBean();
                                    jobsBean.setRealname(Job.getString("realname"));
                                    jobsBean.setPhoto(Job.getString("photo"));
                                    jobsBean.setMyjob(Job.getString("myjob"));
                                    jobsBean.setJobid(Job.getString("jobid"));
                                    jobsBean.setUserid(Job.getString("userid"));
                                    jobsBean.setJobtype(Job.getString("jobtype"));
                                    jobsBean.setTitle(Job.getString("title"));
                                    jobsBean.setSkill(Job.getString("skill"));
                                    jobsBean.setSalary(Job.getString("salary"));
                                    jobsBean.setExperience(Job.getString("experience"));
                                    jobsBean.setEducation(Job.getString("education"));
                                    jobsBean.setCity(Job.getString("city"));
                                    jobsBean.setAddress(Job.getString("address"));
                                    jobsBean.setDescription_job(Job.getString("description_job"));
                                    jobsBean.setCreate_time(Job.getString("create_time"));
                                    jobsBean.setWork_property(Job.getString("work_property"));
                                    listJob.add(jobsBean);
                                }
                                companyInfo.setJobs(listJob);
                                intent.putExtra("data", companyInfo);
                                startActivity(intent);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
}