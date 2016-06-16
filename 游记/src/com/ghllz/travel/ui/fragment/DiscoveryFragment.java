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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.ghllz.travel.R;
import com.ghllz.travel.adapter.DiaryCoverAdapter;
import com.ghllz.travel.adapter.HeaderGridViewAdapter;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.config.Configs;
import com.ghllz.travel.ui.BaseActivity.Position;
import com.ghllz.travel.view.xlist.XListView;
import com.ghllz.travel.view.xlist.XListView.IXListViewListener;

public class DiscoveryFragment extends FragmentBase implements IXListViewListener {

	private List<String> strings ;
	private ViewPager vp;
	private View headerView;
	private DiscoveryReceiver myReceiver;
	private XListView mListView;
	private View headerViewPagerPic;
	private View headerScrollPic;
	private View headerGridView;
	private DiaryCoverAdapter mAdapter;
	private List<Cover> mCoverList;
	private GridView gv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_discovery, container, false);
		headerView = view.findViewById(R.id.title_discovery);
		initHeader();
		initView(view);
		initXListView();
		initReceiver();
		return view;
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(myReceiver);
	}

	private void initHeader() {
		setHeaderViewTitle(headerView,"发现");
		setHeaderViewImage(headerView,R.drawable.search,Position.RIGHT);
		setHeaderViewImage(headerView, R.drawable.sliding_menu, Position.LEFT);
	}

	private void initView(View view) {
		mListView = (XListView)view.findViewById(R.id.lv_discovery);
		mCoverList = new ArrayList<Cover>();
		strings = new ArrayList<String>();
		strings.add("美国");
		strings.add("英国");
		strings.add("法国");
		strings.add("伊朗");
		strings.add("新西兰");
		strings.add("日本");
		strings.add("泰国");
		strings.add("台湾");
		strings.add("韩国");
	}

	private void initXListView() {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		headerViewPagerPic = inflater.inflate(R.layout.header_view_pager_pic, mListView,false);
		headerScrollPic = inflater.inflate(R.layout.header_scroll_pic,mListView,false);
		headerGridView = inflater.inflate(R.layout.header_gridview_pic,mListView,false);
		vp = (ViewPager) headerViewPagerPic.findViewById(R.id.vp_header_pics);
		MyAdpater vpAdapter = new MyAdpater();
		vp.setAdapter(vpAdapter);
		vp.setCurrentItem(0);
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
		gv = (GridView) headerGridView.findViewById(R.id.gv_header);
		HeaderGridViewAdapter gridviewAdapter = new HeaderGridViewAdapter(getActivity(), strings);
		gv.setAdapter(gridviewAdapter);
		mListView.addHeaderView(vp);
		mListView.addHeaderView(headerScrollPic);
		mListView.addHeaderView(headerGridView);
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


	private void initReceiver() {
		myReceiver = new DiscoveryReceiver();
		IntentFilter intentFilter= new IntentFilter();
		intentFilter.addAction(Configs.CHANGE_VIEWPAGER);
		getActivity().registerReceiver(myReceiver, intentFilter);
	}

	public void changeViewPager(int order) {
		vp.setCurrentItem(order%3);
	}

	@Override
	public void onRefresh() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				mListView.stopRefresh();
			}
		});
	}


	@Override
	public void onLoadMore() {
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
			if(action.equals(Configs.CHANGE_VIEWPAGER)){
				changeViewPager(intent.getIntExtra("order",0));
			}
		}
	}
}
