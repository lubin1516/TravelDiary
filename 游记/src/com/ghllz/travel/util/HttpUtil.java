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

import com.ghllz.travel.bean.Covers;
import com.ghllz.travel.listener.OnCoversFinishListener;

public class HttpUtil {
	
public static void getCoversRequest(final String city,final int pages,
										final OnCoversFinishListener listener) {
	new AsyncTask<Void, Void,List<Covers>>() {
		@Override
		protected List<Covers> doInBackground(Void... params) {
			List<Covers> list=new ArrayList<Covers>();
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
			        	Covers Covers=new Covers();
			        	Covers.setTitle(js.getString("title"));
			        	Covers.setStartTime(js.getString("startTime"));
			        	Covers.setDays(js.getString("routeDays"));
			        	Covers.setLikeCount(js.getString("likeCount"));
			        	Covers.setBookUrl(js.getString("bookUrl"));
			        	Covers.setHeadImageUrl(js.getString("headImage"));
			        	Covers.setUserHeadImgUrl(js.getString("userHeadImg"));
			        	Covers.setUserName(js.getString("userName"));
			        	list.add(Covers);
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			   }
			 //  Log.i("TAG","LIST="+list);
			return list;
		}
		@Override
		protected void onPostExecute(List<Covers> result) {
			listener.onGetCovers(result);;
		}
	}.execute();
}









}
