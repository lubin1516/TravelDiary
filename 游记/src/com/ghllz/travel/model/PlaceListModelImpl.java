package com.ghllz.travel.model;

import com.ghllz.travel.listener.OnPlaceFinishListener;
import com.ghllz.travel.util.HttpUtil;

public class PlaceListModelImpl implements IPlaceListModel{

	@Override
	public void getPlaceList(OnPlaceFinishListener listener) {
		HttpUtil.getPlace(listener);
	}

}
