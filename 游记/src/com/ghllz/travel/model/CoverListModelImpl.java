package com.ghllz.travel.model;

import com.ghllz.travel.listener.OnCoversFinishListener;
import com.ghllz.travel.util.HttpUtil;

public class CoverListModelImpl implements ICoverListModel{

	@Override
	public void getCoverList(int page,OnCoversFinishListener listener){
		HttpUtil.getCoversResponse("",page,listener);
	}

}
