package com.example.chy.challenge.login.register.register_bean;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class UserInfo {
//    {
//        "status": "success",
//            "data": {
//                "userid": "657",
//                "realname": null,
//                "usertype": "1",
//                "phone": "17718320703",
//                "password": "###d78daca780e24d968bf098830035aac9",
//                "sex": null,
//                "email": null,
//                "qq": null,
//                "weixin": null,
//                "photo": null,
//                "devicestate": "1",
//                "city": null,
//                "weibo": null,
//                "work_life": null,
//                "company": null,
//                "myjob": null
//    }
//    }
     private String photo;//个人头像
     private String realname;//个人真实姓名
     private String myjob;//我的职位
     private String email;//获取简历邮箱
     private String company;//当前公司
     private String qq;//关联QQ
     private String weixin;//关联微信
     private String weibo;//关联微博
     private Context context;//activity的
     private String userid;
     private SharedPreferences mySharedPreferences;
     private SharedPreferences.Editor editor;

    public UserInfo(Context context) {
        this.context = context;
        mySharedPreferences= context.getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        editor = mySharedPreferences.edit();
    }

    public String getUserid() {
        if("".equals(mySharedPreferences.getString("Userid","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Userid","").toString();
    }

    public void setUserid(String userid) {
        editor.putString("Userid",userid);
        editor.commit();
        this.userid = userid;
    }

    public String getWeibo() {
        if("".equals(mySharedPreferences.getString("Weibo","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Weibo","").toString();
    }

    public void setWeibo(String weibo) {
        editor.putString("Weibo",weibo);
        editor.commit();
        this.weibo = weibo;
    }

    public String getQq() {
        if("".equals(mySharedPreferences.getString("Qq","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Qq","").toString();
    }

    public void setQq(String qq) {
        editor.putString("Qq",qq);
        editor.commit();
        this.qq = qq;
    }

    public String getWeixin() {
        if("".equals(mySharedPreferences.getString("Weixin","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Weixin","").toString();
    }

    public void setWeixin(String weixin) {
        editor.putString("Weixin",weixin);
        editor.commit();
        this.weixin = weixin;
    }

    public String getCompany() {
        if("".equals(mySharedPreferences.getString("Company","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Company","").toString();
    }

    public void setCompany(String company) {
        editor.putString("Company",company);
        editor.commit();
        this.company = company;
    }

    public String getEmail() {
        if("".equals(mySharedPreferences.getString("Email","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Email","").toString();
    }

    public void setEmail(String email) {
        editor.putString("Email",email);
        editor.commit();
        this.email = email;
    }

    public String getMyjob() {
        if("".equals(mySharedPreferences.getString("Myjob","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Myjob","").toString();
    }

    public void setMyjob(String myjob) {
        editor.putString("Myjob",myjob);
        editor.commit();
        this.myjob = myjob;
    }

    public String getPhoto() {
        if("".equals(mySharedPreferences.getString("Photo","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Photo","").toString();
    }

    public void setPhoto(String photo) {
        editor.putString("Photo",photo);
        editor.commit();
        this.photo = photo;
    }

    public String getRealname() {
        if("".equals(mySharedPreferences.getString("Realname","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Realname","").toString();
    }

    public void setRealname(String realname) {
        editor.putString("Realname",realname);
        editor.commit();
        this.realname = realname;
    }
}
