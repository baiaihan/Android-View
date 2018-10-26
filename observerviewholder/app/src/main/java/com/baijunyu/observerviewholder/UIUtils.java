/*--------------------------------------------------
 * Copyright (C) 2015 The Android Y-CarStore Project
 *                http://www.yesway.cn/
 * 创建时间：2016/7/02
 * 内容说明：公共的fragment基类
 * 
 * 编号		日期			担当者		内容                  
 * -------------------------------------------------
 *
 * 
 *--------------------------------------------------*/
package com.baijunyu.observerviewholder;


import android.content.Context;
import android.os.Handler;

import com.yesway.ycarstore.rdx.MainApplication;

/**
 * @描述: 和UI操作相关的类
 */
public class UIUtils
{
	/** 获取上下文 */
	public static Context getContext()
	{
		return MainApplication.getContext();
	}

	/** 获取主线程id */
	public static long getMainThreadId()
	{
		return MainApplication.getMainThreadId();
	}

	/** 获取主线程handler */
	public static Handler getMainThreadHandler()
	{
		return MainApplication.getMainThreadHandler();
	}

	/** 主线程中执行 任务 */
	public static void runOnUiThread(Runnable task)
	{
		long currentThreadId = android.os.Process.myTid();
		long mainThreadId = getMainThreadId();

		if (currentThreadId == mainThreadId)
		{
			// 如果在主线程中执行
			task.run();
		}
		else
		{
			// 需要转的主线程执行
			getMainThreadHandler().post(task);
		}
	}


}
