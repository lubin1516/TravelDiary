package com.ghllz.travel.presenter;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.ghllz.travel.R;
import com.ghllz.travel.bean.DayInfo;
import com.ghllz.travel.bean.DetailBean;
import com.ghllz.travel.bean.PlanBox;
import com.ghllz.travel.listener.OnDetailContentFinishListener;
import com.ghllz.travel.model.DetailListModelImpl;
import com.ghllz.travel.model.IDetailListModel;
import com.ghllz.travel.ui.BaseActivity.Position;
import com.ghllz.travel.ui.IDetailListView;
import com.ghllz.travel.util.DataUtil;

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
	public void ShowDetailList(String url) {
		if(DataUtil.haveDayInfo(url)&&DataUtil.havePlanBox(url)){
			List<DayInfo> days = DataUtil.getDayInfo(url);
			List<PlanBox> fragments = DataUtil.getPlanBox(url);
			view.showDetailList(days, fragments);
		}else{
			model.getDetailContent(url, new OnDetailContentFinishListener() {
				@Override
				public void onGetDetailContents(DetailBean result) {
					if(result.getDayInfo()!=null&&result.getPlanBox()!=null){
						view.showDetailList(result.getDayInfo(),result.getPlanBox());
					}else{
						Log.d("TAG", result.getDayInfo().toString());
						Log.d("TAG", result.getPlanBox().toString());
					}
				}
			});
		}
	}

	@Override
	public void onResume() {

	}

}
