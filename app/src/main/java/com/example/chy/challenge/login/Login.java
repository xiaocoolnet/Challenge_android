package com.example.chy.challenge.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chy.challenge.Identity;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.button.WaveView;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Activity implements View.OnClickListener{
    private EditText phone,passWord;
    private RevealButton login;
    private WaveView forgetPwd,regist;
    private WaveView QQLogin,WechatLogin,WeiboLogin;
    private Context mContext;
    private ProgressDialog dialog;

    private String telPhone,pass;
    private static final int KEY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mContext = this;
        initview();

    }

    private void initview() {
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        phone = (EditText) findViewById(R.id.login_phone);//用户名输入框
        passWord = (EditText) findViewById(R.id.login_passWord);//密码输入框
        login = (RevealButton) findViewById(R.id.login);//登录
        login.setOnClickListener(this);
        forgetPwd = (WaveView) findViewById(R.id.login_forgetPwd);//忘记密码按钮
        forgetPwd.setOnClickListener(this);
        regist = (WaveView) findViewById(R.id.login_regist);//立即注册按钮
        regist.setOnClickListener(this);
        QQLogin = (WaveView) findViewById(R.id.QQLogin);//QQ
        WechatLogin = (WaveView) findViewById(R.id.WechatLogin);
        WeiboLogin = (WaveView) findViewById(R.id.WeboLogin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                startActivity(new Intent(mContext,Identity.class));
                break;
            case R.id.login_forgetPwd:
                startActivity(new Intent(this,ForgetPassWord.class));
                break;
            case R.id.login_regist:
                startActivity(new Intent(this,Regist.class));
                break;
            default:
                break;
        }

    }

    private void loginooo() {
        telPhone = phone.getText().toString().trim();
        pass = passWord.getText().toString().trim();
        if (telPhone == null||"".equals(telPhone)){
            Toast.makeText(mContext,"用户名不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass == null||"".equals(pass)){
            Toast.makeText(mContext,"密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.setMessage("正在登录...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).Login(telPhone, pass, KEY);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }

    }



    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case KEY:
                    if (msg.obj!=null){
                        String resault = (String) msg.obj;
                          try {
                              JSONObject jsonObject = new JSONObject(resault);
                              if ("success".equals(jsonObject.optString("status"))){
                                  startActivity(new Intent(mContext,Identity.class));
                                  dialog.dismiss();
                                  finish();
                              }else {
                                  Toast.makeText(mContext, R.string.login_error,Toast.LENGTH_SHORT).show();
                                  new Thread(){
                                      @Override
                                      public void run() {
                                          try {
                                              Thread.sleep(2000);
                                          } catch (InterruptedException e) {
                                              e.printStackTrace();
                                          }
                                      }
                                  }.start();
                                  dialog.dismiss();
                              }
                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                      }
                    break;
            }
        };
    };
}
