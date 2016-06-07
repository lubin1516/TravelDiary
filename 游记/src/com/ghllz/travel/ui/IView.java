package com.ghllz.travel.ui;

import com.ghllz.travel.ui.BaseActivity.Position;

public interface IView {
	
	void setHeaderViewTitle(String title);
	
	void setHeaderViewImage(int resId,Position position);
}
