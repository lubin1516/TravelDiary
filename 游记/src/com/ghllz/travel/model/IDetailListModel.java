package com.ghllz.travel.model;

import com.ghllz.travel.listener.OnDetailContentFinishListener;

public interface IDetailListModel extends IModel{
	void getDetailContent(String url,OnDetailContentFinishListener listener);
}
