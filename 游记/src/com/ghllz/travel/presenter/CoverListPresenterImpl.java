package com.ghllz.travel.presenter;

import java.util.List;

import android.content.Context;
import android.content.Intent;

import com.activeandroid.util.Log;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.listener.OnCoversFinishListener;
import com.ghllz.travel.model.CoverListModelImpl;
import com.ghllz.travel.model.ICoverListModel;
import com.ghllz.travel.ui.ICoverListView;
import com.ghllz.travel.util.DataUtil;

public class CoverListPresenterImpl implements ICoverListPresenter{
	ICoverListView view;
	ICoverListModel model;
	int page = 0;
	public CoverListPresenterImpl(ICoverListView view) {
		super();
		this.view = view;
		this.model = new CoverListModelImpl();
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
		page++;
		if(DataUtil.haveCoverData(page)){
			List<Cover> covers = DataUtil.getCoverData(page);
			view.showCoverList(covers);
		}else{
			model.getCoverList(page,new OnCoversFinishListener() {
				@Override
				public void onGetCovers(List<Cover> Covers) {
					view.showCoverList(Covers);
					Log.d("Tag","text¥”Õ¯¬Áœ¬‘ÿ");
				}
			});
		}
	}

	@Override
	public void jumpTo(Context context, Intent intent) {
		context.startActivity(intent);
	}


}
