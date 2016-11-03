package com.example.chy.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by 77588 on 2016/10/22.
 */

public class PersonInformationChallenge extends Activity implements View.OnClickListener {
    private RelativeLayout back;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_information_challenge);
        initview();
    }

    private void initview() {
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        create = (Button) findViewById(R.id.person_create_resume);
        create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.person_create_resume:
                startActivity(new Intent(this, WelcomSalary.class));
                break;
        }
    }
}
