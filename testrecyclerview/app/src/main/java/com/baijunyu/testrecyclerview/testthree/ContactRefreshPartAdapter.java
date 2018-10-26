package com.baijunyu.testrecyclerview.testthree;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baijunyu.testrecyclerview.R;
import com.baijunyu.testrecyclerview.testtwo.AVUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 95190 on 2018/8/24.
 */

public class ContactRefreshPartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<AVUser> mList;//数据源
    private Context mContext;//context上下文
    private LayoutInflater mInflater;//布局解析器 用来解析布局生成View的

    private int mSelectedPos = -1;//保存当前选中的position 重点！

    //构造函数，传入context和与当前adapter绑定的recyclerView
    public ContactRefreshPartAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        mList = new ArrayList<>();//防止为设置数据源时为空
    }

    //设置数据源
    public void setDataSource(List<AVUser> list) {
        this.mList = list;
    }

    //创建ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContactHolder holder = new ContactHolder(mInflater.inflate(R.layout.contact_recyclerview_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //不使用 使用3个参数的重载函数
    }

    //绑定ViewHolder中的资源 使用三个参数的onBindViewHolder
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position, List payloads) {
        final ContactHolder contact = (ContactHolder) holder;
        if (payloads.isEmpty()) {
            //payloads即有效负载，当首次加载或调用notifyDatasetChanged() ,notifyItemChange(int position)进行刷新时，payloads为empty 即空
            AVUser user = mList.get(position);
            contact.userName.setText("www");
            contact.userId.setText("123");


        } else {
            //payloads不为空 即调用notifyItemChanged(position,payloads)方法后执行的
            //在这里可以获取payloads中的数据  进行局部刷新
            //假设是int类型
            int type = (int) payloads.get(0);// 刷新哪个部分 标志位
            switch (type) {
                case 0:
                    contact.userName.setText(mList.get(position).getUserName());//只刷新userName
                    break;
                case 1:
                    contact.userId.setText(mList.get(position).getUserID());//只刷新userId
                    break;
            }
        }


    }

    //提供给外部Activity来获取当前checkBox选中的item，这样就不用去遍历了 重点！

    public int getSelectedPos() {
        return mSelectedPos;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        //单选框 重点！
        @BindView(R.id.ContactSelect_list_item_user_name)
        public TextView userName;
        @BindView(R.id.ContactSelect_list_item_user_employeeId)
        public TextView userId;
        @BindView(R.id.ContactSelect_list_item_img)
        public ImageView userImg;

        public ContactHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
