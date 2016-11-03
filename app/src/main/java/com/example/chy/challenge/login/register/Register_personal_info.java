package com.example.chy.challenge.login.register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.TalentMain;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.personal_pop.Personal_camera;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


/**
 * Created by Administrator on 2016/10/13 0013.
 * 个人信息编辑界面
 */

public class Register_personal_info extends Activity implements View.OnClickListener{
    private static final int KEY = 4;
    private static final int SAVEKEY = 5;
    private WaveView personal_head_avater, personal_real_name, persoanl_mine_position, personal_mine_email, personal_current_company, personal_relevance_QQ, personal_relevance_weixin, personal_relevance_weibo;
    private Personal_camera Pcamear;
    private RevealButton personal_relevance_submit;
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_CUT = 3;// 相册
    private static final int PHOTO_REQUEST_ALBUM = 2;// 剪裁
    private RoundImageView roundimage;
    private Activity mactivity;
    private String picname = "newpic",head,filepath = "/sdcard/myheader",path;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private Intent intent = new Intent();
    private UserInfo info;
    private UserInfoBean infobean;


    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case KEY:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                path = json.optString("data");
                                imageLoader.displayImage(NetBaseConstant.NET_HOST + "/"+path, roundimage, options);
                            }else{
                                Toast.makeText(mactivity, "上传失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case SAVEKEY:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                infobean.setPhoto(path);
                                infobean.setRealname(info.getRealname());
                                infobean.setMyjob(info.getMyjob());
                                infobean.setEmail(info.getEmail());
                                infobean.setQq(info.getQq());
                                infobean.setWeixin(info.getWeixin());
                                infobean.setWeibo(info.getWeibo());
                                infobean.setCompany(info.getCompany());
                                Intent intent = new Intent(mactivity, TalentMain.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(mactivity, "请重新提交", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_personal_info);
        mactivity = this;
        info = new UserInfo(mactivity);
        infobean = new UserInfoBean(mactivity);
        Pcamear = new Personal_camera(Register_personal_info.this);
        getinfo();

    }

    private void getinfo() {
        personal_head_avater = (WaveView) findViewById(R.id.personal_head_avater);//头像
        personal_head_avater.setOnClickListener(this);
        personal_real_name = (WaveView) findViewById(R.id.personal_real_name);//真实姓名
        personal_real_name.setOnClickListener(this);
        persoanl_mine_position = (WaveView) findViewById(R.id.persoanl_mine_position);//我的职位
        persoanl_mine_position.setOnClickListener(this);
        personal_mine_email = (WaveView) findViewById(R.id.personal_mine_email);//接受简历的邮箱
        personal_mine_email.setOnClickListener(this);
        personal_current_company = (WaveView) findViewById(R.id.personal_current_company);//当前公司
        personal_current_company.setOnClickListener(this);
        personal_relevance_QQ = (WaveView) findViewById(R.id.personal_relevance_QQ);//QQ关联
        personal_relevance_QQ.setOnClickListener(this);
        personal_relevance_weixin = (WaveView) findViewById(R.id.personal_relevance_weixin);//微信关联
        personal_relevance_weixin.setOnClickListener(this);
        personal_relevance_weibo = (WaveView) findViewById(R.id.personal_relevance_weibo);//微博关联
        personal_relevance_weibo.setOnClickListener(this);
        personal_relevance_submit = (RevealButton) findViewById(R.id.personal_relevance_submit);//保存
        personal_relevance_submit.setOnClickListener(this);
        roundimage = (RoundImageView) findViewById(R.id.personal_head_avater_rundimage);
        // 显示图片的配置
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.color.gray).showImageOnFail(R.color.gray).cacheInMemory(true).cacheOnDisc(true).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personal_head_avater:
                Pcamear.showAsDropDown(personal_relevance_submit);
                break;
            case R.id.personal_real_name:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type","realname");
                startActivity(intent);
                //设置切换动画，从左边进入，上面退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.persoanl_mine_position:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type","myposition");
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_mine_email:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type","myemail");
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_current_company:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type","currentcommany");
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_relevance_QQ:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type","QQ");
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_relevance_weixin:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type","weixin");
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_relevance_weibo:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type","weibo");
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_relevance_submit://提交
                if (NetBaseUtils.isConnnected(mactivity)) {
                    new UserRequest(mactivity, handler).UPDATECOMMANY(info.getUserid(),path,info.getRealname(),info.getMyjob(),info.getEmail(),info.getCompany(),info.getQq(),info.getWeixin(),info.getWeibo(), SAVEKEY);
                }else{
                    Toast.makeText(mactivity, R.string.net_error,Toast.LENGTH_SHORT).show();
                }
               break;
        }
    }
        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case PHOTO_REQUEST_CAMERA:// 相册
                    // 判断存储卡是否可以用，可用进行存储
                    String state = Environment.getExternalStorageState();
                    if (state.equals(Environment.MEDIA_MOUNTED)) {
                        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        File tempFile = new File(path, "newpic.jpg");
                        startPhotoZoom(Uri.fromFile(tempFile));
                    } else {
                        Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case PHOTO_REQUEST_ALBUM:// 图库
                    startPhotoZoom(data.getData());
                    break;

                case PHOTO_REQUEST_CUT: // 图片缩放完成后
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 340);
        intent.putExtra("outputY", 340);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param data
     */
    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(this.getResources(), photo);
            roundimage.setImageDrawable(drawable);
            picname = "avatar" + String.valueOf(new Date().getTime());//加userid
            storeImageToSDCARD(photo, picname, filepath);
            //上传头像
            if (NetBaseUtils.isConnnected(mactivity)) {
                new UserRequest(mactivity, handler).uoloadavator(head, KEY);
            } else {
                Toast.makeText(this, R.string.net_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * storeImageToSDCARD 将bitmap存放到sdcard中
     */
    public void storeImageToSDCARD(Bitmap colorImage, String ImageName, String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        File imagefile = new File(file, ImageName + ".jpg");
        try {
            imagefile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagefile);
            colorImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            head = imagefile.getPath();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
