package com.example.chy.challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.chy.challenge.Models.CompanyInfo;
import com.example.chy.challenge.Utils.LogUtils;
import com.example.chy.challenge.button.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by 77588 on 2016/11/6.
 */

public class CompanyInformation extends Activity implements View.OnClickListener {
    private PopupWindow popupWindow;
    private final int KEY_NAME = 1;
    private final int KEY_WEB = 2;
    private final int KEY_INTRODUCTION = 3;
    private final int KEY_PRODUCT = 4;
    private Context mContext;
    private ImageView back, compelete;
    private TextView company_name, company_web, industry, com_introduce, produte_info, count, financing;
    private LinearLayout setCompanyName, setCompanyWeb, setCount, setFinancing, setIntroduction, setProdute;
    private CompanyInfo.DataBean dataBean;
    private RoundImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_infomation);
        mContext = this;
        dataBean = (CompanyInfo.DataBean) getIntent().getSerializableExtra("data");
        initview();
        initLoad();
    }

    private void initview() {
        company_name = (TextView) findViewById(R.id.company_name);
        company_web = (TextView) findViewById(R.id.company_web);
        industry = (TextView) findViewById(R.id.industry);
        com_introduce = (TextView) findViewById(R.id.com_introduce);
        produte_info = (TextView) findViewById(R.id.produte_info);
        count = (TextView) findViewById(R.id.count);
        financing = (TextView) findViewById(R.id.financing);
        logo = (RoundImageView) findViewById(R.id.logo);
        ImageLoader imageLoader = ImageLoader.getInstance();
//        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap)
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        compelete = (ImageView) findViewById(R.id.compelete);
        compelete.setOnClickListener(this);
        setCompanyName = (LinearLayout) findViewById(R.id.setCompanyName);
        setCompanyName.setOnClickListener(this);
        setCompanyWeb = (LinearLayout) findViewById(R.id.setCompanyWeb);
        setCompanyWeb.setOnClickListener(this);
        setCount = (LinearLayout) findViewById(R.id.setCount);
        setCount.setOnClickListener(this);
        setFinancing = (LinearLayout) findViewById(R.id.setFinancing);
        setFinancing.setOnClickListener(this);
        setIntroduction = (LinearLayout) findViewById(R.id.setIntrodution);
        setIntroduction.setOnClickListener(this);
        setProdute = (LinearLayout) findViewById(R.id.setProdute);
        setProdute.setOnClickListener(this);
    }

    private void initLoad() {
        company_name.setText(dataBean.getCompany_name());
        company_web.setText(dataBean.getCompany_web());
        industry.setText(dataBean.getIndustry());
        com_introduce.setText(dataBean.getCom_introduce());
        produte_info.setText(dataBean.getProdute_info());
        count.setText(dataBean.getCount());
        financing.setText(dataBean.getFinancing());
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.compelete:
                //提交
                break;
            case R.id.setCompanyName:
                startActivityForResult(new Intent(mContext, CompanyName.class), KEY_NAME);
                break;
            case R.id.setCompanyWeb:
                startActivityForResult(new Intent(mContext, CompanyWeb.class), KEY_WEB);
                break;
            case R.id.setCount:
                showPopupwindow(1);
                break;
            case R.id.setFinancing:
                showPopupwindow(2);
                break;
            case R.id.ccp1:
                dataBean.setCount(getResources().getText(R.string.ccp1).toString());
                initLoad();
                popupWindow.dismiss();
                break;
            case R.id.ccp2:
                dataBean.setCount(getResources().getText(R.string.ccp2).toString());
                initLoad();
                popupWindow.dismiss();
                break;
            case R.id.ccp3:
                dataBean.setCount(getResources().getText(R.string.ccp3).toString());
                initLoad();
                popupWindow.dismiss();
                break;
            case R.id.cfp1:
                dataBean.setFinancing(getResources().getText(R.string.cfp1).toString());
                initLoad();
                popupWindow.dismiss();
                break;
            case R.id.cfp2:
                dataBean.setFinancing(getResources().getText(R.string.cfp2).toString());
                initLoad();
                popupWindow.dismiss();
                break;
            case R.id.cfp3:
                dataBean.setFinancing(getResources().getText(R.string.cfp3).toString());
                initLoad();
                popupWindow.dismiss();
                break;
            case R.id.setIntrodution:
                startActivityForResult(new Intent(mContext, CompanyIntroduction.class), KEY_INTRODUCTION);
                break;
            case R.id.setProdute:
                startActivityForResult(new Intent(mContext, CompanyProduct.class), KEY_PRODUCT);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case KEY_NAME:
                    dataBean.setCompany_name(data.getExtras().getString("name"));
                    initLoad();
                    break;
                case KEY_WEB:
                    dataBean.setCompany_web(data.getExtras().getString("web"));
                    initLoad();
                    break;
                case KEY_INTRODUCTION:
                    dataBean.setCom_introduce(data.getExtras().getString("comdata"));
                    LogUtils.e("Tip", dataBean.getCom_introduce());
                    break;
                case KEY_PRODUCT:
                    dataBean.setProdute_info(data.getExtras().getString("prodata"));
                    LogUtils.e("Tip", dataBean.getProdute_info());
                    break;
                default:
                    break;
            }
        }
    }

    private void showPopupwindow(int flag) {
        View contentView = null;
        switch (flag) {
            case 1:
                contentView = LayoutInflater.from(mContext).inflate(R.layout.company_count_popupwindow, null);
                TextView ccp1 = (TextView) contentView.findViewById(R.id.ccp1);
                TextView ccp2 = (TextView) contentView.findViewById(R.id.ccp2);
                TextView ccp3 = (TextView) contentView.findViewById(R.id.ccp3);
                ccp1.setOnClickListener(this);
                ccp2.setOnClickListener(this);
                ccp3.setOnClickListener(this);
                break;
            case 2:
                contentView = LayoutInflater.from(mContext).inflate(R.layout.company_financing_popupwindow, null);
                TextView cfp1 = (TextView) contentView.findViewById(R.id.cfp1);
                TextView cfp2 = (TextView) contentView.findViewById(R.id.cfp2);
                TextView cfp3 = (TextView) contentView.findViewById(R.id.cfp3);
                cfp1.setOnClickListener(this);
                cfp2.setOnClickListener(this);
                cfp3.setOnClickListener(this);
                break;
        }
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        View rootView = LayoutInflater.from(this).inflate(R.layout.company_infomation, null);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }
}
