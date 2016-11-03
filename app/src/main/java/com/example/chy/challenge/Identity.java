package com.example.chy.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 77588 on 2016/9/1.
 */
public class Identity extends Activity implements View.OnClickListener {
    private Button forpernson, formoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identity);
        initview();

    }

    private void initview() {
        forpernson = (Button) findViewById(R.id.forperson);
        forpernson.setOnClickListener(this);
        formoney = (Button) findViewById(R.id.formoney);
        formoney.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forperson:
                if (true) {
                    startActivity(new Intent(this, PersonInformationTalent.class));
                } else {
                    startActivity(new Intent(this, WelcomTalent.class));
                }
                break;
            case R.id.formoney:
                if (true){
                    startActivity(new Intent(this, PersonInformationChallenge.class));
                }else {
                    startActivity(new Intent(this, WelcomSalary.class));
                }
                break;
            default:
                break;
        }
    }
}
