package com.ghllz.travel.model;

import com.ghllz.travel.listener.OnCoversFinishListener;
import com.ghllz.travel.util.HttpUtil;

public class CoverListModelImpl implements ICoverListModel{

	private int page=1;
	@Override
	public void getCoverList(OnCoversFinishListener listener){
		page++;
		HttpUtil.getCoversResponse("",page,listener);
	}

}
