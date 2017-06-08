package com.video.videoproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends  BaseFragment implements OnClickListener{
	private View view;
	private TextView tv_toLand;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_my, container, false);

		initLayout();

		return view;
	}
	
	/**
	 * 获取跳转到登录界面
	 */
	private void toGetMessage(){
		Intent intent = new Intent(getActivity().getApplicationContext(),
				LoginActivity.class);
		startActivity(intent);
//		SMSSDK.initSDK(getActivity().getApplicationContext(), "9da93e9f5e66",
//				"ca53c560cd104f11cd2f804f5168eefd");

		// 打开注册页面
//		RegisterPage registerPage = new RegisterPage();
//
//		registerPage.setRegisterCallback(new EventHandler() {
//			
//			public void afterEvent(int event, int result, Object data) {
//				// 解析注册结果
//				if (result == SMSSDK.RESULT_COMPLETE) {
//					@SuppressWarnings("unchecked")
//					HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
//					String country = (String) phoneMap.get("country");
//					String phone = (String) phoneMap.get("phone");
//
//					// 提交用户信息
//					registerUser(country, phone);		//往数据库里新增数据
//				}
//			}
//		});
//		registerPage.show(getActivity().getApplicationContext());
	}
	/**
	 * 注册用户
	 * @param country
	 * @param phone
	 */
	private void registerUser(String country, String phone) {
		
	}

	/**
	 * 初始化控件
	 */
	private void initLayout() {
		tv_toLand = (TextView) view.findViewById(R.id.tv_to_land);
		tv_toLand.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_to_land:
			toGetMessage();
			break;

		default:
			break;
		}
	}

	@Override
	protected void lazyLoad(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}
}
