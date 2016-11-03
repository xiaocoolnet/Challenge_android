package com.example.chy.challenge;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.chy.challenge.Fragment.Messages;
import com.example.chy.challenge.Fragment.MineForCompany;
import com.example.chy.challenge.Fragment.NoPosition;
import com.example.chy.challenge.Fragment.Talent;

/**
 * Created by 77588 on 2016/9/1.
 */
public class TalentMain extends Activity implements View.OnClickListener,NoPosition.btnAddClickListener,MineForCompany.btnSettingListener {
    private RadioButton btnTalent,btnMessage,btnMine;
    private boolean HadPosition = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_talent);
        initview();
        btnTalent.setChecked(true);
        btnTalent.setTextColor(getResources().getColor(R.color.green));
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Talent talent = new Talent();
        transaction.replace(R.id.talent_layout,talent);
        transaction.commit();

    }

    private void initview() {
        btnTalent = (RadioButton) findViewById(R.id.btnTalent);
        btnTalent.setOnClickListener(this);
        btnMessage = (RadioButton) findViewById(R.id.btnMessage);
        btnMessage.setOnClickListener(this);
        btnMine = (RadioButton) findViewById(R.id.btnMine);
        btnMine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        btnTalent.setTextColor(getResources().getColor(R.color.gray));
        btnMessage.setTextColor(getResources().getColor(R.color.gray));
        btnMine.setTextColor(getResources().getColor(R.color.gray));
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.btnTalent:
                btnTalent.setTextColor(getResources().getColor(R.color.green));
                Talent talent = new Talent();
                transaction.replace(R.id.talent_layout,talent);
                transaction.commit();
                break;
            case R.id.btnMessage:
                btnMessage.setTextColor(getResources().getColor(R.color.green));
                Messages messages = new Messages();
                transaction.replace(R.id.talent_layout,messages);
                transaction.commit();
                break;
            case R.id.btnMine:
                btnMine.setTextColor(getResources().getColor(R.color.green));
                MineForCompany mineForCompany = new MineForCompany();
                transaction.replace(R.id.talent_layout, mineForCompany);
                transaction.commit();
                break;
            default:
                break;
        }
    }
    public void onBtnClick(){
        startActivity(new Intent(this, AddPosition.class));
    }

    @Override
    public void onBtnSettingClick() {
        startActivity(new Intent(this,Settings.class));
    }
}
