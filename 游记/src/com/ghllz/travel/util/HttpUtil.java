package com.ghllz.travel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.util.Log;

import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.bean.Detail;
import com.ghllz.travel.bean.Place;
import com.ghllz.travel.listener.OnCoversFinishListener;
import com.ghllz.travel.listener.OnDetailFinishListener;
import com.ghllz.travel.listener.OnPlaceFinishListener;

public class HttpUtil {

	public static void getCoversResponse(final String city,final int page,
			final OnCoversFinishListener listener) {
		new AsyncTask<Void, Void,List<Cover>>() {
			@Override
			protected List<Cover> doInBackground(Void... params) {
				List<Cover> list=new ArrayList<Cover>();
				BufferedReader reader = null;
				String result = null;
				StringBuffer sbf = null;
				try {
				       String c=URLEncoder.encode(city, "utf-8");
				       String httpUrl = "http://apis.baidu.com/qunartravel/travellist/travellist"
						+ "?query=%22%22"+c+"&page="+String.valueOf(page);
						URL url = new URL(httpUrl);
						HttpURLConnection connection = (HttpURLConnection) url
								.openConnection();
						connection.setRequestMethod("GET");
						// ����apikey��HTTP header
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
							Cover.setPage(page);
//							Document doc=Jsoup.connect(Cover.getBookUrl())
//									.userAgent("Mozilla").timeout(3000).post();
//							Elements elements=doc.select(".main_leftbox").first().select(".text");
//							Cover.setAboutTravel("\t\t"+elements.text());
							list.add(Cover);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				//  Log.i("TAG","LIST="+list);
				return list;
			}
			@Override
			protected void onPostExecute(List<Cover> result) {
				Log.d("Tag", result.toString());
				listener.onGetCovers(result);
			}
		}.execute();
	}
public static void getDetails(final String url,final OnDetailFinishListener listener){
		new AsyncTask<Void,Void,List<Detail>>() {
			List<Detail> details=new ArrayList<Detail>();
			@Override
			protected List<Detail> doInBackground(Void... params) {
				try {
					Document doc=Jsoup.connect(url).timeout(2000).post();
					Elements elements=doc.getElementsByClass("date-content");
					for(int i=1;i<elements.size()-2;i++){
						Detail detail=new Detail();
						//////////////////////////////////////ʱ��/////////////////////////
						String dayAndTima=elements.get(i).select(".date").text();
						String day=dayAndTima.substring
								(0,dayAndTima.length()-11);
						String time=dayAndTima.substring
								(dayAndTima.length()-11,dayAndTima.length());
						detail.setDay(("��"+day.substring(1,day.length())+"��"));
						detail.setDate("����:"+time);
						///////////////////////// ����//////////////////////////////////////////
						Elements elements2=elements.select(".planboxday").get(i-1).select(".planbox");
						StringBuilder sb=new StringBuilder();
						for(int j=0;j<elements2.size();j++){
							String content=elements2.get(j).text();
							String[] ct=content.split("���ظ���ͼƬ");
							sb.append(ct[0]);
							sb.append("[");
							Elements elements3=elements2.get(j).select(".img_link");
							if(elements3.size()>0){
								for(int k=0;k<elements3.size();k++){
									String imageUrl=elements3.get(k).select("img").attr("data-src");
									sb.append(imageUrl);
									sb.append("[");
								}
							}
						}
						detail.setContent(sb.toString());
						details.add(detail);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return details;
			}
			@Override
			protected void onPostExecute(List<Detail> result) {
				listener.onGetDetailContents(result);
			}
		}.execute();
}
public static void getPlace(final OnPlaceFinishListener listener){
    new AsyncTask<Void,Void,List<Place>>() {
    	List<Place> list=new ArrayList<Place>();	
    String url="http://travel.qunar.com/place/";
		@Override
		protected List<Place> doInBackground(Void... params) {
			try {
				Document doc=Jsoup.connect(url).
		data("query","Java").userAgent("Mozilla").cookie("auth","token").timeout(3000).get();
			Elements elements=doc.select(".sub_list");
				for(int i=0;i<elements.size();i++){
				  for(int j=0;j<elements.get(i).select(".link").size();j++){
					  Place place=new Place();
					  place.setCity(elements.get(i).select(".link").get(j).text());
					  place.setSortLetter(getFirstChar(place.getCity()));
					  place.setCity_url(elements.get(i).select(".link").get(j).attr("href"));
					  list.add(place);
				  }
				}
				Elements elements2=doc.select(".current").get(9).select(".listbox");
				for(int i=0;i<elements2.size();i++){
					for(int j=0;j<elements2.get(i).select(".link").size();j++){
						Place place=new Place();
						place.setCity(elements2.get(i).select(".link").get(j).text());
						place.setSortLetter(getFirstChar(place.getCity()));
						place.setCity_url(elements2.get(i).select(".link").get(j).attr("href"));
						list.add(place);
					}
				}
		//Log.i("doc",""+list.size());
		Log.i("doc",""+list);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
		}
		@Override
		protected void onPostExecute(List<Place> result) {
			listener.getPlace(result);;
		}
	}.execute();
}
public static String getFirstChar(String value) {  
// ���ַ�  
char firstChar = value.charAt(0);  
// ����ĸ����  
String first = null;  
// �Ƿ��ǷǺ���  
String[] print = PinyinHelper.toHanyuPinyinStringArray(firstChar);  
if (print == null) {  
    // ��Сд��ĸ�ĳɴ�д  
    if ((firstChar >= 97 && firstChar <= 122)) {  
        firstChar -= 32;  
    }  
    if (firstChar >= 65 && firstChar <= 90) {  
        first = String.valueOf((char) firstChar);  
    } else {  
        // ��Ϊ���ַ�Ϊ���ֻ��������ַ�  
        first = "#";  
    }  
} else {  
    // ��������� �����д��ĸ  
    first = String.valueOf((char) (print[0].charAt(0) - 32));  
}  
if (first == null) {  
    first = "?";  
}  
return first;  
} 







}
