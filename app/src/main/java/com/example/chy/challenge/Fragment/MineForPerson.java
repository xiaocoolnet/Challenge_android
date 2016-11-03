package com.example.chy.challenge.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chy.challenge.R;

/**
 * Created by 77588 on 2016/9/1.
 */
public class MineForPerson extends Fragment implements View.OnClickListener{

    public interface btnSettingListener{
        void onBtnSettingClick();
    }

    private ImageView setting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine_person, container, false);
        initview(rootView);
        return rootView;
    }

    private void initview(View rootView) {
        setting = (ImageView) rootView.findViewById(R.id.setting);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.setting:
                if (getActivity() instanceof btnSettingListener){
                    ((btnSettingListener)getActivity()).onBtnSettingClick();
                }
                break;
            default:
                break;
        }
    }
}

