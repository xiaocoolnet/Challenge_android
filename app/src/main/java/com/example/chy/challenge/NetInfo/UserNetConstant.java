package com.example.chy.challenge.NetInfo;

/**
 * Created by mac on 16/5/9.
 */
public interface UserNetConstant extends NetBaseConstant {
    /**
     * 登录
     */
    public final static String NET_USER_LOGIN = NET_BASE_HOST + "a=applogin";
    /**
     * 验证码
     */
    public final static String NET_USER_CODE = NET_BASE_HOST + "a=SendMobileCode";
    /**
     * 验证手机是否已经被注册
     */
    public final static String CHECK_PHONE = NET_BASE_HOST + "a=checkphone";
    /**
     * 注册
     */
    public final static String REGIST = NET_BASE_HOST + "a=AppRegister";

    /**
     * 修改密码
     */
    public final static String CHANGE_PASSWORD = NET_BASE_HOST + "a=forgetpwd";
    /**
     * 获取招聘列表
     */
    public final static String GET_JOB_LIST = NET_BASE_HOST + "a=getjoblist";
}
