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
 * Created by 77588 on 2016/11/5.
 */

public class EducationAdepter extends ArrayAdapter<TalentResumeInfo.DataBean.EducationBean>{
    private int resourceId;
    public EducationAdepter(Context context, int resource, List<TalentResumeInfo.DataBean.EducationBean> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TalentResumeInfo.DataBean.EducationBean educationBean = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.school = (TextView) view.findViewById(R.id.school);
            viewHolder.time = (TextView) view.findViewById(R.id.time);
            viewHolder.major = (TextView) view.findViewById(R.id.major);
            viewHolder.degree = (TextView) view.findViewById(R.id.degree);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.school.setText(educationBean.getSchool());
        viewHolder.time.setText(educationBean.getTime());
        viewHolder.major.setText(educationBean.getMajor());
        viewHolder.degree.setText(educationBean.getDegree());
        return view;
    }

    class ViewHolder{
        private TextView school;
        private TextView time;
        private TextView major;
        private TextView degree;
    }
}
