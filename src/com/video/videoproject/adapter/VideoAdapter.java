package com.video.videoproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.video.videoproject.R;

public class VideoAdapter extends BaseAdapter {
	private Context cxt;
	private int[] img;
	private String[] title;
	
	public VideoAdapter(Context context, String[] title, int[] img){
		this.cxt = context;
		this.title = title;
		this.img = img;
	}
	@Override
	public int getCount() {
		return img.length;
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder hold;
		
		if (convertView == null) {
			hold = new ViewHolder();
			convertView = View.inflate(cxt, R.layout.activity_video_item, null);
//			hold.mImage = (ImageView) convertView.findViewById(R.id.video_pic);
			hold.mTitle = (TextView) convertView.findViewById(R.id.tv_video_title);
			convertView.setTag(hold);
		}else{
			hold = (ViewHolder) convertView.getTag();
		}
//		hold.mImage.setTag(img[position]);
		hold.mTitle.setText(title[position]);
		return convertView;
	}
	private static class ViewHolder{
		private ImageView mImage;
		private TextView mTitle;
	}
}
