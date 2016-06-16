package com.ghllz.travel.ui;

import java.util.ArrayList;
import java.util.List;

import com.ghllz.travel.R;
import com.ghllz.travel.adapter.DetailDayAdapter;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.bean.DayInfo;
import com.ghllz.travel.bean.PlanBox;
import com.ghllz.travel.config.Configs;
import com.ghllz.travel.presenter.DetailListPresenterImpl;
import com.ghllz.travel.presenter.IDetailListPresenter;
import com.ghllz.travel.util.MImageLoader;
import com.ghllz.travel.view.CircleImageView;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
public class DetailDiaryActivity extends BaseActivity implements IDetailListView{
	private View mHeaderView;
	private IDetailListPresenter mPresenter;
	private Cover mCover;

	private ListView mListView;
	DetailDayAdapter mAdapter;
	private List<DayInfo> mDays;
	private List<PlanBox> mFragments;
	private View headerTitle;
	private ImageView headerImage;
	private CircleImageView userImage;
	private TextView content;
	private TextView days;
	private TextView likeNume;
	private TextView title;
	private TextView userName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_diary);
		initView();
		initListView();
		mPresenter.onCreate();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mPresenter.ShowDetailList(mCover.getBookUrl());
	}

	private void initView() {
		mHeaderView = findViewById(R.id.title_detail);
		mPresenter = new DetailListPresenterImpl(this);
		mCover = (Cover) getIntent().getSerializableExtra(Configs.COVER);
		mListView = (ListView) findViewById(R.id.lv_detail_container);
		mDays = new ArrayList<DayInfo>();
		mFragments = new ArrayList<PlanBox>();
	}

	private void initListView() {
		headerTitle = getLayoutInflater().inflate(R.layout.header_llayout_title_detail, mListView,false);

		headerImage = (ImageView) headerTitle.findViewById(R.id.iv_detail_diary_headImage);
		userImage = (CircleImageView) headerTitle.findViewById(R.id.cv_detail_diary_userHeadImg);
		userName = (TextView) headerTitle.findViewById(R.id.tv_detail_diary_userName);
		content =  (TextView) headerTitle.findViewById(R.id.tv_detail_diary_content);
		days =  (TextView) headerTitle.findViewById(R.id.tv_detail_diary_days);
		likeNume =  (TextView) headerTitle.findViewById(R.id.tv_detail_diary_likeNume);
		title =  (TextView) headerTitle.findViewById(R.id.tv_detail_diary_title);

		MImageLoader.loadImage(mCover.getHeadImageUrl(),headerImage,210,280);
		MImageLoader.loadImage(mCover.getUserHeadImgUrl(),userImage,40,40);
		userName.setText(mCover.getUserName());
		days.setText(mCover.getDays()+"Ìì");
		likeNume.setText(mCover.getLikeCount());
		title.setText(mCover.getTitle());

		mListView.addHeaderView(headerTitle);
		mAdapter = new DetailDayAdapter(this,mDays,mFragments);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

			}

		});
	}

	@Override
	public void setHeaderViewTitle(String title) {
		setHeaderViewTitle(mHeaderView, title);
	}

	@Override
	public void setHeaderViewImage(int resId, Position position) {
		if(Position.LEFT==position){
			ImageView leftImage = setHeaderViewImage(mHeaderView,resId,position);
			leftImage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}else{
			ImageView RightImage = setHeaderViewImage(mHeaderView,resId,position); 
			RightImage.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});
		}
	}

	@Override
	public void showDetailList(List<DayInfo> days,List<PlanBox> fragments) {
		mAdapter.addAll(days,fragments);
	}

}
