package com.example.chy.challenge.Adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chy.challenge.R;

import java.util.List;

/**
 * Created by 77588 on 2016/11/6.
 */

public class WorkAdepter extends ArrayAdapter<TalentResumeInfo.DataBean.WorkBean>{
    private int resourceId;
    public WorkAdepter(Context context, int resource, List<TalentResumeInfo.DataBean.WorkBean> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TalentResumeInfo.DataBean.WorkBean workBean= getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.company_name = (TextView) view.findViewById(R.id.company_name);
            viewHolder.work_period = (TextView) view.findViewById(R.id.work_period);
            viewHolder.jobtype = (TextView) view.findViewById(R.id.jobtype);
            viewHolder.skill = (TextView) view.findViewById(R.id.skill);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.company_name.setText(workBean.getCompany_name());
        viewHolder.work_period.setText(workBean.getWork_period());
        viewHolder.jobtype.setText(workBean.getJobtype());
        viewHolder.skill.setText(workBean.getSkill());
        return view;
    }

    class ViewHolder{
        private TextView company_name;
        private TextView work_period;
        private TextView jobtype;
        private TextView skill;
    }
}
