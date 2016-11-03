package com.example.chy.challenge.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chy.challenge.Identity;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.regular.RegexUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 77588 on 2016/9/1.
 */
public class Regist extends Activity implements View.OnClickListener{
    private Context mContext;
    private RevealButton btnGetCode,registSave;
    private WaveView back,refister_agreement;
    private EditText edtPhone,edtCode,edtPwd;
    private String phone,code;
    private final int KEY_CHECK_PHONE = 1;
    private final int CHANGE_GET_CODE_TEXT1 = 2;
    private final int CHANGE_GET_CODE_TEXT2 = 3;
    private final int KEY_GET_CODE = 4;
    private final int KEY_REGIST = 5;
    private int i = 60;
    private int count = 0;
    private ProgressDialog dialog;
    private UserInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        mContext = this;
        info = new UserInfo(mContext);
        initview();
    }

    private void initview() {
        back = (WaveView) findViewById(R.id.register_btn_back);//返回
        back.setOnClickListener(this);
        btnGetCode = (RevealButton) findViewById(R.id.regist_getCode);//获取验证码
        btnGetCode.setOnClickListener(this);
        registSave = (RevealButton) findViewById(R.id.regist_save);//完成
        registSave.setOnClickListener(this);
        edtPhone = (EditText) findViewById(R.id.register_phone);//手机号输入
        edtCode = (EditText) findViewById(R.id.register_code);//验证码输入
        edtPwd = (EditText) findViewById(R.id.register_pwd);//密码输入
        refister_agreement = (WaveView) findViewById(R.id.refister_agreement);//用户协议
        refister_agreement.setOnClickListener(this);
        //弹出刷新提示框
        dialog=new ProgressDialog(mContext,AlertDialog.THEME_HOLO_LIGHT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_btn_back:
                finish();
                break;
            case R.id.regist_getCode:
                if (NetBaseUtils.isConnnected(mContext)){
                    if (isCanGetCode()){
                        Log.i("code","------------>"+code);
                        edtCode.setText("");
                        edtCode.clearFocus();
                        btnGetCode.setClickable(false);
                        //验证手机号是否注册接口
                        new UserRequest(mContext, handler).CheckPhone(getPhoneNumber(),KEY_CHECK_PHONE);
                    }
                }else{
                    Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.regist_save:
                    if (canSave()){
                        regist();
                    }
                break;
            case R.id.refister_agreement:
                //跳转页面
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
        return edtPhone.getText().toString();
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
        if (getCoding().isEmpty()) {
            edtCode.setError(Html.fromHtml("<font color=#E10979>请输入验证码！</font>"));
            return false;
        }
        if (!code.equals(getCoding())) {
            edtCode.setError(Html.fromHtml("<font color=#E10979>验证码错误！</font>"));
            return false;
        }
//        if (!edtCode.getText().toString().trim().equals(code)){
//            if (count<3){
//                Toast.makeText(mContext,"验证码不正确，请重新输入",Toast.LENGTH_SHORT).show();
//                count++;
//            }else {
//                Toast.makeText(mContext,"错误超过三次，请重新获取验证码",Toast.LENGTH_SHORT).show();
//                edtCode.setText(null);
//                code = null;
//                count = 0;
//            }
//            return false;
//        }

        return true;
    }
    private String getCoding() {
        return edtCode.getText().toString();
    }
    //注册接口
    private void regist(){
        if (NetBaseUtils.isConnnected(mContext)) {
//            new UserRequest(mContext, handler).Regist(getPhoneNumber(),edtPwd.getText().toString().trim(),code,KEY_REGIST);
            new UserRequest(mContext, handler).Regist(getPhoneNumber(),edtPwd.getText().toString(),code,KEY_REGIST);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
    //在手机号码验证过之后显示
    private void getVerificationCode() {
        btnGetCode.setClickable(false);
        btnGetCode.setText("重发("+i+")");
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
                            }else if ("error".equals(jsonObject.optString("status"))){
                                getVerificationCode();
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
                    btnGetCode.setText("重发("+i+")");
                    break;
                case CHANGE_GET_CODE_TEXT2:
                    btnGetCode.setText("重新发送");
                    btnGetCode.setClickable(true);
                    i = 60;
                    break;
                case KEY_REGIST:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject jsonobj = new JSONObject(result);
                            if ("success".equals(jsonobj.optString("status"))){
                                info.setUserid(jsonobj.getString("data"));
                                Intent intent = new Intent(mContext,Identity.class);
                                intent.putExtra("pagetype","register");
                                startActivity(intent);
                            }else{
                                Toast.makeText(mContext,"注册失败，请重新注册",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
            }
        }
    };
}
