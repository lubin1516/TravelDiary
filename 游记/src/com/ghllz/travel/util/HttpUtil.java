package com.ghllz.travel.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.listener.OnCoversFinishListener;

public class HttpUtil {

	public static void getCoversResponse(final String city,final int pages,
			final OnCoversFinishListener listener) {
		new AsyncTask<Void, Void,List<Cover>>() {
			@Override
			protected List<Cover> doInBackground(Void... params) {
				List<Cover> list=new ArrayList<Cover>();
				BufferedReader reader = null;
				String result = null;
				StringBuffer sbf = null;
				for(int p=1;p<pages+1;p++){
					String httpUrl = "http://apis.baidu.com/qunartravel/travellist/travellist"
							+ "?" + "query="+city+"&page="+String.valueOf(p);
					try {
						URL url = new URL(httpUrl);
						HttpURLConnection connection = (HttpURLConnection) url
								.openConnection();
						connection.setRequestMethod("GET");
						// ÌîÈëapikeyµ½HTTP header
						connection.setRequestProperty("apikey","53ccdfc46e871781f303524465102cee");
						connection.connect();
						InputStream is = connection.getInputStream();
						reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
						String strRead = null;
						sbf=new StringBuffer();
						while ((strRead = reader.readLine()) != null) {
							sbf.append(strRead);
							sbf.append("\r\n");
						}
						reader.close();
						result = sbf.toString();
						JSONObject object=new JSONObject(result).getJSONObject("data");
						JSONArray array=object.getJSONArray("books");
						for(int i=0;i<array.length();i++){
							JSONObject js=array.getJSONObject(i);
							Cover Cover=new Cover();
							Cover.setTitle(js.getString("title"));
							Cover.setStartTime(js.getString("startTime"));
							Cover.setDays(js.getString("routeDays"));
							Cover.setLikeCount(js.getString("likeCount"));
							Cover.setBookUrl(js.getString("bookUrl"));
							Cover.setHeadImageUrl(js.getString("headImage"));
							Cover.setUserHeadImgUrl(js.getString("userHeadImg"));
							Cover.setUserName(js.getString("userName"));
							list.add(Cover);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//  Log.i("TAG","LIST="+list);
				return list;
			}
			@Override
			protected void onPostExecute(List<Cover> result) {
				listener.onGetCovers(result);;
			}
		}.execute();
	}









}
