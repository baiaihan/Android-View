package com.yesway.yv_infotextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yesway.infotextview.InfoTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InfoTextView tv_plate = (InfoTextView)findViewById(R.id.tv_plate);
        tv_plate.setValue("小明");
        InfoTextView tv_age = (InfoTextView)findViewById(R.id.tv_age);
        tv_age.setValue("18岁");
        InfoTextView tv_gender = (InfoTextView)findViewById(R.id.tv_gender);
        tv_gender.setBottomViewVisible(View.GONE);
        tv_gender.setValue("男");

    }
}
