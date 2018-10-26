package com.baijunyu.observerviewholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 95190 on 2018/7/18.
 */

@SuppressLint("NewApi")
public class MessageNumHolder extends BaseHolder<AppInfo> implements
        MessageManager.MessageNumObserver {

    /**
     * 信息bean
     */
    private AppInfo mInfoBean;
    /**
     * activity上下文
     */
    private Context mContext;
    /**
     * 消息通知管理者
     */
    private MessageManager mMessageManager;
    /**
     * message信息
     */
    private AppInfo mMessageAppInfo;
    private TextView mtv_message_num;

    public MessageNumHolder(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    protected View initView() {
        //
        View view = View.inflate(UIUtils.getContext(), R.layout.message_num,
                null);
        mtv_message_num = (TextView) view.findViewById(R.id.tv_message_num);
        mMessageManager = MessageManager.getInstance();
        return view;
    }

    /**
     * 初始化UI调用
     */
    @Override
    protected void refreshUI(AppInfo data) {
        // 数据接收
        this.mInfoBean = data;

        safeRefreshView(mInfoBean);

    }

    /**
     * 开始观察
     */
    public void startObserver() {
        mMessageManager.addObserver(this);
        // 根据状态更新UI
        safeRefreshView(mInfoBean);
    }

    /**
     * 停止观察
     */
    public void stopObserver() {
        mMessageManager.deleteObserver(this);
    }

    /**
     * 当消息条数改变时，此方法调用
     */
    @Override
    public void onMessageNumStateChange(MessageManager manager, AppInfo data) {
        mMessageAppInfo = data;
        safeRefreshView(mMessageAppInfo);


    }


    /**
     * 安全的UI刷新,因为有可能在子线程执行
     */
    private void safeRefreshView(final AppInfo data) {
        UIUtils.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                mMessageAppInfo = data;
                refreshView(mMessageAppInfo);
            }
        });
    }

    //

    /**
     * 更新UI界面
     */
    private void refreshView(AppInfo data) {

        String name = data.getAppname();
        mtv_message_num.setText(name);
    }


}

