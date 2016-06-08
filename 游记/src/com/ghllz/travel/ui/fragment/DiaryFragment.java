package com.ghllz.travel.ui.fragment;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ghllz.travel.R;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.presenter.CoverListPresenterImpl;
import com.ghllz.travel.ui.BaseActivity.Position;
import com.ghllz.travel.ui.ICoverListView;

public class DiaryFragment extends FragmentBase implements ICoverListView{

	//头部布局
	View headerView;

	CoverListPresenterImpl presenter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_diary, container, false);
		initView(view);
		Log.d("Tag","fragment_diary");
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.onResume();
	}
	
	private void initView(View view) {
		presenter = new CoverListPresenterImpl(this);
		headerView = view.findViewById(R.id.title_diary);
	}
	
	@Override
	public void setHeaderViewTitle(String title) {
		setHeaderViewTitle(headerView, title);
	}

	@Override
	public void setHeaderViewImage(int resId, Position position,OnClickListener listener) {
		ImageView image = setHeaderViewImage(headerView, resId, position);
		image.setOnClickListener(listener);
	}

	@Override
	public void showCoverList(List<Cover> covers) {

	}
}
