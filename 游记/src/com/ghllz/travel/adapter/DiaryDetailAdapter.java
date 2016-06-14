package com.ghllz.travel.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ghllz.travel.bean.Detail;

public class DiaryDetailAdapter extends BaseListAdapter<Detail> {
	

	public DiaryDetailAdapter(Context context, List<Detail> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
//		View view =null;
//				ViewHolder vh=null;
//				if(convertView==null){//convertView (可重用的列表项对象)
//					view = mInflater.inflate(R.layout.i,null);
//					view.setTag(vh);
//				}else{
//					view=convertView;
//					vh=(ViewHolder)convertView.getTag();
//				}
//		
		return null;
	}

}
