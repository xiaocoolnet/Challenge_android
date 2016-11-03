package com.example.chy.challenge.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.regular.RegexUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 77588 on 2016/9/1.
 */
public class ForgetPassWord extends Activity implements View.OnClickListener{

    private int i = 60;
    private int count = 0;
    private EditText inputPhone,inputCode,inputNewPassword,inputNewPassword2;
    private Button getCode;
    private RelativeLayout visiblep1,visiblep2;
    private WaveView back;
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
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repwd);
        mContext = this;
        initview();
    }

    private void initview() {
        back = (WaveView) findViewById(R.id.register_btn_back);//返回
        back.setOnClickListener(this);
        inputPhone = (EditText) findViewById(R.id.phone_repwd);//账号输入框
        inputCode = (EditText) findViewById(R.id.code);//验证码输入框
        inputNewPassword = (EditText) findViewById(R.id.newpwd);//新密码
        inputNewPassword2 = (EditText) findViewById(R.id.newpwd2);//确认新密码
        getCode = (Button) findViewById(R.id.getcode);//获取验证码
        getCode.setOnClickListener(this);
//        visiblep1 = (RelativeLayout) findViewById(R.id.visiblep1);
//        visiblep1.setOnClickListener(this);
//        visiblep2 = (RelativeLayout) findViewById(R.id.visiblep2);
//        visiblep2.setOnClickListener(this);
        complete = (Button) findViewById(R.id.complete);
        complete.setOnClickListener(this);
        //弹出刷新提示框
        dialog=new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_btn_back:
                finish();
                break;
            case R.id.getcode:
                if (NetBaseUtils.isConnnected(mContext)){
                    if (isCanGetCode()){
                        Log.i("code","------------>"+code);
                        inputCode.setText("");
                        inputCode.clearFocus();
                        getCode.setClickable(false);
                        //验证手机号是否注册接口
                        new UserRequest(mContext, handler).CheckPhone(getPhoneNumber(),KEY_CHECK_PHONE);
                    }
                }else{
                    Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                }
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
    //正则验证手机号
    private Boolean isCanGetCode() {
        if (!RegexUtil.Mobile_phone(getPhoneNumber())) {
            Toast.makeText(mContext,"请填写正确的手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private String getPhoneNumber() {
        return inputPhone.getText().toString();
    }
    //在手机号码验证过之后显示
    private void getVerificationCode() {
        getCode.setClickable(false);
        getCode.setText("重发("+i+")");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;i > 0;i--){
                    handler.sendEmptyMessage(CHANGE_GET_CODE_TEXT1);
                    if (i <= 0){
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(CHANGE_GET_CODE_TEXT2);
            }
        }).start();
        dialog.setMessage("正在发送验证码");
//                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        //获取验证码接口
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GetCode(getPhoneNumber(),KEY_GET_CODE);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
    private void changePwd() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).ChangePwd(phone,inputNewPassword.getText().toString().trim(),code,KEY_CHANGE_PASSWORD);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
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

//    private void grabCode() {
//
//        phone = inputPhone.getText().toString();
//        if (phone==null||"".equals(phone.trim())||phone.length()!=11||!Pattern.matches("(\\+\\d+)?1[34578]\\d{9}$", phone)){
//            Toast.makeText(mContext,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (NetBaseUtils.isConnnected(mContext)) {
//            new UserRequest(mContext, handler).CheckPhone(phone,KEY_CHECK_PHONE);
//        }else{
//            Toast.makeText(mContext,R.string.net_error,Toast.LENGTH_SHORT).show();
//        }
//    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case KEY_CHECK_PHONE:

                    if (msg.obj!=null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if ("success".equals(jsonObject.optString("status"))){
                                getVerificationCode();

                            }else if ("error".equals(jsonObject.optString("status"))){
                                Toast.makeText(mContext,"该号码未注册",Toast.LENGTH_SHORT).show();
                                //验证成功，获取验证码
//                                GetCode gc = new GetCode();
//                                code = gc.GetCode(phone,mContext);
//                                new UserRequest(mContext, handler).GetCode(getPhoneNumber(),KEY_GET_CODE);
//                                if (code!=null){
//                                    new Thread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            for (;i>=0;i-- ){
//                                                    handler.sendEmptyMessage(CHANGE_GET_CODE_TEXT2);
//                                                    break;
//                                                }
//                                                handler.sendEmptyMessage(CHANGE_GET_CODE_TEXT1);
//                                                try {
//                                                    Thread.sleep(1000);
//                                                } catch (InterruptedException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            }
//                                        }
//                                    ).start();
//                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
                    break;

                case KEY_GET_CODE:
                    if (msg.obj != null) {
                        String result = (String) msg.obj;
                        try {
                            JSONObject jsonobj = new JSONObject(result);
                            if ("success".equals(jsonobj.optString("status"))){
                                JSONObject jsondata = new JSONObject(jsonobj.getString("data"));
                                code = jsondata.getString("code");
                                dialog.setMessage("发送成功");
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
                    break;
                case CHANGE_GET_CODE_TEXT1:
                    getCode.setText("重发("+i+")");
                    break;
                case CHANGE_GET_CODE_TEXT2:
                    getCode.setText("重新发送");
                    getCode.setClickable(true);
                    i = 60;
                    break;
                case KEY_CHANGE_PASSWORD:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject jsonobj = new JSONObject(result);
                            if ("success".equals(jsonobj.optString("status"))){
                                Toast.makeText(mContext,"修改成功，请重新登录",Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(mContext,"修改成功，请重新修改",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
