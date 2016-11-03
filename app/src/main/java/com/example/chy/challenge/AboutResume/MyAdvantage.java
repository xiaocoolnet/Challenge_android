package com.example.chy.challenge.AboutResume;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.R;

/**
 * Created by 77588 on 2016/9/13.
 */
public class MyAdvantage extends Activity implements View.OnClickListener{
    private TextView txtLenth;
    private EditText editAdvantage;
    private ImageView back,compelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_myadvantage);
        initview();
    }
    private void initview() {
        back = (ImageView) findViewById(R.id.back );
        back.setOnClickListener(this);
        compelete = (ImageView) findViewById(R.id.compelete);
        compelete.setOnClickListener(this);
        txtLenth = (TextView) findViewById(R.id.txtlength);
        editAdvantage = (EditText) findViewById(R.id.editAdvantage);
        editAdvantage.addTextChangedListener(mTextWatcher);

    }

    TextWatcher mTextWatcher = new TextWatcher(){
        private CharSequence temp;
        private int editStart ;
        private int editEnd ;
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
            temp = s;
        }
        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            txtLenth.setText(String.valueOf(temp.length())+"/120");
        }
        @Override
        public void afterTextChanged(Editable s) {
            editStart = editAdvantage.getSelectionStart();
            editEnd = editAdvantage.getSelectionEnd();
            if (temp.length() > 120) {
                Toast.makeText(getApplicationContext(),"你输入的字数已经超过了限制！", Toast.LENGTH_SHORT).show();
                s.delete(editStart-1, editEnd);
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.compelete:
                finish();
                break;
            default:
                break;
        }
    }
}
