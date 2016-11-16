package com.example.chy.challenge.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.example.chy.challenge.NetInfo.LocationService;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Srearch;

/**
 * Created by 77588 on 2016/11/15.
 */

public class Chance extends Fragment implements View.OnClickListener{
    private LocationService locationService;
    private ImageView search;
    private GradientDrawable left, right;
    private Button btnFindWork, btnFindBoss;
    private Context mContext;
    private TextView detail_loading,tv_chance_local;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chance, container, false);
        initview(view);
        mContext = getActivity();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ChanceFindWork chanceFindWork = new ChanceFindWork();
        transaction.replace(R.id.f_chance, chanceFindWork);
        transaction.commit();
        return view;
    }

    private void initview(View v) {

        btnFindWork = (Button) v.findViewById(R.id.btnfindWork);
        btnFindWork.setOnClickListener(this);
        btnFindBoss = (Button) v.findViewById(R.id.btnfindBoss);
        btnFindBoss.setOnClickListener(this);
        left = (GradientDrawable) btnFindWork.getBackground();
        right = (GradientDrawable) btnFindBoss.getBackground();
        search = (ImageView) v.findViewById(R.id.search);
        search.setOnClickListener(this);
        tv_chance_local = (TextView) v.findViewById(R.id.tv_chance_local);
        tv_chance_local.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:
                Intent intent1 = new Intent();
                intent1.setClass(mContext, Srearch.class);
                intent1.putExtra("city", "homepage");
                startActivity(intent1);
                break;
            case R.id.tv_chance_local:
                Intent intent = new Intent();
                intent.setClass(mContext,LocalAddressActivity.class);
                intent.putExtra("city",tv_chance_local.getText().toString());
                startActivityForResult(intent,1);
            case R.id.btnfindWork:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                ChanceFindWork chanceFindWork = new ChanceFindWork();
                transaction.replace(R.id.f_chance, chanceFindWork);
                transaction.commit();
                right.setColor(getResources().getColor(R.color.green));
                left.setColor(getResources().getColor(R.color.white));
                btnFindWork.setTextColor(getResources().getColor(R.color.green));
                btnFindBoss.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.btnfindBoss:
                left.setColor(getResources().getColor(R.color.green));
                right.setColor(getResources().getColor(R.color.white));
                btnFindWork.setTextColor(getResources().getColor(R.color.white));
                btnFindBoss.setTextColor(getResources().getColor(R.color.green));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            tv_chance_local.setText(data.getStringExtra("city"));
            Log.e("city=",data.getStringExtra("city"));
        }
    }


    public void onrefrsh(){
        // -----------location config ------------
        locationService = ((com.example.chy.challenge.Utils.application)getActivity().getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getActivity().getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();// 定位SDK
        // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
    }

    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nerror code : ");
                sb.append(location.getLocType());
                sb.append("\nlatitude : ");
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");
                sb.append(location.getLongitude());
                sb.append("\nradius : ");
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");
                sb.append(location.getCityCode());
                sb.append("\ncity : ");
                sb.append(location.getCity());
                sb.append("\nDistrict : ");
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");
                sb.append(location.getStreet());
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\nDescribe: ");
                sb.append(location.getLocationDescribe());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());
                sb.append("\nPoi: ");
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 单位：米
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    sb.append("\noperationers : ");
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                Log.e("sb=", sb.toString());
                tv_chance_local.setText(location.getCity());
                locationService.stop();
            }
        }
    };

}
