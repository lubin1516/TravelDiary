package com.ghllz.travel.model;

import com.ghllz.travel.listener.OnCoversFinishListener;

public interface ICoverListModel extends IModel{
	void getCoverList(int page,String theme,OnCoversFinishListener listener);
}
