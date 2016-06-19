package com.ghllz.travel.presenter;

import java.util.ArrayList;
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
	List<Cover> mCoverData;
	int page = 0;
	public CoverListPresenterImpl(ICoverListView view) {
		super();
		this.view = view;
		this.model = new CoverListModelImpl();
		this.mCoverData = new ArrayList<Cover>();
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
	public void showCoverList(String theme) {
		page++;
		if(DataUtil.haveCoverData(page,theme)){
			List<Cover> covers = DataUtil.getCoverData(page,theme);
			if(!mCoverData.contains(covers)){
				mCoverData.addAll(covers);
			}
			view.showCoverList(mCoverData);
		}else{
			model.getCoverList(page,theme,new OnCoversFinishListener() {
				@Override
				public void onGetCovers(List<Cover> covers) {
					if(!mCoverData.contains(covers)){
						mCoverData.addAll(covers);
					}
					view.showCoverList(mCoverData);
				}
			});
		}
	}

	@Override
	public void jumpTo(Context context, Intent intent) {
		context.startActivity(intent);
	}


}
