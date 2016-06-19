package com.ghllz.travel.ui;

import java.util.ArrayList;
import java.util.List;

import com.ghllz.travel.R;
import com.ghllz.travel.adapter.DiaryCoverAdapter;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.config.Configs;
import com.ghllz.travel.presenter.CoverListPresenterImpl;
import com.ghllz.travel.view.xlist.XListView;
import com.ghllz.travel.view.xlist.XListView.IXListViewListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

public class ThemeActivity extends BaseActivity implements ICoverListView, IXListViewListener{

	CoverListPresenterImpl presenter;
	//下拉刷新listview
	XListView mListView;
	DiaryCoverAdapter mAdapter;
	private List<Cover> mCoverList;
	private View headerVeiw;
	
	private int lastSize=0;
	private String theme;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_theme);
		init();
		initView();
		initXListView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		presenter.showCoverList(theme);
	}

	private void init() {
		Intent intent = getIntent();
		theme = intent.getStringExtra(Configs.THEME);
	}

	private void initView() {
		presenter = new CoverListPresenterImpl(this);
		mListView = (XListView)findViewById(R.id.lv_theme);
		mCoverList = new ArrayList<Cover>();
		headerVeiw = findViewById(R.id.header_theme);
		setHeaderViewTitle(headerVeiw, theme);
		ImageView leftImage = setHeaderViewImage(headerVeiw, R.drawable.arrowhead,Position.LEFT);
		leftImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void initXListView() {

		mListView.setPullLoadEnable(true);
		mListView.setPullRefreshEnable(false);
		// 设置监听器
		mListView.setXListViewListener(this);
		mListView.pullRefreshing();
		mListView.setDividerHeight(0);
		mAdapter = new DiaryCoverAdapter(this,mCoverList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Cover item = (Cover) mAdapter.getItem(position-2);
				Intent intent = new Intent(ThemeActivity.this,DetailDiaryActivity.class);
				intent.putExtra(Configs.COVER, item);
				startActivity(intent);
			}

		});
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
	public void changeViewPager(int order) {

	}

	@Override
	public void onRefresh() {

	}

	@Override
	public void onLoadMore() {
		if(mListView.getPullLoading()){
			return;
		}
		presenter.showCoverList(theme);
	}
}
