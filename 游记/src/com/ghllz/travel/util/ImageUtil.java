package com.ghllz.travel.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.TypedValue;

public class ImageUtil {
	
	public static Bitmap getcircleAvatar(Context context,Bitmap avatar) {

		Bitmap bitmap = Bitmap.createBitmap(avatar.getWidth(),avatar.getHeight(),Config.ARGB_8888);
		//»­²¼ canvas
		Canvas canvas = new Canvas(bitmap);
		//»­±ÊPaint 
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
		float radius = Math.min(avatar.getWidth(), avatar.getHeight())/2;
		canvas.drawCircle(avatar.getWidth()/2,avatar.getHeight()/2 , radius, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(avatar, 0,0, paint);

		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
		paint.setStrokeWidth(strokeWidth);
		canvas.drawCircle(avatar.getWidth()/2, avatar.getHeight()/2 , radius-strokeWidth/2, paint);
		return bitmap;
	}
}
