package com.example.chy.challenge.Adepter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 77588 on 2016/11/3.
 */

public class TalentAdepter extends BaseAdapter {
    private Context mContext;
    private List<TalentResumeInfo.DataBean> talenlist;

    public TalentAdepter(Context mContext, List<TalentResumeInfo.DataBean> talenlist) {
        this.mContext = mContext;
        this.talenlist = talenlist;
    }

    @Override
    public int getCount() {
        return talenlist.size();
    }

    @Override
    public Object getItem(int position) {
        return talenlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TalentResumeInfo talentResumeInfo;
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {

        } else {

        }
        return convertView;
    }

    class ViewHolder{
        TextView realname;
        TextView position_type;
        TextView jobstate;
        TextView myjob;
        TextView company;
        TextView wantsalary;
        TextView city;
        TextView work_life;
        TextView degree;
        TextView work_property;
    }
}
