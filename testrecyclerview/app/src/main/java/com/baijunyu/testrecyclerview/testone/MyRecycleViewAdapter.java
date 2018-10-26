package com.baijunyu.testrecyclerview.testone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baijunyu.testrecyclerview.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 95190 on 2018/8/23.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyHolder> {

    private OnItemClickLitener mOnItemClickLitener;
    Context context;
    List<String> list;

    public MyRecycleViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void update(List<String> list) {
        this.list = list;
    }
    public void addData(int position) {
        list.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }



    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    /**
     * 创建viewholder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrecycleview_item, parent, false);
        return new MyHolder(view);
    }

    /**
     * 操作item
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        String s = list.get(position);
        holder.textView.setText(s);
        //事件监听
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_test)
       public TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

}
