package com.ghllz.travel.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghllz.travel.R;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.util.ImageLoadOptions;
import com.ghllz.travel.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DiaryCoverAdapter extends BaseListAdapter<Cover> {

	public DiaryCoverAdapter(Context context, List<Cover> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_diary_cover, null);
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

		String avatar = cover.getUserHeadImgUrl();
		if (avatar != null && !avatar.equals("")) {
			ImageLoader.getInstance().displayImage(avatar, userHeaderImage, ImageLoadOptions.getOptions());
		} else {
			userHeaderImage.setImageResource(R.drawable.default_head);
		}

		String image = cover.getHeadImageUrl();
		if (image != null && !image.equals("")){
			ImageLoader.getInstance().displayImage(image, headImage, ImageLoadOptions.getOptions());
		}

		userName.setText(cover.getUserName());
		days.setText(cover.getDays()+"Ìì");
		likeNume.setText(cover.getLikeCount());
		title.setText(cover.getTitle());
		time.setText(cover.getStartTime());
		content.setText(cover.getAboutTravel());
		return convertView;
	}

}
