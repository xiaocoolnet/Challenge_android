package com.example.chy.challenge.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.chy.challenge.AboutResume.MyAdvantage;
import com.example.chy.challenge.AboutResume.Objective;
import com.example.chy.challenge.AboutResume.ProjectExperience;
import com.example.chy.challenge.AboutResume.WorkExperience;
import com.example.chy.challenge.R;

/**
 * Created by 77588 on 2016/9/1.
 */
public class Resume extends Fragment implements View.OnClickListener {
    private LinearLayout objective,education,workExperience,projectExperience,advantage;
    private Context mContext;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resume,container,false);
        initview(rootView);
        return rootView;
    }
    private void initview(View view) {
        objective = (LinearLayout) view.findViewById(R.id.objective);
        objective.setOnClickListener(this);
        education = (LinearLayout) view.findViewById(R.id.education);
        education.setOnClickListener(this);
        workExperience = (LinearLayout) view.findViewById(R.id.work_experience);
        workExperience.setOnClickListener(this);
        projectExperience = (LinearLayout) view.findViewById(R.id.project_experience);
        projectExperience.setOnClickListener(this);
        advantage = (LinearLayout) view.findViewById(R.id.advantage);
        advantage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.objective:
                startActivity(new Intent(getActivity(), Objective.class));
                break;
            case R.id.education:
                break;
            case R.id.work_experience:
                startActivity(new Intent(getActivity(), WorkExperience.class));
                break;
            case R.id.project_experience:
                startActivity(new Intent(getActivity(), ProjectExperience.class));
                break;
            case R.id.advantage:
                startActivity(new Intent(getActivity(), MyAdvantage.class));
                break;
            default:
                break;
        }
    }
}
