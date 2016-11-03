package com.example.chy.challenge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by 77588 on 2016/9/6.
 */
public class PhoneBinding extends Activity implements View.OnClickListener{
    private RelativeLayout back;
    private Button getCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_binding);
        initview();
    }

    private void initview() {
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        getCode = (Button) findViewById(R.id.getCode);
        getCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

        }
    }
}
