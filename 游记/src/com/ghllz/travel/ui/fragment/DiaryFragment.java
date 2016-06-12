package com.ghllz.travel.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.ghllz.travel.R;
import com.ghllz.travel.adapter.DiaryCoverAdapter;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.presenter.CoverListPresenterImpl;
import com.ghllz.travel.ui.ICoverListView;
import com.ghllz.travel.view.xlist.XListView;
import com.ghllz.travel.view.xlist.XListView.IXListViewListener;

public class DiaryFragment extends FragmentBase implements ICoverListView, IXListViewListener{


	CoverListPresenterImpl presenter;
	//����ˢ��listview
	XListView mListView;
	DiaryCoverAdapter mAdapter;
	private List<Cover> mCoverList;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_diary, container, false);
		initView(view);
		initXListView();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.showCoverList();
	}

	private void initView(View view) {
		presenter = new CoverListPresenterImpl(this);
		mListView = (XListView)view.findViewById(R.id.mListView);
		mCoverList = new ArrayList<Cover>();
	}


	private void initXListView() {
		// ���Ȳ�������ظ���
		mListView.setPullLoadEnable(false);
		// ��������
		mListView.setPullRefreshEnable(true);
		// ���ü�����
		mListView.setXListViewListener(this);
		mListView.pullRefreshing();
		mListView.setDividerHeight(0);
		mAdapter = new DiaryCoverAdapter(getActivity(),mCoverList);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}

		});
	}


	@Override
	public void showCoverList(List<Cover> covers) {
		mAdapter.addAll(covers);
	}

	@Override
	public void onRefresh() {

	}

	@Override
	public void onLoadMore() {

	}
}
