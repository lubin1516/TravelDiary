package com.ghllz.travel.ui.fragment;

import com.ghllz.travel.R;
import com.ghllz.travel.view.MultiColumnListView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NearFragment extends FragmentBase {
private View headerView;
private MultiColumnListView mListview;
private MulitAdapter mutilAdapter;
private String[] urls={"http://pic71.nipic.com/file/20150709/10603465_153718936000_2.jpg",
		"http://imgstore.cdn.sogou.com/app/a/100540002/714860.jpg",
		"http://pic4.nipic.com/20091215/2396136_140959028451_2.jpg",
		"http://img5.imgtn.bdimg.com/it/u=3603943369,1952417318&fm=21&gp=0.jpg",
		"http://pic1.nipic.com/2008-10-30/200810309416546_2.jpg",
		"http://pic24.nipic.com/20121003/10754047_140022530392_2.jpg",
		"http://tupian.enterdesk.com/2014/lxy/2014/05/07/2/8.jpg",
		"http://img01.taopic.com/141128/240418-14112Q04Q824.jpg",
		"http://image.tianjimedia.com/uploadImages/2011/360/89241H21087M.jpg",
		"http://pic51.nipic.com/file/20141030/2531170_080422201000_2.jpg",
		"http://pic1.nipic.com/2008-10-20/20081020104257376_2.jpg",
		"http://d.hiphotos.baidu.com/zhidao/pic/item/3b87e950352ac65c1b6a0042f9f2b21193138a97.jpg",
		"http://img4.duitang.com/uploads/item/201210/04/20121004233016_3vZPJ.thumb.600_0.jpeg",
		"http://img.taopic.com/uploads/allimg/130215/240511-13021510291714.jpg",
		"http://pic44.nipic.com/20140725/19158069_112814747000_2.jpg",
		"http://s.tang-mao.com/Uploads/Editor/2015-05-29/1432892469570136.jpg",
		"http://img0.imgtn.bdimg.com/it/u=2013819174,1049816796&fm=21&gp=0.jpg",
		"http://img4.imgtn.bdimg.com/it/u=3107613829,3873828763&fm=21&gp=0.jpg",
		"http://pic40.nipic.com/20140410/8952533_151855348000_2.jpg"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_near, container, false);
		Log.d("Tag","fragment_near");
		headerView=view.findViewById(R.id.title_near);
		initHeader();
		initView(view);
		return view;
	}
	private void initHeader() {
		setHeaderViewTitle(headerView,"¸½½ü");
	}
	private void initView(View view) {
		mListview=(MultiColumnListView) view.findViewById(R.id.multiListView);
		mutilAdapter=new MulitAdapter(getActivity(), urls);
		mListview.setAdapter(mutilAdapter);
	}

}
