package com.ghllz.travel.ui;

import java.util.List;

import com.ghllz.travel.bean.Cover;

public interface ICoverListView  {
	void showCoverList(List<Cover> covers);
	void changeViewPager(int order);
}
