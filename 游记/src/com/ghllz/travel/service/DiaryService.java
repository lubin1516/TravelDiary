package com.ghllz.travel.service;

import java.util.Timer;
import java.util.TimerTask;

import com.ghllz.travel.config.Configs;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


public class DiaryService extends Service {
	public Context context;
	Timer diary_timer;
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//修改Service的启动方式为非粘性
		diary_timer = new Timer();
		diary_timer.schedule(new TimerTask() {
			int order = 0;
			@Override
			public void run() {
				order++;
				Intent intent = new Intent(Configs.CHANGE_VIEWPAGER);
				intent.putExtra("order", order);
				sendBroadcast(intent);
			}
		}, 0,5000);

		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new MyIBinder();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public class MyIBinder extends Binder{
		public DiaryService getService(){
			return DiaryService.this;
		}
	}
}
