package com.ghllz.travel.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghllz.travel.R;
import com.ghllz.travel.util.PhotoUtil;

public class ItemGridViewAdapter extends BaseListAdapter<String>{

	int[] resource= new int[]{R.drawable.america,R.drawable.britain,R.drawable.france,
			R.drawable.iran,R.drawable.new_zealand,R.drawable.japan,R.drawable.thailand,
			R.drawable.taiwan,R.drawable.korea};

	public ItemGridViewAdapter(Context context, List<String> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_gridview, parent,false);
		}
		String string = getList().get(position);
		TextView country =ViewHolder.get(convertView, R.id.tv_header_gridview);
		ImageView imageView = ViewHolder.get(convertView, R.id.iv_header_gridview);
		country.setText(string);
		Bitmap bmp=BitmapFactory.decodeResource(mContext.getResources(), resource[position]);
		Bitmap bitmap = PhotoUtil.toRoundCorner(bmp,20);
		imageView.setImageBitmap(bitmap);
		return convertView;
	}

}
