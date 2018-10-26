package com.baijunyu.testrecyclerview.testtwo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.baijunyu.testrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95190 on 2018/8/24.
 */

public class ContactSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<AVUser> mList;//数据源
    private Context mContext;//context上下文
    private LayoutInflater mInflater;//布局解析器 用来解析布局生成View的

    private int mSelectedPos = -1;//保存当前选中的position 重点！

    //构造函数，传入context和与当前adapter绑定的recyclerView
    public ContactSelectAdapter(Context context) {
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
        ContactHolder holder = new ContactHolder(mInflater.inflate(R.layout.contact_select_recyclerview_item, parent, false));
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

            contact.checkBox.setChecked(mSelectedPos == position);
        } else {
            //当调用notifyItemChange(int position, Object payload)进行布局刷新时，payloads不会empty ，所以真正的布局刷新应该在这里实现 重点！
            contact.checkBox.setChecked(mSelectedPos == position);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedPos != position) {
                    //当前选中的position和上次选中不是同一个position 执行
                    contact.checkBox.setChecked(true);
                    if (mSelectedPos != -1) {
                        //判断是否有效 -1是初始值 即无效 第二个参数是Object 随便传个int 这里只是起个标志位
                        notifyItemChanged(mSelectedPos, 0);
                    }
                    mSelectedPos = position;
                }
            }
        });
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
        public CheckBox checkBox;
        public TextView userName;
        public TextView userId;
        public ImageView userImg;

        public ContactHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.ContactSelect_list_item_checkBox);
            userName = (TextView) itemView.findViewById(R.id.ContactSelect_list_item_user_name);
            userId = (TextView) itemView.findViewById(R.id.ContactSelect_list_item_user_employeeId);
            userImg = (ImageView) itemView.findViewById(R.id.ContactSelect_list_item_img);
        }
    }
}
