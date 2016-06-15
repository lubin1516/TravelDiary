package com.ghllz.travel.ui;

import java.util.List;

import com.ghllz.travel.bean.DayInfo;
import com.ghllz.travel.bean.PlanBox;

public interface IDetailListView extends IView{
	void showDetailList(List<DayInfo> days,List<PlanBox> fragments);
}
