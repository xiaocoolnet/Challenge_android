package com.example.chy.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomSalary extends Activity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom_salary);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try{
            Thread.sleep(2000);
            startActivity(new Intent(this,SalaryMain.class));
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
