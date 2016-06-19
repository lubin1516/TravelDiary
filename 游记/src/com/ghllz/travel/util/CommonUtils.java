package com.ghllz.travel.util;

import com.ghllz.travel.config.Configs;
import com.ghllz.travel.ui.ThemeActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

public class CommonUtils {

	/** 检查是否有网络 */
	public static boolean isNetworkAvailable(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			return info.isAvailable();
		}
		return false;
	}

	/** 检查是否是WIFI */
	public static boolean isWifi(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			if (info.getType() == ConnectivityManager.TYPE_WIFI)
				return true;
		}
		return false;
	}

	/** 检查是否是移动网络 */
	public static boolean isMobile(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			if (info.getType() == ConnectivityManager.TYPE_MOBILE)
				return true;
		}
		return false;
	}

	private static NetworkInfo getNetworkInfo(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}

	/** 检查SD卡是否存在 */
	public static boolean checkSdCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}

	public static void onImageTouch(final Context context,View v ,int event,final String theme){
		AnimationSet animationSet = new AnimationSet(false);
		switch (event) {
		case MotionEvent.ACTION_DOWN:
			Animation D1 = new ScaleAnimation(1f,1.05f,1f,1.05f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f); 
			Animation D2 = new AlphaAnimation(1f,0.1f);

			animationSet.addAnimation(D1);
			animationSet.addAnimation(D2);
			animationSet.setDuration(500);
			animationSet.setFillAfter(true);
			animationSet.setInterpolator(new DecelerateInterpolator());
			v.startAnimation(animationSet);
			break;
		case MotionEvent.ACTION_UP:
			Animation U1 = new ScaleAnimation(1.05f,1.0f,1.05f,1.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f); 
			Animation U2 = new AlphaAnimation(0.1f,1f);

			animationSet.addAnimation(U1);
			animationSet.addAnimation(U2);
			animationSet.setDuration(1000);
			animationSet.setFillAfter(true);
			animationSet.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					Intent intent = new Intent(context,ThemeActivity.class);
					intent.putExtra(Configs.THEME, theme);
					context.startActivity(intent);
				}
			});
			v.startAnimation(animationSet);
			
			break;
		case MotionEvent.ACTION_CANCEL:
			Animation C1 = new ScaleAnimation(1.05f,1.0f,1.05f,1.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f); 
			Animation C2 = new AlphaAnimation(0.1f,1f);

			animationSet.addAnimation(C1);
			animationSet.addAnimation(C2);
			animationSet.setDuration(500);
			animationSet.setFillAfter(true);
			v.startAnimation(animationSet);
			break;
		}
	}
}
