package com.example.chy.challenge.login.register.personal_pop;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.login.register.Register_personal_info;

import java.io.File;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class Personal_camera implements PopupWindow.OnDismissListener,View.OnClickListener{
    private Register_personal_info mactivity;
    private View view,rootview;
    private RevealButton popcamera_photograph,popcamera_photo_album,popcamera_cancel;
    private PopupWindow popupWindow;

    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_CUT = 3;// 相册
    private static final int PHOTO_REQUEST_ALBUM = 2;// 剪裁
    public Personal_camera(Register_personal_info mactivity) {
        this.mactivity = mactivity;

        view = LayoutInflater.from(mactivity).inflate(R.layout.pop_camera, null);
        popcamera_photograph = (RevealButton) view.findViewById(R.id.popcamera_photograph);//拍照
        popcamera_photograph.setOnClickListener(this);
        popcamera_photo_album = (RevealButton) view.findViewById(R.id.popcamera_photo_album);//相册
        popcamera_photo_album.setOnClickListener(this);
        popcamera_cancel = (RevealButton) view.findViewById(R.id.popcamera_cancel);//取消
        popcamera_cancel.setOnClickListener(this);

        //获取pop所在页面的布局
//        rootview = mactivity.getWindow().getDecorView();


        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置popwindow的动画效果
        popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.popcamera_photograph:
                //添加6.0权限
                getCheck();
                getphotograph();
                break;
            case R.id.popcamera_photo_album:
                getCheck();
                getphotoalbum();
                break;
            case R.id.popcamera_cancel:
                dissmiss();
                break;
        }
    }
    //相册选择方法
    private void getphotoalbum() {
        Intent intentFromGallery = new Intent();
        intentFromGallery.setType("image/*"); // 设置文件类型
        intentFromGallery.setAction(Intent.ACTION_PICK);
        mactivity.startActivityForResult(intentFromGallery, PHOTO_REQUEST_ALBUM);
    }
    //拍照方法
    private void getphotograph() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File file = new File(path, "newpic.jpg");
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        mactivity.startActivityForResult(intentFromCapture, PHOTO_REQUEST_CAMERA);
    }

    /**
     * 消除弹窗
     */
    public void dissmiss() {
        popupWindow.dismiss();
    }
    /**
     * 弹窗显示的位置
     */
    public void showAsDropDown(View parent) {
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        WindowManager manager = (WindowManager) mactivity
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int screenHeight = display.getHeight();
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, screenHeight - location[1]);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
    }
    //6.0申请动态权限
    private void getCheck() {
        if (ContextCompat.checkSelfPermission(mactivity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mactivity, new String[]{Manifest.permission.CAMERA},
                    100);
        }
        if (ContextCompat.checkSelfPermission(mactivity, Manifest.permission.RECEIVE_BOOT_COMPLETED)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mactivity, new String[]{Manifest.permission.RECEIVE_BOOT_COMPLETED},
                    100);
        }
        if (ContextCompat.checkSelfPermission(mactivity, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mactivity, new String[]{Manifest.permission.READ_PHONE_STATE},
                    100);
        }
        if (ContextCompat.checkSelfPermission(mactivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mactivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }
        if (ContextCompat.checkSelfPermission(mactivity, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mactivity, new String[]{Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS},
                    100);
        }
    }

}
