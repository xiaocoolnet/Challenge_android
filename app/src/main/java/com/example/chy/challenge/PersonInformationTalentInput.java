package com.example.chy.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Utils.LogUtils;

/**
 * Created by 77588 on 2016/11/12.
 */

public class PersonInformationTalentInput extends Activity implements View.OnClickListener {
    private ImageView back, save;
    private EditText info;
    private TextView title, note, textLength, maxLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_information_talent_input);
        initview();
        inload();
    }

    private void initview() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        save = (ImageView) findViewById(R.id.compelete);
        save.setOnClickListener(this);
        info = (EditText) findViewById(R.id.info);
        title = (TextView) findViewById(R.id.title);
        note = (TextView) findViewById(R.id.note);
        textLength = (TextView) findViewById(R.id.text_length);
        maxLength = (TextView) findViewById(R.id.max_length);
    }

    private void inload() {
        title.setText(getIntent().getStringExtra("title"));
        note.setText(getIntent().getStringExtra("note"));
        maxLength.setText(getIntent().getStringExtra("maxlength"));
        info.addTextChangedListener(tw);
    }

    TextWatcher tw = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textLength.setText(String.valueOf(temp.length()));
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = info.getSelectionStart();
            editEnd = info.getSelectionEnd();
            if (temp.length() > Integer.parseInt(maxLength.getText().toString())) {
                Toast.makeText(getApplicationContext(), "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT).show();
                s.delete(editStart - 1, editEnd);
            }

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.compelete:
                LogUtils.e("Tip",info.getText().toString());
                if (info.getText() != null) {
                    Intent intent = new Intent();
                    intent.putExtra("info", info.getText().toString());
                    setResult(0, intent);
                } else {
                    setResult(0, null);
                }
                finish();
                break;
        }
    }
}
