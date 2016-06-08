package com.ghllz.travel.presenter;

import android.content.Context;
import android.content.Intent;


public interface ICoverListPresenter extends  IPresenter{
	void showCoverList();
	void jumpTo(Context context,Intent intent);
}
