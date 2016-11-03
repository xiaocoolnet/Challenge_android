package com.example.chy.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by 77588 on 2016/9/7.
 */
public class ValidationPassword extends Activity implements View.OnClickListener{
    private RelativeLayout back;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validation_password);
        initview();
    }

    private void initview() {
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.next:
                startActivity(new Intent(this,NewPassword.class));
                finish();
                break;
            default:
                break;
        }
    }
}
