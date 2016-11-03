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
public class Regist extends Activity implements View.OnClickListener{
    private Context mContext;
    private Button btnGetCode,registSave;
    private ImageView back;
    private EditText edtPhone,edtCode,edtPwd;
    private String phone,code;
    private final int KEY_CHECK_PHONE = 1;
    private final int CHANGE_GET_CODE_TEXT1 = 2;
    private final int CHANGE_GET_CODE_TEXT2 = 3;
    private final int KEY_GET_CODE = 4;
    private final int KEY_REGIST = 5;
    private final int KEY_REGIST_SUCCESS = 6;
    private boolean btnClickLicense = true;
    private int i = 60;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        mContext = this;
        initview();
    }

    private void initview() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        btnGetCode = (Button) findViewById(R.id.regist_getCode);
        btnGetCode.setOnClickListener(this);
        registSave = (Button) findViewById(R.id.regist_save);
        registSave.setOnClickListener(this);
        edtPhone = (EditText) findViewById(R.id.phone_repwd);
        edtCode = (EditText) findViewById(R.id.code);
        edtPwd = (EditText) findViewById(R.id.newpwd);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.regist_getCode:
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
            case R.id.regist_save:
                    if (canSave()){
                        regist();
                    }
                break;
            default:
                break;
        }
    }

    private void grabCode() {
        phone = edtPhone.getText().toString();
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
    private boolean canSave(){

        if (edtPhone.getText().toString()==null||"".equals(edtPhone.getText().toString().trim())){
            Toast.makeText(mContext,"请输入手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!edtPhone.getText().toString().equals(phone)){
            Toast.makeText(mContext,"请先获取该号码的验证码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtPwd.getText().toString()==null||"".equals(edtPwd.getText().toString().trim())){
            Toast.makeText(mContext,"请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (code == null){
            Toast.makeText(mContext,"请先获取验证码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtCode.getText().toString()==null||"".equals(edtCode.getText().toString().trim())){
            Toast.makeText(mContext,"请输入验证码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!edtCode.getText().toString().trim().equals(code)){
            if (count<3){
                Toast.makeText(mContext,"验证码不正确，请重新输入",Toast.LENGTH_SHORT).show();
                count++;
            }else {
                Toast.makeText(mContext,"错误超过三次，请重新获取验证码",Toast.LENGTH_SHORT).show();
                edtCode.setText(null);
                code = null;
                count = 0;
            }
            return false;
        }

        return true;
    }

    private void regist(){
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).Regist(phone,edtPwd.getText().toString().trim(),code,KEY_REGIST);
        }else{
            Toast.makeText(mContext,R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case KEY_CHECK_PHONE:
                    if (msg.obj!=null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if ("success".equals(jsonObject.optString("status"))){
                                Toast.makeText(mContext,"该号码已被注册",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if ("error".equals(jsonObject.optString("status"))){
                                new UserRequest(mContext, handler).GetCode(phone,KEY_GET_CODE);
                                if (code!=null){
                                    btnClickLicense = false;
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            for (;i>=0;i-- ){
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
                                    ).start();
                                }
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
                    btnGetCode.setText("重发"+i);
                    break;
                case CHANGE_GET_CODE_TEXT2:
                    btnGetCode.setText("重新发送");
                    btnClickLicense = true;
                    i = 60;
                    break;
                case KEY_REGIST:
                    try {
                        JSONObject jsonObject = new JSONObject((String) msg.obj);
                        if ("success".equals(jsonObject.optString("status"))){
                            startActivity(new Intent(mContext,Identity.class));
                            Login.l.finish();
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }


    };


}
