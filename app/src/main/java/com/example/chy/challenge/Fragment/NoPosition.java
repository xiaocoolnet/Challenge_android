package com.example.chy.challenge.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chy.challenge.R;

/**
 * Created by 77588 on 2016/9/3.
 */
public class NoPosition extends Fragment{
    public interface btnAddClickListener{
        void onBtnClick();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noposition,container,false);
        view.findViewById(R.id.btnAddPosition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof btnAddClickListener){
                    ((btnAddClickListener)getActivity()).onBtnClick();
                }
            }
        });
        return view;
    }
}
