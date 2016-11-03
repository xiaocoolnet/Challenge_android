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
public class WorkExperience extends Activity implements View.OnClickListener{
    private EditText editText;
    private TextView num;
    private ImageView back,compelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_work_experience);
        initview();
    }
    private void initview() {
        back = (ImageView) findViewById(R.id.back );
        back.setOnClickListener(this);
        compelete = (ImageView) findViewById(R.id.compelete);
        compelete.setOnClickListener(this);
        num = (TextView) findViewById(R.id.txtnum);
        editText = (EditText) findViewById(R.id.edit_work);
        editText.addTextChangedListener(mTextWatcher);
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
            num.setText(String.valueOf(temp.length())+"/300");
        }
        @Override
        public void afterTextChanged(Editable s) {
            editStart = editText.getSelectionStart();
            editEnd = editText.getSelectionEnd();
            if (temp.length() > 300) {
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
