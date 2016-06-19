package com.ghllz.travel.util;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.query.Select;
import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.bean.DayInfo;
import com.ghllz.travel.bean.PlanBox;

public class DataUtil {

	public static Boolean haveDayInfo(String url){
		int size = new Select().from(DayInfo.class).where("url=?",url).execute().size();
		if(size==0){
			return false;
		}
		return true;
	}

	public static Boolean havePlanBox(String url){
		int size = new Select().from(PlanBox.class).where("url=?",url).execute().size();
		if(size==0){
			return false;
		}
		return true;
	}

	public static List<DayInfo> getDayInfo(String url){
		List<DayInfo> days = new Select().from(DayInfo.class).where("url=?",url).execute();
		return days;
	}

	public static List<PlanBox> getPlanBox(String url){
		List<PlanBox> fragment = new Select().from(PlanBox.class).where("url=?",url).execute();
		return fragment;
	}

	public static Boolean haveCoverData(int page, String theme){
		int size = new Select().from(Cover.class).where("page=?",page).where("theme=?",theme).execute().size();
		if(size==0){
			return false;
		}
		return true;
	}

	public static List<Cover> getCoverData(int page, String theme){
		List<Cover> covers = new Select().from(Cover.class).where("page=?",page).where("theme=?",theme).execute();
		return covers;

	}

	public static List<PlanBox> getListByTag(String indexOfDay,List<PlanBox> fragments){
		List<PlanBox> mFragments = new ArrayList<PlanBox>();
		for(PlanBox fragment:fragments){
			if("Ç°ÑÔ".equals(indexOfDay)){
				mFragments.add(fragment);
			}
			if(("µÚ"+fragment.getDays()+"Ìì").equals(indexOfDay)){
				mFragments.add(fragment);
			}
		}
		return mFragments;
	}

	public static String[] getListByString(String content){
		String[] strings = content.split("\\[");
		return strings;
	}

}
