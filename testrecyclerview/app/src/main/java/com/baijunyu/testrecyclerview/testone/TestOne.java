package com.baijunyu.testrecyclerview.testone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.baijunyu.testrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 可以看到RecyclerView可以实现：
 * <p>
 * ListView的功能
 * GridView的功能
 * 横向ListView的功能，参考Android 自定义RecyclerView 实现真正的Gallery效果
 * 横向ScrollView的功能
 * 瀑布流效果
 * 便于添加Item增加和移除动画
 *
 *
 */

public class TestOne extends AppCompatActivity {
    @BindView(R.id.mrecycleview)
    public RecyclerView mRecyclerView;
    private MyRecycleViewAdapter mAdapter;
    private List<String> mList;

    /**
     * 可以看到RecyclerView可以实现：
     * <p>
     * ListView的功能
     * GridView的功能
     * 横向ListView的功能，参考Android 自定义RecyclerView 实现真正的Gallery效果
     * 横向ScrollView的功能
     * 瀑布流效果
     * 便于添加Item增加和移除动画
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testone);
        ButterKnife.bind(this);
        initDate();
        // 系统提供了3个实现类：
        //
        //1.LinearLayoutManager 现行管理器，支持横向、纵向。
        //2.GridLayoutManager 网格布局管理器
        //3.StaggeredGridLayoutManager 瀑布就式布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //分割线，样式修改   <item name="android:listDivider">@drawable/divider_bg</item>
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        //listview
        //mRecyclerView.setLayoutManager(linearLayoutManager);
        //gridview
        // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        //横向gridview(item固定宽高),瀑布流（自适应宽高）
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));

        mRecyclerView.setAdapter(mAdapter = new MyRecycleViewAdapter(this, mList));
        //mAdapter.notifyItemChanged();
        // mAdapter.notifyItemRangeChanged();
        mAdapter.setOnItemClickLitener(new MyRecycleViewAdapter.OnItemClickLitener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(TestOne.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(TestOne.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });
    }

    private void initDate() {
        mList = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mList.add("" + (char) i);
        }
    }
}
