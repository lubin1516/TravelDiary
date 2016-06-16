package com.ghllz.travel.ui;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.ghllz.travel.R;
import com.ghllz.travel.config.Configs;
import com.ghllz.travel.service.DiaryService;
import com.ghllz.travel.ui.fragment.DiaryFragment;
import com.ghllz.travel.ui.fragment.DiscoveryFragment;
import com.ghllz.travel.ui.fragment.MyFragment;
import com.ghllz.travel.ui.fragment.NearFragment;
import com.ghllz.travel.util.MImageLoader;

public class MainActivity extends BaseActivity {

	//脚部布局
	View footerView;
	RadioGroup rgFooter;

	DiaryFragment diaryFragment;
	NearFragment nearFragment;
	DiscoveryFragment discoveryFragment;
	MyFragment myFragment;
	private Fragment[] fragments;
	private int index;
	private int currentIndex;
	private Intent intent;

	ServiceConnection conn;
	DiaryService service;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initFraments();
		initFooterView();
		initServer();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = new Intent(Configs.UPDATE_DETAIL);
		sendBroadcast(intent);
	}
	@Override
	protected void onPause() {
		super.onPause();
		MImageLoader.MemCache.evictAll();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopService(intent);
	}

	private void initFraments(){
		diaryFragment = new DiaryFragment();
		nearFragment =  new NearFragment();
		discoveryFragment = new DiscoveryFragment();
		myFragment = new MyFragment();
		fragments = new Fragment[] {diaryFragment, nearFragment, discoveryFragment, myFragment};
		// 添加显示第一个fragment
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, diaryFragment).
		add(R.id.fragment_container,nearFragment).hide(nearFragment).show(diaryFragment).commit();
	}

	private void initFooterView() {
		footerView = findViewById(R.id.footerview);
		rgFooter = (RadioGroup) footerView.findViewById(R.id.rg_footer_radiogroup);
		rgFooter.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int rbIndex = group.indexOfChild(group.findViewById(checkedId));
				index = rbIndex;
				if (currentIndex != index) {
					FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
					trx.hide(fragments[currentIndex]);
					if (!fragments[index].isAdded()) {
						trx.add(R.id.fragment_container, fragments[index]);
					}
					trx.show(fragments[index]).commit();
				}
				currentIndex = index;
			}
		});
	}

	private void initServer() {
		intent = new Intent(this,DiaryService.class);
		startService(intent);
	}

	@Override  
	public void onSaveInstanceState(Bundle outState) {  
		//	    super.onSaveInstanceState(outState);  
	}
}
