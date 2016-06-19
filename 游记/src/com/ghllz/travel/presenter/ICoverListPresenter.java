package com.ghllz.travel.presenter;

import android.content.Context;
import android.content.Intent;


public interface ICoverListPresenter extends  IPresenter{
	void showCoverList(String theme);
	void jumpTo(Context context,Intent intent);
}
