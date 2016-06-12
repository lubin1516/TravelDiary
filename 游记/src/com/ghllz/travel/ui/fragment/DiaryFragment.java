package com.ghllz.travel.ui.fragment;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghllz.travel.R;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.presenter.CoverListPresenterImpl;
import com.ghllz.travel.ui.ICoverListView;
import com.ghllz.travel.view.xlist.XListView;

public class DiaryFragment extends FragmentBase implements ICoverListView{


	CoverListPresenterImpl presenter;
	//ÏÂÀ­Ë¢ÐÂlistview
	XListView mListView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_diary, container, false);
		initView(view);
		Log.d("Tag","fragment_diary");
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
	}
	
	private void initView(View view) {
		presenter = new CoverListPresenterImpl(this);
		mListView = (XListView)view.findViewById(R.id.mListView);
	}
	
	@Override
	public void showCoverList(List<Cover> covers) {

	}
}
