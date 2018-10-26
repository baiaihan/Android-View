package com.baijunyu.testrecyclerview.testthree;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.baijunyu.testrecyclerview.R;
import com.baijunyu.testrecyclerview.testone.DividerItemDecoration;
import com.baijunyu.testrecyclerview.testtwo.AVUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 95190 on 2018/8/24.
 */

public class TestThree extends AppCompatActivity {
    @BindView(R.id.mrecycleview)
    public RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    public Button refresh;
    @BindView(R.id.refresh2)
    public Button refresh2;
    private ContactRefreshPartAdapter mAdapter;
    private List<AVUser> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testthree);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        for (int j = 0; j <= 10; j++) {

            list.add(new AVUser());
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器 为线性布局
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));//设置分割线
        mAdapter = new ContactRefreshPartAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setDataSource(list);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.refresh)
    public void setRefresh() {
        list.get(0).setUserName("Jason");
        mAdapter.notifyItemChanged(0,0);//刷新position为100上的userName
    }

    @OnClick(R.id.refresh2)
    public void setRefresh2() {
        list.get(2).setUserID("1000");
        mAdapter.notifyItemChanged(2,1);//刷新position为100上的userName
    }

}
