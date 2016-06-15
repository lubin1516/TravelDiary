package com.ghllz.travel.presenter;

import java.util.List;

import android.content.Context;
import android.content.Intent;

import com.ghllz.travel.CustomApplcation;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.listener.OnCoversFinishListener;
import com.ghllz.travel.model.CoverListModelImpl;
import com.ghllz.travel.model.ICoverListModel;
import com.ghllz.travel.ui.ICoverListView;
import com.ghllz.travel.util.DBUtil;

public class CoverListPresenterImpl implements ICoverListPresenter{
	ICoverListView view;
	ICoverListModel model;
	DBUtil dbUtil;

	public CoverListPresenterImpl(ICoverListView view) {
		super();
		this.view = view;
		this.model = new CoverListModelImpl();
		this.dbUtil = new DBUtil(CustomApplcation.getInstance());
	}
	
	@Override
	public void onCreate() {
		
	}

	@Override
	public void onResume() {
	}

	@Override
	public void onDestroy() {
	}

	@Override
	public void showCoverList() {
//		view.showCoverList(dbUtil.query());
		model.getCoverList(new OnCoversFinishListener() {
			@Override
			public void onGetCovers(List<Cover> Covers) {
				view.showCoverList(Covers);
				for(Cover cover:Covers){
					dbUtil.add(cover);
				}
			}
		});
	}

	@Override
	public void jumpTo(Context context, Intent intent) {
		context.startActivity(intent);
	}


}
