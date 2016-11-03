package com.example.chy.challenge.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.chy.challenge.R;

/**
 * Created by SJL on 2016/11/2.
 */

public class SearchReasultActivity extends Activity implements View.OnClickListener {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_result_shop);
        Intent intent = getIntent();
        context=this;

    }

    @Override
    public void onClick(View view) {

    }
}

