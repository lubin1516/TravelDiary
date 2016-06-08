package com.ghllz.travel.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.im.BmobChat;

import com.ghllz.travel.CustomApplcation;
import com.ghllz.travel.R;



/** 基类
 * @ClassName: BaseActivity
 * @Description: TODO
 * @author smile
 * @date 2014-6-13 下午5:05:38
 */
public class BaseActivity extends FragmentActivity {

	CustomApplcation mApplication;
	protected int mScreenWidth;
	protected int mScreenHeight;

	public static final int LEFT = 0;
	public static final int RIGHT= 1;
	public static enum Position{LEFT,RIGHT}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApplication = CustomApplcation.getInstance();
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
	}

	Toast mToast;

	public void ShowToast(final String text) {
		if (!TextUtils.isEmpty(text)) {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					if (mToast == null) {
						mToast = Toast.makeText(getApplicationContext(), text,
								Toast.LENGTH_LONG);
					} else {
						mToast.setText(text);
					}
					mToast.show();
				}
			});

		}
	}

	public void ShowToast(final int resId) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (mToast == null) {
					mToast = Toast.makeText(BaseActivity.this.getApplicationContext(), resId,
							Toast.LENGTH_LONG);
				} else {
					mToast.setText(resId);
				}
				mToast.show();
			}
		});
	}


	public void  setHeaderViewTitle(View headerVeiw,String title){
		TextView  headerTv = (TextView) headerVeiw.findViewById(R.id.tv_header_view);

		if(TextUtils.isEmpty(title)){
			headerTv.setText("");
		}else{
			headerTv.setText(title);
		}
	}

	public ImageView setHeaderViewImage(View headerView,int source,Position position){
		ImageView imageView = null;
		if(position==Position.LEFT){
			imageView = (ImageView) headerView.findViewById(R.id.iv_header_left);
			imageView.setVisibility(View.VISIBLE);
		}else{
			imageView = (ImageView) headerView.findViewById(R.id.iv_header_right);
			imageView.setVisibility(View.VISIBLE);
		}
		imageView.setImageResource(source);

		return imageView;
	}


	/** 打Log
	 * ShowLog
	 * @return void
	 * @throws
	 */
	public void ShowLog(String msg){
		if(BmobChat.DEBUG_MODE){
			Log.i("life",msg);
		}
	}

	public void startAnimActivity(Class<?> cla) {
		this.startActivity(new Intent(this, cla));
	}

	public void startAnimActivity(Intent intent) {
		this.startActivity(intent);
	}
}
