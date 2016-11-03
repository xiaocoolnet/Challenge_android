package com.example.chy.challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.Utils.LogUtils;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.login.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

/**
 * Created by 77588 on 2016/9/1.
 */
public class ForgetPassWord extends Activity implements View.OnClickListener{

    private int i = 60;
    private int count = 0;
    private EditText inputPhone,inputCode,inputNewPassword,inputNewPassword2;
    private Button getCode;
    private ImageView back;
    private Button complete;
    private Context mContext;
    private String phone,code,newPassWord;

    private boolean visibleFlagP1 = true;
    private boolean visibleFlagP2 = true;
    private boolean btnClickLicense = true;

    private final int KEY_CHECK_PHONE = 1;
    private final int KEY_GET_CODE = 2;
    private final int CHANGE_GET_CODE_TEXT1 = 3;
    private final int CHANGE_GET_CODE_TEXT2 = 4;
    private final int KEY_CHANGE_PASSWORD = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        mContext = this;
        initview();
    }

    private void initview() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        inputPhone = (EditText) findViewById(R.id.phone_repwd);
        inputCode = (EditText) findViewById(R.id.code);
        inputNewPassword = (EditText) findViewById(R.id.newpwd);
        inputNewPassword2 = (EditText) findViewById(R.id.newpwd2);
        getCode = (Button) findViewById(R.id.getcode);
        getCode.setOnClickListener(this);
        complete = (Button) findViewById(R.id.complete);
        complete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.getcode:
                if (!btnClickLicense){
                    Toast.makeText(mContext,i+"秒后可重新获取",Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!NetBaseUtils.isConnnected(mContext)){
                    Toast.makeText(mContext,R.string.net_error,Toast.LENGTH_SHORT).show();
                    break;
                }
                grabCode();
                break;
            case R.id.complete:
                if (canUpdatePwd()){
                    newPassWord = inputNewPassword.getText().toString();
                    changePwd();
                }
                break;
            default:
                break;
        }
    }

    private void changePwd() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).ChangePwd(phone,inputNewPassword.getText().toString().trim(),code,KEY_CHANGE_PASSWORD);
        }else{
            Toast.makeText(mContext,R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }

    private boolean canUpdatePwd(){
        if (inputPhone.getText().toString()==null||"".equals(inputPhone.getText().toString().trim())){
            Toast.makeText(mContext,"请输入手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!inputPhone.getText().toString().equals(phone)){
            Toast.makeText(mContext,"请先获取该号码的验证码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (code == null){
            Toast.makeText(mContext,"请先获取验证码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!inputCode.getText().toString().trim().equals(code)){
            if (count<3){
                Toast.makeText(mContext,"验证码不正确，请重新输入",Toast.LENGTH_SHORT).show();
                count++;
            }else {
                Toast.makeText(mContext,"验证码错误3次，请重新获取验证码",Toast.LENGTH_SHORT).show();
                inputCode.setText(null);
                code = null;
                count = 0;
            }
            return false;
        }
        if (inputNewPassword.getText().toString()==null||"".equals(inputNewPassword.getText().toString().trim())) {
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!inputNewPassword.getText().toString().trim().equals(inputNewPassword2.getText().toString().trim())){
            Toast.makeText(mContext,"两次密码不一致！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void grabCode() {

        phone = inputPhone.getText().toString();
        if (phone==null||"".equals(phone.trim())||phone.length()!=11||!Pattern.matches("(\\+\\d+)?1[34578]\\d{9}$", phone)){
            Toast.makeText(mContext,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).CheckPhone(phone,KEY_CHECK_PHONE);
        }else{
            Toast.makeText(mContext,R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case KEY_CHECK_PHONE:

                    if (msg.obj!=null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if ("error".equals(jsonObject.optString("status"))){
                                Toast.makeText(mContext,"该号码未注册",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if ("success".equals(jsonObject.optString("status"))){
                                new UserRequest(mContext, handler).GetCode(phone,KEY_GET_CODE);
                                    btnClickLicense = false;
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            for (;i>=0;i-- ){
                                                if (i<=0){
                                                    handler.sendEmptyMessage(CHANGE_GET_CODE_TEXT2);
                                                    break;
                                                }
                                                handler.sendEmptyMessage(CHANGE_GET_CODE_TEXT1);
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }
                                    ).start();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case KEY_GET_CODE:
                    if (msg.obj != null) {
                        try {
                            JSONObject jsonObject = new JSONObject((String) msg.obj);
                            code = jsonObject.optString("code");
                            LogUtils.i("Tip","196 解析验证码"+code);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case CHANGE_GET_CODE_TEXT1:
                    getCode.setText("重发"+i);
                    break;
                case CHANGE_GET_CODE_TEXT2:
                    getCode.setText("重新发送");
                    btnClickLicense = true;
                    i = 60;
                    break;
                case KEY_CHANGE_PASSWORD:
                    LogUtils.i("Tip","修改密码，验证码是"+code);
                    try {
                        JSONObject jsonObject = new JSONObject((String) msg.obj);
                        LogUtils.i("Tip",jsonObject.optString("status"));
                        if ("success".equals(jsonObject.optString("status"))){
                            Toast.makeText(mContext,"修改成功，请重新登录",Toast.LENGTH_SHORT).show();
                            Login.l.finish();
                            startActivity(new Intent(mContext,Login.class));
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
}
