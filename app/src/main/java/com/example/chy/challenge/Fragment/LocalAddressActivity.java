package com.example.chy.challenge.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chy.challenge.R;
import com.example.chy.challenge.text.City;
import com.example.chy.challenge.text.District;
import com.example.chy.challenge.text.Provence;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by SJL on 2016/11/01.
 */
public class LocalAddressActivity extends Activity implements View.OnClickListener {
    private Context context;
    private String city;
    private TextView tv_back;
    private List<Provence> provences;
    private Spinner spinner01;
    private Spinner spinner02;
    ArrayAdapter<Provence> adapter01;
    private Provence provence;
    ArrayAdapter<City> adapter02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_local_address);
        context = this;
        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        initView();
    }

    private void initView() {
//        et_address = (EditText) findViewById(R.id.et_address);
//        et_address.setText(city);
//        // 切换后将EditText光标置于末尾
//        CharSequence charSequence = et_address.getText();
//        if (charSequence instanceof Spannable) {
//            Spannable spanText = (Spannable) charSequence;
//            Selection.setSelection(spanText, charSequence.length());
//        }
//        KeyBoardUtils.showKeyBoardByTime(et_address, 300);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        spinner01 = (Spinner) findViewById(R.id.spinner01);
        spinner02 = (Spinner) findViewById(R.id.spinner02);
        provences = new ArrayList<>();
        try {
            provences = getProvinces();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter01 = new ArrayAdapter<Provence>(context, android.R.layout.simple_list_item_1, provences);
        adapter01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner01.setAdapter(adapter01);

        spinner01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                provence = provences.get(position);
                adapter02 = new ArrayAdapter<City>(context,
                        android.R.layout.simple_list_item_1, provences.get(
                        position).getCitys());
                spinner02.setAdapter(adapter02);
                spinner02.setSelection(0, true);
//				visable = 1;
//				//设置自动触发
//				spinner02.performClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
//                adapter03 = new ArrayAdapter<District>(MainActivity.this,
//                        android.R.layout.simple_list_item_1, provence.getCitys().get(position)
//                        .getDistricts());
//                spinner03.setAdapter(adapter03);
//                spinner03.setSelection(0, true);
//				if(visable==1){
//
//				}else {
//					spinner03.performClick();
//				}
//				visable=0;
                city= spinner02.getSelectedItem().toString();
                Log.e("city",city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                Intent intent = new Intent();
                intent.putExtra("city",city+"市");
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.putExtra("city",city+"市");
            setResult(RESULT_OK,intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    //此处为XML文件加载数据函数  调用函数可直接加载

    public List<Provence> getProvinces() throws XmlPullParserException,
            IOException {
        List<Provence> provinces = null;
        Provence province = null;
        List<City> citys = null;
        City city = null;
        List<District> districts = null;
        District district = null;
        Resources resources = getResources();

        InputStream in = resources.openRawResource(R.raw.citys_weather);

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        parser.setInput(in, "utf-8");
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    provinces = new ArrayList<Provence>();
                    break;
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();
                    if ("p".equals(tagName)) {
                        province = new Provence();
                        citys = new ArrayList<City>();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("p_id".equals(attrName))
                                province.setId(attrValue);
                        }
                    }
                    if ("pn".equals(tagName)) {
                        province.setName(parser.nextText());
                    }
                    if ("c".equals(tagName)) {
                        city = new City();
                        districts = new ArrayList<District>();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("c_id".equals(attrName))
                                city.setId(attrValue);
                        }
                    }
                    if ("cn".equals(tagName)) {
                        city.setName(parser.nextText());
                    }
                    if ("d".equals(tagName)) {
                        district = new District();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("d_id".equals(attrName))
                                district.setId(attrValue);
                        }
                        district.setName(parser.nextText());
                        districts.add(district);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("c".equals(parser.getName())) {
                        city.setDistricts(districts);
                        citys.add(city);
                    }
                    if ("p".equals(parser.getName())) {
                        province.setCitys(citys);
                        provinces.add(province);
                    }

                    break;

            }
            event = parser.next();

        }
        return provinces;
    }

}
