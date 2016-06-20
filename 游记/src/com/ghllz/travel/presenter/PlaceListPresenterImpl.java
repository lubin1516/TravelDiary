package com.ghllz.travel.presenter;

import java.util.List;

import com.ghllz.travel.bean.Place;
import com.ghllz.travel.listener.OnPlaceFinishListener;
import com.ghllz.travel.model.IPlaceListModel;
import com.ghllz.travel.model.PlaceListModelImpl;
import com.ghllz.travel.ui.IPlaceListView;
import com.ghllz.travel.util.DataUtil;

public class PlaceListPresenterImpl implements IPlaceListPresenter{
	IPlaceListView view;
	IPlaceListModel model;
	public PlaceListPresenterImpl(IPlaceListView view) {
		super();
		this.view = view;
		this.model = new PlaceListModelImpl();
	}

	@Override
	public void ShowPlaceList() {

		if(DataUtil.havePlaceData()){
			List<Place> places = DataUtil.getPlaceData();
			view.showPlaceList(places);
		}else{
			model.getPlaceList(new OnPlaceFinishListener() {

				@Override
				public void getPlace(List<Place> placeList) {
					view.showPlaceList(placeList);
				}
			});
		}
	}

}
