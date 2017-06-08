package com.video.videoproject.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class FragmentStatePagerAdapter extends FragmentPagerAdapter implements OnCheckedChangeListener, OnPageChangeListener {
	private RadioGroup rags;
	private ViewPager viewPager;
	private List<Fragment>fragment;

	public FragmentStatePagerAdapter(FragmentManager fm,RadioGroup rags,ViewPager viewPager,List<Fragment>fragment) {
		super(fm);
		this.rags=rags;
		this.viewPager=viewPager;
		this.fragment=fragment;
		rags.setOnCheckedChangeListener(this);
		viewPager.setOnPageChangeListener(this);
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragment.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragment.size();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

}
