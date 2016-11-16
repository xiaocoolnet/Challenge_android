package com.example.chy.challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 77588 on 2016/10/21.
 */

public class PersonInformationTalent extends Activity implements View.OnClickListener{
    private RelativeLayout back;
    private Button save;
    private LinearLayout myName,myJob,myEmail,myCompany;
    private TextView name,job,email,company;
    private final int KEY_NAME = 1;
    private final int KEY_JOB = 2;
    private final int KEY_EMAIL = 3;
    private final int KEY_COMPANY = 4;
    private Intent intent;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_information_talent);
        mContext = this;
        initview();
        intent = new Intent(mContext,PersonInformationTalentInput.class);
    }

    private void initview() {
        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        save = (Button) findViewById(R.id.person_save);
        save.setOnClickListener(this);
        myName = (LinearLayout) findViewById(R.id.setMyName);
        myName.setOnClickListener(this);
        myJob = (LinearLayout) findViewById(R.id.setMyJob);
        myJob.setOnClickListener(this);
        myEmail = (LinearLayout) findViewById(R.id.setMyEmail);
        myEmail.setOnClickListener(this);
        myCompany = (LinearLayout) findViewById(R.id.setMyCompany);
        myCompany.setOnClickListener(this);
        name = (TextView) findViewById(R.id.real_name);
        job = (TextView) findViewById(R.id.my_job);
        email = (TextView) findViewById(R.id.my_email);
        company = (TextView) findViewById(R.id.my_company);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.person_save:
                startActivity(new Intent(this,WelcomTalent.class));
                break;
            case R.id.setMyName:
                intent.putExtra("title",getResources().getText(R.string.myname_title).toString());
                intent.putExtra("note",getResources().getText(R.string.myname_note).toString());
                intent.putExtra("maxlength",getResources().getText(R.string.myname_maxlength).toString());
                startActivityForResult(intent,KEY_NAME);
                break;
            case R.id.setMyJob:
                intent.putExtra("title",getResources().getText(R.string.myjob_title).toString());
                intent.putExtra("note",getResources().getText(R.string.myjob_note).toString());
                intent.putExtra("maxlength",getResources().getText(R.string.myjob_maxlength).toString());
                startActivityForResult(intent,KEY_JOB);
                break;
            case R.id.setMyEmail:
                intent.putExtra("title",getResources().getText(R.string.myemail_title).toString());
                intent.putExtra("note",getResources().getText(R.string.myemail_note).toString());
                intent.putExtra("maxlength",getResources().getText(R.string.myemail_maxlength).toString());
                startActivityForResult(intent,KEY_EMAIL);
                break;
            case R.id.setMyCompany:
                intent.putExtra("title",getResources().getText(R.string.mycompany_title).toString());
                intent.putExtra("note",getResources().getText(R.string.mycompany_note).toString());
                intent.putExtra("maxlength",getResources().getText(R.string.mycompany_maxlength).toString());
                startActivityForResult(intent,KEY_COMPANY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            switch (requestCode){
                case KEY_NAME:
                    name.setText(data.getExtras().getString("info"));
                    break;
                case KEY_JOB:
                    job.setText(data.getExtras().getString("info"));
                    break;
                case KEY_EMAIL:
                    email.setText(data.getExtras().getString("info"));
                    break;
                case KEY_COMPANY:
                    company.setText(data.getExtras().getString("info"));
                    break;
                default:
                    break;
            }
        }
    }
}
