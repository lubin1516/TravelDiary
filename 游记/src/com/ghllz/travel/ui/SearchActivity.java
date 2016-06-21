package com.ghllz.travel.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.ghllz.travel.R;
import com.ghllz.travel.adapter.SearchAdapter;
import com.ghllz.travel.bean.Place;
import com.ghllz.travel.config.Configs;
import com.ghllz.travel.presenter.IPlaceListPresenter;
import com.ghllz.travel.presenter.PlaceListPresenterImpl;

public class SearchActivity extends BaseActivity implements IPlaceListView, TextWatcher{
	EditText et_search;
	ListView listview;
	SearchAdapter sa;
	List<Place> places,list;
	IPlaceListPresenter mPresenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		init();
	}
	
	private void init() {
		mPresenter = new PlaceListPresenterImpl(this);
		et_search=(EditText) findViewById(R.id.search_et);
		listview=(ListView) findViewById(R.id.search_listView);
		places=new ArrayList<Place>();
		list=new ArrayList<Place>();
		sa=new SearchAdapter(this,places);
		listview.setAdapter(sa);
		et_search.addTextChangedListener(this);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(SearchActivity.this,ThemeActivity.class);
				intent.putExtra(Configs.THEME,list.get(position).getCity());
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		mPresenter.ShowPlaceList();
	}
	@Override
	public void showPlaceList(List<Place> places) {
		this.places=places;
		Log.i("TAGTAGTAGTAG",""+this.places);
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}
	@Override
	public void afterTextChanged(Editable s) {
		if(s.toString().length()>0){
			list.clear();
			for(Place p:places){
				/*int i;
				for(i=0;i<=p.getCity().length();i++){
					if(s.toString().equals(p.getCity().subSequence(0,i))||
							s.toString().equals(p.getLowerCase().substring(0,i))||
							s.toString().equals(p.getUpperCase().substring(0,i)))
					{
						list.add(p);
					}
				}*/
				if(p.getCity().contains(s.toString())||
						p.getLowerCase().contains(s.toString())||
						p.getUpperCase().contains(s.toString())
						){
					list.add(p);
				}
			}
		}else{
		list.clear();
		}
		sa.refresh(list,s.toString());
	}

}
