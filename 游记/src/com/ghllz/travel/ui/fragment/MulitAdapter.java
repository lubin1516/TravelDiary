package com.ghllz.travel.ui.fragment;

import com.ghllz.travel.util.MImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MulitAdapter extends BaseAdapter {
private Context context;
private String[] urls;
	public MulitAdapter(Context context, String[] urls) {
	this.context = context;
	this.urls = urls;
}
	@Override
	public int getCount() {
		return urls.length;
	}
	@Override
	public String getItem(int position) {
		return urls[position];
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh=null;
		if(convertView==null){
			vh=new ViewHolder();
 convertView=LayoutInflater.from(context).inflate(com.ghllz.travel.R.layout.multi_lv_item, parent,false);
	vh.imageView=(ImageView) convertView.findViewById(com.ghllz.travel.R.id.multi_lv_item_lv);
		convertView.setTag(vh);
		}else{
		vh=(ViewHolder) convertView.getTag();
		}
		MImageLoader.loadImage(urls[position],vh.imageView,300,300);
		return convertView;
	}
private static class ViewHolder {
	ImageView imageView;
    }
}
