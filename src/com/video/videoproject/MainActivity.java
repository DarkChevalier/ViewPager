package com.video.videoproject;

import java.util.ArrayList;
import java.util.List;

import com.video.videoproject.R;

import android.os.Bundle;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private ViewPager viewPager;// 翻转页viewpager
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();
	private LinearLayout[] bottonMenu;
	
	private ImageView mMyBottemMainImg, mMyBottemFoundImg, mMyBottemMyImg,
		mMyBottemRankingImg;
	private TextView mMyBottemMainTxt, mMyBottemFoundTxt, mMyBottemMyTxt,
		mMyBottemRankingTxt;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initView();
        initLayout();
        bottonMenu = getMenuButtonObject();
        
        setBottonEvent();
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mFragments.get(arg0);
			}
		};
		viewPager.setAdapter(mAdapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			private int currentIndex;

			@Override
			public void onPageSelected(int position) {
				resetTabBtn();
				switch (position) {
				case 0:
					mMyBottemMainImg
							.setImageResource(R.drawable.pic_check_main);
					mMyBottemMainTxt.setTextColor(Color.parseColor("#579908"));
					break;
				case 1:
					mMyBottemFoundImg
							.setImageResource(R.drawable.pic_check_found);
					mMyBottemFoundTxt.setTextColor(Color.parseColor("#579908"));
					break;
				case 2:
					mMyBottemRankingImg
							.setImageResource(R.drawable.pic_check_ranking);
					mMyBottemRankingTxt.setTextColor(Color.parseColor("#579908"));
					break;
				case 3:
					mMyBottemMyImg
							.setImageResource(R.drawable.pic_check_my);
					mMyBottemMyTxt.setTextColor(Color.parseColor("#579908"));
					break;
				default:
					mMyBottemMainImg
							.setImageResource(R.drawable.pic_check_main);
					mMyBottemMainTxt.setTextColor(Color.parseColor("#579908"));	
					break;
				}
				currentIndex = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
    }
    /**
     * 设置底部菜单点击事件
     */
    private void setBottonEvent() {
    	// 首页
		bottonMenu[0].setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				resetTabBtn();
				mMyBottemMainImg.setImageResource(R.drawable.pic_check_main);
				mMyBottemMainTxt.setTextColor(Color.parseColor("#579908"));
				viewPager.setCurrentItem(0);
			}
		});
		//发现
		bottonMenu[1].setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				resetTabBtn();
				mMyBottemFoundImg.setImageResource(R.drawable.pic_check_found);
				mMyBottemFoundTxt.setTextColor(Color.parseColor("#579908"));
				viewPager.setCurrentItem(1);
			}
		});
		// 排行
		bottonMenu[2].setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				resetTabBtn();
				mMyBottemRankingImg.setImageResource(R.drawable.pic_check_ranking);
				mMyBottemRankingTxt.setTextColor(Color.parseColor("#579908"));
				viewPager.setCurrentItem(2);
			}
		});
		// 我的
		bottonMenu[3].setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				resetTabBtn();
				mMyBottemMyImg.setImageResource(R.drawable.pic_check_my);
				mMyBottemMyTxt.setTextColor(Color.parseColor("#579908"));
				viewPager.setCurrentItem(3);
			}
		});
	}
    /*
	 * 重置图片按钮颜色
	 */
	protected void resetTabBtn() {
		mMyBottemMainImg.setImageResource(R.drawable.pic_main);
		mMyBottemFoundImg.setImageResource(R.drawable.pic_found);
		mMyBottemMyImg.setImageResource(R.drawable.pic_my);
		mMyBottemRankingImg.setImageResource(R.drawable.pic_ranking);
		
		mMyBottemMainTxt.setTextColor(Color.rgb(56,56,56));
		mMyBottemFoundTxt.setTextColor(Color.rgb(56,56,56));
		mMyBottemMyTxt.setTextColor(Color.rgb(56,56,56));
		mMyBottemRankingTxt.setTextColor(Color.rgb(56,56,56));
	}
    
	/**
     * 初始化底部菜单事件
     */
    public void initLayout(){
//    	HomeFragment home = new HomeFragment();
    	FoundFragment found = new FoundFragment();
    	RankingFragment ranking = new RankingFragment();
    	MyFragment my = new MyFragment();
    	HomeMainFragment hm = new HomeMainFragment();
    	
    	mFragments.add(hm);
//    	mFragments.add(home);
    	mFragments.add(found);
    	mFragments.add(ranking);
    	mFragments.add(my);
    }
    /**
     * 初始化控件
     */
    private void initView() {
		mMyBottemMainImg = (ImageView)findViewById(R.id.MyBottemMainImg);
		mMyBottemFoundImg = (ImageView)findViewById(R.id.MyBottemFoundImg);
		mMyBottemRankingImg = (ImageView)findViewById(R.id.MyBottemRankingImg);
		mMyBottemMyImg = (ImageView)findViewById(R.id.MyBottemMyImg);
		
		mMyBottemMainTxt = (TextView)findViewById(R.id.MyBottemMainTxt);
		mMyBottemFoundTxt = (TextView)findViewById(R.id.MyBottemFoundTxt);
		mMyBottemRankingTxt = (TextView)findViewById(R.id.MyBottemRankingTxt);
		mMyBottemMyTxt = (TextView)findViewById(R.id.MyBottemMyTxt);
		viewPager = (ViewPager)findViewById(R.id.id_viewpager);
    }
    /**
     * 获取底部菜单事件
     * @return
     */
    private LinearLayout[] getMenuButtonObject() {
		LinearLayout[] lv_bottonMenu = new LinearLayout[4];

		lv_bottonMenu[0] = (LinearLayout) findViewById(R.id.MyBottemMainBtn);
		lv_bottonMenu[1] = (LinearLayout) findViewById(R.id.MyBottemFoundBtn);
		lv_bottonMenu[2] = (LinearLayout) findViewById(R.id.MyBottemRankingBtn);
		lv_bottonMenu[3] = (LinearLayout) findViewById(R.id.MyBottemMyBtn);
		return lv_bottonMenu;
	}
}
