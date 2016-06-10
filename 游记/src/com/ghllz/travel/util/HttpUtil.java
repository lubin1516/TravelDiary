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
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

import android.os.AsyncTask;
import android.util.Log;

import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.listener.OnCoversFinishListener;
import com.ghllz.travel.listener.OnDetailContentsFinishListener;

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
/*
 * ����ҳ��Url,����String���͵ļ��ϡ�
 * ���������ǰ��մ�������(����+ͼƬurl+����+ͼƬurl+���֡�������)��˳��	
 */
public static void getDetails(final String url,final OnDetailContentsFinishListener listener){
		new AsyncTask<Void,Void,List<String>>() {
			List<String> contents=new ArrayList<String>();
			@Override
			protected List<String> doInBackground(Void... params) {
				try {
					Document doc=Jsoup.connect(url).timeout(3000).post();
					Elements elements=doc.getElementsByClass("date-content");
					for(int i=1;i<elements.size()-2;i++){
				//////////////////////////////////////ʱ��(String)/////////////////////////
						String dayAndTima=elements.get(i).select(".date").text();
						String day=dayAndTima.substring
								(0,dayAndTima.length()-11);
						String time=dayAndTima.substring
								(dayAndTima.length()-11,dayAndTima.length());
						contents.add("��"+day.substring(1,day.length())+"��");//�õ��ڼ���
						contents.add("����:"+time);//�õ���������
			   ///////////////////////// ����(String)//////////////////////////////////////
		Elements elements2=elements.select(".planboxday").get(i-1).select(".planbox");
		           for(int j=0;j<elements2.size();j++){
		        	   String content=elements2.get(j).text();
		        	   String[] ct=content.split("���ظ���ͼƬ");
		        	   contents.add("\t\t"+ct[0]);	//�õ��μ�����
               /////////////////////////ͼƬUrl(String)////////////////////////////////////// 	   
		     Elements elements3=elements2.get(j).select(".img_link");
		     			if(elements3.size()>0){
		     			for(int k=0;k<elements3.size();k++){
		     				String imageUrl=elements3.get(k).select("img").attr("data-src");
		     				Log.i("SRC","SRC="+imageUrl);//�õ��μǲ�ͼ
						contents.add(imageUrl);
		     			}
		     		}
		          }
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return contents;
			}
			@Override
			protected void onPostExecute(List<String> result) {
				listener.onGetDetailContents(result);
			}
		}.execute();
	}








}
