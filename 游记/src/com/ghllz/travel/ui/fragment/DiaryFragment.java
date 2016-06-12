package com.ghllz.travel.ui.fragment;

import java.util.ArrayList;
import java.util.List;

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

import com.ghllz.travel.R;
import com.ghllz.travel.adapter.DiaryCoverAdapter;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.presenter.CoverListPresenterImpl;
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
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_diary, container, false);
		initView(view);
		initXListView();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.showCoverList();
		mListView.addHeaderView(vp);
	}

	private void initView(View view) {
		presenter = new CoverListPresenterImpl(this);
		mListView = (XListView)view.findViewById(R.id.mListView);
		mCoverList = new ArrayList<Cover>();
	}


	private void initXListView() {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		headerPic = inflater.inflate(R.layout.header_list_pic, mListView,false);
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
		// 首先不允许加载更多
		mListView.setPullLoadEnable(false);
		// 允许下拉
		mListView.setPullRefreshEnable(true);
		// 设置监听器
		mListView.setXListViewListener(this);
		mListView.pullRefreshing();
		mListView.setDividerHeight(0);
		mAdapter = new DiaryCoverAdapter(getActivity(),mCoverList);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}

		});
	}


	@Override
	public void showCoverList(List<Cover> covers) {
		mAdapter.addAll(covers);
	}

	@Override
	public void onRefresh() {
		presenter.showCoverList();
	}

	@Override
	public void onLoadMore() {

	}

	private class MyAdpater extends PagerAdapter{

		List<Integer> ids;

		public MyAdpater(){
			ids = new ArrayList<Integer>();
			ids.add(R.layout.list_pics_1);
			ids.add(R.layout.list_pics_2);
			ids.add(R.layout.list_pics_3);
		}

		@Override
		public int getCount() {
			return 3000;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			int layoutid = ids.get(position%3);
			View view = getActivity().getLayoutInflater().inflate(layoutid, vp,false);
			ImageView image = (ImageView) view.findViewById(R.id.iv_pics_list);
			image.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
	}
}
