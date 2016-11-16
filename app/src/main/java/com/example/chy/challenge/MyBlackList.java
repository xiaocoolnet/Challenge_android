package com.example.chy.challenge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.chy.challenge.Adepter.MyBlackListAdepter;
import com.example.chy.challenge.Models.BlackListInfo;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.Utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77588 on 2016/11/15.
 */

public class MyBlackList extends Activity implements View.OnClickListener{
    private MyBlackList myBlackList = this;
    private ListView layoutBlackList;
//    private BlackListInfo.DataBean blackList;
    private List<BlackListInfo.DataBean> bList = new ArrayList<>();
    private List<BlackListInfo.DataBean.BlacksBean.EducationBean> eduList = new ArrayList<>();
    private List<BlackListInfo.DataBean.BlacksBean.WorkBean> workList = new ArrayList<>();
    private List<BlackListInfo.DataBean.BlacksBean.ProjectBean> proList = new ArrayList<>();
    private RelativeLayout back;
    private Context mContext;
    private final int KEY_GET_BLACK_LIST = 1;
    private final int KEY_DELETE_BLACK_LIST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_black_list);
        mContext = this;
        initview();
        initload();
    }

    private void initload() {
        new UserRequest(mContext, handler).GetBlackList("301","2",KEY_GET_BLACK_LIST);
    }

    private void initview() {
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        layoutBlackList = (ListView) findViewById(R.id.black_list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
    public void delete(String userid,String type,String blackid){
        LogUtils.e("Tip",userid);
        new UserRequest(mContext,handler).DelBlackList(userid,type,blackid,KEY_DELETE_BLACK_LIST);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what){
                case KEY_GET_BLACK_LIST:
                    try {
                        bList = new ArrayList<>();
                        JSONObject json = new JSONObject((String) msg.obj);
                        if ("success".equals(json.optString("status"))){
                            JSONArray jBlackList = json.getJSONArray("data");
                            for (int i = 0;i<jBlackList.length();i++){
                                BlackListInfo.DataBean blackList = new BlackListInfo.DataBean();
                                JSONObject jBL = jBlackList.getJSONObject(i);
                                blackList.setId(jBL.getString("id"));
                                blackList.setUserid(jBL.getString("userid"));
                                blackList.setBlackid(jBL.getString("blackid"));
                                blackList.setCreate_time(jBL.getString("create_time"));
                                blackList.setStatus(jBL.getString("status"));
                                blackList.setReason(jBL.getString("reason"));
                                blackList.setType(jBL.getString("type"));
                                BlackListInfo.DataBean.BlacksBean blacks = new BlackListInfo.DataBean.BlacksBean();
                                JSONObject jBlacks = jBL.getJSONObject("blacks");
                                blacks.setUserid(jBlacks.getString("userid"));
                                blacks.setRealname(jBlacks.getString("realname"));
                                blacks.setUsertype(jBlacks.getString("usertype"));
                                blacks.setPhone(jBlacks.getString("phone"));
                                blacks.setPassword(jBlacks.getString("password"));
                                blacks.setSex(jBlacks.getString("sex"));
                                blacks.setEmail(jBlacks.getString("email"));
                                blacks.setQq(jBlacks.getString("qq"));
                                blacks.setWeixin(jBlacks.getString("weixin"));
                                blacks.setPhoto(jBlacks.getString("photo"));
                                blacks.setDevicestate(jBlacks.getString("devicestate"));
                                blacks.setCity(jBlacks.getString("city"));
                                blacks.setWeibo(jBlacks.getString("weibo"));
                                blacks.setWork_life(jBlacks.getString("work_life"));
                                blacks.setCompany(jBlacks.getString("company"));
                                blacks.setMyjob(jBlacks.getString("myjob"));
                                blacks.setResumes_id(jBlacks.getString("resumes_id"));
                                blacks.setWork_property(jBlacks.getString("work_property"));
                                blacks.setAddress(jBlacks.getString("address"));
                                blacks.setPosition_type(jBlacks.getString("position_type"));
                                blacks.setCategories(jBlacks.getString("categories"));
                                blacks.setWantsalary(jBlacks.getString("wantsalary"));
                                blacks.setJobstate(jBlacks.getString("jobstate"));
                                blacks.setAdvantage(jBlacks.getString("advantage"));
                                JSONArray jEdu = jBlacks.getJSONArray("education");
                                for (int n = 0;n<jEdu.length();n++){
                                    JSONObject aEdu = jEdu.getJSONObject(n);
                                    BlackListInfo.DataBean.BlacksBean.EducationBean edu = new BlackListInfo.DataBean.BlacksBean.EducationBean();
                                    edu.setUserid(aEdu.getString("userid"));
                                    edu.setSchool(aEdu.getString("school"));
                                    edu.setMajor(aEdu.getString("major"));
                                    edu.setDegree(aEdu.getString("degree"));
                                    edu.setTime(aEdu.getString("time"));
                                    edu.setExperience(aEdu.getString("experience"));
                                    edu.setCreate_time(aEdu.getString("create_time"));
                                    eduList.add(edu);
                                }
                                blacks.setEducation(eduList);
                                JSONArray jWork = jBlacks.getJSONArray("work");
                                for (int n = 0;n<jWork.length();n ++){
                                    JSONObject aWork = jWork.getJSONObject(n);
                                    BlackListInfo.DataBean.BlacksBean.WorkBean work = new BlackListInfo.DataBean.BlacksBean.WorkBean();
                                    work.setUserid(aWork.getString("userid"));
                                    work.setCompany_name(aWork.getString("company_name"));
                                    work.setCompany_industry(aWork.getString("company_industry"));
                                    work.setJobtype(aWork.getString("jobtype"));
                                    work.setSkill(aWork.getString("skill"));
                                    work.setWork_period(aWork.getString("work_period"));
                                    work.setCreate_time(aWork.getString("create_time"));
                                    work.setContent(aWork.getString("content"));
                                    workList.add(work);
                                }
                                blacks.setWork(workList);
                                JSONArray jPro = jBlacks.getJSONArray("project");
                                for (int n = 0;n<jPro.length();n++){
                                    JSONObject aPro = jPro.getJSONObject(n);
                                    BlackListInfo.DataBean.BlacksBean.ProjectBean pro = new BlackListInfo.DataBean.BlacksBean.ProjectBean();
                                    pro.setUserid(aPro.getString("userid"));
                                    pro.setProject_name(aPro.getString("project_name"));
                                    pro.setStart_time(aPro.getString("start_time"));
                                    pro.setEnd_time(aPro.getString("end_time"));
                                    pro.setDescription_project(aPro.getString("description_project"));
                                    pro.setCreate_time(aPro.getString("create_time"));
                                    proList.add(pro);
                                }
                                blacks.setProject(proList);
                                blackList.setBlacks(blacks);
                                bList.add(blackList);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    MyBlackListAdepter myBlackListAdepter = new MyBlackListAdepter(mContext,R.layout.my_blacklist_adepter,bList,myBlackList);
                    layoutBlackList.setAdapter(myBlackListAdepter);
                    break;
                case KEY_DELETE_BLACK_LIST:
                    try {
                        JSONObject jsonObject = new JSONObject((String) msg.obj);
                        if ("success".equals(jsonObject.optString("status"))){
                            Toast.makeText(mContext,"删除成功",Toast.LENGTH_SHORT).show();
                            initload();
                        }else {

                            Toast.makeText(mContext,"删除失败",Toast.LENGTH_SHORT).show();
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
