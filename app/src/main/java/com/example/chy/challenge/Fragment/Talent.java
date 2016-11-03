package com.example.chy.challenge.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.chy.challenge.PopTalentExperience;
import com.example.chy.challenge.R;

/**
 * Created by 77588 on 2016/9/2.
 *
 */
public class Talent extends Fragment implements View.OnClickListener{
    private TextView tv;
    private PopTalentExperience popTalentExperience;
    private RadioButton btnNew,btnExperience,btnEducation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talent,container,false);
//        popTalentExperience = new PopTalentExperience(Talent.this);

        initview(rootView);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        NoPosition noPosition = new NoPosition();
        transaction.replace(R.id.intalent_layout, noPosition);
        transaction.commit();
        return rootView;
    }
    private void initview(View rootView) {
        btnExperience = (RadioButton) rootView.findViewById(R.id.btnExperience);
        btnExperience.setOnClickListener(this);
        btnEducation = (RadioButton) rootView.findViewById(R.id.btnEducation);
        btnEducation.setOnClickListener(this);
        tv = (TextView) rootView.findViewById(R.id.tv);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnExperience:
//                popTalentExperience.showAsDropDown(tv,btnExperience.getText().toString());
                break;
            case R.id.btnEducation:
                break;
            default:
                break;
        }

    }
}
