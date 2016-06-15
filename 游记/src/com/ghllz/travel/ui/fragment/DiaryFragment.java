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
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.ghllz.travel.R;
import com.ghllz.travel.adapter.DiaryCoverAdapter;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.config.Configs;
import com.ghllz.travel.presenter.CoverListPresenterImpl;
import com.ghllz.travel.ui.DetailDiaryActivity;
import com.ghllz.travel.ui.ICoverListView;
import com.ghllz.travel.view.xlist.XListView;
import com.ghllz.travel.view.xlist.XListView.IXListViewListener;

public class DiaryFragment extends FragmentBase implements ICoverListView, IXListViewListener{
	

	CoverListPresenterImpl presenter;
	//下拉刷新listview
	XListView mListView;
	DiaryCoverAdapter mAdapter;
	private List<Cover> mCoverList;
	private View headerPic;
	private ViewPager vp;

	int lastSize=0;
	private DiaryReceiver myReceiver;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_diary, container, false);
		initView(view);
		initXListView();
		initReceiver();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.showCoverList();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(myReceiver);
	}
	private void initView(View view) {
		presenter = new CoverListPresenterImpl(this);
		mListView = (XListView)view.findViewById(R.id.lv_diary);
		mCoverList = new ArrayList<Cover>();
	}


	private void initXListView() {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		headerPic = inflater.inflate(R.layout.header_view_pager_pic, mListView,false);
		vp = (ViewPager) headerPic.findViewById(R.id.vp_header_pics);
		MyAdpater vpAdapter = new MyAdpater();
		vp.setAdapter(vpAdapter);
		vp.setCurrentItem(1500);
		vp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				//把事件的处理权还给ListView
				mListView.requestDisallowInterceptTouchEvent(false);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				//告诉ListView，不要抢我的事件
				mListView.requestDisallowInterceptTouchEvent(true);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		mListView.addHeaderView(vp);
		// 首先不允许加载更多
		mListView.setPullLoadEnable(true);
		// 允许下拉
		mListView.setPullRefreshEnable(false);
		// 设置监听器
		mListView.setXListViewListener(this);
		mListView.pullRefreshing();
		mListView.setDividerHeight(0);
		mAdapter = new DiaryCoverAdapter(getActivity(),mCoverList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Cover item = (Cover) mAdapter.getItem(position-2);
				Intent intent = new Intent(getActivity(),DetailDiaryActivity.class);
				intent.putExtra(Configs.COVER, item);
				startActivity(intent);
			}

		});
	}
	
	private void initReceiver() {
		myReceiver = new DiaryReceiver();
		IntentFilter intentFilter= new IntentFilter();
		intentFilter.addAction(Configs.CHANGE_VIEWPAGER);
		getActivity().registerReceiver(myReceiver, intentFilter);
	}


	@Override
	public void showCoverList(List<Cover> covers) {
		mAdapter.addAll(covers);
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setSelection(lastSize);
		lastSize = mListView.getChildCount();
	}

	@Override
	public void onRefresh() {
		
	}

	@Override
	public void onLoadMore() {
		if(mListView.getPullLoading()){
			return;
		}
		presenter.showCoverList();
	}
	
	@Override
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
	
	public class DiaryReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action.equals(Configs.CHANGE_VIEWPAGER)){
				changeViewPager(intent.getIntExtra("order",0));
			}
		}
	}
}
