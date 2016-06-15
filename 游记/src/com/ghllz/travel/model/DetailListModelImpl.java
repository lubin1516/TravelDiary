package com.ghllz.travel.model;

import com.ghllz.travel.listener.OnDetailContentFinishListener;
import com.ghllz.travel.util.HttpUtil;


public class DetailListModelImpl implements IDetailListModel{

	@Override
	public void getDetailContent(String url,OnDetailContentFinishListener listener) {
		HttpUtil.getDetailContent(url, listener);
	}
}
