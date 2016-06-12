package com.ghllz.travel.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.ghllz.travel.R;
import com.ghllz.travel.model.CoverListModelImpl;
import com.ghllz.travel.model.ICoverListModel;
import com.ghllz.travel.ui.BaseActivity.Position;
import com.ghllz.travel.ui.ICoverListView;

public class CoverListPresenterImpl implements ICoverListPresenter{
	ICoverListView view;
	ICoverListModel model;

	public CoverListPresenterImpl(ICoverListView view) {
		super();
		this.view = view;
		this.model = new CoverListModelImpl();
	}

	@Override
	public void onResume() {
	}

	@Override
	public void onDestroy() {
	}

	@Override
	public void showCoverList() {

	}

	@Override
	public void jumpTo(Context context, Intent intent) {
		context.startActivity(intent);
	}


}
