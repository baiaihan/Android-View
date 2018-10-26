package com.baijunyu.testrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.baijunyu.testrecyclerview.testone.TestOne;
import com.baijunyu.testrecyclerview.testthree.TestThree;
import com.baijunyu.testrecyclerview.testtwo.TestTwo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.testone)
    public Button testone;
    @BindView(R.id.testtwo)
    public Button testtwo;
    @BindView(R.id.testthree)
    public Button testthree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.testone)
    public void toTestOneActivity() {
        Intent intent = new Intent();
        intent.setClass(this,TestOne.class);
        startActivity(intent);

    }

    @OnClick(R.id.testtwo)
    public void toTestTwoActivity() {
        Intent intent = new Intent();
        intent.setClass(this,TestTwo.class);
        startActivity(intent);
    }
    @OnClick(R.id.testthree)
    public void toTestthreeActivity() {
        Intent intent = new Intent();
        intent.setClass(this,TestThree.class);
        startActivity(intent);
    }

}
