package com.ghllz.travel.ui;

import java.util.List;

import com.ghllz.travel.R;
import com.ghllz.travel.bean.Place;
import com.ghllz.travel.presenter.IPlaceListPresenter;
import com.ghllz.travel.presenter.PlaceListPresenterImpl;

import android.os.Bundle;

public class SearchActivity extends BaseActivity implements IPlaceListView{

	IPlaceListPresenter mPresenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_theme);
		init();
	}
	
	private void init() {
		mPresenter = new PlaceListPresenterImpl(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mPresenter.ShowPlaceList();
	}
	@Override
	public void showPlaceList(List<Place> places) {
		
	}

}
