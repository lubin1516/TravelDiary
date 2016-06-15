package com.ghllz.travel.util;

import java.util.ArrayList;
import java.util.List;

import com.ghllz.travel.bean.PlanBox;

import android.util.Log;

public class DataUtil {
	public static List<PlanBox> getListByTag(String indexOfDay,List<PlanBox> fragments){
		List<PlanBox> mFragments = new ArrayList<PlanBox>();
		for(PlanBox fragment:fragments){
			if(("µÚ"+fragment.getDays()+"Ìì").equals(indexOfDay)){
				mFragments.add(fragment);
			}
		}
		return mFragments;
	}

	public static String[] getListByString(String content){
		String[] strings = content.split("\\[");
//		List<List<String>> result = new ArrayList<List<String>>();
//		List<String> http = new ArrayList<String>();
//		List<String> text = new ArrayList<String>();
//		for(int i = 0;i<strings.length;i++){
//			if(strings[i].contains("http")){
//				if(text.size()!=0){
//					result.add(text);
//					text.clear();
//				}
//				http.add(strings[i]);
//				if(i==(strings.length-1)){
//					result.add(http);
//					http.clear();
//				}
//			}else{
//				if(http.size()!=0){
//					result.add(http);
//					http.clear();
//				}
//				text.add(strings[i]);
//			}
//		}
		return strings;
	}
}
