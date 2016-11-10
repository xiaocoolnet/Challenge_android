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

/**
 * Created by 77588 on 2016/11/7.
 */

public class CompanyName extends Activity implements View.OnClickListener {
    private ImageView back, submit;
    private EditText editText;
    private TextView textLengh,maxLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_name);
        initview();
    }

    private void initview() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        submit = (ImageView) findViewById(R.id.compelete);
        submit.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.comNameInput);
        editText.addTextChangedListener(mTextWatcher);
        textLengh = (TextView) findViewById(R.id.text_length);
        maxLength = (TextView) findViewById(R.id.manLength);
    }
    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart ;
        private int editEnd ;
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textLengh.setText(String.valueOf(temp.length()));
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = editText.getSelectionStart();
            editEnd = editText.getSelectionEnd();
            if (temp.length() > Integer.parseInt(maxLength.getText().toString())) {
                Toast.makeText(getApplicationContext(),"你输入的字数已经超过了限制！", Toast.LENGTH_SHORT).show();
                s.delete(editStart-1, editEnd);
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
                Intent intent = new Intent();
                intent.putExtra("name",editText.getText().toString());
                setResult(0,intent);
                finish();
                break;
            default:
                break;
        }
    }
}
