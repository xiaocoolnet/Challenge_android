package com.example.chy.challenge;

/**
 * Created by 77588 on 2016/10/20.
 */

public class Bin {

    /**
     * status : success
     * data : {"userid":"303","realname":"","usertype":"0","phone":"18705459075","password":"###5a69c662688231c55475277620b088ab","sex":"0","email":"","qq":"","weixin":"","photo":"","devicestate":"2","city":"","weibo":"","work_life":"","company":"","myjob":""}
     */

    private String status;
    /**
     * userid : 303
     * realname :
     * usertype : 0
     * phone : 18705459075
     * password : ###5a69c662688231c55475277620b088ab
     * sex : 0
     * email :
     * qq :
     * weixin :
     * photo :
     * devicestate : 2
     * city :
     * weibo :
     * work_life :
     * company :
     * myjob :
     */

    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String userid;
        private String realname;
        private String usertype;
        private String phone;
        private String password;
        private String sex;
        private String email;
        private String qq;
        private String weixin;
        private String photo;
        private String devicestate;
        private String city;
        private String weibo;
        private String work_life;
        private String company;
        private String myjob;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getDevicestate() {
            return devicestate;
        }

        public void setDevicestate(String devicestate) {
            this.devicestate = devicestate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getWeibo() {
            return weibo;
        }

        public void setWeibo(String weibo) {
            this.weibo = weibo;
        }

        public String getWork_life() {
            return work_life;
        }

        public void setWork_life(String work_life) {
            this.work_life = work_life;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getMyjob() {
            return myjob;
        }

        public void setMyjob(String myjob) {
            this.myjob = myjob;
        }
    }
}
