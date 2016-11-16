package com.example.chy.challenge.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chy.challenge.Adepter.Detail_Adepter;
import com.example.chy.challenge.Adepter.RecruitmentInfo;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.LogUtils;
import com.example.chy.challenge.pnlllist.PullToRefreshBase;
import com.example.chy.challenge.pnlllist.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 77588 on 2016/9/1.
 */
public class ChanceFindWork extends Fragment implements View.OnClickListener {
    private Detail_Adepter detail_adepter;
    private Context mContext;
    private List<RecruitmentInfo> list = new ArrayList<RecruitmentInfo>();
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private View viewH = null;
    private ListView lv_view;
    private PullToRefreshListView pulllist;
    private final int KEY = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chance_find_work, container, false);
        initview(view);
        mContext = getActivity();
        new UserRequest(mContext,handler).GetJobList(KEY);
        pulllist = (PullToRefreshListView) view.findViewById(R.id.lv_comprehensive);
        //设置下拉活动
        pulllist.setPullLoadEnabled(true);
        pulllist.setScrollLoadEnabled(false);


        pulllist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new UserRequest(mContext,handler).GetJobList(KEY);
                stopRefresh();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                stopRefresh();
            }
        });

        //获取当前时间
        setLastData();

        //自动刷新，500毫秒
//        pulllist.doPullRefreshing(true, 500);

        //设置pulllistview
        lv_view = pulllist.getRefreshableView();
        for (int i = 0; i < 10; i++) {
            //list.add(String.valueOf(i));
        }
        detail_adepter = new Detail_Adepter(mContext,R.layout.detail_adepter, list);
        lv_view.setAdapter(detail_adepter);

        //添加头部信息
//        viewH = LayoutInflater.from(getActivity()).inflate(R.layout.firstpagenew, null);
//        lv_view.addHeaderView(viewH);
        return view;


    }

    private void initview(View v) {
    }

    //获取当前时间
    private void setLastData() {
        String text = formatdatatime(System.currentTimeMillis());
        pulllist.setLastUpdatedLabel(text);
        Log.i("time", "-------->" + text);
    }

    //停止刷新
    private void stopRefresh() {
        pulllist.postDelayed(new Runnable() {
            @Override
            public void run() {
                pulllist.onPullUpRefreshComplete();
                pulllist.onPullDownRefreshComplete();
                setLastData();
            }
        }, 2000);
    }

    private String formatdatatime(long time) {
        if (0 == time) {
            return "";
        }
        return mdata.format(new Date(time));
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg){
            switch (msg.what){
                case KEY:
                    String result = msg.obj.toString();
                    try {
                        JSONObject demojson = new JSONObject(result);
                        JSONArray jsonlist = demojson.getJSONArray("data");
                        for (int i=0; i<jsonlist.length(); i++){
                            list.clear();
                            list.add(new RecruitmentInfo(jsonlist.getJSONObject(i).getString("title"),
                                    jsonlist.getJSONObject(i).getString("company_name"),
                                    jsonlist.getJSONObject(i).getString("realname"),
                                    jsonlist.getJSONObject(i).getString("myjob"),
                                    jsonlist.getJSONObject(i).getString("count"),
                                    jsonlist.getJSONObject(i).getString("company_score"),
                                    jsonlist.getJSONObject(i).getString("salary"),
                                    jsonlist.getJSONObject(i).getString("distance"),
                                    jsonlist.getJSONObject(i).getString("address"),
                                    jsonlist.getJSONObject(i).getString("experience"),
                                    jsonlist.getJSONObject(i).getString("education"),
                                    jsonlist.getJSONObject(i).getString("work_property")
                                    ));
                        }
                        LogUtils.e("Tip",list.get(0).toString());
                        detail_adepter = new Detail_Adepter(mContext,R.layout.detail_adepter, list);
                        lv_view.setAdapter(detail_adepter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

}
