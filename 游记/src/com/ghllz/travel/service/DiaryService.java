package com.ghllz.travel.service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.bean.DetailBean;
import com.ghllz.travel.bean.Place;
import com.ghllz.travel.config.Configs;
import com.ghllz.travel.listener.OnDetailContentFinishListener;
import com.ghllz.travel.listener.OnPlaceFinishListener;
import com.ghllz.travel.util.DataUtil;
import com.ghllz.travel.util.HttpUtil;


public class DiaryService extends Service {
	public Context context;
	Timer diary_timer;
	int order = 0;
	private ServiceReceiver myReceiver;
	public void onCreate() {
		super.onCreate();
		initReceiver();
		initUpdate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		return Service.START_NOT_STICKY;
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(myReceiver);
	}


	private void updateDetail() {
		new Thread(){
			public void run() {
				for(int i=1;i<=3;i++){
					if(!DataUtil.haveCoverData(i,"")){
						continue;
					}
					Log.d("TAG", i+"");
					List<Cover> covers = DataUtil.getCoverData(i,"");
					for(Cover cover:covers){
						String url = cover.getBookUrl();
						if(!(DataUtil.haveDayInfo(url)||DataUtil.havePlanBox(url))){
							HttpUtil.getDetailContent(cover.bookUrl, new OnDetailContentFinishListener() {
								@Override
								public void onGetDetailContents(DetailBean result) {

								}
							});
						}
					}
				}
			};
		}.start();
	}
	
	private void loadPlace() {
		new Thread(){
			public void run() {
				if(DataUtil.havePlaceData()){
					return;
				}else{
					HttpUtil.getPlace(new OnPlaceFinishListener() {
						@Override
						public void getPlace(List<Place> placeList) {
							Log.d("TAG", placeList.toString());
						}
					});
				}
			}
		}.start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new MyIBinder();
	}


	private void initUpdate() {
		//修改Service的启动方式为非粘性
		diary_timer = new Timer();
		diary_timer.schedule(new TimerTask() {
			@Override
			public void run() {
				order++;
				Intent intent = new Intent(Configs.CHANGE_VIEWPAGER);
				intent.putExtra("order", order);
				sendBroadcast(intent);
			}
		}, 0,5000);
	}

	private void initReceiver() {
		myReceiver = new ServiceReceiver();
		IntentFilter intentFilter= new IntentFilter();
		intentFilter.addAction(Configs.UPDATE_DETAIL);
		registerReceiver(myReceiver, intentFilter);
	}

	public class ServiceReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action.equals(Configs.UPDATE_DETAIL)){
				Log.d("TAG", "RUN Start");
				loadPlace();
			}
		}

	}

	public class MyIBinder extends Binder{
		public DiaryService getService(){
			return DiaryService.this;
		}
	}

}
