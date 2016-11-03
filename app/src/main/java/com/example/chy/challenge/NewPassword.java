package com.example.chy.challenge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by 77588 on 2016/9/7.
 */
public class NewPassword extends Activity implements View.OnClickListener{
    private RelativeLayout back;
    private Button comletePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password);
        initview();
    }

    private void initview() {
        comletePassword = (Button) findViewById(R.id.complete_password);
        comletePassword.setOnClickListener(this);
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.complete_password:
                finish();
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
