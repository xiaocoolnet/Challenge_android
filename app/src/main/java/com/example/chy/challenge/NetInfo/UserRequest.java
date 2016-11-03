package com.example.chy.challenge.NetInfo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.chy.challenge.Utils.LogUtils;
import com.example.chy.challenge.Utils.NetBaseUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77588 on 2016/9/20.
 */
public class UserRequest {
    private Context mContext;
    private Handler handler;

    public UserRequest(Context mContext, Handler handler) {
        this.mContext = mContext;
        this.handler = handler;
    }

    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @param KEY
     */
    public void Login(final String phone, final String pwd, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("phone", phone));
                params.add(new BasicNameValuePair("password", pwd));
                LogUtils.i("TuiSong", "device_token" + params.toString());
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.NET_USER_LOGIN, params, mContext);
                LogUtils.i("Tip", result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }

            ;
        }.start();
    }

    /**
     * 获取验证码
     *
     * @param phone
     */
    public void GetCode(final String phone, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                Message msg = new Message();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("phone", phone));
                LogUtils.i("TuiSong", "device_token" + params.toString());
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.NET_USER_CODE, params, mContext);
                LogUtils.i("Tip", "原数据" + result);
                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        msg.what = KEY;
                        msg.obj = jsonObject.optString("data");
                        LogUtils.i("Tip", msg.obj.toString());
                        handler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            ;
        }.start();
    }

    /**
     * 检查号码是否可用
     *
     * @param phone
     * @param KEY
     */
    public void CheckPhone(final String phone, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("phone", phone));
                LogUtils.i("Tip", " Flag" + parmas.toString());
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.CHECK_PHONE, parmas, mContext);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 注册
     *
     * @param phone
     * @param password
     * @param code
     */
    public void Regist(final String phone, final String password, final String code, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("phone", phone));
                parmas.add(new BasicNameValuePair("password", password));
                parmas.add(new BasicNameValuePair("code", code));
                parmas.add(new BasicNameValuePair("devicestate", "2"));//1.iso2.android
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.REGIST, parmas, mContext);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 修改密码
     *
     * @param phone
     * @param password
     * @param code
     * @param KEY
     */
    public void ChangePwd(final String phone, final String password, final String code, final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("phone", phone));
                parmas.add(new BasicNameValuePair("code", code));
                parmas.add(new BasicNameValuePair("password", password));
                String resault = NetBaseUtils.getResponseForPost(UserNetConstant.CHANGE_PASSWORD, parmas, mContext);
                msg.what = KEY;
                msg.obj = resault;
                handler.sendMessage(msg);
            }
        }.start();
    }


    /**
     * 获取招聘列表
     *
     * @param KEY
     */
    public void GetJobList(final int KEY) {
        new Thread() {
            Message msg = Message.obtain();

            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.GET_JOB_LIST, parmas, mContext);
                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if ("success".equals(jsonObject.optString("status"))) {
                            msg.what = KEY;
                            msg.obj = result;
                            handler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
