package com.example.chy.challenge.AboutResume;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.chy.challenge.R;

/**
 * Created by 77588 on 2016/9/13.
 */
public class ProjectExperience extends Activity implements View.OnClickListener{
    private ImageView back,compelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_project_experience);
        initview();
    }
    private void initview() {
        back = (ImageView) findViewById(R.id.back );
        back.setOnClickListener(this);
        compelete = (ImageView) findViewById(R.id.compelete);
        compelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.compelete:
                finish();
                break;
            default:
                break;
        }
    }
}
