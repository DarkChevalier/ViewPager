package com.video.videoproject;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FoundFragment extends BaseFragment {
	private View view;
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		view = inflater.inflate(R.layout., container, false);

		return view;
	}

	@Override
	protected void lazyLoad(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}
}
