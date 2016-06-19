package com.ghllz.travel.adapter;

import java.util.ArrayList;
import java.util.List;

import com.ghllz.travel.R;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.util.CommonUtils;
import com.ghllz.travel.view.CircleImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class DiscoveryCoverAdapter extends BaseAdapter{
	Context mContext;
	List<Cover> mCovers ;
	LayoutInflater mInflater;
	private List<String> strings ;

	final int TYPE_GridView = 0;
	final int TYPE_Cover = 1;

	public DiscoveryCoverAdapter(Context context,List<Cover> covers ) {
		mContext = context;
		mCovers = covers;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		strings = new ArrayList<String>();
		strings.add("美国");
		strings.add("英国");
		strings.add("法国");
		strings.add("伊朗");
		strings.add("新西兰");
		strings.add("日本");
		strings.add("泰国");
		strings.add("台湾");
		strings.add("韩国");
	}

	@Override
	public int getItemViewType(int position) {
		if(position == 0){
			return TYPE_GridView;
		}else{
			return TYPE_Cover;
		}
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getCount() {
		return mCovers.size()+1;
	}


	@Override
	public Cover getItem(int position) {
		if(position!=0){
			return mCovers.get(position-1);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(position!=0){
			Cover cover = mCovers.get(position-1);
		}
		int type = getItemViewType(position); 
		viewHolderGridView holderGridView = null;
		viewHolderCover holderCover = null;

		if (convertView == null) {
			switch (type) {  
			case TYPE_GridView:  
				holderGridView = new viewHolderGridView();
				convertView = mInflater.inflate(R.layout.item_discovery_gridview,null);
				holderGridView.gridView = (GridView) convertView.findViewById(R.id.gv_disovery_item);
				convertView.setTag(holderGridView);
				break;  
			case TYPE_Cover:  
				holderCover = new viewHolderCover();

				break; 
			}  
		}else{
			switch (type) {  
			case TYPE_GridView:  
				holderGridView = (viewHolderGridView) convertView.getTag();
				break;  
			case TYPE_Cover:  

				break; 
			}
		}
		
		switch (type) {  
		case TYPE_GridView:  
			holderGridView.gridView.setAdapter(new ItemGridViewAdapter(mContext,strings));
			holderGridView.gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					CommonUtils.onImageTouch(mContext,view,0,strings.get(position));
					CommonUtils.onImageTouch(mContext,view,1,strings.get(position));
				}
			});
			break;  
		case TYPE_Cover:  

			break; 
		}
		
		return convertView;
	}

	class viewHolderGridView{
		GridView gridView;
	}

	class viewHolderCover{
		TextView days;
		TextView likeNume;
		TextView title;
		TextView time;
		TextView userName;
		ImageView headImage;
		CircleImageView userHeaderImage;
	}
}	