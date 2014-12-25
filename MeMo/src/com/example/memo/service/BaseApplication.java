package com.example.memo.service;



import com.example.memo.entity.DaoMaster;
import com.example.memo.entity.DaoSession;
import com.example.memo.entity.DaoMaster.OpenHelper;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application{
	private BaseApplication mInstance;
	private static DaoMaster mDaoMaster;
	private static DaoSession mDaoSession;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		if(mInstance == null){
			mInstance = this;
		}
	}
	//创建数据库
	public static DaoMaster getDaoMaster(Context context){
		if(mDaoMaster == null){
			OpenHelper helper = new DaoMaster.DevOpenHelper(context, "DAO", null);
			mDaoMaster = new DaoMaster(helper.getWritableDatabase());
		}
		return mDaoMaster;
	}
	
	public static DaoSession getDaoSession(Context context){
		if(mDaoSession == null){
			if(mDaoMaster == null){
				getDaoMaster(context);
			}
			mDaoSession = mDaoMaster.newSession();
		}
		return mDaoSession;
	}
	
}
