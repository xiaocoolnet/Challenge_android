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
 * Created by 77588 on 2016/11/3.
 */

public class TalentAdepter2 extends ArrayAdapter<TalentResumeInfo.DataBean>{
    private int resourceId;
    public TalentAdepter2(Context context, int textViewResourceId,List<TalentResumeInfo.DataBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        TalentResumeInfo.DataBean dataBean = getItem(position).getData().get(position);
        TalentResumeInfo.DataBean talentResumeInfo = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.realname = (TextView) view.findViewById(R.id.realname);
            viewHolder.position_type = (TextView) view.findViewById(R.id.position_type);
            viewHolder.jobstate = (TextView) view.findViewById(R.id.jobstate);
            viewHolder.myjob = (TextView) view.findViewById(R.id.myjob);
            viewHolder.company = (TextView) view.findViewById(R.id.company);
            viewHolder.wantsalary = (TextView) view.findViewById(R.id.wantsalary);
            viewHolder.city = (TextView) view.findViewById(R.id.city);
            viewHolder.work_life = (TextView) view.findViewById(R.id.work_life);
            viewHolder.degree = (TextView) view.findViewById(R.id.degree);
            viewHolder.work_property = (TextView) view.findViewById(R.id.work_property);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.realname.setText(talentResumeInfo.getRealname());
        viewHolder.position_type.setText(talentResumeInfo.getPosition_type());
        viewHolder.jobstate.setText(talentResumeInfo.getJobstate());
        viewHolder.myjob.setText(talentResumeInfo.getMyjob());
        viewHolder.company.setText(talentResumeInfo.getCompany());
        viewHolder.wantsalary.setText(talentResumeInfo.getWantsalary());
        viewHolder.city.setText(talentResumeInfo.getCity());
        viewHolder.work_life.setText(talentResumeInfo.getWork_life());
        if (talentResumeInfo.getEducation()!=null&&talentResumeInfo.getEducation().size()>0){
            viewHolder.degree.setText(talentResumeInfo.getEducation().get(0).getDegree());
        }
        viewHolder.work_property.setText(talentResumeInfo.getWork_property());
        return view;
    }

    class ViewHolder {
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
