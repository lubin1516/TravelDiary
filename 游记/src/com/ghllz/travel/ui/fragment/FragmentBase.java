package com.ghllz.travel.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.im.util.BmobLog;

import com.ghllz.travel.CustomApplcation;
import com.ghllz.travel.R;
import com.ghllz.travel.ui.BaseActivity.Position;

/** Fragmenet 基类
 * @ClassName: FragmentBase
 */
public abstract class FragmentBase extends Fragment {

	public CustomApplcation mApplication;

	public LayoutInflater mInflater;

	private Handler handler = new Handler();

	public void runOnWorkThread(Runnable action) {
		new Thread(action).start();
	}

	public void runOnUiThread(Runnable action) {
		handler.post(action);
	}
	
	public void runOnUiThread(Runnable action,long time){
		handler.postAtTime(action,time);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mApplication = CustomApplcation.getInstance();
		mInflater = LayoutInflater.from(getActivity());
	}

	public FragmentBase() {

	}

	public void  setHeaderViewTitle(View headerView,String title){
		TextView  headerTv = (TextView) headerView.findViewById(R.id.tv_header_view);

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


	Toast mToast;

	public void ShowToast(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	public void ShowToast(int text) {
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	/** 打Log
	 * ShowLog
	 * @return void
	 * @throws
	 */
	public void ShowLog(String msg){
		BmobLog.i(msg);
	}

	public View findViewById(int paramInt) {
		return getView().findViewById(paramInt);
	}

	/**
	 * 动画启动页面 startAnimActivity
	 * @throws
	 */
	public void startAnimActivity(Intent intent) {
		this.startActivity(intent);
	}

	public void startAnimActivity(Class<?> cla) {
		getActivity().startActivity(new Intent(getActivity(), cla));
	}

}
