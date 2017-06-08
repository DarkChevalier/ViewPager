package com.video.videoproject;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HomeMainFragment  extends BaseFragment implements OnClickListener{
	private View view;
	private ViewPager mPaper;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();

	private TextView tv_footable,tv_midfield,tv_forward,tv_guard;
	/** 标志位，标志已经初始化完成 */
	private boolean isPrepared;
	/** 是否已被加载过一次，第二次就不再去请求数据了 */
	private boolean mHasLoadedOnce;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if (view == null) {
			view = inflater.inflate(R.layout.activity_home_main, container,false);
			initLayout();
			isPrepared = true;
			setPage();
		}
		// 因为共用一个Fragment视图，所以当前这个视图已被加载到Activity中，必须先清除后再加入Activity
		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeView(view);
		}
		return view;
	}

	/**
	 * 初始化控件
	 */
	public void initLayout(){
		tv_footable = (TextView)view.findViewById(R.id.tv_youngfootable);
		tv_midfield = (TextView)view.findViewById(R.id.tv_midfield);
		tv_forward = (TextView)view.findViewById(R.id.tv_forward);
		tv_guard = (TextView)view.findViewById(R.id.tv_guard);
		
		mPaper = (ViewPager)view.findViewById(R.id.view_pager);
		
		tv_footable.setOnClickListener(this);
		tv_midfield.setOnClickListener(this);
		tv_forward.setOnClickListener(this);
		tv_guard.setOnClickListener(this);
		
		FootableFragment f1 = new FootableFragment();
    	FragmentPage2 f2 = new FragmentPage2();
    	FragmentPage3 f3 = new FragmentPage3();
    	FragmentPage4 f4 = new FragmentPage4();
    	
    	mFragments.add(f1);
    	mFragments.add(f2);
    	mFragments.add(f3);
    	mFragments.add(f4);
	}

	private void setPage() {
		mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {           

			@Override
			public int getCount() {
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mFragments.get(arg0);
			}
		};
		mPaper.setAdapter(mAdapter);
		mPaper.setOnPageChangeListener(new OnPageChangeListener() {

//			private int currentIndex;

			@Override
			public void onPageSelected(int position) {
				resetColor();
				switch (position) {
				case 0:
					tv_footable.setTextColor(Color.rgb(87,153,8));
					break;
				case 1:
					tv_midfield.setTextColor(Color.rgb(87,153,8));
					break;
				case 2:
					tv_forward.setTextColor(Color.rgb(87,153,8));
					
					break;
				case 3:
					tv_guard.setTextColor(Color.rgb(87,153,8));
					break;
				default:
					tv_footable.setTextColor(Color.rgb(87,153,8));
					break;
				}
//				currentIndex = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}
	public void resetColor(){
		tv_footable.setTextColor(Color.rgb(56,56,56));
		tv_midfield.setTextColor(Color.rgb(56,56,56));
		tv_forward.setTextColor(Color.rgb(56,56,56));
		tv_guard.setTextColor(Color.rgb(56,56,56));
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_youngfootable:
			resetColor();
			tv_footable.setTextColor(Color.rgb(87,153,8));
			mPaper.setCurrentItem(0);
			break;
		case R.id.tv_midfield:
			resetColor();
			tv_midfield.setTextColor(Color.rgb(87,153,8));
			mPaper.setCurrentItem(1);
			break;
		case R.id.tv_forward:
			resetColor();
			tv_forward.setTextColor(Color.rgb(87,153,8));
			mPaper.setCurrentItem(2);
			break;
		case R.id.tv_guard:
			resetColor();
			tv_guard.setTextColor(Color.rgb(87,153,8));
			mPaper.setCurrentItem(3);
			break;

		default:
			break;
		}
	}
	/**
     * ViewPager适配器
    */ 
    public class MyPagerAdapter extends PagerAdapter { 
        public List<Activity> mListViews; 
 
        public MyPagerAdapter(List<Activity> mListViews) { 
            this.mListViews = mListViews; 
        } 
 
       
        @Override 
        public void finishUpdate(View arg0) { 
        } 
 
        @Override 
        public int getCount() { 
            return mListViews.size(); 
        } 
 
      
 
        @Override 
        public boolean isViewFromObject(View arg0, Object arg1) { 
            return arg0 == (arg1); 
        } 
 
        @Override 
        public void restoreState(Parcelable arg0, ClassLoader arg1) { 
        } 
 
        @Override 
        public Parcelable saveState() { 
            return null; 
        } 
 
        @Override 
        public void startUpdate(View arg0) { 
        } 
    }
    
    
	@Override
	protected void lazyLoad(boolean isVisible) {
		// TODO Auto-generated method stub
		
	} 
}
