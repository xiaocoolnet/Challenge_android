package com.example.chy.challenge.NetInfo;

/**
 * Created by mac on 16/5/9.
 */
public interface UserNetConstant extends NetBaseConstant {
    //登录
    public final static String NET_USER_LOGIN = NET_BASE_HOST + "a=applogin";
    //验证码
    public final static String NET_USER_CODE = NET_BASE_HOST + "a=SendMobileCode";
    //验证手机是否已经被注册
    public final static String CHECK_PHONE = NET_BASE_HOST + "a=checkphone";
    //注册
    public final static String REGIST = NET_BASE_HOST + "a=AppRegister";
    //修改密码
    public final static String CHANGE_PASSWORD = NET_BASE_HOST + "a=forgetpwd";
    //获取招聘列表
    public final static String GET_JOB_LIST = NET_BASE_HOST + "a=getjoblist";
    //提交个人信息
    public final static String UPDATECOMMANY = NET_BASE_HOST + "&a=savecompanyinfo";
    //上传头像
    public final static String UPLOADAVATAR= NET_BASE_HOST + "&a=uploadavatar";
    //获取简历列表
    public final static String GET_RESUME_LIST = NET_BASE_HOST + "a=getResumeList";
    //获取我的企业信息
    public final static String GET_MY_COMPANY_INFO = NET_BASE_HOST + "a=getMyCompany_info";
    //获取收藏列表
    public final static String GET_FAVORITE_LIST = NET_BASE_HOST+"a=getfavoritelist";
}
