package com.ghllz.travel.model;

import com.ghllz.travel.listener.OnCoversFinishListener;
import com.ghllz.travel.util.HttpUtil;

public class CoverListModelImpl implements ICoverListModel{

	private int i=1;
	@Override
	public void getCoverList(OnCoversFinishListener listener){
		i++;
		HttpUtil.getCoversResponse("",i,listener);
	}

}
