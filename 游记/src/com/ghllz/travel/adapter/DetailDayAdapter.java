package com.ghllz.travel.adapter;

import java.util.ArrayList;
import java.util.List;

import com.ghllz.travel.R;
import com.ghllz.travel.bean.DayInfo;
import com.ghllz.travel.bean.PlanBox;
import com.ghllz.travel.util.DataUtil;
import com.ghllz.travel.util.MImageLoader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DetailDayAdapter extends BaseListAdapter<DayInfo>{

	List<PlanBox> fragments;
	List<DetailFragmentAdapter> mAdapters;
	List<List<PlanBox>> mFragments;
	public DetailDayAdapter(Context context, List<DayInfo> list,List<PlanBox> fragments) {
		super(context, list);
		this.fragments= fragments;
		this.mFragments = new ArrayList<List<PlanBox>>();
		this.mAdapters = new ArrayList<DetailDayAdapter.DetailFragmentAdapter>();
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_detail_diary_day, parent,false);
		}

		DayInfo dayInfo = getList().get(position);
		TextView day = ViewHolder.get(convertView, R.id.tv_detail_day_index);

		ListView lv = ViewHolder.get(convertView, R.id.lv_detail_day_Fcontainer);
		lv.setAdapter(null);
		List<PlanBox> fragments = DataUtil.getListByTag(dayInfo.getIndexOfDay(), this.fragments);
		DetailFragmentAdapter adapter = new DetailFragmentAdapter(mContext,fragments);
		//		mAdapters.add(adapter);
		lv.setAdapter(adapter);

		if(dayInfo.getIndexOfDay().equals("г╟ят")){
			day.setText("г╟ят");
		}else{
			day.setText(dayInfo.getIndexOfDay());
		}
		return convertView;
	}

	public void addAll(List<DayInfo> days,List<PlanBox> fragments) {
		super.addAll(days);
		this.fragments = fragments;
		notifyDataSetChanged();
	}

	class DetailFragmentAdapter extends BaseListAdapter<PlanBox>{

		public DetailFragmentAdapter(Context context, List<PlanBox> list) {
			super(context, list);
		}

		@Override
		public View bindView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_detail_diary_fragment, parent,false);
			}

			LinearLayout fragment = (LinearLayout) convertView.findViewById(R.id.ll_detail_fragment);
			fragment.removeAllViews();
			PlanBox planBox = getList().get(position);
			String[] strings = DataUtil.getListByString(planBox.getContent());			
			for(int i = 0;i<strings.length;i++){
				View inflate = mInflater.inflate(R.layout.item_detail_fragment_image,parent,false);
				if(strings[i].contains("http")){
					if (strings[i] != null && !strings[i].equals("")){
						ImageView imageView = (ImageView) inflate.findViewById(R.id.detail_fragment_image);
						imageView.setVisibility(View.VISIBLE);
						MImageLoader.loadImage(strings[i],imageView,180,240);
					}
					fragment.addView(inflate);
				}else{
					if (strings[i] != null && !strings[i].equals("")){
						TextView textView = (TextView) inflate.findViewById(R.id.detail_fragment_text);
						textView.setVisibility(View.VISIBLE);
						textView.setText("    "+strings[i]);
						fragment.addView(inflate);
					}
				}
			}
			return convertView;
		}

	}
}