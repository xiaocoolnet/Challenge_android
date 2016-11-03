package com.example.chy.challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chy.challenge.Fragment.SearchReasultActivity;


public class Srearch extends Activity implements View.OnClickListener{
    private EditText searchInput;
    private LinearLayout searchHint;
    private Context context;
    private TextView tv_search;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        context = this;
        initview();
    }

    private void initview() {
        searchInput = (EditText) findViewById(R.id.search_input);
        searchHint = (LinearLayout) findViewById(R.id.search_hint);
        searchHint.setOnClickListener(this);
        tv_search = (TextView) findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_hint:
                searchInput.requestFocus();
                searchHint.setVisibility(View.INVISIBLE);
                break;
            case R.id.search_input:
                searchHint.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_search:
                if (!TextUtils.isEmpty(searchInput.getText().toString())){
                        Intent intent1 = new Intent();
                        intent1.putExtra("search_content",tv_search.getText().toString());
                        intent1.putExtra("city",city);
                        intent1.setClass(context, SearchReasultActivity.class);
                        startActivity(intent1);
                        finish();
                }else {
                    Log.e("无内容","无内容");
                }
                break;
            default:
                searchHint.setVisibility(View.VISIBLE);
                break;
        }
    }
}
