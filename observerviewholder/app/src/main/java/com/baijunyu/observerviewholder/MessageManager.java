package com.baijunyu.observerviewholder;

/**
 * Created by 95190 on 2018/7/18.
 */

import android.content.Context;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 消息通知num管理类
 */
public class MessageManager {


    /**
     * 存储的是观察者
     */
    private List<MessageNumObserver> mObservers = new LinkedList<MessageNumObserver>();

    /**
     * Tag
     */
    public static final String TAG = MessageManager.class.getSimpleName();
    /**
     * 单例
     */
    private static MessageManager sDownloadManager;
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 单例
     *
     * @return
     */
    public static MessageManager getInstance() {

        if (sDownloadManager == null) {
            synchronized (MessageManager.class) {
                sDownloadManager = new MessageManager();
            }
        }

        return sDownloadManager;
    }

    /**
     * 私有构造方法
     */
    private MessageManager() {

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            public void run() {
//
//                AppInfo appInfo = new AppInfo();
//                appInfo.setAppname(""+System.currentTimeMillis());
//                notifyStateChange(appInfo);
//            }
//        }, 1000, 5000);

    }

    /**
     * 通知观察者消息num改变
     */
    public void notifyStateChange(AppInfo data) {

        // 使用迭代器，保证线程安全的操作(进行优化之后)
        ListIterator<MessageNumObserver> iterator = mObservers.listIterator();
        while (iterator.hasNext()) {
            MessageNumObserver observer = iterator.next();
            observer.onMessageNumStateChange(this, data);

        }

    }

    /**
     * 添加观察者
     */
    public void addObserver(MessageNumObserver observer) {
        if (observer == null) {
            throw new NullPointerException("observer == null");
        }
        synchronized (this) {
            if (!mObservers.contains(observer))
                mObservers.add(observer);
        }
    }

    /**
     * 删除观察者
     */
    public synchronized void deleteObserver(MessageNumObserver observer) {
        mObservers.remove(observer);
    }

    /**
     * 自定义消息num观察者接口
     */
    public interface MessageNumObserver {

        void onMessageNumStateChange(MessageManager manager, AppInfo data);


    }


}
