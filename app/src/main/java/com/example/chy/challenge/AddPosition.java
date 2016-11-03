package com.example.chy.challenge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by 77588 on 2016/9/3.
 */
public class AddPosition extends Activity implements View.OnClickListener{
    private RelativeLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_position);
        initview();
    }

    private void initview() {
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.btnSave:
                finish();
            default:
                break;
        }
    }
}
