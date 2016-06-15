package com.ghllz.travel.ui;

import com.ghllz.travel.R;
import com.ghllz.travel.presenter.DetailListPresenterImpl;
import com.ghllz.travel.presenter.IDetailListPresenter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class DetailDiaryActivity extends BaseActivity implements IDetailListView{
	private View headerView;
	private IDetailListPresenter presenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_diary);
		iniView();
		presenter.onCreate();
	}

	private void iniView() {
		headerView = findViewById(R.id.title_detail);
		presenter = new DetailListPresenterImpl(this);
	}

	@Override
	public void setHeaderViewTitle(String title) {
		setHeaderViewTitle(headerView, title);
	}

	@Override
	public void setHeaderViewImage(int resId, Position position) {
		if(Position.LEFT==position){
			ImageView leftImage = setHeaderViewImage(headerView,resId,position);
			leftImage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}else{
			ImageView RightImage = setHeaderViewImage(headerView,resId,position); 
			RightImage.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});
		}
	}

	@Override
	public void showDetailList() {
		
	}
}
