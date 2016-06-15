package com.ghllz.travel.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghllz.travel.R;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.util.MImageLoader;
import com.ghllz.travel.view.CircleImageView;

public class DiaryCoverAdapter extends BaseListAdapter<Cover> {

	public DiaryCoverAdapter(Context context, List<Cover> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_diary_cover, parent,false);
		}
		Cover cover = getList().get(position);

		TextView days = ViewHolder.get(convertView, R.id.tv_diary_cover_days);
		TextView likeNume = ViewHolder.get(convertView, R.id.tv_diary_cover_likeNume);
		TextView title = ViewHolder.get(convertView, R.id.tv_diary_cover_title);
		TextView time = ViewHolder.get(convertView, R.id.tv_diary_cover_time);
		TextView userName = ViewHolder.get(convertView, R.id.tv_diary_cover_userName);
		TextView content = ViewHolder.get(convertView, R.id.tv_diary_cover_content);
		
		ImageView headImage = ViewHolder.get(convertView, R.id.iv_diary_cover_headImage);
		CircleImageView userHeaderImage = ViewHolder.get(convertView, R.id.cv_diary_cover_userHeadImg);
		View view = ViewHolder.get(convertView, R.id.v_diary_cover);
		
		Log.d("Tag",position+"");
		if(position==0){
			view.setVisibility(View.GONE);
		}else{
			view.setVisibility(View.VISIBLE);
		} 
		String avatar = cover.getUserHeadImgUrl();
		if (avatar != null && !avatar.equals("")) {
			MImageLoader.loadImage(avatar, userHeaderImage,30,30);
		} else {
			userHeaderImage.setImageResource(R.drawable.default_head);
		}

		String image = cover.getHeadImageUrl();
		if (image != null && !image.equals("")){
			MImageLoader.loadImage(image,headImage,180,240);
		}

		userName.setText(cover.getUserName());
		days.setText(cover.getDays()+"天");
		likeNume.setText(cover.getLikeCount());
		title.setText(cover.getTitle());
		time.setText(cover.getStartTime());
		//		content.setText(cover.getAboutTravel());

		//		View view =null;
		//		ViewHolder vh=null;
		//		if(convertView==null){//convertView (可重用的列表项对象)
		//			view = mInflater.inflate(R.layout.item_diary_cover,null);
		//			vh=new ViewHolder();
		//			vh.days = (TextView) view.findViewById(R.id.tv_diary_cover_days);
		//			vh.likeNume = (TextView) view.findViewById(R.id.tv_diary_cover_likeNume);
		//			vh.title = (TextView) view.findViewById(R.id.tv_diary_cover_title);
		//			vh.time = (TextView) view.findViewById(R.id.tv_diary_cover_time);
		//			vh.userName = (TextView) view.findViewById(R.id.tv_diary_cover_userName);
		//			vh.content = (TextView) view.findViewById(R.id.tv_diary_cover_content);
		//
		//			vh.headImage = (ImageView) view.findViewById(R.id.iv_diary_cover_headImage);
		//			vh.userHeaderImage = (CircleImageView) view.findViewById(R.id.cv_diary_cover_userHeadImg);
		//
		//			view.setTag(vh);
		//		}else{
		//			view=convertView;
		//			vh=(ViewHolder)convertView.getTag();
		//		}
		//
		//		Cover cover = getList().get(position);
		//
		//		String avatar = cover.getUserHeadImgUrl();
		//		if (avatar != null && !avatar.equals("")) {
		//			MImageLoader.loadImage(avatar,vh.userHeaderImage,30,30);
		//		} else {
		//			vh.userHeaderImage.setImageResource(R.drawable.default_head);
		//		}
		//
		//		String image = cover.getHeadImageUrl();
		//		if (image != null && !image.equals("")){
		//			MImageLoader.loadImage(image,vh.headImage,150,200);
		//		}
		//
		//		vh.userName.setText(cover.getUserName());
		//		vh.days.setText(cover.getDays()+"天");
		//		vh.likeNume.setText(cover.getLikeCount());
		//		vh.title.setText(cover.getTitle());
		//		vh.time.setText(cover.getStartTime());
		//		vh.content.setText(cover.getAboutTravel());

		return convertView;
	}

}
