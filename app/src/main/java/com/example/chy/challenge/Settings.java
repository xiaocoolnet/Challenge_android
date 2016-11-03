package com.example.chy.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.chy.challenge.login.Login;

/**
 * Created by 77588 on 2016/9/6.
 */
public class Settings extends Activity implements View.OnClickListener{
    private RelativeLayout back;
    private LinearLayout phoneBinding,setPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        initview();
    }
    private void initview() {
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        phoneBinding = (LinearLayout) findViewById(R.id.phone_binding);
        phoneBinding.setOnClickListener(this);
        setPassword = (LinearLayout) findViewById(R.id.set_password);
        setPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.phone_binding:
                startActivity(new Intent(this,PhoneBinding.class));
                break;
            case R.id.set_password:
                startActivity(new Intent(this,ValidationPassword.class));
                break;
            case R.id.logout:
                startActivity(new Intent(this,Login.class));
            default:
                break;
        }
    }
}
