package com.baijunyu.testrecyclerview.testtwo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baijunyu.testrecyclerview.R;
import com.baijunyu.testrecyclerview.testone.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 95190 on 2018/8/24.
 */

public class TestTwo extends AppCompatActivity {
    @BindView(R.id.mrecycleview)
    public RecyclerView mRecyclerView;
    private ContactSelectAdapter mAdapter;
    private List<AVUser> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testone);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        for (int j=0;j<=10;j++){

            list.add( new AVUser());
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器 为线性布局
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));//设置分割线
        mAdapter=new ContactSelectAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setDataSource(list);
        mAdapter.notifyDataSetChanged();
    }
}
