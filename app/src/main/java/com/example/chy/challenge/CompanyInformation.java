package com.example.chy.challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chy.challenge.Models.CompanyInfo;

/**
 * Created by 77588 on 2016/11/6.
 */

public class CompanyInformation extends Activity implements View.OnClickListener{
    private String companyName;
    private Context mContext;
    private ImageView back,compelete;
    private TextView company_name,company_web,industry,com_introduce,produte_info;
    private LinearLayout setCompanyName;
    private CompanyInfo.DataBean dataBean;
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
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        compelete = (ImageView) findViewById(R.id.compelete);
        compelete.setOnClickListener(this);
        setCompanyName = (LinearLayout) findViewById(R.id.setCompanyName);
        setCompanyName.setOnClickListener(this);
    }
    private void initLoad() {
        company_name.setText(dataBean.getCompany_name());
        company_web.setText(dataBean.getCompany_web());
        industry.setText(dataBean.getIndustry());
        com_introduce.setText(dataBean.getCom_introduce());
        produte_info.setText(dataBean.getProdute_info());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.compelete:
                //提交
                break;
            case R.id.setCompanyName:
                Intent intent = new Intent(mContext,CompanyName.class);
                startActivityForResult(intent,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
                dataBean.setCompany_name(data.getExtras().getString("name"));
                initLoad();
                break;
        }
    }
}
