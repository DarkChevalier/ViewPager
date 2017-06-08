package com.video.videoproject;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.video.videoproject.ImgScroll.MyImgScroll;
import com.video.videoproject.adapter.VideoAdapter;
import com.video.videoproject.xListView.HorizontalListView;

public class HomeFragment extends Fragment {
	private View view;
	private ImageView iv_img1,iv_img2,iv_img3;
	private RadioButton rb_young,rb_forward,rb_guard,rb_midfield;
	private MyImgScroll myPager; // 图片容器
	private LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组
	
	private Display display;
	private int itemWidth;		
	private VideoAdapter videoAdapter_hot,videoAdapter_new;
	private HorizontalListView horizonList;
	
	private GridView gridView_newVideo,gridView_hotVideo;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(listViews != null){
			listViews.clear();
			listViews = null;
		}
		listViews = new ArrayList<View>();
		int[] imageResId = new int[] { R.drawable.pic_01, R.drawable.pic_02, R.drawable.pic_03};
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(activity);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {// 设置图片点击事件
					Toast.makeText(getActivity(),
							"点击的:" + myPager.getCurIndex(), Toast.LENGTH_SHORT)
							.show();
				}
			});
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_home, container,false);
		initLayout();
		
		//开始滚动
		myPager = (MyImgScroll)view.findViewById(R.id.myvp);
		myPager.start(getActivity(), listViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_focused, R.drawable.dot_normal);
		
		display = getActivity().getWindowManager().getDefaultDisplay();
		itemWidth = display.getWidth() / 2;// 计算宽度
		
		return view;
	}
	/**
	 * 初始化控件
	 */
	public void initLayout(){
		rb_young = (RadioButton)view.findViewById(R.id.rb_youngfootable);
		rb_young.setButtonDrawable(getResources().getDrawable(android.R.color.transparent)) ;
		rb_forward = (RadioButton)view.findViewById(R.id.rb_forward);	//前锋
		rb_forward.setButtonDrawable(getResources().getDrawable(android.R.color.transparent)) ;
		rb_guard = (RadioButton)view.findViewById(R.id.rb_guard);		//后卫
		rb_guard.setButtonDrawable(getResources().getDrawable(android.R.color.transparent)) ;
		rb_midfield = (RadioButton)view.findViewById(R.id.rb_midfield);	//中场
		rb_midfield.setButtonDrawable(getResources().getDrawable(android.R.color.transparent)) ;
		
		iv_img1 = (ImageView)view.findViewById(R.id.iv_center1);
		iv_img2 = (ImageView)view.findViewById(R.id.iv_center2);
		iv_img3 = (ImageView)view.findViewById(R.id.iv_center3);
		
		
		
		String[] titles = {"2015年第一季比赛", "2015第二季比赛", "2015第三季比赛", "2015第四季比赛"};  
		String[] hot = {"最热视频一季比赛", "最热视频二季比赛"}; 
        int[] ids = {R.drawable.pic_video01,R.drawable.pic_video02};  
//		horizonList = (HorizontalListView)view.findViewById(R.id.horizon_listview);
		videoAdapter_new = new VideoAdapter(getActivity().getApplicationContext() , titles , ids);
		videoAdapter_hot = new VideoAdapter(getActivity().getApplicationContext(), hot, ids);
//		horizonList.setAdapter(videoAdapter);
        
        gridView_hotVideo = (GridView)view.findViewById(R.id.gridview_hotvideo);
        gridView_hotVideo.setAdapter(videoAdapter_hot);
		
		gridView_newVideo = (GridView)view.findViewById(R.id.gridview_newvideo);
        gridView_newVideo.setAdapter(videoAdapter_new);
		
		rb_young.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				resetColor();
				rb_young.setBackgroundResource(R.drawable.check_text_bg);
				iv_img1.setVisibility(View.GONE);
				iv_img2.setVisibility(View.VISIBLE);
				iv_img3.setVisibility(View.VISIBLE);
			}
		});
		rb_forward.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				resetColor();
				rb_forward.setBackgroundResource(R.drawable.check_text_bg);
				iv_img3.setVisibility(View.GONE);
				iv_img2.setVisibility(View.GONE);
				iv_img1.setVisibility(View.VISIBLE);
			}
		});
		rb_guard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				resetColor();
				rb_guard.setBackgroundResource(R.drawable.check_text_bg);
				iv_img1.setVisibility(View.VISIBLE);
				iv_img2.setVisibility(View.VISIBLE);
				iv_img3.setVisibility(View.GONE);
			}
		});
		rb_midfield.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				resetColor();
				rb_midfield.setBackgroundResource(R.drawable.check_text_bg);
				iv_img1.setVisibility(View.GONE);
				iv_img2.setVisibility(View.GONE);
				iv_img3.setVisibility(View.VISIBLE);
			}
		});
	}
	public void resetColor(){
		rb_young.setBackgroundResource(R.drawable.nocheck_text_bg);
		rb_guard.setBackgroundResource(R.drawable.nocheck_text_bg);
		rb_forward.setBackgroundResource(R.drawable.nocheck_text_bg);
		rb_midfield.setBackgroundResource(R.drawable.nocheck_text_bg);
	}
}
