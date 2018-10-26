package com.baijunyu.observerviewholder;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 *
 */

public class MainApplication extends Application {

    /**
     * 主线程
     */
    private static Thread mMainThread;
    /**
     * 主线程id
     */
    private static long mMainThreadId;
    /**
     * 主线程handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 上下文
     */
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        // ②主线程和子线程
        mMainThread = Thread.currentThread();
        // ③主线程id
        // mMainThreadId = mMainThread.getId();//其中的一种获取主线程id的方法
        // android.os.Process.myPid();// 进程id
        mMainThreadId = android.os.Process.myTid();// 当前线程id
        // android.os.Process.myUid();// 用户id

        // ④主线程handler
        mMainThreadHandler = new Handler();
    }
    public static Context getContext() {
        return sContext;
    }
    /**
     * 获取主线程id的方法
     *
     * @return
     */
    public static long getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程handler的方法
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

}
