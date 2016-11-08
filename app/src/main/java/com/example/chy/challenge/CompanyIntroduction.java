package com.example.chy.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by 77588 on 2016/11/8.
 */

public class CompanyIntroduction extends Activity implements View.OnClickListener{
    private ImageView back,submit;
    private EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_com_introduction);
        initview();
    }
    private void initview() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        submit = (ImageView) findViewById(R.id.compelete);
        submit.setOnClickListener(this);
        input = (EditText) findViewById(R.id.com_intro_input);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.compelete:
                Intent intent = new Intent();
                intent.putExtra("comdata",input.getText().toString());
                setResult(0,intent);
                finish();
                break;
        }
    }
}
