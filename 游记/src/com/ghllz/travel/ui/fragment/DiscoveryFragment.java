package com.ghllz.travel.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghllz.travel.R;

public class DiscoveryFragment extends FragmentBase {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_discovery, container, false);
		Log.d("Tag","fragment_discovery");
		return view;
	}

}
