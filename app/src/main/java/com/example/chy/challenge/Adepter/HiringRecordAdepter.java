package com.example.chy.challenge.Adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chy.challenge.Models.CompanyInfo;
import com.example.chy.challenge.R;

import java.util.List;

/**
 * Created by 77588 on 2016/11/10.
 */

public class HiringRecordAdepter extends ArrayAdapter<CompanyInfo.DataBean.JobsBean> {
    private int resourceId;
    public HiringRecordAdepter(Context context, int textViewResourceId, List<CompanyInfo.DataBean.JobsBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CompanyInfo.DataBean.JobsBean jobsBean = getItem(position);
        View view;
        ViewHolder viewHolder = new ViewHolder();
        if (convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.salary = (TextView) view.findViewById(R.id.salary);
            viewHolder.city = (TextView) view.findViewById(R.id.city);
            viewHolder.experience = (TextView) view.findViewById(R.id.experience);
            viewHolder.education = (TextView) view.findViewById(R.id.education);
            viewHolder.jobtype = (TextView) view.findViewById(R.id.jobtype);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(jobsBean.getTitle());
        viewHolder.salary.setText(jobsBean.getSalary());
        viewHolder.city.setText(jobsBean.getCity());
        viewHolder.experience.setText(jobsBean.getExperience());
        viewHolder.education.setText(jobsBean.getEducation());
        viewHolder.jobtype.setText(jobsBean.getJobtype());
        return view;
    }
    class ViewHolder{
        private TextView title;
        private TextView salary;
        private TextView city;
        private TextView experience;
        private TextView education;
        private TextView jobtype;
    }

}
