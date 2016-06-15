package com.ghllz.travel.presenter;

import android.content.Context;

import com.ghllz.travel.R;
import com.ghllz.travel.bean.DetailBean;
import com.ghllz.travel.listener.OnDetailContentFinishListener;
import com.ghllz.travel.model.DetailListModelImpl;
import com.ghllz.travel.model.IDetailListModel;
import com.ghllz.travel.ui.BaseActivity.Position;
import com.ghllz.travel.ui.IDetailListView;

public class DetailListPresenterImpl implements IDetailListPresenter{
	private IDetailListView view;
	private IDetailListModel model;
	private Context context;
	public DetailListPresenterImpl(IDetailListView view){
		this.view = view;
		this.context = (Context) view;
		this.model = new DetailListModelImpl();
	}

	@Override
	public void onCreate() {
		view.setHeaderViewImage(R.drawable.share_icon, Position.RIGHT);
		view.setHeaderViewImage(R.drawable.arrowhead, Position.LEFT);
	}

	@Override
	public void onDestroy() {
		
	}

	@Override
	public void ShowDetailList(String Url) {
		model.getDetailContent(Url, new OnDetailContentFinishListener() {
			@Override
			public void onGetDetailContents(DetailBean result) {
				view.showDetailList(result.getDayInfo(),result.getPlanBox());
			}
		});
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		
	}

}
