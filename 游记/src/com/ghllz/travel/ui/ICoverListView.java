package com.ghllz.travel.ui;

import java.util.List;

import com.ghllz.travel.bean.Cover;

public interface ICoverListView extends IView {
	void showCoverList(List<Cover> covers);
}
