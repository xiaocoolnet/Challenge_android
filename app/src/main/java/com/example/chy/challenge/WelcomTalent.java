package com.example.chy.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 77588 on 2016/9/1.
 */
public class WelcomTalent extends Activity implements Runnable{
    private boolean firstUseFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom_talent);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try{
            Thread.sleep(2000);
            startActivity(new Intent(this,TalentMain.class));
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
