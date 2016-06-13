package com.ghllz.travel.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.ghllz.travel.R;
import com.ghllz.travel.config.Config;
import com.ghllz.travel.ui.BaseActivity.Position;

public class DiscoveryFragment extends FragmentBase {
	
	private ViewPager vp;
	private View headerView;
	private DiscoveryReceiver myReceiver;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_discovery, container, false);
		headerView = view.findViewById(R.id.title_discovery);
		initHeader();
		initViewPage(view);
		initReceiver();
		return view;
	}
	

	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(myReceiver);
	}
	
	private void initHeader() {
		setHeaderViewTitle(headerView,"·¢ÏÖ");
		setHeaderViewImage(headerView,R.drawable.search,Position.RIGHT);
	}
	
	private void initViewPage(View view) {
		vp = (ViewPager) view.findViewById(R.id.vp_discovery_pics);
		MyAdpater vpAdapter = new MyAdpater();
		vp.setAdapter(vpAdapter);
		vp.setCurrentItem(0);
	}

	private void initReceiver() {
		myReceiver = new DiscoveryReceiver();
		IntentFilter intentFilter= new IntentFilter();
		intentFilter.addAction(Config.CHANGE_VIEWPAGER);
		getActivity().registerReceiver(myReceiver, intentFilter);
	}
	
	public void changeViewPager(int order) {
		vp.setCurrentItem(order%3);
	}

	private class MyAdpater extends PagerAdapter{

		List<Integer> ids;

		public MyAdpater(){
			ids = new ArrayList<Integer>();
			ids.add(R.drawable.diary_list_pics_1);
			ids.add(R.drawable.diary_list_pics_2);
			ids.add(R.drawable.diary_list_pics_3);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			int layoutid = ids.get(position%3);
			ShowLog(layoutid+"");
			ImageView image = new ImageView(getActivity());
			image.setImageResource(layoutid);
			image.setScaleType(ScaleType.FIT_XY);
			image.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});
			container.addView(image);
			return image;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
	}
	
	public class DiscoveryReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action.equals(Config.CHANGE_VIEWPAGER)){
				changeViewPager(intent.getIntExtra("order",0));
			}
		}
	}
}
