package com.baijunyu.observerviewholder;

import android.view.View;

/**
 * BaseHolder
 */

public abstract class BaseHolder<T>
{
    protected View mRootView;
    protected T		mData;

    public BaseHolder() {
        mRootView = initView();

        // 打标记
        mRootView.setTag(this);
    }

    /** 初始化视图 */
    protected abstract View initView();

    /** UI刷新 */
    protected abstract void refreshUI(T data);

    public void setData(T data)
    {
        this.mData = data;

        // UI刷新
        refreshUI(mData);
    }

    /** 获取根视图 */
    public View getRootView()
    {
        return mRootView;
    }

}

