package com.video.videoproject;

import java.util.ArrayList;
import java.util.List;
import com.video.videoproject.ImgScroll.MyImgScroll;
import com.video.videoproject.adapter.VideoAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class FootableFragment extends BaseFragment{
	private View view;
	
	private MyImgScroll myPager; // 图片容器
	private LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组
	
	private VideoAdapter videoAdapter_hot,videoAdapter_new;
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
		view = inflater.inflate(R.layout.fragment_footable, container,false);
		
		//开始滚动
		myPager = (MyImgScroll)view.findViewById(R.id.myvp);
		myPager.start(getActivity(), listViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_focused, R.drawable.dot_normal);
		
		
		String[] titles = {"2015年第一季比赛", "2015第二季比赛", "2015第三季比赛", "2015第四季比赛"};  
		String[] hot = {"最热视频一季比赛", "最热视频二季比赛"}; 
        int[] ids = {R.drawable.pic_video01,R.drawable.pic_video02};  
		videoAdapter_new = new VideoAdapter(getActivity().getApplicationContext() , titles , ids);
		videoAdapter_hot = new VideoAdapter(getActivity().getApplicationContext(), hot, ids);
        
        gridView_hotVideo = (GridView)view.findViewById(R.id.gv_hotvideo);
        gridView_hotVideo.setAdapter(videoAdapter_hot);
		
		gridView_newVideo = (GridView)view.findViewById(R.id.gv_newvideo);
        gridView_newVideo.setAdapter(videoAdapter_new);
		return view;		
	}

	@Override
	protected void lazyLoad(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}	
}
