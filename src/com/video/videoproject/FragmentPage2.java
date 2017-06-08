package com.video.videoproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPage2 extends BaseFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		return inflater.inflate(R.layout.fragment_2, null);		
	}

	@Override
	protected void lazyLoad(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}	
}