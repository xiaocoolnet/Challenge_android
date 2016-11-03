package com.example.chy.challenge.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chy.challenge.R;

import java.util.List;

/**
 * Created by 77588 on 2016/9/8.
 */
public class Detail_Adepter extends ArrayAdapter<RecruitmentInfo> {
    private int resourceId;

    public Detail_Adepter(Context context, int textViewResourceId, List<RecruitmentInfo> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecruitmentInfo recruitmentInfo = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.company_name = (TextView) view.findViewById(R.id.company_name);
            viewHolder.realname = (TextView) view.findViewById(R.id.realname);
            viewHolder.myjob = (TextView) view.findViewById(R.id.myjob);
            viewHolder.count = (TextView) view.findViewById(R.id.count);
            viewHolder.company_score = (TextView) view.findViewById(R.id.company_score);
            viewHolder.salary = (TextView) view.findViewById(R.id.salary);
            viewHolder.distance = (TextView) view.findViewById(R.id.distance);
            viewHolder.address = (TextView) view.findViewById(R.id.address);
            viewHolder.experience = (TextView) view.findViewById(R.id.experience);
            viewHolder.education = (TextView) view.findViewById(R.id.education);
            viewHolder.work_property = (TextView) view.findViewById(R.id.work_property);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(recruitmentInfo.getTitle());
        viewHolder.company_name.setText(recruitmentInfo.getCompany_name());
        viewHolder.realname.setText(recruitmentInfo.getRealname());
        viewHolder.myjob.setText(recruitmentInfo.getMyjob());
        viewHolder.count.setText(recruitmentInfo.getCount());
        viewHolder.company_score.setText(recruitmentInfo.getCompany_score());
        viewHolder.salary.setText(recruitmentInfo.getSalary());
        viewHolder.distance.setText(recruitmentInfo.getDistance());
        viewHolder.address.setText(recruitmentInfo.getAddress());
        viewHolder.experience.setText(recruitmentInfo.getExperience());
        viewHolder.education.setText(recruitmentInfo.getEducation());
        viewHolder.work_property.setText(recruitmentInfo.getWork_property());
        return view;
    }

    class ViewHolder {
        TextView title;
        TextView company_name;
        TextView realname;
        TextView myjob;
        TextView count;
        TextView company_score;
        TextView salary;
        TextView distance;
        TextView address;
        TextView experience;
        TextView education;
        TextView work_property;
    }
}
